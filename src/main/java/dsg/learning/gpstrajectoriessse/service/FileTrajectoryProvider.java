package dsg.learning.gpstrajectoriessse.service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import dsg.learning.gpstrajectoriessse.domain.Trajectory;
import reactor.core.publisher.Flux;

@Service
public class FileTrajectoryProvider implements TrajectoryProvider {

    @Override
    /**
     * This method should be improved so to read csv line by line. Line 30 is reading the CSV all at once
     * and converting to Trajectory and if one line fails, then no line is returned.
     */
    public Flux<Trajectory> getTrajectory(String userId) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] csv = resolver.getResources("classpath*:trajectories/" + userId + "_*.csv");
            File file = csv[0].getFile();
            MappingIterator<Trajectory> personIter = new CsvMapper().readerWithTypedSchemaFor(Trajectory.class)
            .readValues(file);
            return Flux.fromIterable(personIter.readAll());
        } catch (IOException e) {
            return Flux.error(e);
        }
    }

}
