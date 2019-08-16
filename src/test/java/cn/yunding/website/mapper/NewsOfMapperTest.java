package cn.yunding.website.mapper;

import cn.yunding.website.BaseTest;
import cn.yunding.website.entity.Member;
import cn.yunding.website.entity.News;
import cn.yunding.website.entity.Work;
import cn.yunding.website.service.WorkService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther leeyf
 */

public class NewsOfMapperTest extends BaseTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private WorkService workService;
    @Test
    public void NewsTest(){
        System.out.println(articleMapper);
//        List list=new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        String a= String.valueOf(list+",");
//        Work work=new Work();
//        work.setWorkId(1);
//        System.out.println(workService.selectById(work,1));
    }
}
