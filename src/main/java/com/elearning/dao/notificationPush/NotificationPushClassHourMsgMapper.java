package com.elearning.dao.notificationPush;

import com.elearning.pojo.notificationPush.NotificationPushClassHourMsg;

public interface NotificationPushClassHourMsgMapper {
    int deleteByPrimaryKey(Integer tenantId);

    int insert(NotificationPushClassHourMsg record);

    int insertSelective(NotificationPushClassHourMsg record);

    NotificationPushClassHourMsg selectByPrimaryKey(Integer tenantId);

    int updateByPrimaryKeySelective(NotificationPushClassHourMsg record);

    int updateByPrimaryKey(NotificationPushClassHourMsg record);
}