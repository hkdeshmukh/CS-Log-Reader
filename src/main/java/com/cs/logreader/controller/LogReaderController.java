package com.cs.logreader.controller;

import com.cs.logreader.ServerLogService;
import com.cs.logreader.model.ServerLogEntity;
import com.cs.logreader.repo.ServerLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("logs")
@AllArgsConstructor
@Slf4j
public class LogReaderController {

    private final ServerLogRepository repository;
    private final ServerLogService service;

    @GetMapping("/get")
    private List<ServerLogEntity> getAllEvents(){
        log.debug("getAllEvents controller method invoked by client.");
        return repository.findAll();
    }

    @GetMapping("/get/{eventId}")
    private List<ServerLogEntity> getEventById(@PathVariable("eventId") String eventId){
        log.debug("getEventById controller method invoked by client with event id {}.",eventId);
        return repository.findByEventId(eventId);
    }

    @GetMapping("/getFailedEvents")
    private List<ServerLogEntity> getFailedEvents(){
        log.debug("getFailedEvents controller method invoked by client.");
        return repository.findByAlertFlag();
    }

    @GetMapping("/loadDefaultFile")
    private String loadDefaultFile() throws Exception{
        log.debug("loadDefaultFile controller method invoked by client.");
        String fileName = "log.json";
        service.loadDataFromJsonFile(fileName);
        return "Loaded server file = "+fileName;
    }

    @PostMapping("/loadCustomData")
    private String loadCustomData(List<ServerLogEntity> customData){
        repository.saveAll(customData);
        log.debug("loadCustomData controller method invoked by client with data {}.",customData);
        return "Loaded custom data.";
    }

}
