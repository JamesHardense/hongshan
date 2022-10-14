package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {
}
