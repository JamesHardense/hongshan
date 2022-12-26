/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2022, hongshan. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Channel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer>, JpaSpecificationExecutor<Channel> {
	List<Channel> findAllByStatus(int status, Sort sort);

	@Query("select coalesce(max(weight), 0) from Channel")
	int maxWeight();

	@Query(value = "SELECT * FROM mto_channel WHERE STATUS=0  ORDER BY id asc LIMIT 0,4", nativeQuery = true)
	List<Channel> findChannels1();

	@Query(value = "SELECT * FROM mto_channel WHERE STATUS=0", nativeQuery = true)
	List<Channel> findChannelsall();
}
