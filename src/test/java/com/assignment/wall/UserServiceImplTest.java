package com.assignment.wall;

import com.assignment.wall.model.User;
import com.assignment.wall.repository.UserRepository;
import com.assignment.wall.service.UserService;
import com.assignment.wall.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by adharamshi on 11/13/17.
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    /**
     * To check the Service class, we need to have an instance of Service class
     * created and available as a @Bean so that we can @Autowire it in our
     * test class.
     */

    @TestConfiguration //annotation to indicate that they should not be picked up by scanning.
    public static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

        @Autowired
        private UserService userService;

        @MockBean
        private UserRepository userRepository;

        @Before
        public void setUp() {
            User usr = new User("Jon", "jon");

            Mockito.when(userRepository.findOne(usr.getUserId()))
                    .thenReturn(usr);
        }

        @Test
        public void whenValidUserId_ThenUserShouldBeFound() {
            String usrId = "Jon";
            User found = userService.find(usrId);
            //System.out.println(found);

            assertThat(found.getUserId())
                    .isEqualTo(usrId);
        }



}
