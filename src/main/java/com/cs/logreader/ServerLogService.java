package com.cs.logreader;

import com.cs.logreader.model.STATE_TYPE;
import com.cs.logreader.model.ServerLogEntity;
import com.cs.logreader.repo.ServerLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
@Slf4j
public class ServerLogService {

    private final ServerLogRepository repository;

    public void loadDataFromJsonFile(String fileName) throws Exception {
        log.debug("Loading default data file.");
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
        Stream<String> lines = Files.lines(path);
        // convert JSON file to entities
        List<ServerLogEntity> entities = lines.map(line -> convert(line, ServerLogEntity.class))
                .sorted(Comparator.comparing(ServerLogEntity::getId)).collect(Collectors.toList());
        log.debug("Number of data data items in file {}.",entities.size());
        setTheAlertFlagsForDelayedEvents(entities);
        repository.saveAll(entities);
    }

    static <T> T convert(String string, Class<T> pojo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(string, pojo);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void setTheAlertFlagsForDelayedEvents(List <ServerLogEntity> list) {
        long timeDifference = 0;
        for (int i = 0; i < list.size(); i=i+2) {
                if (list.get(i).getId().equals(list.get(i+1).getId())) {
                        if (list.get(i).getState().equals(STATE_TYPE.STARTED) && list.get(i+1).getState().equals(STATE_TYPE.FINISHED)) {
                            timeDifference = list.get(i+1).getTimestamp() - list.get(i).getTimestamp();
                    }
                }
                if(list.get(i+1).getState().equals(STATE_TYPE.FINISHED)) {
                    list.get(i+1).setAlertFlag(timeDifference > 4 ? true : false);
                    list.get(i+1).setEventDuration((int) timeDifference);
                    timeDifference = 0;
                }
        }
    }

}
