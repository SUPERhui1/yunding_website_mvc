package cn.yunding.website.service;


import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Inform;

/**
 * @author liyuanxuan
 * 检查管理员的登录
 */

public interface ManagerService {

    ServiceResult checkLogin(String managerName, String managerPassword);

}
