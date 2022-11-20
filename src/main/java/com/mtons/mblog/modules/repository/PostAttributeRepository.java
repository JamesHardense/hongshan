package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.PostAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostAttributeRepository extends JpaRepository<PostAttribute, Long>, JpaSpecificationExecutor<PostAttribute> {
    @Query(value = "SELECT * FROM mto_post_attribute AS a LEFT JOIN mto_post AS b ON a.id=b.id WHERE b.status=1", nativeQuery = true)
    List<PostAttribute> checkSummary();
}
