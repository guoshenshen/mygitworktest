package com.elearning.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/23 16:10
 */
public class ZipUtil {

    private static final Log log = LogFactory.getLog(ZipUtil.class);

    /**
     * 打包文件
     * @param files 文件或文件夹的集合
     * @param out 输出的zip文件
     */
    public static void zip(File[] files, File out) {
        if (files != null) {
            Map<String, File> map = new HashMap<String, File>();
            for (File f : files) {
                list(f, null, map);
            }
            if (!map.isEmpty()) {
                try {
                    ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(out);
                    for (Map.Entry<String, File> entry : map.entrySet()) {
                        File file = entry.getValue();
                        ZipArchiveEntry zae = new ZipArchiveEntry(file, entry.getKey());
                        zaos.putArchiveEntry(zae);
                        InputStream is = new FileInputStream(file);
                        byte[] b = new byte[1024 * 5];
                        int i = -1;
                        while ((i = is.read(b)) != -1) {
                            zaos.write(b, 0, i);
                        }
                        is.close();
                        zaos.closeArchiveEntry();
                    }
                    zaos.finish();
                    zaos.close();
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * 将filePath[]数组下的文件路径对应的文件压缩到名称为zipFileName的压缩包中，返回压缩文件的完整路径
     * @param filePaths：存储待压缩文件路径信息
     * @param zipFileName： 压缩文件名称
     * @return
     */
    public static String createZip(List<String> filePaths, String zipFileName) throws IOException {
        String zipFilePath=null;
        List<File> files=new ArrayList<File>();
        if(filePaths==null||filePaths.size()<=0||zipFileName==null)
            return zipFilePath;
        else{
            for(String filePath:filePaths){
                File file=new File(filePath);
                if(file.isFile()){
                    files.add(file);
                }
            }
            File[] fileArray=new File[files.size()];
            int i=0;
            for(File file:files){
                fileArray[i++]=file;
            }


            try {

                String zipFilePathFolder=PropertiesUtil.getProperty("uploadFile.fulladdress")+"systemManage";
                File exportFileDir=new File(zipFilePathFolder);
                if(!exportFileDir.isDirectory()){
                    exportFileDir.mkdir();
                }
                File exportFile=new File(zipFilePathFolder+File.separator+zipFileName);
                if(!exportFile.exists()){
                    exportFile.createNewFile();
                }
                ZipUtil.zip(fileArray, exportFile);
                zipFilePath=exportFile.getAbsolutePath();
                return zipFilePath;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }
        }

    }

    private static void list(File f, String parent, Map<String, File> map) {
        String name = f.getName();
        if (parent != null) {
            name = parent + "/" + name;//设置在zip包里的相对路径
        }
        if (f.isFile()) {
            map.put(name, f);
        } else if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                list(file, name, map);
            }
        }
    }
}
