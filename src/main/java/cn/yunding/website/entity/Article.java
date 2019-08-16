package cn.yunding.website.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 46960
 */

public class Article {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private Integer articleId;

    /**
     * 状态：0：删除；1：通过
     */
    private Integer articleState;

    /**
     * 正文
     */
    private String articleContent;

    /**
     * 配图URL
     */
    private String articleImage;

    /**
     * 类别：0：作品；1：教程；2：观点；3：咨询；4：文章
     */
    private Integer articleCategoryId;

    /**
     * 标题
     */
    private String articleIntroduce;

    /**
     * 点赞数
     */
    private Integer articlePraise;

    /**
     * 浏览量
     */
    private Integer articleViews;

    /**
     * 发布者
     */
    private String articleSender;

    /**
     * 创建时间
     */
    private Date articleCreatedAt;

    /**
     * 更新时间
     */
    private Date articleUpdatedAt;

    @Override
    public String toString() {
        return "ArticleMapper{" +
                "articleId=" + articleId +
                ", articleState=" + articleState +
                ", articleContent='" + articleContent + '\'' +
                ", articleImage='" + articleImage + '\'' +
                ", articleCategoryId=" + articleCategoryId +
                ", articleIntroduce='" + articleIntroduce + '\'' +
                ", articlePraise=" + articlePraise +
                ", articleViews=" + articleViews +
                ", articleSender='" + articleSender + '\'' +
                ", articleCreatedAt=" + articleCreatedAt +
                ", articleUpdatedAt=" + articleUpdatedAt +
                '}';
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public String getArticleIntroduce() {
        return articleIntroduce;
    }

    public void setArticleIntroduce(String articleIntroduce) {
        this.articleIntroduce = articleIntroduce;
    }

    public Integer getArticlePraise() {
        return articlePraise;
    }

    public void setArticlePraise(Integer articlePraise) {
        this.articlePraise = articlePraise;
    }

    public Integer getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(Integer articleViews) {
        this.articleViews = articleViews;
    }

    public String getArticleSender() {
        return articleSender;
    }

    public void setArticleSender(String articleSender) {
        this.articleSender = articleSender;
    }

    public Date getArticleCreatedAt() {
        return articleCreatedAt;
    }

    public void setArticleCreatedAt(Date articleCreatedAt) {
        this.articleCreatedAt = articleCreatedAt;
    }

    public Date getArticleUpdatedAt() {
        return articleUpdatedAt;
    }

    public void setArticleUpdatedAt(Date articleUpdatedAt) {
        this.articleUpdatedAt = articleUpdatedAt;
    }

    public Article(Integer articleId, Integer articleState, String articleContent, String articleImage, Integer articleCategoryId, String articleIntroduce, Integer articlePraise, Integer articleViews, String articleSender, Date articleCreatedAt, Date articleUpdatedAt) {

        this.articleId = articleId;
        this.articleState = articleState;
        this.articleContent = articleContent;
        this.articleImage = articleImage;
        this.articleCategoryId = articleCategoryId;
        this.articleIntroduce = articleIntroduce;
        this.articlePraise = articlePraise;
        this.articleViews = articleViews;
        this.articleSender = articleSender;
        this.articleCreatedAt = articleCreatedAt;
        this.articleUpdatedAt = articleUpdatedAt;
    }

    public Article(Article article) {

        this.articleId = article.articleId;
        this.articleState = article.articleState;
        this.articleContent = article.articleContent;
        this.articleImage = article.articleImage;
        this.articleCategoryId = article.articleCategoryId;
        this.articleIntroduce = article.articleIntroduce;
        this.articlePraise = article.articlePraise;
        this.articleViews = article.articleViews;
        this.articleSender = article.articleSender;
        this.articleCreatedAt = article.articleCreatedAt;
        this.articleUpdatedAt = article.articleUpdatedAt;
    }

    public Article() {


    }
}

