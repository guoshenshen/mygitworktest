package com.elearning.service.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.systemManage.TenantMapper;
import com.elearning.pojo.systemManage.Tenant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;


@Service("exportFileService")
public class ExportFileServiceImpl implements IExportFileService{

    //@Autowired
    //private TenantMapper tenantMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse export(HttpServletRequest request) {

        Integer formId = Integer.valueOf(request.getParameter("formId").toString());
        if (request.getSession().getAttribute(formId.toString()) != null && ((List) request.getSession().getAttribute(formId.toString())).size() > 0) {
            List dataList = (List) request.getSession().getAttribute(formId.toString());
            String theWebPath = request.getSession().getServletContext().getRealPath("/");

            //String filePath = exportExcel(theWebPath, dataList, formId);
            String filePath = "ceshi.zip";
            String zipFilePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + filePath;

            return  ServiceResponse.createBySuccess(zipFilePath);
        }
        return ServiceResponse.createByError();
    }

    /*private String exportExcel(String theWebPath, List dataList, int formId) {
        String filePath = "";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("elearning.properties");
        Properties p = new Properties();
        p.load(inputStream);


        int lastpath = theWebPath.lastIndexOf(File.separator);
        theWebPath = theWebPath.substring(0, lastpath);
        lastpath = theWebPath.lastIndexOf(File.separator);
        theWebPath = theWebPath.substring(0, lastpath);

        theWebPath = theWebPath.substring(0, lastpath)
                + p.getProperty("uploadFile.address");
        String downloadPathStr = p.getProperty("uploadFile.fulladdress")
                + "systemManage";

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化当前系统日期
        String fileName = dateFm.format(new java.util.Date()) + ".xls";
        File exportFileDir = new File(downloadPathStr);
        if (!exportFileDir.isDirectory()) {
            exportFileDir.mkdirs();
        }
        File exportFile = new File(downloadPathStr + File.separator + fileName);
        if (!exportFile.exists()) {
            try {
                exportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(exportFile);

            // 创建工作本
            HSSFWorkbook demoWorkBook = new HSSFWorkbook();

            this.baseInfoSheetCreate(demoWorkBook, dataList, formId); // 培训计划基本信息sheet创建
            demoWorkBook.write(os);
            // 将已上传的文档压缩
            File[] fileList = new File[] { exportFile };
            File zipFile = new File(exportFile.getAbsolutePath().substring(0,
                    exportFile.getAbsolutePath().lastIndexOf("."))
                    + ".zip");
            ZipUtil.zip(fileList, zipFile);
            filePath =  p.getProperty("uploadFile.address") + "systemManage/"
                    + fileName.substring(0, fileName.lastIndexOf(".")) + ".zip";

            return  filePath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;

    }*/




}
