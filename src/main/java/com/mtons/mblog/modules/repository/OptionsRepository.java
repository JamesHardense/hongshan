package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OptionsRepository extends JpaRepository<Options, Long>, JpaSpecificationExecutor<Options> {
	Options findByKey(String key);
}
