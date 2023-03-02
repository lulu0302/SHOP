import com.Enum.ShopStateEnum;
import com.dto.ShopExecution;
import com.exceptions.ShopOperationException;
import com.pojo.Area;
import com.pojo.PersonInfo;
import com.pojo.Shop;
import com.pojo.ShopCategory;
import com.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest{

    @Autowired
    private ShopService shopService;
    @Test
    public  void testAddShop() throws ShopOperationException, FileNotFoundException {
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test");
        shop.setCreatTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg=new File("C:\\Users\\lulu\\Desktop\\1.jpg");
        InputStream is=new FileInputStream(shopImg);
      ShopExecution se=shopService.addShop(shop,is,shopImg.getName());
      assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
}
