package com.elearning.common.excel;

import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.pub.IEosorgTOrganizationService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class ExcelOperate {

	private static IEosorgTOrganizationService eosorgTOrganizationService;

	/**
	 * @param
	 * @throws IOException
	 * @throws
	 */
	public static void operateOrgExcel() throws IOException {
		// TODO Auto-generated method stub

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}

		/**
		 * 获取到webroot里面的数据
		 */
		java.net.URL url2 = classLoader.getResource("");
		String ROOT_CLASS_PATH = url2.getPath() + "/";
		File rootFile = new File(ROOT_CLASS_PATH);
		String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
		File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";
		// 这里 SERVLET_CONTEXT_PATH 就是WebRoot的路径

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(
					SERVLET_CONTEXT_PATH + "/modelExcel/test.xlsx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		List<String> orgCode = new ArrayList<String>();
		List<String> orgName = new ArrayList<String>();
		List<String> parentOrgCode = new ArrayList<String>(); // Sheet1
		List<String> parentOrgName = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<Row> rit = (Iterator<Row>) sheet.rowIterator(); rit
				.hasNext();) {
			Row row = rit.next();

			if (null != row.getCell(0)) {
				orgCode.add(row.getCell(0).toString());
			}
			if (null != row.getCell(1)) {
				String[] orgTree = row.getCell(1).toString().split(" - ");
				orgName.add(orgTree[orgTree.length - 1]);
				if (orgTree.length == 1) {
					parentOrgCode.add("");
					parentOrgName.add(null);
				} else {
					List<EosorgTOrganization> parentOrgList = eosorgTOrganizationService.getListByOrgNameAndParentOrgIdAndStatus(orgTree[orgTree.length - 2], null, true);
					if(parentOrgList.size() == 0){
						parentOrgCode.add(null);
						parentOrgName.add(orgTree[orgTree.length - 2]);
					} else{
						parentOrgCode.add(parentOrgList.get(0).getOrgCode());
						parentOrgName.add(null);
					}
				}
				map.put(orgTree[orgTree.length - 1], row.getCell(0).toString());
			}
		}
		
		for(int i = 0; i < parentOrgCode.size(); i++){
			if(parentOrgCode.get(i) == null){
				parentOrgCode.set(i, map.get(parentOrgName.get(i)));
			}
		}

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet sheetNew = wb.createSheet("test");
		String[] XMLtitle = { "单位编号", "单位名称", "上级单位编号", "是否独立组织培训机构",
				"是否虚拟组织机构" };
		XSSFRow row0 = sheetNew.createRow(0);
		for (int i = 0; i < XMLtitle.length; i++) {
			XSSFCell cell = row0.createCell((short) i);
			cell.setCellValue(new XSSFRichTextString(XMLtitle[i]));
		}
		List<List<String>> title = new ArrayList<List<String>>();
		List<String> is = new ArrayList<String>();
		is.add("否");
		is.add("是");
		title.add(orgCode);
		title.add(orgName);
		title.add(parentOrgCode);
		title.add(is);
		title.add(is);
		for (int j = 0; j < orgCode.size(); j++) {
			XSSFRow row = sheetNew.createRow(j + 1);
			for (int i = 0; i < 3; i++) {
				XSSFCell cell = row.createCell((short) i);
				cell.setCellValue(new XSSFRichTextString(title.get(i).get(j)));
			}
			XSSFCell cell4 = row.createCell((short) 3);
			cell4.setCellValue(new XSSFRichTextString(title.get(3).get(0)));
			XSSFCell cell5 = row.createCell((short) 4);
			cell5.setCellValue(new XSSFRichTextString(title.get(4).get(0)));
		}

		FileOutputStream fOut = new FileOutputStream(new File(
				SERVLET_CONTEXT_PATH + "/modelExcel/test01.xlsx"));
		// 将创建的内容写到指定的Excel文件中
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}

	public static void operateOperatorExcel() throws IOException {
		// TODO Auto-generated method stub

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}

		/**
		 * 获取到webroot里面的数据
		 */
		java.net.URL url2 = classLoader.getResource("");
		String ROOT_CLASS_PATH = url2.getPath() + "/";
		File rootFile = new File(ROOT_CLASS_PATH);
		String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
		File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";
		// 这里 SERVLET_CONTEXT_PATH 就是WebRoot的路径

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(
					SERVLET_CONTEXT_PATH + "/modelExcel/testOperator.xlsx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("Sheet1");// �xȡ��һ��������
		List<String> operatorCode = new ArrayList<String>();
		List<String> operatorName = new ArrayList<String>();
		List<String> OrgCode = new ArrayList<String>();
		List<String> operatorJob = new ArrayList<String>(); // Sheet1
		List<String> tel = new ArrayList<String>();
		List<String> mobile = new ArrayList<String>();
		List<String> email = new ArrayList<String>();
		List<String> userId = new ArrayList<String>();
		for (Iterator<Row> rit = (Iterator<Row>) sheet.rowIterator(); rit
				.hasNext();) {
			Row row = rit.next();
			if (null != row.getCell(0) && row.getCell(0).toString().trim().equals("名称")){
				continue;
			}
			if (null != row.getCell(0)) {
				operatorCode.add(row.getCell(0).toString());
			}else{
				operatorCode.add("");
			}

			if (null != row.getCell(1)) {
				operatorName.add(row.getCell(1).toString());
			}else{
				operatorName.add("");
			}

			if (null != row.getCell(2)) {
				String[] orgTree = row.getCell(2).toString().split(" - ");
				OrgCode.add(orgTree[orgTree.length - 1]);
			}else{
				OrgCode.add("");
			}

			if (null != row.getCell(3)) {
				operatorJob.add(row.getCell(3).toString());
			}else{
				operatorJob.add("");
			}

			if (null != row.getCell(4)) {
				tel.add(row.getCell(4).toString());
			}else{
				tel.add("");
			}

			if (null != row.getCell(5)) {
				mobile.add(row.getCell(5).toString());
			}else{
				mobile.add("");
			}

			if (null != row.getCell(6)) {
				email.add(row.getCell(6).toString());
			}else{
				email.add("");
			}

			if (null != row.getCell(7)) {
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				userId.add(row.getCell(7).getStringCellValue());
			}else{
				userId.add("");
			}
		}

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet sheetNew = wb.createSheet("test");
		String[] XMLtitle = { "员工姓名", "单位名称", "员工ARP编号", "员工登陆名(邮箱)",
				"性别", "职务", "所学专业", "学位", "出生日期", "入职时间", "身份证号", "联系电话", "电子邮件" };
		
		XSSFRow row0 = sheetNew.createRow(0);
		for (int i = 0; i < XMLtitle.length; i++) {
			XSSFCell cell = row0.createCell((short) i);
			cell.setCellValue(new XSSFRichTextString(XMLtitle[i]));
		}
		List<List<String>> title = new ArrayList<List<String>>();
		title.add(operatorName);
		title.add(OrgCode);
		title.add(operatorCode);
		title.add(userId);
		title.add(null);
		title.add(operatorJob);
		title.add(null);
		title.add(null);
		title.add(null);
		title.add(null);
		title.add(null);
		title.add(mobile);
		title.add(email);
		
		XSSFCellStyle cellStyle2 = wb.createCellStyle();

		XSSFDataFormat format = wb.createDataFormat();

		cellStyle2.setDataFormat(format.getFormat("@"));
		
		for (int j = 1; j < operatorCode.size(); j++) {
			
			XSSFRow row = sheetNew.createRow(j);
			XSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(cellStyle2);
			cell0.setCellValue(new XSSFRichTextString(title.get(0).get(j)));
			XSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(cellStyle2);
			cell1.setCellValue(new XSSFRichTextString(title.get(1).get(j)));
			XSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(cellStyle2);
			cell2.setCellValue(new XSSFRichTextString(title.get(2).get(j)));
			XSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(cellStyle2);
			cell3.setCellValue(new XSSFRichTextString(title.get(3).get(j)));
			XSSFCell cell4 = row.createCell((short) 5);
			cell4.setCellStyle(cellStyle2);
			cell4.setCellValue(new XSSFRichTextString(title.get(5).get(j)));
			XSSFCell cell6 = row.createCell((short) 11);
			cell6.setCellStyle(cellStyle2);
			cell6.setCellValue(new XSSFRichTextString(title.get(11).get(j)));
			XSSFCell cell7 = row.createCell((short) 12);
			cell7.setCellStyle(cellStyle2);
			cell7.setCellValue(new XSSFRichTextString(title.get(12).get(j)));
		}

		FileOutputStream fOut = new FileOutputStream(new File(
				SERVLET_CONTEXT_PATH + "/modelExcel/testOperator01.xlsx"));
		// 将创建的内容写到指定的Excel文件中
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}
}
