package com.thenorthw.blog.common.dao.account;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.thenorthw.blog.common.model.account.Account;

/**
 * Created by theNorthW on 03/05/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
@Repository
public interface AccountDao {
    public int insertNewAccount(Account account);

    public Account getAccountByLoginname(String loginname);

    public Account getAccountByAccountId(long account_id);

    public int updatePassword(@Param("account_id") long account_id, @Param("newpass") String newpass);

    public int deleteExistAccount(String loginname);

    public int updateLoginTime(Account account);

    public Account getAccountByWeChatId(String open_id);
}
