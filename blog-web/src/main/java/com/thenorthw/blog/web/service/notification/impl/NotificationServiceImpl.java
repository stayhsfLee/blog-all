package com.thenorthw.blog.web.service.notification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thenorthw.blog.common.dao.notification.NotificationDao;
import com.thenorthw.blog.common.model.notification.Notification;
import com.thenorthw.blog.web.service.notification.NotificationService;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 12/10/2017.
 * blog: theNorthW.net
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationDao notificationDao;

	public List<Notification> getRecentNotifications(int limit) {
		return notificationDao.getNotifications(10);
	}

	public List<Notification> getAllNotifications() {
		return notificationDao.getAllNotifications();
	}

	public int deleteNotification(Long id) {
		return notificationDao.deleteNotification(id);
	}

	public int addNotification(Long creator, String name, String content, int grammar) {
		return notificationDao.addNotification(creator,name,content,grammar);
	}
}
