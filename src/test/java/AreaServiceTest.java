import com.pojo.Area;
import com.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest{
@Autowired
    private AreaService areaService;
@Test
    public void testGetAreaList(){
    List<Area>areaList=areaService.getAreaList();


}

}
