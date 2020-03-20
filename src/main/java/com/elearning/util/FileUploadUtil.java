package com.elearning.util;

import com.elearning.common.FileTool;
import com.elearning.common.ImageTool;
import com.elearning.common.UploadFolder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 14:33
 */
public class FileUploadUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

    public static String uploadFile(HttpServletRequest request,String pic_height, String pic_width) throws IOException {
        String result = null;
        String filePath ;
        String uploadTypeStr=request.getParameter("uploadType");
        UploadFolder uploadType= UploadFolder.findByCode(Short.parseShort(uploadTypeStr));
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName ;
        String type = "";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            if (fileName.lastIndexOf(".") >= 0){
                type = fileName.substring(fileName.lastIndexOf("."));
            }
            fileName = String.valueOf(System.currentTimeMillis()) + type;
            filePath = PropertiesUtil.getProperty("uploadFile.fulladdress") +
                    uploadType.getFolder().replace("/", File.separator);
            File dest = new File(filePath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            File uploadFile = new File(filePath+ File.separator, fileName);
            if (uploadFile.exists()) {
                uploadFile.delete();
            }
            try {
                log.info("start upload file: " + fileName);
                FileCopyUtils.copy(mf.getBytes(), uploadFile);
            } catch (IOException e) {
                log.info("upload failed. filename: " + fileName + e.getMessage());
                return null;
            }
            result = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
                    PropertiesUtil.getProperty("uploadFile.address")+ uploadType.getFolder()+File.separator+fileName;
            if(pic_height != null && pic_width != null){
                InputStream is = new FileInputStream(uploadFile);
                BufferedImage bufferImage = ImageIO.read(is);
                String outSrc= FileTool.convertFromURLToPhysicalPath(result);
                ImageTool.compressBySize(bufferImage, outSrc, Integer.parseInt(pic_height), Integer.parseInt(pic_width));
            }
        }

        return result;
    }


    public static Map<String,File> uploadFile(HttpServletRequest request,String rootPath,String urlPath,String savePath){
        Map<String,File> resultMap=new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName ;
        String type = "";
        String revisePath=savePath.replace("/", File.separator);
        String uploadPathStr =rootPath+revisePath;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            if (fileName.lastIndexOf(".") >= 0){
                type = fileName.substring(fileName.lastIndexOf("."));
            }
            String name = String.valueOf(System.currentTimeMillis());
            fileName = name + type;
            File theRTEUploadDir = new File( uploadPathStr );
            if(!theRTEUploadDir.isDirectory()){
                theRTEUploadDir.mkdirs();
            }
            File file = new File(uploadPathStr + File.separator, fileName);
            try {
                FileCopyUtils.copy(mf.getBytes(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String savedFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+urlPath + savePath+"/"+fileName;
            savedFilePath=savedFilePath.replaceAll("\\\\", "/");
            resultMap.put(savedFilePath, file);
        }
/*        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<File> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
        }
        Iterator<File> it = fileList.iterator();
        String name ;

        while (it.hasNext()){
            FileItem item = (FileItem) it.next();
            if (!item.isFormField()){
                fileName = item.getName();
                if (fileName == null || fileName.trim().equals("")){
                    continue;
                }
                if (fileName.lastIndexOf(".") >= 0){
                    type = fileName.substring(fileName.lastIndexOf("."));
                }
                name = String.valueOf(System.currentTimeMillis());
                fileName = name + type;
                File theRTEUploadDir = new java.io.File( uploadPathStr );

                if(!theRTEUploadDir.isDirectory()){
                    theRTEUploadDir.mkdirs();
                }
                File file = new File(uploadPathStr + File.separator, fileName);
                try {
                    item.write(file);
                    String savedFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+urlPath + savePath+"/"+fileName;
                    savedFilePath=savedFilePath.replaceAll("\\\\", "/");
                    resultMap.put(savedFilePath, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
        return resultMap;
    }
    public static String zipFile(HttpServletRequest request,String rootPath,String urlPath,String zipSavePath,String zipFileName,File[] fileList){
        String zipPath;
        String reviseSavePath=(rootPath+zipSavePath).replace("/", File.separator);
        File theRTEUploadDir = new File( reviseSavePath);
        if(!theRTEUploadDir.isDirectory()){
            theRTEUploadDir.mkdirs();
        }
        File zipFile = new File(reviseSavePath+File.separator+zipFileName);
        ZipUtil.zip(fileList, zipFile);
        String savedFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+urlPath+zipSavePath+File.separator+zipFileName;
        zipPath=savedFilePath.replaceAll("\\\\", "/");
        return zipPath;
    }
}
