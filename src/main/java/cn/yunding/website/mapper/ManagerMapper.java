package cn.yunding.website.mapper;

import cn.yunding.website.entity.Manager;

/**
 * @author liyuanxuan
 */
public interface ManagerMapper {

    /**
     * 查找管理员和密码
     * @param managerName
     * @return
     */
    Manager findByManagerName(String managerName);

}
