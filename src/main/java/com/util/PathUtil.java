package com.util;

import java.util.Locale;

/**
 * 返回项目图片的根路径
 * 返回项目图片的子路径
 */
public class PathUtil {
    public static String seperator=System.getProperty("file.separator");
    public static String getImgBasePath(){

        String os=System.getProperty("os.name");
        String basePath="";
        if (os.toLowerCase(Locale.ROOT).startsWith("win")){
            basePath="C:\\Users\\lulu\\Desktop\\image";
        }else {
            basePath="/home/xiangze/image";
        }
        basePath=basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath="upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",seperator);
    }
}
