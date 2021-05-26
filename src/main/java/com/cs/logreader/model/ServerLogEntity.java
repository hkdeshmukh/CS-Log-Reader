package com.cs.logreader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "SERVER_LOGS")

public class ServerLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uuid;

    private String id;
    private STATE_TYPE state;
    private String type;
    private boolean alertFlag;

    private Long timestamp;
    private Integer eventDuration;

    private String host;


}
