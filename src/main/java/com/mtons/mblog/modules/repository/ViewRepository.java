package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Post;
import com.mtons.mblog.modules.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewRepository extends JpaRepository<View, String>, JpaSpecificationExecutor<View> {
    @Query(value = "SELECT * FROM(( mto_post_history AS b  LEFT JOIN mto_user AS  a  ON a.id=b.author_id)left JOIN mto_channel AS c on c.id=b.channel_id) WHERE b.hid IN (SELECT MAX(d.hid) FROM mto_post_history as d GROUP BY d.id)",nativeQuery = true)
    List<View> findAuthorChannel();
}