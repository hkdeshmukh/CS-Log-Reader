package com.cs.logreader.repo;

import com.cs.logreader.model.ServerLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerLogRepository extends JpaRepository<ServerLogEntity, String> {

    @Query("select e from ServerLogEntity e where e.id =:id")
    List<ServerLogEntity> findByEventId(@Param("id") String eventId);

    @Query("select e from ServerLogEntity e where e.alertFlag = true")
    List<ServerLogEntity> findByAlertFlag();

}
