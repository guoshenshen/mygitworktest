package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.vo.mixtraining.MtMixTrainUserArrangeForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IAddrBookService {

    List<MtMixTrainUserArrangeForm>  listAddrBook(Map<String,Object> map);

    List<MtMixTrainUserArrangeForm>  listAllAddrBook(Map<String,Object> map);

    ServiceResponse showStaffOfAddrBook(HttpServletRequest request);

    ServiceResponse getUserInfoByMixTrainUserInfo(HttpServletRequest request);

    ServiceResponse generateTrainBook(HttpServletRequest request);

    ServiceResponse getAddressBookListInfo(HttpServletRequest request);

    void showAddrBook(HttpServletRequest request);

    void forUpdateAddrBook(HttpServletRequest request);

    ServiceResponse removeAddrBook(HttpServletRequest request);

    ServiceResponse quoteAddrBook(HttpServletRequest request);

    ServiceResponse updateTrainBook(HttpServletRequest request);



}
