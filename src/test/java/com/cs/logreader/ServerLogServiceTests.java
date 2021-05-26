package com.cs.logreader;

import com.cs.logreader.model.STATE_TYPE;
import com.cs.logreader.model.ServerLogEntity;
import com.cs.logreader.repo.ServerLogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ServerLogServiceTests {

    @Mock
    ServerLogRepository mockRepository;

    @InjectMocks
    ServerLogService testee;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenLoadDataFromJsonFile() throws Exception {
        //When
        testee.loadDataFromJsonFile("log.json");

        //Then
        //Mockito.verify(mockRepository, Mockito.times(1)).saveAll(any());

    }
}
