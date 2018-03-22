package com.thenorthw.blog.web.service.account;

import com.thenorthw.blog.common.model.account.Account;
import com.thenorthw.blog.face.form.user.UserRegisterForm;

/**
 * Created by theNorthW on 03/05/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
public interface AccountService {
    public boolean isAccountExist(Account account);

    /**
     * 该方法用于初始为用户创立account和user_profile, 所有相关操作都集成在这一个方法内,外者只需调用即可
     * @param form 创建账户所需参数
     * @return 是否创立成功
     */
    public int createNormalUserAccount(UserRegisterForm form);

    /**
     *
     * @param account
     * @return
     */
    public int deleteExistedAccount(Account account);

    public int changePassword(Long userId, String oldpassword, String newpassword);

    public Account getAccountByName(String name);

    public int updateLoginTime(Account account);

    public Account getAccountByWeChatId(String openId);

}
