package com.elearning.service.coursemanage;

import com.elearning.pojo.coursemanage.UscLectureFile;
import com.elearning.pojo.pub.Course;
import com.elearning.service.pub.ICourseService;
import com.elearning.util.PropertiesUtil;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/14 8:48
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements IFileUploadService{

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @Autowired
    private ICourseService courseService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean uploadCourse(HttpServletRequest request,MultipartFile coursezipfile) {
        boolean result;
        String pathSeparator = File.separator;
        String uploadPathStr = PropertiesUtil.getProperty("course.fulladdress") + "uploadFiles"
                + pathSeparator;
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(10 * 1024);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("utf-8");
        File theRTEUploadDir = null;
        try {
            StringBuilder fileName = new StringBuilder();


            String type = "";

            String value = coursezipfile.getOriginalFilename();
            if (value == null || value.equalsIgnoreCase("")) {
                return false;
            }
            if (value.lastIndexOf(".") >= 0) {
                type = value.substring(value.lastIndexOf("."));
            }
            String fieldValue = (new Date()).getTime()+type;
            uploadPathStr = uploadPathStr + courseId;
            theRTEUploadDir = new File(uploadPathStr);
            if (!theRTEUploadDir.isDirectory()) {
                theRTEUploadDir.mkdirs();
            }
            File file = new File(uploadPathStr + pathSeparator, fieldValue);
            if (!file.exists()) {
                file.createNewFile();
            }
            coursezipfile.transferTo(file);
            fileName.append(fieldValue).append("");

            String zipFile = uploadPathStr + pathSeparator + fieldValue;
            ZipFile archive = new ZipFile(zipFile);
            byte[] buffer = new byte[16384];
            for (Enumeration e = archive.entries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (!entry.isDirectory()) {
                    String filename = entry.getName();
                    filename = filename.replace('/', File.separatorChar);
                    filename = PropertiesUtil.getProperty("course.fulladdress")
                            + "CourseImports" + File.separator + courseId
                            + File.separator + filename;
                    File destFile = new File(filename);
                    String parent = destFile.getParent();
                    if (parent != null) {
                        File parentFile = new File(parent);
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                    }
                    InputStream in = archive.getInputStream(entry);
                    OutputStream outStream = new FileOutputStream(filename);
                    int count;
                    while ((count = in.read(buffer)) != -1){
                        outStream.write(buffer, 0, count);
                    }
                    in.close();
                    outStream.close();
                }
            }


            String sequencingFileName = PropertiesUtil.getProperty("course.fulladdress")
                    + "CourseImports/" + courseId + "/sequence.obj";

            File sequencingFile = new File(sequencingFileName);
            FileOutputStream ostream = new FileOutputStream(sequencingFile);
            ObjectOutputStream oos = new ObjectOutputStream(ostream);
            //oos.writeObject(myManifestHandler.getOrgsCopy());
            oos.flush();
            oos.close();

            File uploadFiles[] = theRTEUploadDir.listFiles();
            for (int i = 0; i < uploadFiles.length; i++) {
                uploadFiles[i].delete();
            }
            theRTEUploadDir.delete();


            List<UscLectureFile> lectureFileList = uscLectureFileService.findAllLectureFile(courseId).getData();
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + path + "/";
            Course course = courseService.getCourse(courseId);
            course.setEnterUrl(basePath);
            courseService.updateCourse(course);
            request.setAttribute("courseUrl", basePath);
            request.setAttribute("courseTypeId",request.getParameter("courseTypeId"));
            request.setAttribute("LectureList", lectureFileList);
            request.setAttribute("courseId", courseId);
            request.setAttribute("courseAddress", value);
            request.setAttribute("courseTypeId", request.getParameter("courseTypeId"));
            request.setAttribute("uploadCourseSuccess", "上传课件成功!");
            if(request.getParameter("uploadChapter") != null  && !"".equals(request.getParameter("uploadChapter"))){
                request.setAttribute("uploadChapter", 1);
                Long chapterId = Long.parseLong(request.getParameter("chapterId"));
                request.setAttribute("chapterId", chapterId);
            }
            result = true;
        } catch (Exception e) {
            result = false;
            request.setAttribute("uploadCourseSuccess", "上传课件失败!");
            e.printStackTrace();
        }
        return result;
    }
}
