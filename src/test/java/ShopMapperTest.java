import com.mapper.ShopMapper;
import com.pojo.Area;
import com.pojo.PersonInfo;
import com.pojo.Shop;
import com.pojo.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopMapperTest extends BaseTest{
    @Autowired
    private ShopMapper shopMapper;
    @Test
    @Ignore
    public  void testInsertShop (){
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
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreatTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopMapper.insertShop(shop);
        assertEquals(1, effectedNum);
    }
@Test
    public  void testUpdateShop (){
    Shop shop = new Shop();
    shop.setShopId(38L);//输入要更新的店铺id
    shop.setShopDesc("测试描述");
    shop.setShopAddr("测试地址");
    shop.setLastEditTime(new Date());
    int effectedNum = shopMapper.updateShop(shop);
    assertEquals(1,effectedNum);


}





}
