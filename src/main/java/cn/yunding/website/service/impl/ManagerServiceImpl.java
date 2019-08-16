package cn.yunding.website.service.impl;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Manager;
import cn.yunding.website.mapper.ManagerMapper;
import cn.yunding.website.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author liyuanxuan
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public ServiceResult checkLogin(String managerName,String managerPassword) {
        try {
            Manager manager = managerMapper.findByManagerName(managerName);
            if(managerName!=null){
                if(managerPassword!=null){
                if(manager.getManagerName().equals(managerName)&
                        manager.getManagerPassword().equals(managerPassword)){
                    return ServiceResult.success(manager);
                }else {
                    return ServiceResult.failure("账号或者密码错误");
                }
            }else {
                    return ServiceResult.failure("密码不能为空");
                }
            }else {
                return ServiceResult.failure("账号不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}