package cn.yunding.website.service;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.User;

public interface UserService {
    ServiceResult UploadUser(User user);
    ServiceResult VerifyUser(String userCode);
}
