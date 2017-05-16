package cn.it.shop.utils.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;
import cn.it.shop.utils.FileUpload;
@Component("fileUpload")
public class FileUploadUtil implements FileUpload {

	private String filePath;
	@Value("#{prop.filePath}")
	public void setFilePath(String filePath){
		this.filePath=filePath;
		System.out.println(filePath);
	}
	
	public String getFileExt(String filename){
		return FilenameUtils.getExtension(filename);
	}
	
	public String newFileName(String fileName){
		String ext=getFileExt(fileName);
		return UUID.randomUUID().toString()+"."+ext;
		//生成UUID随机数作为文件的名称
		//这个一定不会重复吗？think
	}
	
	public String uploadFile(FileImage fileImage) {
		String pic=newFileName(fileImage.getFileName());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			fileImage.getFile().delete();
		}
	}
	@Value("#{prop.bankImagePath}")
	private String bankImagePath;
	public String[] getBankImage(){
		String[] list=new File(bankImagePath).list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				System.out.println("dir:"+dir+",name:"+name);
				return name.endsWith(".gif");
			}
			
		});
		return list;
	}

}
