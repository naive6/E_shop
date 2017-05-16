package cn.it.shop.utils;

import cn.it.shop.model.FileImage;

public interface FileUpload {
	public abstract String uploadFile(FileImage fileImage);
	public String[] getBankImage();
}
