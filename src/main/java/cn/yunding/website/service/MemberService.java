package cn.yunding.website.service;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Member;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mr.h
 */

public interface MemberService {


    /**
     * 上传成员信息
     * @param member
     * @param pic
     * @param realPath
     * @return
     */
    ServiceResult saveMember(Member member,
                             MultipartFile pic,
                             String realPath);


    /**
     * 删除成员信息
     * @param member
     * @return
     */
    ServiceResult delete(Member member);

    /**
     * 修改成员信息（含照片）
     * @param member
     * @param pic
     * @param realPath
     * @return
     */
    ServiceResult updateMember(Member member, MultipartFile pic, String realPath);

    /**
     * 修改成员信息（不含照片）
     * @param member
     * @return
     */
    ServiceResult updateMember(Member member);

    /**
     * 获取全部成员信息
     * @return
     */
    ServiceResult selectAll();

    /**
     * 根据用户Name查询用户信息
     * @param member
     * @return
     */
    ServiceResult selectByName(Member member);


    /**
     * 获取大咖
     * @return
     */
    ServiceResult selectGreatPerson();

    /**
     * 通过状态获取成员信息
     * @param memberState
     * @return
     */
    ServiceResult selectPerson(Integer memberState);

    /**
     * 通过Id获取成员
     * @param member
     * @return
     */
    ServiceResult selectById(Member member);

    /**
     * 通过Id获取成员状态
     * @param
     * @param memberId
     * @return
     */
    ServiceResult selectState(Integer memberId);

    /**
     * 通过用户状态更改用户状态
     * @param member
     * @return
     */
    ServiceResult updateGreatState(Member member);

    /**
     * 通过成员Id更改用户状态
     * @param member
     * @return
     */
    ServiceResult updateGreatState1(Member member);

    /**
     * 通过姓名模糊搜索
     * @param memberName
     * @return
     */
    ServiceResult getMemberSInformationByName(String memberName);
    /**
     * 成员分页
     */
    ServiceResult gertMemberByPageNum(Integer pageNum);

    /**
     *
     * @return
     */
    ServiceResult getPageNum();

}
