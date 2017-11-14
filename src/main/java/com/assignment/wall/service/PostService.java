package com.assignment.wall.service;

import com.assignment.wall.model.Post;
import com.assignment.wall.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.assignment.wall.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class PostService {
    @Autowired
    PostRepository postRepository;

    /**
     * Find all posts
     * @return
     */
    public List<Post> findAllDesc(){
        List<Post> res = postRepository.findAllByOrderByPostTimeDesc();
        if(res==null){
            res=new ArrayList<>();
        }
        return res;
    }

    /**
     * Get the logedin user timeline
     * @param loginuser
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<Post> getTimeLineforLoginUser(User loginuser, int pageStart, int pageSize){
        List<User> following=loginuser.getFollowing();
        //following.add(loginuser);
        List<String> ids=following.stream().map(User::getUserId).collect(Collectors.toList());
        ids.add(loginuser.getUserId());
        //return postRepository.findTop100ByPostUser_UserIdInOrderByPostTimeDesc(ids);
        Pageable pageable = new PageRequest(pageStart,pageSize);
        return postRepository.findByPostUser_UserIdInOrderByPostTimeDesc(ids, pageable);
    }

    /**
     * Save the post
     * @param post
     * @return
     */
    public Post save(Post post){
        return postRepository.save(post);
    }

    /**
     * Delete post based on postId
     * @param id
     */
    public void delete(Integer id){
        postRepository.delete(id);
    }

    /**
     * Find post based on postId
     * @param id
     * @return
     */
    public Post find(Integer id){
        return postRepository.findOne(id);
    }
}
