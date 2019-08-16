package cn.yunding.website.mapper;

import cn.yunding.website.BaseTest;
import cn.yunding.website.entity.User;
import cn.yunding.website.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author supperhui
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class userOfMapper {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        User user=new User();
        user.setUserName("赵泽辉");
        user.setUserPassword("123456");
        user.setUserEmail("724899612@qq.com");
        //user.setUserCode("111");
        userService.UploadUser(user);
        //System.out.println(userMapper.VerifyUser("111"));
    }
}
