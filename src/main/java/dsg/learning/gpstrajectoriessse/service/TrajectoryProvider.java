package dsg.learning.gpstrajectoriessse.service;

import dsg.learning.gpstrajectoriessse.domain.Trajectory;
import reactor.core.publisher.Flux;

public interface TrajectoryProvider {
    Flux<Trajectory> getTrajectory(String userId);
}
