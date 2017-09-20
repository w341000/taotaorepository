package com.taotao.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
/**
 * �ϴ�ͼƬ����controller
 */
@Controller
public class PictureController {
	@Resource
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	public @ResponseBody String upload(MultipartFile uploadFile){
		Map map = pictureService.uploatPicture(uploadFile);
		//Ϊ��֤���ܵļ�����,ֱ�ӷ���json�ַ���
		String json = JsonUtils.objectToJson(map);
		return json;
	}

}
