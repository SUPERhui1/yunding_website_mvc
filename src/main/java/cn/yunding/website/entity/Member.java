package cn.yunding.website.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author Mr.h
 */

public class Member {

    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private Integer memberId;
    /**
     * 成员状态
     */
    private Integer memberState;

    /**
     * 姓名
     */
    private String memberName;

    /**
     * 照相
     */
    private String memberPhoto;

    /**
     * 专业年级
     */
    private String memberGrade;

    /**
     * 身份
     */
    private String memberPosition;

    /**
     * 代表作
     */
    private String memberAchievement;

    /**
     * 加入时间
     */
    private String memberJoinedAt;

    /**
     * 创建时间
     */
    private Date memberCreatedAt;

    /**
     * 更新时间
     */
    private Date memberUpdatedAt;

    public Member(){

    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberState() {
        return memberState;
    }

    public void setMemberState(Integer memberState) {
        this.memberState = memberState;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberPosition() {
        return memberPosition;
    }

    public void setMemberPosition(String memberPosition) {
        this.memberPosition = memberPosition;
    }

    public String getMemberAchievement() {
        return memberAchievement;
    }

    public void setMemberAchievement(String memberAchievement) {
        this.memberAchievement = memberAchievement;
    }

    public String getMemberJoinedAt() {
        return memberJoinedAt;
    }

    public void setMemberJoinedAt(String memberJoinedAt) {
        this.memberJoinedAt = memberJoinedAt;
    }

    public Date getMemberCreatedAt() {
        return memberCreatedAt;
    }

    public void setMemberCreatedAt(Date memberCreatedAt) {
        this.memberCreatedAt = memberCreatedAt;
    }

    public Date getMemberUpdatedAt() {
        return memberUpdatedAt;
    }

    public void setMemberUpdatedAt(Date memberUpdatedAt) {
        this.memberUpdatedAt = memberUpdatedAt;
    }

    public Member(Integer memberState,String memberName, String memberPhoto, String memberGrade, String memberPosition, String memberAchievement, String memberJoinedAt, Date memberCreatedAt, Date memberUpdatedAt) {

        this.memberState = memberState;
        this.memberName = memberName;
        this.memberPhoto = memberPhoto;
        this.memberGrade = memberGrade;
        this.memberPosition = memberPosition;
        this.memberAchievement = memberAchievement;
        this.memberJoinedAt = memberJoinedAt;
        this.memberCreatedAt = memberCreatedAt;
        this.memberUpdatedAt = memberUpdatedAt;
    }
}

