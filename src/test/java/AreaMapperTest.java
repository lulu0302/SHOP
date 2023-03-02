import com.mapper.AreaMapper;
import com.pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaMapperTest extends BaseTest{
    @Autowired
    private AreaMapper areaMapper;
    @Test
    public  void testQueryArea(){
        List<Area> areas=areaMapper.queryArea();
        areas.forEach(System.out::println);
    }
}
