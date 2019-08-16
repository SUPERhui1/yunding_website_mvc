package cn.yunding.website.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author liyuanxuan
 */

public class Manager {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private Integer managerId;

    /**
     * 管理员权限，1：新闻/作品；2.：新闻/作品/成员
     */
    private Integer managerPermission;

    /**
     * 用户名
     */
    private String managerName;

    /**
     * 密码
     */
    private String managerPassword;

    /**
     * 创建时间
     */
    private Date managerCreatedAt;

    /**
     * 更新时间
     */
    private Date managerUpdatedAt;


    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerPermission=" + managerPermission +
                ", managerName='" + managerName + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerCreatedAt=" + managerCreatedAt +
                ", managerUpdatedAt=" + managerUpdatedAt +
                '}';
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getManagerPermission() {
        return managerPermission;
    }

    public void setManagerPermission(Integer managerPermission) {
        this.managerPermission = managerPermission;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public Date getManagerCreatedAt() {
        return managerCreatedAt;
    }

    public void setManagerCreatedAt(Date managerCreatedAt) {
        this.managerCreatedAt = managerCreatedAt;
    }

    public Date getManagerUpdatedAt() {
        return managerUpdatedAt;
    }

    public void setManagerUpdatedAt(Date managerUpdatedAt) {
        this.managerUpdatedAt = managerUpdatedAt;
    }

    public Manager(Integer managerPermission, String managerName, String managerPassword, Date managerCreatedAt, Date managerUpdatedAt) {

        this.managerPermission = managerPermission;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
        this.managerCreatedAt = managerCreatedAt;
        this.managerUpdatedAt = managerUpdatedAt;
    }

    public Manager() {

    }

    public Manager(Manager manager) {

        this.managerPermission = manager.managerPermission;
        this.managerName = manager.managerName;
        this.managerPassword = manager.managerPassword;
        this.managerCreatedAt = manager.managerCreatedAt;
        this.managerUpdatedAt = manager.managerUpdatedAt;
    }
}
