package cn.yunding.website.web;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Member;
import cn.yunding.website.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Mr.h
 */
@Controller
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    MemberService membersService;

    /**
     * 上传成员信息
     * @param member
     * @param pic
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public RequestResult memberUpload(Member member,
                                       MultipartFile pic,
                                       HttpServletRequest request){
        try{

            if (pic == null){
                return RequestResult.failure("上传图片信息不能为空！");
            }else{
                String realPath = request
                        .getServletContext()
                        .getRealPath("/static/upload/member/");
                member.setMemberCreatedAt(new Date());
                member.setMemberUpdatedAt(new Date());
                ServiceResult result = membersService.saveMember(member,pic,realPath);
                if (result.isSuccess()){
                    return RequestResult.success(result.getData());
                }else {
                    return RequestResult.failure(result.getMessage());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("上传成员信息失败！");
        }

    }

    /**
     * 修改成员信息
     * @param member
     * @param pic
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/change",method = RequestMethod.POST)
    public RequestResult memberChange(Member member,
                                      MultipartFile pic,
                                      HttpServletRequest request) {
        try {
            if(pic == null){
                member.setMemberUpdatedAt(new Date());
                ServiceResult result = membersService.updateMember(member);
                if (result.isSuccess()){
                    return RequestResult.success(result.getData());
                }else{
                    return RequestResult.failure("修改成员信息失败！");
                }
            }else{
                String realPath = request
                            .getServletContext()
                            .getRealPath("../static/upload");
                member.setMemberUpdatedAt(new Date());
                ServiceResult result = membersService.saveMember(member,pic,realPath);
                if (result.isSuccess()){
                    return RequestResult.success(result.getData());
                }else {
                    return RequestResult.failure(result.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("修改成员信息失败！");
        }

    }


    /**
     * 更换大咖
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateState",method = RequestMethod.POST)
    public RequestResult changeGreatPerson(Member member){
        try {
            ServiceResult result = membersService.selectState(member.getMemberId());
            if (result.getData().equals(member.getMemberState())){
                return RequestResult.success(result.getData());
            }else {
                ServiceResult result1 = membersService.updateGreatState(member);
                if (result1.isSuccess()){
                    RequestResult.success(result.getData());
                    ServiceResult result2 = membersService.updateGreatState1(member);
                    if (result2.isSuccess()){
                        return RequestResult.success("更换大咖成功！");
                    }else {
                        return RequestResult.failure("更换大咖失败失败！");
                    }
                }else {
                    return RequestResult.failure("更新大咖状态失败！");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("更换大咖失败！");
        }
    }

    /**
     * 删除成员信息
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RequestResult memberDelete(Member member){
        try {
               member.setMemberUpdatedAt(new Date());
               ServiceResult result = membersService.delete(member);
               if (result.isSuccess()){
                   return RequestResult.success("删除成功！");
               }else{
                   return RequestResult.failure(result.getMessage());
               }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("未知错误！");
        }
    }

    /**
     *获取全部成员
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public RequestResult selectAll(){
        try{
            ServiceResult result = membersService.selectAll();
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("查询成员信息失败！");
        }
    }

    /**
     * 获取大咖
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectGreatPerson",method = RequestMethod.GET)
    public RequestResult selectGreatPerson(){
        try{
            ServiceResult result = membersService.selectGreatPerson();
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure("获取大咖失败！");
            }
        }catch (Exception e){
            return RequestResult.failure("获取大咖失败！");
        }
    }

    /**
     * 通过成员状态获取成员信息
     * @param memberState
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectPerson",method = RequestMethod.POST)
    public RequestResult selectPerson(Integer memberState){
        try {
            ServiceResult result = membersService.selectPerson(memberState);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("接收成员信息失败！");
        }
    }

    /**
     * 通过Id获取成员
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectById",method = RequestMethod.POST)
    public RequestResult selectByID(Member member){
        try {
            ServiceResult result = membersService.selectById(member);
            if (result.isSuccess()){
                return RequestResult.success(result.getData());
            }else {
                return RequestResult.failure("获取成员失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("获取成员失败！");
        }
    }


    /**
     * 按照Name获取成员
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByName",method = RequestMethod.POST)
    public RequestResult selectByName(Member member){
        try{
            ServiceResult result = membersService.selectByName(member);
            if (result.isSuccess()){
                return RequestResult.success("获取成员信息成功！");
            }else {
                return RequestResult.failure("查询成员信息失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("查询成员信息失败！");
        }
    }

    /**
     * 通过姓名模糊搜索成员
     * @param memberName
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getByName",method =RequestMethod.POST)
    public RequestResult getMemberByName(String memberName){
        try{
            ServiceResult result=membersService.getMemberSInformationByName(memberName);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            }else{
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure(e.getMessage());
        }
    }
    /**
     * 分页查询成员
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RequestResult getByPage(Integer pageNum){
        try{
            ServiceResult result=membersService.gertMemberByPageNum(pageNum);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            }else{
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure(e.getMessage());
        }
    }
    /**
     * 获取总页数
     */
    @ResponseBody
    @RequestMapping(value = "/pageNum",method = RequestMethod.GET)
    public RequestResult getPage(){
        try{
            ServiceResult result=membersService.getPageNum();
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            }else{
                return RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure(e.getMessage());
        }
    }


}
