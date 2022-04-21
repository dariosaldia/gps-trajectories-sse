package dsg.learning.gpstrajectoriessse.service;

import org.junit.jupiter.api.Test;

import dsg.learning.gpstrajectoriessse.domain.Trajectory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TestFileTrajectoryProvider {
    
    @Test
    public void validTrajectory() {
        FileTrajectoryProvider simpleTrajectoryProvider = new FileTrajectoryProvider();
        
        Flux<Trajectory> trajectoryToCount = simpleTrajectoryProvider.getTrajectory("010");
        StepVerifier.create(trajectoryToCount).expectNextCount(3).verifyComplete();

        Flux<Trajectory> trajectoryValuesToVerify = simpleTrajectoryProvider.getTrajectory("010");
        StepVerifier.create(trajectoryValuesToVerify)
            .expectNext(new Trajectory("39.991376","116.32641","354"))
            .expectNext(new Trajectory("39.991358","116.326438","361"))
            .expectNext(new Trajectory("39.991364","116.326458","371"))
            .verifyComplete();
    }

    @Test
    /**
     * This test works but it's not correct.
     */
    public void trajectoryNotValid() {
        FileTrajectoryProvider simpleTrajectoryProvider = new FileTrajectoryProvider();
        
        Flux<Trajectory> trajectoryToCount = simpleTrajectoryProvider.getTrajectory("180");
        StepVerifier.create(trajectoryToCount)
            //.expectNext(new Trajectory("39.991376","116.32641","354"))
            //.expectNext(new Trajectory("39.991358","116.326438","361"))
            .expectError()
            .verify();
    }
}
