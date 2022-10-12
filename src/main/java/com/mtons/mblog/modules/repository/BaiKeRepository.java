package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.BaiKe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaiKeRepository extends JpaRepository<BaiKe, Integer>, JpaSpecificationExecutor<BaiKe> {
    @Query(value = "SELECT * FROM Baike WHERE title =:title ", nativeQuery = true)
    BaiKe findByTitle(@Param("title")String title);
}
