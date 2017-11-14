package com.assignment.wall.service.impl;

import com.assignment.wall.app.WallController;
import com.assignment.wall.model.User;
import com.assignment.wall.service.UserIdAlreadyExistsException;
import com.assignment.wall.service.UserIdNotFoundException;
import com.assignment.wall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.wall.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(User user) throws UserIdAlreadyExistsException {
        User tmp=userRepository.findOne(user.getUserId());
        if(tmp!=null)
            throw new UserIdAlreadyExistsException(user.getUserId()+" is already exists.");
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if(!userRepository.exists(user.getUserId()))
            throw new UserIdNotFoundException(user.getUserId() +"is not found.");

        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(String id){
        userRepository.delete(id);
    }

    @Override
    public User find(String id){
        return userRepository.findOne(id);
    }

    public List<User> getRecommended10Users(User loginUser, WallController wallController){
        WallController.log.info("loginuser is: " + loginUser.toString());

        List<User> alluser= findAll();
        List<User> following=loginUser.getFollowing();
        List<User> recommended10Users=alluser.stream()
                                    .limit(10)
                                    .filter(u->!(following.contains(u) || u.equals(loginUser) ))
                                    .collect(Collectors.toList());
        return recommended10Users;
    }

}
