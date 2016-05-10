package com.dreamy.ipcool.controllers;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.params.ImageUploadParams;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 下午12:29
 */
public class UploadController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/upload/img")
    public void uploadImag(HttpServletResponse response, @RequestParam(value = "upfile", required = false) MultipartFile upfile,
                           @RequestParam(required = false) Integer width,
                           @RequestParam(required = false) Integer height,
                           @RequestParam(required = false) Integer size,
                           @RequestParam(required = false) boolean check) throws IOException {

        ImageUploadParams param = new ImageUploadParams();
        param.setImgFile(upfile);
        param.setHeight(height);
        param.setWidth(width);
        param.setSize(size);
        InterfaceBean bean = new InterfaceBean();
        Map<String, Object> map = new HashMap<String, Object>();

        response.setContentType("text/html");

        if (upfile == null) {
            bean.failure(1, "图片文件不存在");
            response.getWriter().write(JsonUtils.toString(bean));
            return;
        }
//        if (!IMG_TYPE.contains(upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase())) {
//            bean.failure(1, "图片支持jpg和png格式");
//            response.getWriter().write(JsonUtils.toString(bean));
//            return;
//        }
        try {
            String tmpFileName = UUID.randomUUID().toString() + upfile.getOriginalFilename();
            String fileName = "" + tmpFileName;
            File tmp = new File(fileName);
            IOUtils.copy(upfile.getInputStream(), new FileOutputStream(tmp));

            BufferedImage oldImage = ImageIO.read(tmp);
            if (check) {//图片大小校验
                int realWidth = oldImage.getWidth();
                int realHeight = oldImage.getHeight();
                if (size != realWidth || size != realHeight) {
                    bean.failure(1, "请上传" + size + "*" + size + "大小的图片");
                    response.getWriter().write(JsonUtils.toString(bean));
                    return;
                }
            }


            String url = "";
            if (StringUtils.isEmpty(url)) {
                bean.failure(1, "图片内容格式不正确");
                response.getWriter().write(JsonUtils.toString(bean));
                return;
            }
            map.put("url", url);
            map.put("fileName", tmpFileName);
            bean.success();
            bean.setData(map);
        } catch (Exception e) {
            LOG.error("uploadImageOccurError", e);
            bean.failure(ErrorCodeEnums.image_upload_failed.getErrorCode(), "图片上传出现异常");
        }

        response.getWriter().write(JsonUtils.toString(bean));

    }
}
