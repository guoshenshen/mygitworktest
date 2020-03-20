package com.elearning.common;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author zq@cnic.cn
 * 使用Google开源Java图片处理类库Thumbnailator
 *
 */
public class ImageTool {
	
	
	public static BufferedImage ImageFormUrlToBuffer(URL url){
		BufferedImage image = null;  
		try {
			image=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	
	public static BufferedImage ImagefromUrlToBuffer(String urlPath){
		BufferedImage image = null;  
		try {
			URL url = new URL(urlPath); 
			image=ImageTool.ImageFormUrlToBuffer(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
        return image;
	}
	
	
	public static Boolean compressBySize(String inSrc, String outSrc, Integer height, Integer width){
		boolean flag = false;
		try {
			Thumbnails.of(inSrc).forceSize(width, height).toFile(outSrc);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static Boolean compressBySize(BufferedImage image,String outSrc,Integer height,Integer width){
		boolean flag = false;
		try {
			Thumbnails.of(image).forceSize(width, height).toFile(outSrc);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	
	public static Boolean compressByScale(String inSrc, String outSrc, double percent){  
        boolean flag = false;  
   
        try {  
        	Thumbnails.Builder<File> builder = Thumbnails.of(inSrc);
            builder.scale(percent);  
            builder.toFile(outSrc);  
            flag = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
	
	public static Boolean compressByScale(BufferedImage image,String outSrc,double percent){
		boolean flag = false;
		try {
			Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(image);
		    builder.scale(percent);  
		    builder.toFile(outSrc);  
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
