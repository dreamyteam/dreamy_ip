//package com.dreamy.utils.filesystem.impl;
//
//
//import com.dreamy.utils.TimeUtils;
//import com.dreamy.utils.filesystem.FileSystemService;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.*;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.io.IOUtils;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageOutputStream;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Date;
//
///**
// * Created with IntelliJ IDEA.
// * User: wangyongxing
// * Date: 16/6/21
// * Time: 下午1:23
// * To change this template use File | Settings | File Templates.
// */
//public class HDSFFileSystem implements FileSystemService {
//    static FileSystem fileSystem = null;
//
//    /**
//     * 初始化
//     *
//     * @param
//     */
//    public static final void init(String hadoopPath) throws URISyntaxException, IOException {
//        fileSystem = FileSystem.get(new URI(hadoopPath), new Configuration());
//    }
//
//    @Override
//    public Boolean saveFile(Object src, String filePath) throws Exception {
//
//        OutputStream out = fileSystem.create(new Path(filePath));
//        boolean result = false;
//        try {
//            if (src instanceof File) {
//                File file = (File) src;
//                InputStream in = new BufferedInputStream(new FileInputStream(file));
//                IOUtils.copyBytes(in, out, 4096, true);
//                out.close();
//                result = true;
//            } else if (src instanceof String) {
//                // 保存文本
//                // 指定UTF-8编码写文件
//                byte[] contents = ((String) src).getBytes("UTF-8");
//                out.write(contents);
//                out.close();
//                result = true;
//            } else if (src instanceof InputStream) {
//                // 保存流
//                InputStream in = (InputStream) src;
//                IOUtils.copyBytes(in, out, 4096, true);
//                out.close();
//                result = true;
//            } else if (src instanceof BufferedImage) {
//                // 保存图片
//                InputStream in = getImageStream((BufferedImage) src);
//                IOUtils.copyBytes(in, out, 4096, true);
//                out.close();
//                result = true;
//            }
//        } finally {
//            fileSystem.close();
//            return result;
//        }
//
//    }
//
//    @Override
//    public Boolean copyFile(String src, String destinationPath) throws Exception {
//        Path srcPath = new Path(src); //原路径
//        Path dstPath = new Path(destinationPath); //目标路径
//        //调用文件系统的文件复制函数,前面参数是指是否删除原文件，true为删除，默认为false
//        fileSystem.copyFromLocalFile(false, srcPath, dstPath);
//        fileSystem.close();
//        return null;
//    }
//
//    @Override
//    public Boolean copyFileToLocal(String filePath, String localFilePath) throws Exception {
//        Path remotePath = new Path(filePath);
//        Path localPath = new Path(localFilePath);
//        fileSystem.copyToLocalFile(remotePath, localPath);
//        fileSystem.close();
//        return null;
//    }
//
//    @Override
//    public Boolean deleteFile(String filePath) throws Exception {
//        boolean result = fileSystem.delete(new Path(filePath), true);
//        fileSystem.close();
//        return result;
//    }
//
//    @Override
//    public Boolean deleteDir(String filePath) throws Exception {
//        boolean result = fileSystem.delete(new Path(filePath), true);
//        fileSystem.close();
//        return result;
//    }
//
//    @Override
//    public InputStream getFileAsStream(String filePath) throws Exception {
//        return null;
//    }
//
//    @Override
//    public String getFileAsString(String filePath) throws Exception {
//
//        String fileContent = null;
//
//        Path path = new Path(filePath);
//        InputStream inputStream = null;
//        ByteArrayOutputStream outputStream = null;
//        try {
//            inputStream = fileSystem.open(path);
//            outputStream = new ByteArrayOutputStream(inputStream.available());
//            IOUtils.copyBytes(inputStream, outputStream, new Configuration());
//            fileContent = outputStream.toString();
//        } finally {
//            IOUtils.closeStream(inputStream);
//            IOUtils.closeStream(outputStream);
//            fileSystem.close();
//        }
//        return fileContent;
//
//    }
//
//    @Override
//    public String[] getFiles(String dirPath) throws Exception {
//
//        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path(dirPath), true);
//        try {
//            while (files.hasNext()) {
//                System.out.println(files.next());
//            }
//        } finally {
//            fileSystem.close();
//        }
//
//
//        return new String[0];
//    }
//
//    @Override
//    public String[] getFilesName(String dirPath) throws Exception {
//
//        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(dirPath));
//        fileSystem.close();
//        String[] names = new String[fileStatuses.length];
//        for (int i = 0; i < fileStatuses.length; i++) {
//            FileStatus temp = fileStatuses[i];
//            names[i] = temp.getPath().getName();
//        }
//        return names;
//    }
//
//    @Override
//    public String getFileName(String filePath) {
//        return null;
//    }
//
//    @Override
//    public Boolean fileExists(String filePath) throws IOException {
//
//        return fileSystem.exists(new Path(filePath));
//
//    }
//
//    @Override
//    public Boolean dirExists(String dirPath) {
//        return null;
//    }
//
//    @Override
//    public Long getFileLength(String filePath) throws IOException {
//
//        FileStatus fileStatus = fileSystem.getFileStatus(new Path(filePath));
//        fileSystem.close();
//        return fileStatus.getLen();
//    }
//
//    @Override
//    public Date getLastModified(String filePath) throws Exception {
//        FileStatus fileStatus = fileSystem.getFileStatus(new Path(filePath));
//        Date date = TimeUtils.transferLongToDate(fileStatus.getModificationTime());
//        return date;
//    }
//
//    @Override
//    public Boolean mkDir(String filePath) throws Exception {
//
//        Path dir = new Path(filePath);
//        boolean result = fileSystem.mkdirs(dir);
//        fileSystem.close();
//        return result;
//    }
//
//    @Override
//    public Boolean cutFile(String srcPath, String destinationPath) throws IOException {
//        Path oldPath = new Path(srcPath);
//        Path newPath = new Path(destinationPath);
//        boolean result = fileSystem.rename(oldPath, newPath);
//        fileSystem.close();
//        return result;
//    }
//
//    @Override
//    public String getPatentFile(String filePath) {
//        return null;
//    }
//
//    @Override
//    public File getFile(String filePath) {
//        return null;
//    }
//
//    private InputStream getImageStream(BufferedImage bi) throws IOException {
//        ByteArrayOutputStream bs = new ByteArrayOutputStream();
//        ImageOutputStream imOut;
//        imOut = ImageIO.createImageOutputStream(bs);
//        ImageIO.write(bi, "jpg", imOut);
//        InputStream is = new ByteArrayInputStream(bs.toByteArray());
//        return is;
//    }
//}
