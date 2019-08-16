package cn.yunding.website.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author leeyf
 * 通知消息
 */
public class Inform {
    /**
     * 主键
     * 通知消息id
     */
    private Integer informId;

    private Integer informState;

    private String informContent;

    private Date informCreatedAt;

    private Date informUpdatedAt;

    public Date getInformCreatedAt() {
        return informCreatedAt;
    }

    public void setInformCreatedAt(Date informCreatedAt) {
        this.informCreatedAt = informCreatedAt;
    }

    public Date getInformUpdatedAt() {
        return informUpdatedAt;
    }

    public void setInformUpdatedAt(Date informUpdatedAt) {
        this.informUpdatedAt = informUpdatedAt;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "informId=" + informId +
                ", informState=" + informState +
                ", informContent='" + informContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Inform inform = (Inform) o;
        return Objects.equals(informId, inform.informId) &&
                Objects.equals(informState, inform.informState) &&
                Objects.equals(informContent, inform.informContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(informId, informState, informContent);
    }

    public Integer getInformId() {
        return informId;
    }

    public void setInformId(Integer informId) {
        this.informId = informId;
    }

    public Integer getInformState() {
        return informState;
    }


    public void setInformState(Integer informState) {
        this.informState = informState;
    }

    public String getInformContent() {
        return informContent;
    }

    public void setInformContent(String informContent) {
        this.informContent = informContent;
    }
}
