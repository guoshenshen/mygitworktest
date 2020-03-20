package com.elearning.service.pub;

import com.elearning.pojo.pub.QRLogin;

public interface IQRLoginService {

    public QRLogin findByRandNum(Long randNum);

    public Integer save(QRLogin transientInstance);

    public void delete(QRLogin persistentInstance);

    public void update(QRLogin transientInstance);
}
