package com.cs.logreader;

import com.cs.logreader.model.STATE_TYPE;
import com.cs.logreader.model.ServerLogEntity;
import com.cs.logreader.repo.ServerLogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LogReaderRepositoryTest {

    @Mock
    ServerLogRepository testee;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindByEventId() {
        List<ServerLogEntity> response = List.of(ServerLogEntity.builder().state(STATE_TYPE.STARTED).id("TEST1").eventDuration(7).alertFlag(Boolean.TRUE).build(),
               ServerLogEntity.builder().state(STATE_TYPE.STARTED).id("TEST1").eventDuration(7).alertFlag(Boolean.FALSE).build());
        Mockito.lenient().when(testee.findByEventId("TEST1")).thenReturn(response);

        Assertions.assertEquals(2, response.size());

    }

    @Test
    public void whenFindByAlertFlag() {
        List<ServerLogEntity> response = List.of(ServerLogEntity.builder().state(STATE_TYPE.STARTED).id("TEST1").eventDuration(7).alertFlag(Boolean.TRUE).build(),
                ServerLogEntity.builder().state(STATE_TYPE.STARTED).id("TEST1").eventDuration(7).alertFlag(Boolean.TRUE).build());
        Mockito.lenient().when(testee.findByAlertFlag()).thenReturn(response);

        Assertions.assertEquals(2, response.size());

    }

}
