package com.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {
    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static  final Random r=new Random();
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转换成File类
     * @param
     * @return
     */

    public static  File transferCommonsMulitpartFileToFile(CommonsMultipartFile file){
        File newFile=new File(file.getOriginalFilename());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
           logger.error(e.toString());
           e.printStackTrace();
        }
        return newFile;
    }
    /**
     * 处理缩略图，并返回新生成图片的相对值路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static  String generateThumbnail(InputStream thumbnail, String targetAddr,String fileName){
        String realFileName=getRandomFileName();
        String extension=getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is:"+relativeAddr);
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete is:"+PathUtil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200,200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 若创建目标路径所涉及的目录，即/home/work/xiangze/xxx.jpg
     * 那么要创建home work xiangze三个文件夹
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if (!dirPath.exists()){//创建一路所需的文件夹
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     * @param
     * @return
     */
    private static String getFileExtension(String fileName) {

        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     *生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的5位数
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr +rannum;
    }

    public static void main(String[] args) throws IOException {
        String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("C:\\Users\\lulu\\Desktop\\1.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f)
                .toFile("C:\\Users\\lulu\\Desktop\\new.jpg");


    }
}
