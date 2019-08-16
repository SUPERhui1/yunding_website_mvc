package cn.yunding.website.web;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liyuanxuan
 */

@Controller
@RequestMapping("/api/managerlogin")

@SessionAttributes("maneger")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public RequestResult checkLogin(HttpServletRequest request, String managerName, String managerPassword){
        try{
            ServiceResult result=managerService.checkLogin(managerName,managerPassword);
            if(result.isSuccess()){
                request.getSession().setAttribute("currentUser", result.getData());
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("账号或密码错误");
        }
    }
    @RequestMapping(value = "/outLogin")
    public RequestResult outLogin(HttpSession session){
        session.invalidate();
        return RequestResult.success("注销成功");
    }
}
