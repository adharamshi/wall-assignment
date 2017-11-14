package com.assignment.wall.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.wall.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer>{
    List<Post> findAllByOrderByPostTimeDesc();
    List<Post> findTop100ByPostUser_UserIdInOrderByPostTimeDesc(List<String> postUserId);
    List<Post> findByPostUser_UserIdInOrderByPostTimeDesc(List<String> postUserIds, Pageable pageable);
}
