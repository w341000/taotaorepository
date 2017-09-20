package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
//图片上传服务service
@Service
public class PictureServiceImpl implements PictureService {
	//@Value可以将spring读取的properties信息注入
	@Value(value = "${FTP_HOST}")
	private String FTP_HOST;
	@Value(value = "${FTP_PORT}")
	private Integer FTP_PORT;
	@Value(value = "${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value(value = "${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value(value = "${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value(value = "${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map uploatPicture(MultipartFile file)  {
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			//生成一个新的文件名
			//取文件原始名
			String oldName = file.getOriginalFilename();
			String ext = oldName.substring(oldName.lastIndexOf("."));
			//生成文件名
			String newName = IDUtils.genImageName();
			newName=newName+ext;
			String imagePath=new DateTime().toString("/yyyy/MM/dd");
			//上传文件
			boolean result=FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH,imagePath , newName, file.getInputStream());
			if(!result){
				map.put("error", 1);
				map.put("message", "文件上传失败");
				return map;
			}
			map.put("error", 0);
			map.put("url", IMAGE_BASE_URL+imagePath+"/"+newName);
			return map;
		}catch (Exception e) {
			map.put("error", 1);
			map.put("message", "文件上传发生异常");
			return map;
		}
	}

}
