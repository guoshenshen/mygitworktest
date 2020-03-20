package com.elearning.controller.pub;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.FileUploadUtil;
import com.elearning.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 13:48
 */
@Controller
@RequestMapping("/filesTool/")
public class FilesToolController {

    /**
     * 上传头像
     * @param request
     * @return
     */
    @RequestMapping("uploadPic.do")
    @ResponseBody
    public ServiceResponse uploadPic(HttpServletRequest request){
        String filePath = "";
        String pic_height=request.getParameter("picHeight");
        String pic_width=request.getParameter("picWidth");
        try {
            filePath = FileUploadUtil.uploadFile(request,pic_height,pic_width);

        } catch(Exception ex){
            ex.printStackTrace();
        }
        if(filePath.length() == 0){
            return ServiceResponse.createByErrorMessage("系统错误");
        }
        return ServiceResponse.createBySuccess(filePath);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("uploadCourseRelatedMaterial.do")
    @ResponseBody
    public ServiceResponse uploadCourseRelatedMaterial(Long courseId,HttpServletRequest request){
        //操作者
        Map<String,File> uploadMaterials=FileUploadUtil.uploadFile(request,
                PropertiesUtil.getProperty("course.fulladdress"),
                PropertiesUtil.getProperty("course.dateaddress"),"uploadFiles"+File.separator+ courseId);
        Set<String> uploadPathSet=uploadMaterials.keySet();
        File[] fileList = new File[uploadPathSet.size()];
        Integer index=0;
        for(String uploadurl:uploadPathSet){
            File file=uploadMaterials.get(uploadurl);
            fileList[index]=file;
        }
        String zipFileAddress = FileUploadUtil.zipFile(request, PropertiesUtil.getProperty("course.fulladdress"),
                PropertiesUtil.getProperty("course.dateaddress"),
                "uploadFiles"+File.separator+ courseId, DateTimeUtil.dateToStr(new Date())+ ".zip", fileList);
        return ServiceResponse.createBySuccess(zipFileAddress);
    }


    @RequestMapping("uploadPicSeries.do")
    @ResponseBody
    public ServiceResponse uploadPicSeries(HttpServletRequest request){
        String uploadTypeStr=request.getParameter("uploadType");
        UploadFolder uploadType= UploadFolder.findByCode(Short.parseShort(uploadTypeStr));
        String pic_height=request.getParameter("picHeight");
        String pic_width=request.getParameter("picWidth");
        Map<String,File> uploadImg= ImportFileTool.uploadFile(request, uploadType.getFolder()+File.separator);
        Set<String> uploadImgPathSet=uploadImg.keySet();
        Iterator<String> iterate=uploadImgPathSet.iterator();
        String savePath=iterate.next();
        File imageUpload=uploadImg.get(savePath);
        BufferedImage bufferImage;
        try {
            bufferImage= ImageIO.read(imageUpload);
            String outSrc= FileTool.convertFromURLToPhysicalPath(savePath);
            ImageTool.compressBySize(bufferImage, outSrc, Integer.parseInt(pic_height), Integer.parseInt(pic_width));
        } catch (IOException e) {
            ServiceResponse.createByError();
        }
        return ServiceResponse.createBySuccess(savePath);
    }


}
