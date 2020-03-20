package com.elearning.service.pub;

import com.elearning.pojo.pub.QRLogin;
import org.springframework.stereotype.Service;


@Service("qrLoginService")
public class QRLoginServiceImpl implements IQRLoginService {

    @Override
    public QRLogin findByRandNum(Long randNum) {
        return null;
    }

    @Override
    public Integer save(QRLogin transientInstance) {
        return null;
    }

    @Override
    public void delete(QRLogin persistentInstance) {

    }

    @Override
    public void update(QRLogin transientInstance) {

    }
}
