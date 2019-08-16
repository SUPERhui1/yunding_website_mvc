package cn.yunding.website.mapper;

import cn.yunding.website.entity.User;

public interface UserMapper {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean UploadUser(User user);

    /**
     * 验证用户
     * @param userCode
     * @return
     */
    Integer VerifyUser(String userCode);

}
