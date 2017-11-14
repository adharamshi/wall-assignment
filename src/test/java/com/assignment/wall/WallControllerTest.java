package com.assignment.wall;

/**
 * Created by adharamshi on 11/13/17.
 */

import com.assignment.wall.app.WallController;
import com.assignment.wall.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(WallController.class) //@WebMvcTest. It will auto-configure the Spring MVC infrastructure for our unit tests.
public class WallControllerTest {
    //@WebMvcTest also auto-configures MockMvc which offers a powerful way of easy testing
    // MVC controllers without starting a full HTTP server.
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void givenUser_whenGetTimeline_thenStatus200(){

    }


}
