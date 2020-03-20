package com.elearning.dao.notificationPush;

import com.elearning.pojo.notificationPush.NotificationReminderMessageDept;

public interface NotificationReminderMessageDeptMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(NotificationReminderMessageDept record);

    int insertSelective(NotificationReminderMessageDept record);

    NotificationReminderMessageDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(NotificationReminderMessageDept record);

    int updateByPrimaryKey(NotificationReminderMessageDept record);
}