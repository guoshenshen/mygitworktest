package com.elearning.service.mixtraining;

import javax.servlet.http.HttpServletRequest;

public interface IMtMixTrainTopbandService {

    void selectTopband(HttpServletRequest request);

    void deleteTrainTopband(HttpServletRequest request);

    void deleteSelfTopband(HttpServletRequest request);


}
