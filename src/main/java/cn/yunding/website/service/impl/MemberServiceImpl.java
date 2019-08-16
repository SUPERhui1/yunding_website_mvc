package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Member;
import cn.yunding.website.mapper.MemberMapper;
import cn.yunding.website.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Mr.h
 */
@Service
public class MemberServiceImpl implements MemberService {
    private final int PNUM=9;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    MemberService memberService;


    /**
     * 删除成员信息
     * @param member
     * @return
     */
    @Override
    @Transactional
    public ServiceResult delete(Member member) {
        try{
            Member member1 = memberMapper.selectState(member.getMemberId());
            if (member1.getMemberState() < 0){
                return ServiceResult.failure("该成员已被删除！");
            }else {
                member.setMemberUpdatedAt(new Date());
                member.setMemberState(-1);
                memberMapper.delete(member);
                return ServiceResult.success("删除成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 修改成员信息（含照片）
     * @param member
     * @param pic
     * @param realPath
     * @return
     */
    @Override
    @Transactional
    public ServiceResult updateMember(Member member,
                                      MultipartFile pic,
                                      String realPath) {
        try{
            String sf = getSuffix(pic);
            if (sf.equals("jpg")){
                String picture = saveMember(pic, realPath);
                member.setMemberPhoto(picture);
                member.setMemberCreatedAt(new Date());
                member.setMemberUpdatedAt(new Date());
                memberMapper.insert(member);
                return ServiceResult.success(null);
            }else {
                return ServiceResult.failure("上传图片格式为jpg格式");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 修改成员信息（不含照片）
     * @param member
     * @return
     */
    @Override
    public ServiceResult updateMember(Member member) {
        try{
            member.setMemberUpdatedAt(new Date());
            memberMapper.update(member);
            return ServiceResult.success(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 获取大咖
     * @return
     */
    @Override
    public ServiceResult selectGreatPerson() {
        try{
            List<Member> members = memberMapper.selectGreatPerson();
            if (members.size() > 0) {
                return ServiceResult.success(members);
            } else {
                return ServiceResult.failure("查询到的条数为0");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过成员状态获取成员信息
     * @param memberState
     * @return
     */
    @Override
    public ServiceResult selectPerson(Integer memberState) {
        try {
            List<Member> members = memberMapper.selectPerson(memberState);
            if (members.size() > 0){
                return ServiceResult.success(members);
            }else {
                return ServiceResult.failure("查询到的条数为0！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过Id获取成员
     * @param member
     * @return
     */
    @Override
    public ServiceResult selectById(Member member) {
        try {
            List<Member> members = memberMapper.selectById(member.getMemberId());
            if (members.size()==0){
                return ServiceResult.failure("查询到的条数为0");
            }else {
                return ServiceResult.success(members);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过Id获得成员状态
     * @param memberId
     * @return
     */
    @Override
    public ServiceResult selectState(Integer memberId) {
        try {
            Member member = memberMapper.selectState(memberId);
            return ServiceResult.success(member.getMemberState());
        }catch (Exception e){
            return ServiceResult.failure(null);
        }

    }

    /**
     * 通过Id将大咖的状态更改为0
     * @param member
     * @return
     */
    @Override
    public ServiceResult updateGreatState(Member member) {
        try {
            member.setMemberUpdatedAt(new Date());
            Member member1 = memberMapper.selectId(member.getMemberState());
            member1.setMemberState(0);
            memberMapper.updateGreatState(member1);
            return ServiceResult.success(member1);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过Id更改成员状态为(1～6)
     * @param member
     * @return
     */
    @Override
    public ServiceResult updateGreatState1(Member member) {
        try{
            member.setMemberUpdatedAt(new Date());
            member.setMemberState(member.getMemberState());
            memberMapper.updateGreatState1(member);
            return ServiceResult.success(member);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 查询全部成员信息
     * @return
     */
    @Override
    public ServiceResult selectAll() {
        try {
            List<Member> members = memberMapper.selectAll();
            if (members.size() > 0) {
                return ServiceResult.success(members);
            } else {
                return ServiceResult.failure("查询到的条数为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 按照名字查询成员信息
     * @param member
     * @return
     */
    @Override
    public ServiceResult selectByName(Member member) {
        try{
            List<Member> members = memberMapper.selectByName(member.getMemberName());
            if (members.size()==0){
                return ServiceResult.failure("查询到的条数为0");
            }else {
                return ServiceResult.success(members);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 上传成员信息
     * @param member
     * @param pic
     * @param realPath
     * @return
     */
    @Override
    @Transactional
    public ServiceResult saveMember(Member member,
                                    MultipartFile pic,
                                    String realPath) {
        try {
            String sf = getSuffix(pic);
            if (sf.equals("jpg")){
                String picture = saveMember(pic, realPath);
                member.setMemberPhoto(picture);
                member.setMemberCreatedAt(new Date());
                member.setMemberUpdatedAt(new Date());
                memberMapper.insert(member);
                return ServiceResult.success(null);
            }else {
                return ServiceResult.failure("上传图片格式为jpg格式");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("成员信息上传失败!");
        }
    }

    /**
     * 获取图片的地址
     * @param pic
     * @param realPath
     * @return
     * @throws IOException
     */
    private String saveMember(MultipartFile pic, String realPath) throws IOException {
        // 获取原始文件的后缀
        String originalFilename = pic.getOriginalFilename();
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1);

        // 生成随机文件名
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;

        String fileSaveName = realPath+"/" + filename;
        pic.transferTo(new File(fileSaveName));
        return "../static/upload/member/" + filename;
    }

    /**
     * 获取图片后缀
     * @param pic
     * @return
     * @throws IOException
     */
    private String getSuffix(MultipartFile pic) throws IOException{
        if (pic!=null){
            //获取原始文件后缀
            String originalFilename = pic.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

            return suffix;
        }else {
            return("请上传jpg文件！");
        }
    }

    /**
     * 通过姓名模糊查询
     * @param memberName
     * @return
     */
    @Override
    public ServiceResult getMemberSInformationByName(String memberName) {
        try{
            Set<Member> set=new HashSet<>();
            String[] nameList=memberName.split("");
            for(int i=0;i<nameList.length;i++) {
                set .addAll(memberMapper.getMemberInformationByName(nameList[i])) ;
            }
            if (set.size() > 0) {
                return ServiceResult.success(set);
            } else {
                return ServiceResult.failure("查无此人");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("获取成员失败");
        }
    }

    @Override
    public ServiceResult gertMemberByPageNum(Integer pageNum) {
        try {
            int ArticleNum = memberMapper.getMemberNum();
            int pageSum;
            if (ArticleNum % PNUM == 0) {
                pageSum = ArticleNum / PNUM;
            } else {
                pageSum = ArticleNum / PNUM + 1;
            }
            if (pageSum == 0) {
                return ServiceResult.failure("成员总量为0");
            }
            if (pageNum < 1) {
                pageNum = 1;
            }
            if (pageNum > pageSum) {
                pageNum = pageSum;
            }
            int beginIndex = PNUM * (pageNum - 1);
            List<Member> list = memberMapper.getMember(beginIndex, PNUM);
            if (list.size() > 0) {
                return ServiceResult.success(list);
            } else {
                return ServiceResult.failure("成员总量为0");
            }
        }catch (Exception e){
            throw new RuntimeException("查询成员失败");
        }
    }

    @Override
    public ServiceResult getPageNum() {
        try{
            int ArticleNum = memberMapper.getMemberNum();
            int pageSum;
            if (ArticleNum % PNUM == 0) {
                pageSum = ArticleNum / PNUM;
            } else {
                pageSum = ArticleNum / PNUM + 1;
            }
            if(pageSum>0){
                return ServiceResult.success(pageSum);
            }else{
                return ServiceResult.failure("页数为0");
            }
        }catch (Exception e){
            throw new RuntimeException("获取页数失败");
        }
    }


}
