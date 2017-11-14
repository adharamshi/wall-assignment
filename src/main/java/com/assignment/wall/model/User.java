package com.assignment.wall.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User{

    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/

    @Id
    @NotNull
    //@Column(unique = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

//    @NotNull
//    private String password;

    @NotNull
    private String screenName;

    @NotNull
    private RoleName roleName;

    private String iconPath;

    @OneToMany(mappedBy = "postUser")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "relation",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;

    public User(String userId, String screenName) {
        this.userId = userId;
        this.screenName = (screenName == null || screenName.equals("")) ?
                "no name" : screenName;
//        this.screenName = userId;
        this.roleName = RoleName.USER;
        this.iconPath = "/images/noicon.png";   //Util.getNoIcon();
    }

    public User() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }


    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

//        if (!getUserId().equals(user.getUserId())) return false;
//        return getPassword().equals(user.getPassword());
        return getUserId().equals(user.getUserId());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUserId().hashCode();
        return result;
    }
}
