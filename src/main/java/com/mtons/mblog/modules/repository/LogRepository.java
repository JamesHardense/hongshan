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
}
