package com.elearning.service.mixtraining;

import com.elearning.common.CacheUtils;
import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.dao.mixtraining.MtMixTrainAddrBookMapper;
import com.elearning.pojo.message.MsgMessageUser;
import com.elearning.pojo.mixtraining.MtMixTrainAddrBook;
import com.elearning.pojo.mixtraining.MtMixTrainAndAddrBook;
import com.elearning.pojo.mixtraining.MtMixTrainUserArrange;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.*;
import com.elearning.service.message.IMsgMessageUserService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.mixtraining.MtMixTrainUserArrangeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("addrBookService")
public class AddrBookService implements IAddrBookService{


    @Autowired
    private MtMixTrainAddrBookMapper mtMixTrainAddrBookMapper;

    @Autowired
    private IMtMixTrainAndAddrBookService mtMixTrainAndAddrBookService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IMtMixTrainUserArrangeService mtMixTrainUserArrangeService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IMsgMessageUserService msgMessageUserService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MtMixTrainUserArrangeForm>  listAddrBook(Map<String,Object> condition){

        List<MtMixTrainUserArrangeForm> list = null;
        if(condition.get("trainId")!=null && !condition.get("trainId").toString().equals("")){
            List<MtMixTrainAndAddrBook> mMtMixTrainAndAddrBookList = this.mtMixTrainAndAddrBookService.findByTrainId(Integer.valueOf(condition.get("trainId").toString()));
            if(mMtMixTrainAndAddrBookList != null && mMtMixTrainAndAddrBookList.size()>0){
                List<Integer> addrbookIdList = new ArrayList<>() ;
                for(MtMixTrainAndAddrBook mt:mMtMixTrainAndAddrBookList){
                    addrbookIdList.add(mt.getAddrbookId());
                }
                if(addrbookIdList !=null && addrbookIdList.size()>0){
                    condition.put("addrbookIdList", addrbookIdList);
                    List<MtMixTrainAddrBook> mtMixTrainAddrBookList = this.mtMixTrainAddrBookMapper.listAddrBook(condition);
                    list = this.adjustListAddrBook(mtMixTrainAddrBookList,condition);
                }
            }
        }else{
            List<MtMixTrainAddrBook> mtMixTrainAddrBookList = this.mtMixTrainAddrBookMapper.listAddrBook(condition);
            list = this.adjustListAddrBook(mtMixTrainAddrBookList,condition);
        }

        return list;
    }

    @Transactional(rollbackFor = {Exception.class })
    public List<MtMixTrainUserArrangeForm> adjustListAddrBook(List<MtMixTrainAddrBook> mtMixTrainAddrBookList,Map<String,Object> map){

        Iterator it = mtMixTrainAddrBookList.iterator();
        List<MtMixTrainUserArrangeForm> result = new ArrayList<>();
        while(it.hasNext()){
            MtMixTrainAddrBook mtMixTrainAddrBook = (MtMixTrainAddrBook)it.next();

            MtMixTrainUserArrangeForm temp = new MtMixTrainUserArrangeForm();
            temp.setAddrbookId(mtMixTrainAddrBook.getAddrbookId().toString());
            temp.setAddrbookName(mtMixTrainAddrBook.getAddrbookName());
            int bookRole = mtMixTrainAddrBook.getAddrbookRole();
            String addrbookRole = "";
            if(bookRole==0){
                addrbookRole = "部门学员通讯录";
            }else if(bookRole==1){
                addrbookRole = "部门秘书通讯录";
            }else{
                addrbookRole = "部门领导通讯录";
            }
            temp.setBookRole(addrbookRole);
            String addrBookDate = DateTimeUtil.dateToStr(mtMixTrainAddrBook.getAddrbookDate());
            temp.setAddrbookDate(addrBookDate);

            if (map.get("trainId") != null && !map.get("trainId").equals("")) {
                int trainId = (Integer)map.get("trainId");
                TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
                temp.setTrainId(trainId);
                if(train!=null){
                    temp.setTrainName(train.getTrainName());
                }
            } else if(map.get("currentTrainId") != null && !map.get("currentTrainId").equals("")) {
                int trainId = (Integer)map.get("currentTrainId");
                Train train = this.onlineTrainingService.findById(trainId);
                temp.setTrainId(trainId);
                if(train!=null){
                    temp.setTrainName(train.getTrainName());
                }
            }
            result.add(temp);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MtMixTrainUserArrangeForm>  listAllAddrBook(Map<String,Object> condition){

        List<MtMixTrainAddrBook> mtMixTrainAddrBookList = this.mtMixTrainAddrBookMapper.getListByOperatorIdAndKeyWords(condition);
        List<MtMixTrainUserArrangeForm> result = new ArrayList<>();

        for(int i=0;i<mtMixTrainAddrBookList.size();i++){
            String orgSeq = mtMixTrainAddrBookList.get(i).getOrgSEQ();

            MtMixTrainUserArrangeForm temp = new MtMixTrainUserArrangeForm();
            temp.setAddrbookId(mtMixTrainAddrBookList.get(i).getAddrbookId().toString());
            temp.setAddrbookName(mtMixTrainAddrBookList.get(i).getAddrbookName());
            int bookRole = mtMixTrainAddrBookList.get(i).getAddrbookRole();
            String addrbookRole = "";
            if(bookRole==0){
                addrbookRole = "部门学员通讯录";
            }else if(bookRole==1){
                addrbookRole = "部门秘书通讯录";
            }else{
                addrbookRole = "部门领导通讯录";
            }
            temp.setBookRole(addrbookRole);
            String addrBookDate = DateTimeUtil.dateToStr(mtMixTrainAddrBookList.get(i).getAddrbookDate());
            temp.setAddrbookDate(addrBookDate);

            result.add(temp);
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getAddressBookListInfo(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("operatorId", operator.getOperatorId());

        // 获取查询条件（可为空）
        String keyWords = request.getParameter("keyWords");
        if (keyWords != null && keyWords != "") {
            conditionMap.put("keyWords", keyWords);
        }
        List<MtMixTrainUserArrangeForm> addrBookList = this.listAllAddrBook(conditionMap);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("addressBookList",addrBookList);
        return ServiceResponse.createBySuccess(returnMap);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse showStaffOfAddrBook(HttpServletRequest request){

        String addrbookId = request.getParameter("addrbookId");
        List<BasicUserForm> staffs = new ArrayList<>();
        if(addrbookId != null) {
            Map<String,Object> map = new HashMap<>();
            map.put("addrbookId", addrbookId);
            staffs = this.mtMixTrainUserArrangeService.findMtMixTrainUserArrangeListByAddrBookId(map);
            this.resetOrgName(staffs, request);
        }

        return ServiceResponse.createBySuccess(staffs);
    }

    private void resetOrgName(List<BasicUserForm> userFormList,HttpServletRequest request){
        EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=login_user.getTenantId();
        for(BasicUserForm user:userFormList){
            if(user.getTenantId() != null){
                if(user.getTenantId().equals(tenantId)){

                } else{
                    user.setOrgName(CacheUtils.getTenantName(user.getTenantId()));
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getUserInfoByMixTrainUserInfo(HttpServletRequest request){

        List<Map<String,Object>> returnList = new ArrayList<>();

        String flag = "fromMtMixTrainUserTrainInfo"; //从"实际参会人员列表"点开"生成通讯录"
        if(request.getParameter("flag")!=null && !request.getParameter("flag").equals("")){
            flag = request.getParameter("flag");
        }

        String[] MixTrainUserInfos=request.getParameterValues("mixTrainUserInfo");

        if(MixTrainUserInfos==null){

        } else{
            int i=0;
            for(String mixTrainUserInfo : MixTrainUserInfos){
                Integer userId = null;
                if(flag.equals("fromMtMixTrainUserTrainInfo")){
                    MtMixTrainUserTrainInfo  trainUserInfo=this.mtMixTrainUserTrainInfoService.findById(Integer.parseInt(mixTrainUserInfo));
                    if(trainUserInfo != null){
                        userId = trainUserInfo.getUserID();
                    }
                }else if(flag.equals("fromMsgMessageUserForm")){
                    MsgMessageUser msgMessageUser = this.msgMessageUserService.selectByPrimaryKey(Integer.parseInt(mixTrainUserInfo));
                    if(msgMessageUser != null){
                        userId = msgMessageUser.getUserID();
                    }
                }

                UserForm userForm = this.eosorgTEmployeeService.findByUserId(userId);

                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("trainUserInfoId",mixTrainUserInfo);

                returnMap.put("operatorName",userForm.getOperatorName() != null ? userForm.getOperatorName():"");
                returnMap.put("orgName",userForm.getOrgName() != null ? userForm.getOrgName():"");
                returnMap.put("userId",userForm.getUserId() != null ? userForm.getUserId():"");
                returnMap.put("oemail",userForm.getOemail() != null ? userForm.getOemail():"");
                returnMap.put("operatorId",userForm.getOperatorId() != null ? userForm.getOperatorId():"");
                returnMap.put("otel",userForm.getOtel1() != null ? userForm.getOtel1():"");

                returnList.add(returnMap);
            }
        }
        return ServiceResponse.createBySuccess(returnList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse generateTrainBook(HttpServletRequest request){

        String message = "通讯录创建成功";

        //importPersonnelList:审核通过人员信息队列
        String[] importPersonIds = request.getParameterValues("importPersonId");
        if(importPersonIds==null || importPersonIds.length==0){
            message = "创建失败，没有添加学员";
            return ServiceResponse.createByErrorMessage(message);
        }

        if(importPersonIds!=null && importPersonIds.length>0){
            //获得通讯录信息(或者存储通讯录)
            EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
            int operatorId = login_user.getOperatorId();
            Integer addrbookId = null;
            String addrbookName = null;
            String addrbookRole = null;
            Integer openScope = 2201;
            Date addrbookDate = new Date();
            addrbookRole=request.getParameter("addrbookRole");
            MtMixTrainAddrBook addrbook = null;//mt_mixtrainaddrbook
            if(request.getParameter("addrbook")==null||request.getParameter("addrbook").trim().equals("-1")){
                //表明是创建新的通讯录
                addrbookName=request.getParameter("new_addr");
                if(request.getParameter("openScope")!=null){
                    openScope = Integer.parseInt(request.getParameter("openScope"));
                }
                if(addrbookName==null||addrbookName.trim().length()==0){
                    message = "创建失败，没有填写通讯录名称";
                    return ServiceResponse.createByErrorMessage(message);
                }
                addrbook = new MtMixTrainAddrBook();
                addrbook.setAddrbookName(addrbookName);
                addrbook.setOperatorId(operatorId);
                addrbook.setOpenScope(openScope);
                Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
                EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);

                String orgSeq = org.getOrgSEQ();
                addrbook.setOrgSEQ(orgSeq);

                Map<String,Object> parmM = new HashMap<>();
                parmM.put("addrbookName",addrbookName);
                parmM.put("operatorId",operatorId);
                parmM.put("openScope",openScope);
                parmM.put("orgSEQ",orgSeq);

                List<MtMixTrainAddrBook> list = this.mtMixTrainAddrBookMapper.findListByMap(parmM);
                if(list != null && list.size()>0){
                    message = "创建失败，当前培训已有同名通讯录，请重新命名或者对已有通讯录进行修改";
                    return ServiceResponse.createByErrorMessage(message);
                } else{
                    this.mtMixTrainAddrBookMapper.insert(addrbook);
                    Object trainIdAttribute = request.getSession().getAttribute("trainId");
                    if (trainIdAttribute != null) {
                        int trainId = Integer.parseInt(trainIdAttribute.toString());
                        MtMixTrainAndAddrBook trainAndAddrBook = new MtMixTrainAndAddrBook();
                        trainAndAddrBook.setAddrbookId(addrbook.getAddrbookId());
                        trainAndAddrBook.setTrainId(trainId);
                        this.mtMixTrainAndAddrBookService.insert(trainAndAddrBook);
                    }
                }
            }else{
                addrbookId = Integer.parseInt(request.getParameter("addrbook"));
                addrbook = this.mtMixTrainAddrBookMapper.selectByPrimaryKey(addrbookId);
            }
            addrbook.setAddrbookDate(addrbookDate);
            addrbook.setAddrbookRole(Integer.parseInt(addrbookRole));
            this.mtMixTrainAddrBookMapper.updateByPrimaryKey(addrbook);

            List<MtMixTrainUserArrange> alreadyMember = this.mtMixTrainUserArrangeService.getListByAddrbookId(addrbook.getAddrbookId());
            Set<String> alreadyMemberIds = new HashSet<>();
            Iterator<MtMixTrainUserArrange> iter = alreadyMember.iterator();
            while(iter.hasNext()){
                MtMixTrainUserArrange nexter=iter.next();
                alreadyMemberIds.add(nexter.getUserID().toString());
            }

            for(String importPersonId: importPersonIds){
                if(importPersonId!=null&&importPersonId.trim().length()>0){
                    if(alreadyMemberIds.contains(importPersonId)){
                        //表明这个人已经在当前目录了，不得再次添加
                    } else{
                        MtMixTrainUserArrange newArrange=new MtMixTrainUserArrange();
                        newArrange.setAddrbookId(addrbook.getAddrbookId());
                        try {
                            newArrange.setUserID(Integer.parseInt(importPersonId));
                            this.mtMixTrainUserArrangeService.insert(newArrange);
                        } catch (Exception e) {

                            message = "存在学员账号填写有误,请联系技术支持人员";
                            return ServiceResponse.createByErrorMessage(message);
                        }
                    }
                }
            }
        }
        return ServiceResponse.createBySuccessMessage(message);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void showAddrBook(HttpServletRequest request){

        String addrbookId = request.getParameter("addrbookId");
        MtMixTrainAddrBook AddrBook = this.mtMixTrainAddrBookMapper.selectByPrimaryKey(Integer.valueOf(addrbookId.toString()));

        //获取通讯录人员信息
        Map<String,Object> map = new HashMap<>();
        map.put("addrbookId", addrbookId);

        List<BasicUserForm> userFormList = mtMixTrainUserArrangeService.findMtMixTrainUserArrangeListByAddrBookId(map);
        this.resetOrgName(userFormList, request);

        String addressBookDate="";
        addressBookDate = DateTimeUtil.dateToStr(AddrBook.getAddrbookDate());

        request.setAttribute("addressBookDate", addressBookDate);
        request.setAttribute("addressBookInfo", AddrBook);
        request.setAttribute("addressBookUsers",userFormList);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void forUpdateAddrBook(HttpServletRequest request){

        String addrbookIdStr=request.getParameter("addrbookId");

        if(addrbookIdStr!=null && addrbookIdStr.trim().length()>0){
            int addrbookId = Integer.valueOf(addrbookIdStr);
            request.getSession().setAttribute("addrbookId", addrbookId);
            MtMixTrainAddrBook AddrBook = this.mtMixTrainAddrBookMapper.selectByPrimaryKey(addrbookId);

            Map<String,Object> map = new HashMap<>();
            map.put("addrbookId", addrbookId);
            List<BasicUserForm> arrangeUserList = mtMixTrainUserArrangeService.findMtMixTrainUserArrangeListByAddrBookId(map);
            this.resetOrgName(arrangeUserList, request);

            request.setAttribute("arrangeUserList", arrangeUserList);
            request.setAttribute("AddrBook", AddrBook);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse removeAddrBook(HttpServletRequest request){

        Integer addrbookId = null;
        MtMixTrainAddrBook AddrBook = null;

        String message = "通讯录已成功删除";

        //删除通讯录下的成员
        List<MtMixTrainUserArrange> mtMixTrainUserArrangeList = null;
        List<MtMixTrainAndAddrBook> trainAndAddrBookList = new ArrayList<>();
        try {
            addrbookId = Integer.valueOf(request.getParameter("addrbookId"));
            AddrBook = this.mtMixTrainAddrBookMapper.selectByPrimaryKey(addrbookId);
            MtMixTrainAndAddrBook trainAndAddrBook = this.mtMixTrainAndAddrBookService.getMtMixTrainAndAddrBookByAddrBookId(addrbookId);
            trainAndAddrBookList.add(trainAndAddrBook);
            mtMixTrainUserArrangeList = mtMixTrainUserArrangeService.getListByAddrbookId(addrbookId);
        } catch (Exception e1) {
            message = "通讯录人员删除失败，未能成功删除通讯录";
            return ServiceResponse.createByErrorMessage(message);
        }
        if(mtMixTrainUserArrangeList!=null && mtMixTrainUserArrangeList.size()>0){
            List<Integer> arrangeListIds = new ArrayList<>();
            for(MtMixTrainUserArrange arrange : mtMixTrainUserArrangeList){
                arrangeListIds.add(arrange.getUserID());
            }
            try {
                if(arrangeListIds.size() > 0){
                    this.mtMixTrainUserArrangeService.deleteMtMixTrainUsers(addrbookId, arrangeListIds);
                    this.mtMixTrainAddrBookMapper.deleteByPrimaryKey(AddrBook.getAddrbookId());
                    for(MtMixTrainAndAddrBook mtMixTrainAndAddrBook : trainAndAddrBookList){
                        this.mtMixTrainAndAddrBookService.deleteByPrimaryKey(mtMixTrainAndAddrBook.getID());
                    }
                }else{
                    message = "通讯录人员删除失败，未能成功删除通讯录";
                    return ServiceResponse.createByErrorMessage(message);
                }
            } catch (Exception e) {
                message = "通讯录人员删除失败，未能成功删除通讯录";
                return ServiceResponse.createByErrorMessage(message);
            }
        }else{
            if(AddrBook != null){
                try {
                    this.mtMixTrainAddrBookMapper.deleteByPrimaryKey(AddrBook.getAddrbookId());
                    for(MtMixTrainAndAddrBook mtMixTrainAndAddrBook : trainAndAddrBookList){
                        this.mtMixTrainAndAddrBookService.deleteByPrimaryKey(mtMixTrainAndAddrBook.getID());
                    }
                } catch (Exception e) {
                    message = "通讯录人员删除失败，未能成功删除通讯录";
                    return ServiceResponse.createByErrorMessage(message);
                }
            }else{
                message = "通讯录人员删除失败，未能成功删除通讯录";
                return ServiceResponse.createByErrorMessage(message);
            }
        }
        return  ServiceResponse.createBySuccessMessage(message);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse quoteAddrBook(HttpServletRequest request){

        EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        String message = "通讯录引用成功";

        int addrbookId = Integer.valueOf(request.getParameter("addrbookId"));
        MtMixTrainAddrBook AddrBook =  this.mtMixTrainAddrBookMapper.selectByPrimaryKey(addrbookId);
        String addrbookName = AddrBook.getAddrbookName();
        int addrbookRole = AddrBook.getAddrbookRole();
        int openScope = AddrBook.getOpenScope();

        MtMixTrainAddrBook copiedAddrBook = new MtMixTrainAddrBook();
        copiedAddrBook.setAddrbookDate(new Date());
        copiedAddrBook.setAddrbookName("(复制)"+addrbookName);
        copiedAddrBook.setAddrbookRole(addrbookRole);
        copiedAddrBook.setOperatorId(login_user.getOperatorId());
        copiedAddrBook.setOpenScope(openScope);
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);
        copiedAddrBook.setOrgSEQ(org==null?"":org.getOrgSEQ());
        try {
            this.mtMixTrainAddrBookMapper.insert(copiedAddrBook);
            int newAddrBookId = copiedAddrBook.getAddrbookId();
            String trainID = request.getParameter("trainId");
            if (trainID != null && trainID != "") {
                int trainId = Integer.valueOf(trainID);
                MtMixTrainAndAddrBook trainAndAddrBook = new MtMixTrainAndAddrBook();
                trainAndAddrBook.setAddrbookId(newAddrBookId);
                trainAndAddrBook.setTrainId(trainId);
                this.mtMixTrainAndAddrBookService.insert(trainAndAddrBook);
            }
            List<MtMixTrainUserArrange> mtMixTrainUserArrangeList = mtMixTrainUserArrangeService.getListByAddrbookId(addrbookId);
            Iterator it = mtMixTrainUserArrangeList.iterator();
            while(it.hasNext()){
                MtMixTrainUserArrange mtMixTrainUserArrange = (MtMixTrainUserArrange)it.next();
                int operatorId = mtMixTrainUserArrange.getUserID();

                MtMixTrainUserArrange newMtMixTrainUserArrange = new MtMixTrainUserArrange();
                newMtMixTrainUserArrange.setAddrbookId(newAddrBookId);
                newMtMixTrainUserArrange.setUserID(operatorId);
                mtMixTrainUserArrangeService.insert(newMtMixTrainUserArrange);
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "通讯录引用失败";
            ServiceResponse.createByErrorMessage(message);
        }

        return  ServiceResponse.createBySuccessMessage(message);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse updateTrainBook(HttpServletRequest request){

        //通讯录创建人员
        //EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        //int operatorId=login_user.getOperatorId();
        //没用到

        //人员当前的organization
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);

        String addrbookIdStr = request.getParameter("addrbook");
        String addrbookName = request.getParameter("new_addr");
        String addrbookRole = request.getParameter("addrbookRole");
        Integer openScope = Integer.parseInt(request.getParameter("openScope"));
        String msg = "";
        if(addrbookName == null){
            msg = "没有填写培训录名称，通讯录修改失败";
            return ServiceResponse.createByErrorMessage(msg);
        } else if(addrbookIdStr == null){
            msg = "提交信息有误，通讯录修改失败";
            return ServiceResponse.createByErrorMessage(msg);
        } else{
            MtMixTrainAddrBook addrbook=null;
            Integer addrbookId=null;
            try {
                addrbookId = Integer.parseInt(addrbookIdStr);
                addrbook = this.mtMixTrainAddrBookMapper.selectByPrimaryKey(addrbookId);
                addrbook.setAddrbookName(addrbookName);
                addrbook.setAddrbookDate(new Date());
                addrbook.setOpenScope(openScope);
                addrbook.setOrgSEQ(org==null?"":org.getOrgSEQ());
                if(addrbookRole != null){
                    addrbook.setAddrbookRole(Integer.parseInt(addrbookRole));
                }
                this.mtMixTrainAddrBookMapper.updateByPrimaryKeySelective(addrbook);

                //删除人员
                String[] deleteUsers = request.getParameterValues("deletePersonId");
                if(deleteUsers!=null && deleteUsers.length>0){
                    List<Integer> deleteUsersIdArray = new ArrayList<>();
                    for(String deleteUserId : deleteUsers){
                        deleteUsersIdArray.add(Integer.parseInt(deleteUserId));
                    }
                    this.mtMixTrainUserArrangeService.deleteMtMixTrainUsers(addrbookId, deleteUsersIdArray);
                }

                //添加新人员
                String[] newUsers = request.getParameterValues("importPersonId");
                if(newUsers!=null && newUsers.length>0){
                    for(String newUserId : newUsers){
                        MtMixTrainUserArrange newArrange = new MtMixTrainUserArrange();
                        newArrange.setAddrbookId(addrbookId);
                        newArrange.setUserID(Integer.parseInt(newUserId));
                        this.mtMixTrainUserArrangeService.insert(newArrange);
                    }
                }
                msg = "通讯录修改成功！";
                return ServiceResponse.createBySuccessMessage(msg);
            } catch (Exception e) {
                msg = "提交信息有误，通讯录修改失败！";
                return ServiceResponse.createByErrorMessage(msg);
            }
        }
    }


}
