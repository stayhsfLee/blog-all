package com.thenorthw.blog.web.service.notification;

import com.thenorthw.blog.common.model.notification.Notification;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 12/10/2017.
 * blog: theNorthW.net
 */
public interface NotificationService {
	/**
	 * 通过用户自己来进行设置自己实现多少条最近通知展示
	 * @return
	 */
	public List<Notification> getRecentNotifications(int limit);

	/**
	 * 用来给管理员用的
	 * @return
	 */
	public List<Notification> getAllNotifications();

	public int deleteNotification(Long id);

	public int addNotification(Long creator, String name, String content, int grammar);
}
