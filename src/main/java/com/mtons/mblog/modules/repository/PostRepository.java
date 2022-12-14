
package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    /**
     * 查询指定用户
     *
     * @param pageable
     * @param authorId
     * @return
     */
    Page<Post> findAllByAuthorId(Pageable pageable, long authorId);

    @Query("select coalesce(max(weight), 0) from Post")
    int maxWeight();

    @Query(value = "SELECT * FROM mto_post WHERE title =:title ", nativeQuery = true)
    Post findPostByTitle(@Param("title")String title);

    @Modifying
    @Query("update Post set views = views + :increment where id = :id")
    void updateViews(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set favors = favors + :increment where id = :id")
    void updateFavors(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set comments = comments + :increment where id = :id")
    void updateComments(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set status = :status  where id = :id")
    void updateStatus(@Param("id") long id, @Param("status") int status);

    @Modifying
    @Query("update Post set summary = :summary  where id = :id")
    void updateSummary(@Param("id") long id, @Param("summary") String summary);


//    @Query(value = "SELECT * FROM mto_post WHERE title like ", nativeQuery = true)
//    List<Post> findPostsByTitle(@Param("title")String title);
}
