package com.thenorthw.blog.web.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thenorthw.blog.common.dao.account.AccountDao;
import com.thenorthw.blog.common.enums.RoleType;
import com.thenorthw.blog.common.model.account.Account;
import com.thenorthw.blog.common.model.user.User;
import com.thenorthw.blog.common.utils.ShortUUIDUtil;
import com.thenorthw.blog.face.form.user.UserRegisterForm;
import com.thenorthw.blog.web.service.account.AccountService;
import com.thenorthw.blog.web.service.user.UserService;

import java.util.Date;

/**
 * Created by theNorthW on 03/05/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
@SuppressWarnings("ALL")
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserService userService;
    @Autowired
    AccountDao accountDao;

    public boolean isAccountExist(Account account) {
        return getAccountByName(account.getName()) != null;
    }


    /**
     * account账户和user信息表是分开的，之间是通过account_id来连接的
     * @param userRegisterForm
     * @return -1 创建不成功 -2 重复account 0 成功
     */
    public int createNormalUserAccount(UserRegisterForm userRegisterForm) {
        //首先进行判断是否已经有相同loginname的account
        Account account = new Account(null,userRegisterForm.getLoginname(),userRegisterForm.getPassword());

        if(isAccountExist(account)){
            return -2;
        }

        //普通用户
        account.setRoleId(RoleType.NORMAL_USER.getRoleId());
        Date d = new Date();
        account.setGmtModified(d);
        account.setGmtCreate(d);
        account.setLastLoginTime(d);
        account.setStatus(1);
        //此处需要创建account
        if(accountDao.insertNewAccount(account) != 1){
            return -1;
        }

        //创建好账户后,开始对user_profiler进行增加记录
        //不需要用户来填写信息,用户只需提供信息即可,后续信息更改由用户自行实施
        User user = new User();

        user.setId(account.getId());
        user.setNick(ShortUUIDUtil.randomUUID().substring(0,10));
        user.setSex(3);
        user.setIntroduction("This is the introduction");
        user.setAvatar("/user/avatar/default");
        user.setBackgroundImg("/user/bkimg/default");
        Date date = new Date(System.currentTimeMillis());
        user.setGmtCreate(date);
        user.setGmtModified(date);

        int result1 = userService.createNewUser(user);

        //如果创建user_profile失败,则需要撤销对account的创建
        if(result1 != 1){
            deleteExistedAccount(account);
            return -1;
        }
        return 0;
    }

    public int deleteExistedAccount(Account account) {
        int result = accountDao.deleteExistAccount(account.getName());
        return result;
    }

    public int changePassword(Long userId, String oldpassword,String newpassword) {
        //根据account_id找到account
        Account account = accountDao.getAccountByAccountId(userId);

        //首先判断旧密码是否符合
        if(!account.getPassword().equals(oldpassword)){
            return -1;
        }

        //开始进行更新
        int result= accountDao.updatePassword(userId,newpassword);

        return result;
    }

    public Account getAccountByName(String name) {
        return accountDao.getAccountByLoginname(name);
    }

    public int updateLoginTime(Account account) {
        return accountDao.updateLoginTime(account);
    }

    public Account getAccountByWeChatId(String open_id) {
        return accountDao.getAccountByWeChatId(open_id);
    }
}
