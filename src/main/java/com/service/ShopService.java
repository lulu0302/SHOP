package com.service;

import com.dto.ShopExecution;
import com.exceptions.ShopOperationException;
import com.pojo.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImg,String fileName) throws ShopOperationException;
}
