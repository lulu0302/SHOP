package com.controller.shopadmin;

import com.Enum.ShopStateEnum;
import com.dto.ShopExecution;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.ShopMapper;
import com.pojo.PersonInfo;
import com.pojo.Shop;
import com.service.ShopService;
import com.util.HttpServletRequestUtil;
import com.util.ImageUtil;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.KeyCode.F;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
   @RequestMapping(value = "/registershop",method = RequestMethod.POST )
   @ResponseBody
   private Map<String,Object> registerShop(HttpServletRequest request){


       Map<String,Object>modelMap=new HashMap<String,Object>();
       //1.接受并转发相应的参数，包括店铺信息以及图片信息
      String shopStr= HttpServletRequestUtil.getString(request,"shopStr");
       ObjectMapper mapper=new ObjectMapper();
       Shop shop=null;
       try {
           shop=mapper.readValue(shopStr,Shop.class);
       } catch (IOException e) {
           modelMap.put("success",false);
           modelMap.put("errMsg",e.getMessage());
           return modelMap;
       }

       CommonsMultipartFile shopImg=null;
       CommonsMultipartResolver CMR=new CommonsMultipartResolver(
               request.getSession().getServletContext()
       );
       if (CMR.isMultipart(request)){
           MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest)request;
            shopImg=(CommonsMultipartFile) mhsr.getFile("shopImg");
       }else {
           modelMap.put("success",false);
           modelMap.put("errMsg","上传图片不能为空");
           return modelMap;
       }
       //2.注册店铺
       if (shop!=null&&shopImg!=null){
           PersonInfo owner=new PersonInfo();
           //todo 后续完善部分
           owner.setUserId(1L);
           shop.setOwner(owner);
           ShopExecution se= null;
           try {
               se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
               if (se.getState()== ShopStateEnum.CHECK.getState()){
                   modelMap.put("success",true);
               }else {
                   modelMap.put("success",false);
                   modelMap.put("erroMsg",se.getStateInfo());
               }
           } catch (IOException e) {
               modelMap.put("success",false);
               modelMap.put("errMsg",e.getMessage());
               return modelMap;
           }
      return modelMap;
       }else {
           modelMap.put("success",false);
           modelMap.put("errMsg","请输入店铺信息");
           return modelMap;

       }
   }
//   //获取文件输入流将其改为file
//   private static void InputStreamToFile(InputStream ins, File file){
//       FileOutputStream os=null;
//       try {
//           os=new FileOutputStream(file);
//           int bytesRead=0;
//           byte[] buffer=new byte[1024];
//           while ((bytesRead=ins.read(buffer))!=-1){
//               os.write(buffer,0,bytesRead);
//           }
//       }catch (Exception e){
//           throw  new RuntimeException("调用inputSteamToFile出现异常"+e.getMessage());
//       }finally {
//           try {
//               if (os!=null){
//                   os.close();
//               }
//               if (ins!=null){
//                   ins.close();
//               }
//           }catch (IOException e){
//               throw  new RuntimeException("inputSteamToFile关闭IO出现异常"+e.getMessage());
//           }
//       }
//   }
}
