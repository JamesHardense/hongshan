package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {
    @Query(value = "SELECT * FROM mto_post_history", nativeQuery = true)
    List<Log> findAllLog();

    @Query(value = "SELECT * FROM mto_post_history WHERE id =:id", nativeQuery = true)
    Log findById(@Param("id")long id);

    @Query(value = "SELECT * FROM mto_post_history AS b \n" +" WHERE b.hid in\n" +
        " (SELECT MAX(a.hid) FROM mto_post_history as a GROUP BY a.id)", nativeQuery = true)
    List<Log> findLatestLog();

    @Query(value = "SELECT * FROM mto_post_history AS b  WHERE b.hid IN (SELECT MAX(a.hid) FROM mto_post_history as a GROUP BY a.id) AND b.channel_id =:channel_id",nativeQuery = true)
    List<Log> findLatestChannel(@Param("channel_id") long channel_id);

    @Query(value = "SELECT * FROM mto_post_history AS b  WHERE b.hid IN (SELECT MAX(a.hid) FROM mto_post_history as a GROUP BY a.id) AND b.title =:title",nativeQuery = true)
    List<Log> findLatestTitle(@Param("title") String title);



}
