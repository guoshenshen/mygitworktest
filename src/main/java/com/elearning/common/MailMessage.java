package com.elearning.common;

import java.io.Serializable;
public class MailMessage  implements Serializable{

    private static final long serialVersionUID = 1L;

    public String host;
    public String authenticate;

    public String title;
    public String content;
    public String fromEmail;
    public String[] toEmail;
    public Integer[] receiverIDs;
    public String adminUserName;
    public String adminPassword;
    public String attachment;//附件
    public Integer sendType; //0:直接发送；1：抄送；2：密送
    public String mailstamp;
    public Integer msgId;  //通知发送邮件时用来保存邮件是由哪个通知发送出来的

    public Integer isUrgent;//增加优先级：0:不紧急；1:紧急；不设置则默认为"不紧急"

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String[] getToEmail() {
        return toEmail;
    }

    public void setToEmail(String[] toEmail) {
        this.toEmail = toEmail;
    }

    public Integer[] getReceiverIDs() {
        return receiverIDs;
    }

    public void setReceiverIDs(Integer[] receiverIDs) {
        this.receiverIDs = receiverIDs;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getMailstamp() {
        return mailstamp;
    }

    public void setMailstamp(String mailstamp) {
        this.mailstamp = mailstamp;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }
}
