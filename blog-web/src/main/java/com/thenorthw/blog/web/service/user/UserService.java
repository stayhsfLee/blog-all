package com.thenorthw.blog.web.service.user;

import com.thenorthw.blog.common.model.user.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by theNorthW on 03/05/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
public interface UserService {

    public User isUserHasRegisterWeChat(String openId);

    public User userLogin(String name, String password, ServletResponse response);

    public int createNewUser(User user);

    public User getUserProfileByUserId(Long userId);

    public int updateUserInfo(User user);

    public int updateUserAvatar(byte[] avatarBytes, Long userId);

    List<User> getUserProfileByUserIds(@RequestParam("userIds") String[] userIds);
}
