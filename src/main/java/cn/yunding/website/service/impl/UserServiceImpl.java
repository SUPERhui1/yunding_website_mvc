package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.User;
import cn.yunding.website.mapper.UserMapper;
import cn.yunding.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author supperhui
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ServiceResult UploadUser(User user) {
        try {
            String userCode = UUID.randomUUID().toString().toLowerCase()
                    .replace("-","");
            user.setUserCode(userCode);
            if(userMapper.UploadUser(user)){
                return ServiceResult.success("注册成功");
            }else{
                return ServiceResult.failure("注册失败");
            }
        }catch (Exception e){
         return ServiceResult.failure("注册失败");
        }
    }
    @Override
    public ServiceResult VerifyUser(String userCode) {
        try{
            Integer userId=userMapper.VerifyUser(userCode);
            if(userId!=null){
                return ServiceResult.success(userId);
            }else{
                return ServiceResult.failure("验证失败");
            }
        }catch (Exception e){
            return ServiceResult.failure("验证失败");
        }
    }
}
