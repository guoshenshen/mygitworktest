package com.elearning.controller.pub;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.EosOrgTOrganizationVo;
import com.elearning.vo.pub.BasicOrgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:03
 */
@Controller
@RequestMapping("/eosorgTOrganization/")
public class EosorgTOrganizationController {

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;


    /**
     * 机构管理，查看某机构的具体信息
     * @param orgId
     * @param request
     * @return
     */
    @RequestMapping("detail.do")
    public String detail(Integer orgId,HttpServletRequest request){
        StringBuffer message = new StringBuffer();
        int id = 0;
        if(orgId!=null)
            id=orgId;
        else if(request.getAttribute("orgId")!=null)
            id=Integer.parseInt(request.getAttribute("orgId").toString());
        else id = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization eosorgTOrganization = eosorgTOrganizationService.findById(id);
        EosOrgTOrganizationVo eosorgTOrganizationForm = null;
        if(eosorgTOrganization!=null)
            eosorgTOrganizationForm = this.eosorgTOrganizationService.getOrgForm(eosorgTOrganization) ;
        else{
            eosorgTOrganizationForm = new EosOrgTOrganizationVo();
            message.append("此机构已经删除");
            request.setAttribute("message", message.toString());
        }
        request.setAttribute("orginfo", eosorgTOrganizationForm);
        return "system/orgdetail";
    }

    /**
     * 跳转到机构编辑页面，在某机构下增加子机构
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("foradd.do")
    public String forAddOrgForm(Integer orgId,HttpServletRequest request){
        Integer parentOrgId=orgId!=null?orgId:Integer.parseInt(request.getAttribute("orgId").toString());
        EosorgTOrganization parentOrg=this.eosorgTOrganizationService.findById(orgId);
        EosOrgTOrganizationVo orgForm = new EosOrgTOrganizationVo() ;
        if(parentOrg.getIsVirOrg().intValue()==1)
            orgForm.setIsVirOrg(1);
        else
            orgForm.setIsVirOrg(0);
        if(parentOrg.getIsOrg().intValue()==0)
            orgForm.setIsOrg(0);
        else
            orgForm.setIsOrg(1);
        orgForm.setParentOrgName(parentOrg.getOrgName());
        orgForm.setParentOrgId(parentOrgId);
        orgForm.setStatus(1);
        request.setAttribute("orginfo", orgForm);
        return "system/orgforadd";
    }

    /**
     * 跳转到机构编辑页面，修改某机构
     * @param orgId
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("forupdate.do")
    public String forUpdateOrgForm(Integer orgId,HttpServletRequest request){
        EosorgTOrganization eosorgTOrganization = this.eosorgTOrganizationService.findById(orgId);
        EosOrgTOrganizationVo eosorgTOrganizationForm = this.eosorgTOrganizationService.getOrgForm(eosorgTOrganization) ;
        request.setAttribute("orginfo", eosorgTOrganizationForm);
        return "system/orgforupdate";
    }


    /**
     * 增加机构
     * @param eosorgTOrganizationForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("add.do")
    public String addOrg(EosOrgTOrganizationVo eosorgTOrganizationForm,HttpServletRequest request ){

        EosOperator currentOperator = ( EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantOrgId=CacheUtils.getTenant(currentOperator.getTenantId()).getOrgId();
        String message = this.eosorgTOrganizationService.save(eosorgTOrganizationForm,tenantOrgId);
        boolean isNum = message.matches("[0-9]+");
        if(isNum==true){
            int orgId = Integer.valueOf(message);
            request.setAttribute("orgId", orgId);
            request.setAttribute("message", "部门信息保存成功");
            return "forward:/eosorgTOrganization/detail.do";
        }else {
            request.setAttribute("message", message);
            return "forward:/eosorgTOrganization/foradd.do?orgId="+eosorgTOrganizationForm.getParentOrgId();
        }
    }

    /**
     * 修改机构
     * @param eosorgTOrganizationForm
     * @param vorgId
     * @param vorgName
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("update.do")
    public String updateOrg(EosOrgTOrganizationVo eosorgTOrganizationForm,Integer vorgId,String vorgName,HttpServletRequest request){
        EosOperator currentOperator = ( EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantOrgId=CacheUtils.getTenant(currentOperator.getTenantId()).getOrgId();
        eosorgTOrganizationForm.setOrgName(vorgName);
        eosorgTOrganizationForm.setOrgId(vorgId);
        String message = this.eosorgTOrganizationService.update(eosorgTOrganizationForm,tenantOrgId);
        boolean isNum = message.matches("[0-9]+");
        if(isNum==true){
            EosorgTOrganization eosorgTOrganization = this.eosorgTOrganizationService.findById(vorgId);
            eosorgTOrganizationForm.setLogo(eosorgTOrganization.getLogo());
            request.setAttribute("orgId", eosorgTOrganizationForm.getOrgId());
            request.setAttribute("message", "部门信息更新成功");
            return "forward:/eosorgTOrganization/detail.do";
        }
        else{
            request.setAttribute("message", message);
            return "forward:/eosorgTOrganization/forupdate.do?orgId="+eosorgTOrganizationForm.getOrgId();
        }
    }


    @RequestMapping("select.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public ServiceResponse selectEosorgTOrganization(Integer orgId){
        if(orgId == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        EosorgTOrganization org=eosorgTOrganizationService.findById(orgId);
        if(org!=null){
            return ServiceResponse.createBySuccess();
        }
        else{
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NOTFOUNDBYID.getCode(), ResponseCode.NOTFOUNDBYID.getDesc());
        }
    }

    /**
     * 根据一组机构编号查询机构信息
     * @param orgIdList
     * @param request
     * @return
     */
    @RequestMapping("selectOrgListById.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public ServiceResponse selectOrgListById(String[] orgIdList,HttpServletRequest request){

        try {
            String[] orgIdStrList = request.getParameterValues("orgIdList");
            List<Integer> orgIdListArray = new ArrayList<Integer>();
            for (String orgIdStr : orgIdStrList) {
                orgIdListArray.add(Integer.parseInt(orgIdStr));
            }
            List<BasicOrgForm> orgFormList = this.eosorgTOrganizationService.findBasicOrgInfoById(orgIdListArray);
            return ServiceResponse.createBySuccess(orgFormList);
        }
        catch (Exception e){
            return ServiceResponse.createByErrorMessage("部门信息获取失败");
        }
    }


    /**
     * 获取以某机构为顶点的组织机构树
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("getSubOrgInfoForTree.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public void getSubOrgInfoForTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        Map<String,Object> resultMap = new HashMap<>();

        EosOperator currentOperator = ( EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=currentOperator.getTenantId();
        //当前管理员所属研究所级机构
        Tenant tenant=this.tenantService.findById(tenantId);
        EosorgTOrganization tenantOrg=this.eosorgTOrganizationService.findById(tenant.getOrgId());
        String orgSeq=tenantOrg.getOrgSEQ();

        String rootOrgId=request.getParameter("orgId");
        String showOrderedCASTenant=request.getParameter("showOrderedCASTenant");
        Boolean loadRoot=false;
        Integer status=null;
        if(request.getParameter("loadRoot")!=null){
            loadRoot=Boolean.parseBoolean(request.getParameter("loadRoot"));
        }
        if(request.getParameter("status")!=null){
            status=Integer.parseInt(request.getParameter("status"));
        }
        Integer searchOrgId=null;
        EosorgTOrganization org =null;
        if(rootOrgId==null){
            //未传递参数,则显示当前操作者所属一级机构orgId
            searchOrgId=tenant.getOrgId();
            org=tenantOrg;
        }
        else{
            searchOrgId=Integer.parseInt(rootOrgId.trim());
            org=this.eosorgTOrganizationService.findById(searchOrgId);
        }
        StringBuffer result=new StringBuffer("[");
        try {
            ArrayList<BasicOrgForm> subOrgList =new ArrayList<BasicOrgForm>();
            //在明确需要加载根节点(loadRoot=true)或者尚未加载根节点(rootOrgId==null)时,将org添加至subOrgList
            if(rootOrgId==null||loadRoot){
                subOrgList.add(this.eosorgTOrganizationService.getSimpleOrgInfo(org));

            }
            if(searchOrgId==2&&showOrderedCASTenant!=null&&showOrderedCASTenant.trim().equals("true")){
                //表示需要按照中科院研究所标准顺序显示各个研究所
                subOrgList.addAll(this.eosorgTOrganizationService.findOrderTenantOrgByCondition(null));

            }
            else{
                Map<String,Object> condition=new HashMap<String,Object>();
                if(status!=null&&status!=-1){
                    condition.put("status", status);
                }
                condition.put("parentOrgId", searchOrgId);
                subOrgList.addAll(this.eosorgTOrganizationService.getSubOrgInfo(condition));
            }

            if(subOrgList!=null&&subOrgList.size()>0){
                int index=0;
                for(BasicOrgForm subOrg:subOrgList){
                    if(index!=0){
                        result.append(",");
                    }
                    result.append("{");
                    result.append("\"parentOrgId\":\"").append(searchOrgId).append("\"");
                    result.append(",\"orgId\":\"").append(subOrg.getOrgId()).append("\"");
                    result.append(",\"name\":\"").append(subOrg.getOrgName()).append("\"");
                    result.append(",\"status\":\"").append(subOrg.getStatus()).append("\"");
                    Integer subTenantId=subOrg.getTenantId();
                    String tenantName="";
                    if(subTenantId!=null){
                        result.append(",\"tenantId\":\"").append(subTenantId).append("\"");
                        tenantName= CacheUtils.getTenantName(subTenantId);
                    }
                    else{
                        if(org.getTenantId()!=null){
                            tenantName=CacheUtils.getTenantName(org.getTenantId());
                        }
                    }
                    result.append(",\"tenantName\":\"").append(tenantName).append("\"");
                    String subOrgseq=subOrg.getOrgSeq();
                    //当操作者所属机构具有对子机构的管辖权时,才能展开子机构
                    if(subOrgseq!=null&&subOrgseq.contains(orgSeq)){
                        result.append(",\"isParent\":\"true\"");
                    }
                    if(index==0&&(rootOrgId==null||loadRoot)){
                        result.append(",\"open\":\"true\"");
                    }
                    else{
                    }
                    result.append("}");
                    index++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            result.append("]");
            resultMap.put("result",result);
            response.getWriter().write(result.toString());
        }
    }

}
