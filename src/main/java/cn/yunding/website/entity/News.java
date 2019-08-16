package cn.yunding.website.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author 46960
 */

public class News {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private Integer newsId;

    /**
     * 状态：0：删除；1：通过；
     */
    private Integer newsState;

    /**
     * 标题
     */
    private String newsTitle;

    /**
     * 内容url
     */
    private String newsContent;

    /**
     * 配图url
     */
    private String newsImage;

    /**
     * 位置
     */
    private Integer newsPlace;

    /**
     * 描述
     */
    private String newsIntroduce;

    /**
     * 发布者
     */
    private String newsSender;

    /**
     * 创建时间
     */
    private Date newsCreatedAt;

    /**
     * 更新时间
     */
    private Date newsUpdatedAt;

    public News(Integer newsState, String newsTitle, String newsContent, String newsImage, Integer newsPlace, String newsIntroduce, String newsSender, Date newsCreatedAt, Date newsUpdatedAt) {
        this.newsState = newsState;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
        this.newsPlace = newsPlace;
        this.newsIntroduce = newsIntroduce;
        this.newsSender = newsSender;
        this.newsCreatedAt = newsCreatedAt;
        this.newsUpdatedAt = newsUpdatedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsState=" + newsState +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsImage='" + newsImage + '\'' +
                ", newsPlace=" + newsPlace +
                ", newsIntroduce='" + newsIntroduce + '\'' +
                ", newsSender='" + newsSender + '\'' +
                ", newsCreatedAt=" + newsCreatedAt +
                ", newsUpdatedAt=" + newsUpdatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, newsState, newsTitle, newsContent, newsImage, newsPlace, newsIntroduce, newsSender, newsCreatedAt, newsUpdatedAt);
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsState() {
        return newsState;
    }

    public void setNewsState(Integer newsState) {
        this.newsState = newsState;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public Integer getNewsPlace() {
        return newsPlace;
    }

    public void setNewsPlace(Integer newsPlace) {
        this.newsPlace = newsPlace;
    }

    public String getNewsIntroduce() {
        return newsIntroduce;
    }

    public void setNewsIntroduce(String newsIntroduce) {
        this.newsIntroduce = newsIntroduce;
    }

    public String getNewsSender() {
        return newsSender;
    }

    public void setNewsSender(String newsSender) {
        this.newsSender = newsSender;
    }

    public Date getNewsCreatedAt() {
        return newsCreatedAt;
    }

    public void setNewsCreatedAt(Date newsCreatedAt) {
        this.newsCreatedAt = newsCreatedAt;
    }

    public Date getNewsUpdatedAt() {
        return newsUpdatedAt;
    }

    public void setNewsUpdatedAt(Date newsUpdatedAt) {
        this.newsUpdatedAt = newsUpdatedAt;
    }

    public News() {

    }

    public News(News news) {
        this.newsState = news.newsState;
        this.newsTitle = news.newsTitle;
        this.newsContent = news.newsContent;
        this.newsImage = news.newsImage;
        this.newsPlace = news.newsPlace;
        this.newsIntroduce = news.newsIntroduce;
        this.newsSender = news.newsSender;
        this.newsCreatedAt = news.newsCreatedAt;
        this.newsUpdatedAt = news.newsUpdatedAt;
    }
}
