package cn.yunding.website.mapper;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Member;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.h
 */
public interface MemberMapper {

    /**
     * 插入成员信息
     * @param member
     * @return
     */
    int insert(Member member);

    /**
     *删除成员信息
     * @param member
     * @return
     */
    int delete(Member member);


    /**
     *修改成员信息
     * @param member
     * @return
     */
    int update(Member member);

    /**
     * 查询全部成员信息
     * @return
     */
    List<Member> selectAll();

    /**
     * 按照成员名字查询成员信息
     * @param memberName
     * @return
     */
    List<Member> selectByName(String memberName);

    /**
     * 更新大咖
     * @param member
     * @return
     */
    int updateState(Member member);

    /**
     * 获取大咖
     * @return
     */
    List<Member> selectGreatPerson();

    /**
     * 通过状态获取成员信息
     * @param memberState
     * @return
     */
    List<Member> selectPerson(Integer memberState);

    /**
     * 通过成员Id获取成员
     * @param memberId
     * @return
     */
    List<Member> selectById(Integer memberId);

    /**
     * 通过Id获得成员状态
     * @param memberId
     * @return
     */
    Member selectState(Integer memberId);

    /**
     * 通过list获取成员
     * @param ids
     * @return
     */
    List<Member> selectUserByIds(List<Integer> ids);
    /**
     * 通过状态将大咖的状态更改为0
     * @param member
     * @return
     */
    Integer updateGreatState(Member member);

    /**
     * 通过Id更改成员状态为(1～6)
     * @param member
     * @return
     */
    Integer updateGreatState1(Member member);

    /**
     * 通过成员状态获取成员Id
     * @param memberState
     * @return
     */
    Member selectId(Integer memberState);

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
     * 通过姓名模糊搜索
     */
    List<Member> getMemberInformationByName(@Param("memberName") String memberName);

    /**
     * 分页展示成员信息
     * @param beginIndex
     * @param number
     * @return
     */
    List<Member> getMember(@Param("beginIndex")Integer beginIndex,
                           @Param("number")Integer number);
    /**
     * 查询成员数量
     */
    Integer getMemberNum();


}
