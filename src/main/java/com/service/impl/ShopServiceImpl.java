package com.service.impl;

import com.Enum.ShopStateEnum;
import com.dto.ShopExecution;
import com.exceptions.ShopOperationException;
import com.mapper.ShopMapper;
import com.pojo.Shop;
import com.service.ShopService;
import com.util.ImageUtil;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    public ShopMapper mapper;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImg, String fileName) throws ShopOperationException{

        //控制判断
        if (shop==null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreatTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = mapper.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (shopImg!= null) {
                    //存储图片
                    try {
                        addShopImg(shop,shopImg,fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = mapper.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
}
    private void addShopImg(Shop shop, InputStream thumbnail,String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail,dest,fileName);
        shop.setShopImg(shopImgAddr);
    }
}