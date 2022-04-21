package dsg.learning.gpstrajectoriessse.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dsg.learning.gpstrajectoriessse.domain.Trajectory;
import dsg.learning.gpstrajectoriessse.service.TrajectoryProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/trajectories")
@Slf4j
@AllArgsConstructor
public class TrajectoriesController {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private TrajectoryProvider trajectoryProvider;
    
    @GetMapping("/{userId}")
    public SseEmitter getTrajectory(@PathVariable String userId) {
        log.info("Getting trajectory for userid {}", userId);
        
        SseEmitter sseEmitter = new SseEmitter();
        
        executor.execute(() -> {

            Flux<Trajectory> trajectory = trajectoryProvider.getTrajectory(userId);
            trajectory.delayElements(Duration.ofSeconds(1))
                .doOnNext(currentEventLocation -> sendEvent(sseEmitter, currentEventLocation))
                .subscribe();

        });
        
        return sseEmitter;
    }

    private void sendEvent(SseEmitter sseEmitter, Trajectory currentEventLocation) {
        try {
            sseEmitter.send(currentEventLocation);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }

}
