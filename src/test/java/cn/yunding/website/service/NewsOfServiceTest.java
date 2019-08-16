package cn.yunding.website.service;

import cn.yunding.website.BaseTest;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther leeyf
 * 测试newsService
 */

public class NewsOfServiceTest  extends BaseTest {
    @Autowired
    private NewsService newsService;

    @Test
    public void fun(){
        News news1 =new News();
        news1.setNewsPlace(2);
        System.out.println(newsService.selectNewsByPageNum(1));

    }

}
