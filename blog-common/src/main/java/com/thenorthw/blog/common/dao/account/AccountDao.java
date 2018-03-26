package com.thenorthw.blog.common.dao.account;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.thenorthw.blog.common.model.account.Account;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by theNorthW on 03/05/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
@Repository
public interface AccountDao {
    public int insertNewAccount(Account account);

    public Account getAccountByName(String name);

    public Account getAccountByAccountId(long account_id);

    public int updatePassword(@Param("account_id") long account_id, @Param("newpass") String newpass);

    public int deleteExistAccount(String name);

    public int updateLoginTime(Account account);

    public Account getAccountByWeChatId(String open_id);
}
