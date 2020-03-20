package com.elearning.dao.notificationPush;

import com.elearning.pojo.notificationPush.NotificationReminderBlacklistUser;

public interface NotificationReminderBlacklistUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(NotificationReminderBlacklistUser record);

    int insertSelective(NotificationReminderBlacklistUser record);

    NotificationReminderBlacklistUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(NotificationReminderBlacklistUser record);

    int updateByPrimaryKey(NotificationReminderBlacklistUser record);
}