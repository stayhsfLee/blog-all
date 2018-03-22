package com.thenorthw.blog.common.dao.notification;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.thenorthw.blog.common.model.notification.Notification;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 12/10/2017.
 * blog: theNorthW.net
 */
@Repository
public interface NotificationDao {
	public List<Notification> getNotifications(@Param("limit") int limit);

	public List<Notification> getAllNotifications();

	public int deleteNotification(Long id);

	public int addNotification(@Param("creator") Long creator, @Param("name") String name, @Param("content") String content, @Param("grammar") Integer grammar);
}
