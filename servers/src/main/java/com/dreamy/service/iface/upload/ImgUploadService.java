package com.dreamy.service.iface.upload;

import com.aliyun.oss.OSSClient;
import com.dreamy.beans.InterfaceBean;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 下午5:31
 */
public interface ImgUploadService {

    /**
     * 获取阿里云oss的client
     * @return
     */
    OSSClient getOssClient();


    /**
     *
     * @param file
     * @return
     */
    String uploadImage(MultipartFile upfile, int x, int y, int width, int height);


    /**
     *
     * @param width
     * @param height
     * @param size
     * @param image
     * @return
     */
    InterfaceBean propertyCheck(Integer width,Integer height,Integer size,MultipartFile image);

}
