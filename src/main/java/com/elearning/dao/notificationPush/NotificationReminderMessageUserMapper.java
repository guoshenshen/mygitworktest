package com.elearning.dao.notificationPush;

import com.elearning.pojo.notificationPush.NotificationReminderMessageUser;

public interface NotificationReminderMessageUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(NotificationReminderMessageUser record);

    int insertSelective(NotificationReminderMessageUser record);

    NotificationReminderMessageUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(NotificationReminderMessageUser record);

    int updateByPrimaryKey(NotificationReminderMessageUser record);
}