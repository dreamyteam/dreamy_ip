package com.dreamy.ipcool.controllers;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.params.ImageUploadParams;
import com.dreamy.service.iface.upload.ImgUploadService;
import com.dreamy.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 下午12:29
 */
@Controller
public class UploadController extends IpcoolController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private ImgUploadService imgUploadService;

    @RequestMapping("/upload/img")
    @ResponseBody
    public void uploadImage(HttpServletResponse response,
                            @RequestParam(value = "file", required = false) MultipartFile upfile,
                            @RequestParam(required = false) int x,
                            @RequestParam(required = false) int y,
                            @RequestParam(required = false) int width,
                            @RequestParam(required = false) int height,
                            @RequestParam(required = false) Integer size) throws IOException {

        InterfaceBean bean = imgUploadService.propertyCheck(width, height, size, upfile);
        if (bean.getErrorCode() == 0) {
            Map<String, String> map = new HashMap<String, String>();
            String url = imgUploadService.uploadImage(upfile, x, y, width, height);
            map.put("image_url", "http://"+url);
            map.put("url", "http://"+url);
            map.put("fileName", "temp.jpg");
            bean.setData(map);
        }


        interfaceReturn(response, JsonUtils.toString(bean), "");

    }

}
