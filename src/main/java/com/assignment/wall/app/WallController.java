package com.assignment.wall.app;


import com.assignment.wall.model.Post;
import com.assignment.wall.service.PostService;
import com.assignment.wall.service.UserService;
import com.assignment.wall.service.WallUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.assignment.wall.model.User;
import com.assignment.wall.util.Util;

import java.security.Principal;
import java.util.*;


@Controller
public class WallController {

    public static final Logger log = LoggerFactory.getLogger(WallController.class);

    private PostService postService;
    private UserService userService;
    private WallUserDetailsService userDetailsService;


    @Autowired
    public WallController(PostService postService,
                          UserService userService,
                          WallUserDetailsService userDetailsService) {
        this.postService = postService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/feed")
    String timeline(Principal principal, Model model,
                    @RequestParam(value = "pageStart", required = false, defaultValue = "0") int pageStart,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        model.addAttribute("postForm", new PostForm());    //attribute can be omitted.

        //default attribute name is Classname whose first letter is lower case.


        User loginUser = Util.getLoginuserFromPrincipal(principal);
        model.addAttribute("userinfo", loginUser);

        model.addAttribute("posts", postService.getTimeLineforLoginUser(loginUser, pageStart, pageSize));

        model.addAttribute("recommend", userService.getRecommended10Users(loginUser, this));


        return "timeline";
    }


    @PostMapping(value = "/feed")
    String post(Principal principal, @Validated PostForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Set<String> err = new HashSet<>();
            bindingResult.getAllErrors().forEach(e -> err.add(e.getDefaultMessage()));
            model.addAttribute("errors", err);
            return timeline(principal, model,0,100);
            //return "redirect:/";
        }
        Post post = new Post(form.getContent(),Util.getLoginuserFromPrincipal(principal));

        try {
            postService.save(post);
        } catch (Exception e) {
            Set<String> err = new HashSet<>();
            err.add("an error occured. try again.");
            model.addAttribute("errors", err);
            log.info(e.toString());
            e.printStackTrace();
            //return timeline(principal,model);
            return "redirect:/feed";
        }

        return "redirect:/feed";
    }

//    //register
//    @GetMapping(value = "/register")
//    String registerPage(Model model) {
//        model.addAttribute("registerForm", new RegisterForm());
//        return "register";
//    }
//
//    @PostMapping(value = "/register")
//    String register(@Validated RegisterForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            log.info("user:" + form.getUserId());
//            log.info("pass:" + form.getPassword());
//            log.info("scr:" + form.getScreenName());
//            Set<String> err = new HashSet<>();
//            bindingResult.getAllErrors().forEach(e -> err.add(e.getDefaultMessage()));
//            model.addAttribute("errors", err);
//            return "register";
//        }
//
//        log.info("user:" + form.getUserId());
//        log.info("pass:" + form.getPassword());
//        log.info("scr:" + form.getScreenName());
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        User user = new User(form.getUserId(), encoder.encode(form.getPassword()), form.getScreenName());
//        try {
//            userService.create(user);
//        } catch (UserIdAlreadyExistsException e) {
//            Set<String> errors = new HashSet<>();
//            errors.add(e.getMessage());
//            model.addAttribute("errors", errors);
//            return "register";
//        } catch (Exception e) {
//
//            Set<String> errors = new HashSet<>();
//            errors.add("unexpected error occured. try again.");
//            model.addAttribute("errors", errors);
//
//            log.info(e.toString());
//            return "register";
//        }
//        return "redirect:/loginForm";
//    }

//    @GetMapping("/update")
//    String updateUserDataPage(Model model) {
//        model.addAttribute("userForm", new UserForm());
//        //model.addAttribute("uploadForm",new UploadFileForm());
//        return "mypage";
//    }
//
//    @PostMapping("/update")
//    String updateUserData(Principal principal, @Validated UserForm form, BindingResult bindingResult,
//                          Model model) {
//        if (bindingResult.hasErrors()) {
//            Set<String> err = new HashSet<>();
//            bindingResult.getAllErrors().forEach(e -> err.add(e.getDefaultMessage()));
//            model.addAttribute("errors", err);
//            //return updateUserDataPage(model);
//            return "mypage"; //多分上と同義
//        }
//
//        try {
//            User newUser = userService.find(Util.getLoginuserFromPrincipal(principal).getUserId());
//            if (!Objects.equals(form.getScreenName(), ""))
//                newUser.setScreenName(form.getScreenName());
//            if (!Objects.equals(form.getBiography(), ""))
//                newUser.setBiography(form.getBiography());
//            userService.update(newUser);
//
//            Util.updateAuthenticate(principal, newUser);
//
//            model.addAttribute("userinfo", newUser);
//        } catch (UserIdNotFoundException e) {
//            Set<String> errors = new HashSet<>();
//            errors.add(e.getMessage());
//            model.addAttribute("errors", errors);
//            return "mypage";
//        } catch (Exception e) {
//            Set<String> errors = new HashSet<>();
//            errors.add("unexpected error occured. try again.");
//            model.addAttribute("errors", errors);
//            log.info(e.getMessage());
//            return "mypage";
//        }
//        return "redirect:/";
//    }

    @PostMapping(value = "/follow/{userid}")
    String follow(Principal principal, @PathVariable("userid") String userid, RedirectAttributes attributes){
        User loginUser=Util.getLoginuserFromPrincipal(principal);
        try {
            User target = userService.find(userid);
            loginUser.getFollowing().add(target);
            userService.update(loginUser);
            Util.updateAuthenticate(principal, loginUser);
        }catch (Exception e) {
            Set<String> errors = new HashSet<>();
            errors.add("unexpected error occured. try again.");
            log.info(e.toString());
            attributes.addFlashAttribute("errors", errors);
            log.info(e.getMessage());
        }
        return "redirect:/feed";
    }


    //------Util--------------------------------------

    @GetMapping("/debug")
    String debug() {
        List<User> users = userService.findAll();
        for (User u : users) {
            log.info(u.toString());
        }
        return "redirect:/";
    }

}
