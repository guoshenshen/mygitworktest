package com.elearning.controller.pub;

import com.elearning.common.FileUtil;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SnowflakeIdWorker;
import com.elearning.util.ExportFileTool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/23 9:01
 */

@Controller
@RequestMapping("excelTool")
public class ExcelToolController {

    private Map<String,String[][]> infoMap=new HashMap<>();

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    @RequestMapping("getTaskId.do")
    @ResponseBody
    public ServiceResponse getTaskId(Integer columnCount ,Integer rowCount){
        try {
            String taskId=idWorker.nextId()+"";
            String[][] tableInfo ;
            tableInfo=new String[columnCount][];
            for(Integer i=0;i<columnCount;i++){
                tableInfo[i]=new String[rowCount];
            }
            infoMap.put(taskId, tableInfo);
            return ServiceResponse.createBySuccess(infoMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResponse.createByError();
    }

    @RequestMapping("removeTempInfo.do")
    @ResponseBody
    public ServiceResponse removeTempInfo(String exportTaskId){
        try {
            if(this.infoMap.containsKey(exportTaskId)){
                this.infoMap.remove(exportTaskId);
            }
            return ServiceResponse.createBySuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServiceResponse.createByError();
    }


    @RequestMapping("tempSaveInfo.do")
    @ResponseBody
    public ServiceResponse tempSaveInfo(String exportTaskId, Integer index, Integer count, HttpServletRequest request){
        try {
            Integer columnCount=0;
            String[][] tableInfo=null;
            if(this.infoMap.containsKey(exportTaskId)){
                tableInfo=this.infoMap.get(exportTaskId);
                columnCount=tableInfo.length;
            }
            Integer k ;
            for(k=0;k<columnCount;k++){
                String[] params=request.getParameterValues("column"+k);
                Integer k1=0;
                for(;k1<count;k1++){
                    tableInfo[k][k1+index]=params[k1];
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("nextIndex",k);
            map.put("exportTaskId",exportTaskId);
            return ServiceResponse.createBySuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResponse.createByError();
    }

    @RequestMapping("exportCurrentPageByTempData.do")
    @ResponseBody
    public ServiceResponse exportCurrentPageByTempData(String exportTaskId,HttpServletRequest request){
        try {
            HSSFWorkbook workBook=new HSSFWorkbook();
            HSSFCellStyle headerCellStyle = workBook.createCellStyle();
            HSSFCellStyle bodyCellStyle = workBook.createCellStyle();
            HSSFFont HeaderFont = workBook.createFont();
            HSSFFont bodyFont=workBook.createFont();
            HeaderFont.setFontHeightInPoints((short) 14);
            bodyFont.setFontHeightInPoints((short)12);
            HeaderFont.setFontName("黑体");
            headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
            bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            bodyCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            bodyCellStyle.setAlignment(HSSFCellStyle.SOLID_FOREGROUND);
            headerCellStyle.setFillForegroundColor(new HSSFColor.GREY_40_PERCENT().getIndex());
            headerCellStyle.setFont(HeaderFont);
            bodyCellStyle.setFont(bodyFont);
            HSSFSheet demoSheet = workBook.createSheet();
            String[][] tableInfo=null;
            if(this.infoMap.containsKey(exportTaskId)){
                tableInfo=this.infoMap.get(exportTaskId);
                this.infoMap.remove(exportTaskId);
            }
            else{
                throw new Exception("未获取待导出的表格信息");
            }

            Integer columnCount=tableInfo.length;
            Integer rowCount=tableInfo[0].length;

            Integer []columnWidth=new Integer[columnCount];
            for(int i=0;i<rowCount;i++){
                HSSFRow excelRow=demoSheet.createRow((short)i);

                for(int j=0;j<columnCount;j++){
                    HSSFCell excelCell=excelRow.createCell(j);

                    if(i==0){
                        excelCell.setCellStyle(headerCellStyle);
                    }
                    else{
                        excelCell.setCellStyle(bodyCellStyle);
                    }
                    excelCell.setCellValue(tableInfo[j][i]);
                    if(columnWidth[j]==null){
                        columnWidth[j]=tableInfo[j][i].getBytes().length;
                    }
                    else{
                        if(tableInfo[j][i].getBytes().length>columnWidth[j]){
                            columnWidth[j]=tableInfo[j][i].getBytes().length;
                        }
                    }
                }
            }
            //列宽度自适应
            for(int m=0;m<columnCount;m++){
                Integer width=(columnWidth[m]*2*256>5000)?(columnWidth[m]*2*256>255*256?255*256:columnWidth[m]*2*256):5000;
                demoSheet.setColumnWidth(m, width);
            }
            String zipFileLink= ExportFileTool.generateZipLinkFile(workBook, request,"systemManage");
            Map<String,Object> map = new HashMap<>();
            map.put("downloadPath",zipFileLink);
            return ServiceResponse.createBySuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResponse.createByError();
    }


    @RequestMapping("defaultExportCurrentPage.do")
    @ResponseBody
    public ServiceResponse defaultExportCurrentPage(Integer columnCount,Integer rowCount,HttpServletRequest request){
        try {
            HSSFWorkbook workBook=new HSSFWorkbook();
            HSSFCellStyle headerCellStyle = workBook.createCellStyle();
            HSSFCellStyle bodyCellStyle = workBook.createCellStyle();
            HSSFFont HeaderFont = workBook.createFont();
            HSSFFont bodyFont=workBook.createFont();
            HeaderFont.setFontHeightInPoints((short) 14);
            bodyFont.setFontHeightInPoints((short)12);
            HeaderFont.setFontName("黑体");
            headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
            bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            bodyCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            bodyCellStyle.setAlignment(HSSFCellStyle.SOLID_FOREGROUND);
            headerCellStyle.setFillForegroundColor(new HSSFColor.GREY_40_PERCENT().getIndex());
            headerCellStyle.setFont(HeaderFont);
            bodyCellStyle.setFont(bodyFont);
            HSSFSheet demoSheet = workBook.createSheet();
            String[][] tableInfo=new String[columnCount][];
            Integer []columnWidth=new Integer[columnCount];

            for(int k=0;k<columnCount;k++){
                tableInfo[k]=request.getParameterValues("column"+k);
            }
            for(int i=0;i<rowCount;i++){
                HSSFRow excelRow=demoSheet.createRow((short)i);
                for(int j=0;j<columnCount;j++){
                    HSSFCell excelCell=excelRow.createCell(j);

                    if(i==0){
                        excelCell.setCellStyle(headerCellStyle);
                    }
                    else{
                        excelCell.setCellStyle(bodyCellStyle);
                    }
                    excelCell.setCellValue(tableInfo[j][i]);
                    if(columnWidth[j]==null){
                        columnWidth[j]=tableInfo[j][i].getBytes().length;
                    }
                    else{
                        if(tableInfo[j][i].getBytes().length>columnWidth[j]){
                            columnWidth[j]=tableInfo[j][i].getBytes().length;
                        }
                    }
                }
            }
            //列宽度自适应
            for(int m=0;m<columnCount;m++){
                Integer width=(columnWidth[m]*2*256>5000)?(columnWidth[m]*2*256>255*256?255*256:columnWidth[m]*2*256):5000;
//				System.out.println(width);
                demoSheet.setColumnWidth(m, width);
//				System.out.println(m);
            }
            String zipFileLink=ExportFileTool.generateZipLinkFile(workBook, request,"systemManage");

            Map<String,Object> map = new HashMap<>();
            map.put("downloadPath",zipFileLink);
            return ServiceResponse.createBySuccess(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ServiceResponse.createByError();
    }



    @RequestMapping("isFileExistFromInternet.do")
    @ResponseBody
    public ServiceResponse isFileExistFromInternet(String urlPath){
        boolean result = FileUtil.isFileExistFromNet(urlPath);
        return ServiceResponse.createBySuccess(result);
    }



}
