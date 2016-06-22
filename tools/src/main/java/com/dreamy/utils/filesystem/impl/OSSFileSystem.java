package com.dreamy.utils.filesystem.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.filesystem.FileSystemService;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 阿里云oss服务器
 * Created by wangyongxing on 16/6/21.
 */
public class OSSFileSystem implements FileSystemService {
    private static String BUCKETNAME = "ipcool";
    private static OSSClient client = null;
    private String accessKeyId;

    private String endpoint;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 初始化
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param endpoint
     * @param bucketName1
     */
    public static final void init(String accessKeyId, String accessKeySecret, String endpoint, String bucketName1) {
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        BUCKETNAME = bucketName1;
    }

    public void init() {
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        BUCKETNAME = bucketName;
    }

    @Override
    public Boolean saveFile(Object src, String filePath) throws Exception {
        filePath = setPath(filePath);
        if (src instanceof File) {
            // 文件
            client.putObject(new PutObjectRequest(BUCKETNAME, filePath, (File) src));
            return true;
        } else if (src instanceof String) {
            // 保存文本
            InputStream is = new ByteArrayInputStream(((String) src).getBytes());
            try {
                client.putObject(BUCKETNAME, filePath, is);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(is);
            }
            return true;
        } else if (src instanceof InputStream) {
            // 保存流
            client.putObject(BUCKETNAME, filePath, (InputStream) src);
            return true;
        } else if (src instanceof BufferedImage) {
            // 保存图片
            InputStream is = getImageStream((BufferedImage) src);
            try {
                client.putObject(BUCKETNAME, filePath, is);
            } finally {
                IOUtils.closeQuietly(is);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean copyFile(String srcPath, String destinationPath) throws Exception {
        OSSObject object = getOSSObject(srcPath);
        if (object == null) {
            return false;
        }
        destinationPath = setPath(destinationPath);
        InputStream in = object.getObjectContent();
        try {
            client.putObject(BUCKETNAME, destinationPath, in);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return true;
    }

    @Override
    public Boolean copyFileToLocal(String filePath, String localFilePath) throws Exception {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return false;
        }
        OutputStream os = new FileOutputStream(localFilePath);
        InputStream in = object.getObjectContent();
        try {
            IOUtils.copy(in, os);
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(in);
        }
        return true;
    }

    @Override
    public Boolean deleteFile(String filePath) throws Exception {
        filePath = setPath(filePath);
        client.deleteObject(BUCKETNAME, filePath);
        return true;
    }


    @Override
    public InputStream getFileAsStream(String filePath) throws Exception {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return null;
        }
        return object.getObjectContent();
    }

    @Override
    public String getFileAsString(String filePath) throws Exception {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return null;
        }
        InputStream in = object.getObjectContent();
        try {
            StringBuffer sb = displayTextInputStream(in);
            return sb.toString();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @Override
    public Boolean deleteDir(String filePath) throws Exception {
        filePath = setPath(filePath);
        String[] keys = getFiles(filePath);
        client.deleteObjects(new DeleteObjectsRequest(BUCKETNAME).withKeys(Arrays.asList(keys)));
        return null;
    }

    @Override
    public String[] getFiles(String dirPath) throws Exception {
        int maxKeys = 100;
        List<String> list = new ArrayList<String>();

        boolean isTruncated = true;
        String nextMarker = null;
        while (isTruncated) {
            ObjectListing objectListing = client.listObjects(new ListObjectsRequest(BUCKETNAME)
                    .withMarker(nextMarker).withMaxKeys(maxKeys).withPrefix(dirPath));
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                list.add(s.getKey());
            }
            nextMarker = objectListing.getNextMarker();
            isTruncated = objectListing.isTruncated();
        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    public String[] getFilesName(String dirPath) throws Exception {
        dirPath = setPath(dirPath);
        String[] keys = getFiles(dirPath);
        String[] temp = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            temp[i] = getFileName(keys[i]);
        }
        return temp;
    }

    @Override
    public Boolean dirExists(String dirPath) {
        return true;
    }

    @Override
    public Boolean mkDir(String filePath) throws Exception {
        //不存在目录的概念，直接都返回true
        return true;
    }

    @Override
    public String getPatentFile(String filePath) {
        //解析filePath
        String[] str = filePath.split("/");
        if (str.length <= 1) {
            return "";
        } else {
            return str[str.length - 2];
        }
    }

    @Override
    public String getFileName(String filePath) {
        String[] str = filePath.split("/");
        return str[str.length - 1];
    }

    @Override
    public Boolean fileExists(String filePath) {
        filePath = setPath(filePath);
        boolean exists = client.doesObjectExist(BUCKETNAME, filePath);
        return exists;
    }

    @Override
    public Long getFileLength(String filePath) {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return 0l;
        }
        return object.getObjectMetadata().getContentLength();
    }

    @Override
    public Date getLastModified(String filePath) throws Exception {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return null;
        }
        return object.getObjectMetadata().getLastModified();
    }


    @Override
    public Boolean cutFile(String srcPath, String destinationPath) {
        //先复制再删除
        try {
            copyFile(srcPath, destinationPath);
            deleteFile(srcPath);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public File getFile(String filePath) {
        OSSObject object = getOSSObject(filePath);
        if (object == null) {
            return null;
        }
        //解析filePath路径
        String dir = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        File f = new File(dir);//存放到临时文件中
        if (!f.exists()) {
            f.mkdirs();
        }
        File file = new File(f, filePath.substring(filePath.lastIndexOf("/") + 1));
        if (file.exists()) {
            return file;
        } else {
            try {
                InputStream ins = object.getObjectContent();
                OutputStream os = new FileOutputStream(file);
                try {
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                } finally {
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(ins);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file;
        }
    }

    private OSSObject getOSSObject(String filePath) {
        filePath = setPath(filePath);
        OSSObject object = client.getObject(new GetObjectRequest(BUCKETNAME, filePath));
        return object;
    }

    private StringBuffer displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuffer sb = new StringBuffer();
        try {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return sb;
    }

    private InputStream getImageStream(BufferedImage bi) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(bi, "jpg", imOut);
        InputStream is = new ByteArrayInputStream(bs.toByteArray());
        return is;
    }

    private String setPath(String filePath) {
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        return filePath;
    }

    public static void main(String[] args) {
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "F5ySitlEtTLdJ3pp";
        String accessKeySecret = "wmF80JSkM1pe6jhh3xsKfl6XyPaktP";

        OSSFileSystem.init(accessKeyId, accessKeySecret, endpoint, "ipcool");
        OSSFileSystem aliOssFileSystem = new OSSFileSystem();
        try {
            String liveImg = "/book/" + System.currentTimeMillis() + ".jpg";
            String url = "https://img1.doubanio.com/mpic/s5821307.jpg";
            InputStream dataInputStream = HttpUtils.getInputStream(url);
            aliOssFileSystem.saveFile(dataInputStream, liveImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
