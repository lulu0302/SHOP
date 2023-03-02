package com.mapper;

import com.pojo.Shop;

public interface ShopMapper {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    int updateShop(Shop shop);


}
