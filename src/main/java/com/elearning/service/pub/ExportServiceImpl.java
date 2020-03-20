package com.elearning.service.pub;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.dao.coursemanage.ItemInfoMapper;
import com.elearning.dao.pub.CourseMapper;
import com.elearning.dao.pub.DDictionaryMapper;
import com.elearning.pojo.coursemanage.ItemInfo;
import com.elearning.pojo.pub.Course;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.util.ZipUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/23 15:58
 */

@Service("exportService")
public class ExportServiceImpl implements IExportService{


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private DDictionaryMapper dDictionaryMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public ServiceResponse exportCourseInfo(String[] selectbox, HttpServletRequest request) throws IOException {

        String theWebPath = request.getSession().getServletContext().getRealPath( "/");
        int lastpath = theWebPath.lastIndexOf(File.separator);
        theWebPath = theWebPath.substring(0,lastpath);
        lastpath = theWebPath.lastIndexOf(File.separator);
        theWebPath = theWebPath.substring(0,lastpath);
        theWebPath = theWebPath.substring(0,lastpath)+ PropertiesUtil.getProperty("uploadFile.address");
        String downloadPathStr = PropertiesUtil.getProperty("uploadFile.fulladdress")+ "coursemanage";
        String fileName = "courseInfo.xls";
        int k= fileName.indexOf(".");
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); //格式化当前系统日期
        String strDate = dateFm.format(new java.util.Date());
        String name = fileName.substring(0, k) +  strDate ;
        String type = fileName.substring(k + 1);
        fileName = name + "." + type;
        File exportFileDir = new File(downloadPathStr);
        if (!exportFileDir.isDirectory() ){
            exportFileDir.mkdirs();
        }
        File exportFile = new File(downloadPathStr + File.separator+ fileName);
        if (!exportFile.exists()) {
            exportFile.createNewFile();
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(exportFile);

            //创建工作本
            HSSFWorkbook demoWorkBook = new HSSFWorkbook();
            this.baseInfoSheetCreate(demoWorkBook,selectbox);                    //课件基本信息sheet创建
            this.SCOInfoSheetCreate(demoWorkBook, selectbox);					 //SCO基本信息sheet创建
            demoWorkBook.write(os);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //将已上传的文档压缩
        File[] fileList = new File[]{exportFile};
        File zipFile = new File(exportFile.getAbsolutePath().substring(0, exportFile.getAbsolutePath().lastIndexOf("."))+".zip");
        ZipUtil.zip(fileList, zipFile);
        String zipFilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
                PropertiesUtil.getProperty("uploadFile.address")+"coursemanage/"+name+".zip";
        return ServiceResponse.createBySuccess(zipFilePath);
    }

    private void SCOInfoSheetCreate(HSSFWorkbook demoWorkBook, String[] ids) {

        //创建表
        HSSFSheet demoSheet = demoWorkBook.createSheet();
        //表头的单元格个数目
        short cellNumber = 8;
        //数据库表的列数
        int rowNumber = ids.length;
        //创建表头
        //表头
        String[] tableHeader ={"SCOID","标识","类型","标题","URL","序号","层级","课程ID"};
        HSSFHeader header = demoSheet.getHeader();
        header.setCenter("课程信息导出");
        HSSFRow headerRow = demoSheet.createRow((short) 0);
        for(int i = 0;i < cellNumber;i++)
        {
            HSSFCell headerCell = headerRow.createCell((short) i);
            headerCell.setCellValue(tableHeader[i]);
        }

        //创建整个excel
        for(int j=1,rowid = 1;j<rowNumber+1;j++)
        {
            List<ItemInfo> itemList = itemInfoMapper.findItemInfo(Long.valueOf(ids[j-1]));
            for(ItemInfo item:itemList){
                //创建行
                //创建第rowIndex行
                HSSFRow row = demoSheet.createRow((short) rowid);
                HSSFCell cell0 = row.createCell(0);
                HSSFCell cell1= row.createCell(1);
                HSSFCell cell2 = row.createCell(2);
                HSSFCell cell3 = row.createCell(3);
                HSSFCell cell4 = row.createCell(4);
                HSSFCell cell5 = row.createCell(5);
                HSSFCell cell6 = row.createCell(6);
                HSSFCell cell7 = row.createCell(7);

                cell0.setCellValue(String.valueOf(item.getItemId()));
                if(null != item.getIdentifier())
                    cell1.setCellValue(item.getIdentifier());
                if(null != item.getType())
                    cell2.setCellValue(item.getType());
                if(null != item.getTitle())
                    cell3.setCellValue(item.getTitle());
                if(null != item.getLaunch())
                    cell4.setCellValue(item.getLaunch());
                if(null != item.getSequence())
                    cell5.setCellValue(item.getSequence().toString());
                if(null != item.getTheLevel())
                    cell6.setCellValue(item.getTheLevel().toString());
                cell7.setCellValue(ids[j-1]);

                rowid++;
            }
        }

        //导出表格
        demoSheet.setGridsPrinted(true);
        HSSFFooter footer = demoSheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " +
                HSSFFooter.numPages());


    }

    private void baseInfoSheetCreate(HSSFWorkbook demoWorkBook, String[] ids) {

        //创建表
        HSSFSheet demoSheet = demoWorkBook.createSheet();
        //表头的单元格个数目
        short cellNumber = 19;
        //数据库表的列数
        int rowNumber = ids.length;
        //创建表头
        //表头
        String[] tableHeader ={"课程ID","课程编号","课程名称","创建日期","创建人","主讲人","是否纳入教师库","学时","专业领域","科目分类","资源分类",
                "课件类别","关键词","适用对象","课程适用岗位类型","课程费用","公开范围","主要内容","备注"};
        HSSFHeader header = demoSheet.getHeader();
        header.setCenter("课程信息导出");
        HSSFRow headerRow = demoSheet.createRow((short) 0);
        for(int i = 0;i < cellNumber;i++){
            HSSFCell headerCell = headerRow.createCell((short) i);
            headerCell.setCellValue(tableHeader[i]);
        }

        //创建整个excel
        for(int j=1;j<rowNumber+1;j++){

            //创建行
            //创建第rowIndex行
            HSSFRow row = demoSheet.createRow((short) j);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1= row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            HSSFCell cell3 = row.createCell(3);
            HSSFCell cell4 = row.createCell(4);
            HSSFCell cell5 = row.createCell(5);
            HSSFCell cell6 = row.createCell(6);
            HSSFCell cell7 = row.createCell(7);
            HSSFCell cell8 = row.createCell(8);
            HSSFCell cell9= row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            HSSFCell cell12 = row.createCell(12);
            HSSFCell cell13 = row.createCell(13);
            HSSFCell cell14 = row.createCell(14);
            HSSFCell cell15 = row.createCell(15);
            HSSFCell cell16 = row.createCell(16);
            HSSFCell cell17 = row.createCell(17);
            HSSFCell cell18 = row.createCell(18);
            Long courseId =Long.valueOf(ids[j-1]);
            Course _couseBaseInfo = courseMapper.selectByPrimaryKey(courseId);
            if(null != _couseBaseInfo.getCourseId())
                cell0.setCellValue(_couseBaseInfo.getCourseId().toString());
            if(null != _couseBaseInfo.getCourseNo())
                cell1.setCellValue(_couseBaseInfo.getCourseNo());
            if(null != _couseBaseInfo.getCourseName())
                cell2.setCellValue(_couseBaseInfo.getCourseName());
            if(null != _couseBaseInfo.getCreateDate()) {
                cell3.setCellValue(DateTimeUtil.dateToStr(_couseBaseInfo.getCreateDate()));
            }
            if(null != _couseBaseInfo.getMaker())
                cell4.setCellValue(_couseBaseInfo.getMaker());
            if(null != _couseBaseInfo.getCreator())
                cell5.setCellValue(_couseBaseInfo.getCreator());
            if(null != _couseBaseInfo.getHasTeacher())
                cell6.setCellValue(_couseBaseInfo.getHasTeacher()==1?"是":"否");
            if(null != _couseBaseInfo.getClassHour())
                cell7.setCellValue( _couseBaseInfo.getClassHour());
//   		  专业领域
            if(null != courseTypeMapper.findByCourseId(Long.valueOf(ids[j-1])))
                cell8.setCellValue(courseTypeMapper.findByCourseId(Long.valueOf(ids[j-1])).getCourseTypeName());
//   		  科目分类
            if(null != _couseBaseInfo.getCategory())
                cell9.setCellValue(dDictionaryMapper.getDDictionaryMapperByCode(_couseBaseInfo.getCategory().toString(), "4020").getName());
//   		  资源分类
            if(null != _couseBaseInfo.getClassfication())
                cell10.setCellValue(dDictionaryMapper.getDDictionaryMapperByCode(_couseBaseInfo.getClassfication().toString(), "4000").getName());
//   		  课件类别
            if(null != _couseBaseInfo.getSliceType())	{
                if(_couseBaseInfo.getSliceType().equals("1")){
                    cell11.setCellValue("SCORM课件");
                }else if(_couseBaseInfo.getSliceType().equals("2")){
                    cell11.setCellValue("单一网址课件");
                }else if(_couseBaseInfo.getSliceType().equals("3")){
                    cell11.setCellValue("单一视频/文档课件");
                }
            }
//   		 关键词
            if(null != _couseBaseInfo.getKeyWords())
                cell12.setCellValue(_couseBaseInfo.getKeyWords());
//   		  适用对象
            if(null != _couseBaseInfo.getSuitableObject())
                cell13.setCellValue(_couseBaseInfo.getSuitableObject());
//   		适用岗位类型
            if(null != _couseBaseInfo.getStationId()){
                StringBuffer stationList = new StringBuffer();
                if(_couseBaseInfo.getStationId().indexOf("2901")!=-1)
                    stationList.append(Constants.GANGWEI001).append(";");
                if(_couseBaseInfo.getStationId().indexOf("2902")!=-1)
                    stationList.append(Constants.GANGWEI002).append(";");
                if(_couseBaseInfo.getStationId().indexOf("2903")!=-1)
                    stationList.append(Constants.GANGWEI003).append(";");
                if(_couseBaseInfo.getStationId().indexOf("2904")!=-1)
                    stationList.append(Constants.GANGWEI004).append(";");
                if(_couseBaseInfo.getStationId().indexOf("2905")!=-1)
                    stationList.append(Constants.GANGWEI005).append(";");
                cell14.setCellValue(stationList.toString());
            }
//  		 课程费用
            if(null != _couseBaseInfo.getFee())
                cell15.setCellValue(_couseBaseInfo.getFee());

//  		  if(null != _couseBaseInfo.getIsOpenCourse())
//         		   cell12.setCellValue(_couseBaseInfo.getIsOpenCourse().equals("1")?"公开课":"非公开课");

//  		公开范围
            if(null != _couseBaseInfo.getOpenScope()){
                if(_couseBaseInfo.getOpenScope().intValue()==2201){
                    cell16.setCellValue("不公开");
                }else if(_couseBaseInfo.getOpenScope().intValue()==2202){
                    cell16.setCellValue("本单位公开");
                }else if(_couseBaseInfo.getOpenScope().intValue()==2203){
                    cell16.setCellValue("机构内公开");
                }else if(_couseBaseInfo.getOpenScope().intValue()==2204){
                    cell16.setCellValue("全院公开");
                }else if(_couseBaseInfo.getOpenScope().intValue()==2205){
                    cell16.setCellValue("完全公开");
                }
            }
//  		主要内容
            if(null != _couseBaseInfo.getContent())
                cell17.setCellValue(ToolsUtil.stripHtml(_couseBaseInfo.getContent()));
//  		  备注
            if(null != _couseBaseInfo.getRemark())
                cell18.setCellValue(_couseBaseInfo.getRemark());

        }
        //导出表格
        demoSheet.setGridsPrinted(true);
        HSSFFooter footer = demoSheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " +
                HSSFFooter.numPages());


    }
}
