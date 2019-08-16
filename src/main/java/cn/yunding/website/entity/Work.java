package cn.yunding.website.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author liyuanxuan
 */

public class Work {
    /**
     * 主键
     */
    @Id
//    @GeneratedValue(generator = "JDBC")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private Integer workId;

    /**
     * 名称
     */
    private String workName;

    /**
     * 作品内容url
     */
    private String workContent;

    /**
     * 介绍
     */
    private String workIntroduce;

    /**
     * 网址
     */
    private String workUrl;

    /**
     * 配图
     */
    private String workImage;

    /**
     * 发布者
     */
    private String workSender;

    /**
     * 创建时间
     */
    private Date workCreatedAt;

    /**
     * 更新时间
     */
    private Date workUpdatedAt;

    /**
     * 作品状态
     */
    private Integer permission;

    public Work() {
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkIntroduce() {
        return workIntroduce;
    }

    public void setWorkIntroduce(String workIntroduce) {
        this.workIntroduce = workIntroduce;
    }

    public String getWorkUrl() {
        return workUrl;
    }

    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl;
    }

    public String getWorkImage() {
        return workImage;
    }

    public void setWorkImage(String workImage) {
        this.workImage = workImage;
    }

    public String getWorkSender() {
        return workSender;
    }

    public void setWorkSender(String workSender) {
        this.workSender = workSender;
    }

    public Date getWorkCreatedAt() {
        return workCreatedAt;
    }

    public void setWorkCreatedAt(Date workCreatedAt) {
        this.workCreatedAt = workCreatedAt;
    }

    public Date getWorkUpdatedAt() {
        return workUpdatedAt;
    }

    public void setWorkUpdatedAt(Date workUpdatedAt) {
        this.workUpdatedAt = workUpdatedAt;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Work{" +
                "workId=" + workId +
                ", workName='" + workName + '\'' +
                ", workContent='" + workContent + '\'' +
                ", workIntroduce='" + workIntroduce + '\'' +
                ", workUrl='" + workUrl + '\'' +
                ", workImage='" + workImage + '\'' +
                ", workSender='" + workSender + '\'' +
                ", workCreatedAt=" + workCreatedAt +
                ", wokeUpdatedAt=" + workUpdatedAt +
                ", permission=" + permission +
                '}';
    }

    public Work(String workName, String workContent, String workIntroduce, String workUrl, String workImage, String workSender, Date workCreatedAt, Date wokeUpdatedAt, Integer permission) {
        this.workName = workName;
        this.workContent = workContent;
        this.workIntroduce = workIntroduce;
        this.workUrl = workUrl;
        this.workImage = workImage;
        this.workSender = workSender;
        this.workCreatedAt = workCreatedAt;
        this.workUpdatedAt = wokeUpdatedAt;
        this.permission = permission;
    }

    public Work(Work work) {
        this.workName = work.workName;
        this.workContent = work.workContent;
        this.workIntroduce = work.workIntroduce;
        this.workUrl = work.workUrl;
        this.workImage = work.workImage;
        this.workSender = work.workSender;
        this.workCreatedAt = work.workCreatedAt;
        this.workUpdatedAt = work.workUpdatedAt;
        this.permission = work.permission;
    }
}
