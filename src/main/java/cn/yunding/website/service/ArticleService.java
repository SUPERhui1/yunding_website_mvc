package cn.yunding.website.service;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Article;

/**
 * @author supperhui
 */
public interface ArticleService {
    /**
     * 通过页数获取文章
     * @param pageNum
     * @param articleCategoryId
     * @param hotOrTime
     * @return
     */
    ServiceResult getArticleByNum(Integer pageNum, Integer articleCategoryId, String hotOrTime);

    /**
     * 通过文章id不现实文章
     * @param articleId
     * @return
     */
    ServiceResult deleteArticleById(Integer articleId);

    /**
     * 插入文章
     * @param article
     * @param articlePhoto
     * @return
     */
    ServiceResult updateArticle(Article article, String articlePhoto);

    /**
     * 修改文章
     * @param article
     * @param articlePhoto
     * @return
     */
    ServiceResult changeArticle(Article article, String articlePhoto);

    /**
     * 通过id查找文章
     * @param articleId
     * @return
     */
    ServiceResult getArticleById(Integer articleId);

    /**
     *通过标题模糊搜索文章
     * @param articleIntroduce
     * @return
     */
    ServiceResult getArticleByIntroduce(String articleIntroduce);

    /**
     * 查询文章页数
     * @return
     */
    ServiceResult getArticlePage();

    /**
     * 获取制定类文章的页数
     * @param articleCategoryId
     * @return
     */
    ServiceResult getArticlePageCat(Integer articleCategoryId);

    /**
     * 根据id和点击次数改变点赞数
     * @param articleId
     * @param clickNum
     * @return
     */
    ServiceResult changePraise(Integer articleId, Integer clickNum);

    /**
     * 根据id增加浏览量
     * @param articleId
     * @return
     */
    ServiceResult upViews(Integer articleId);

    /**
     * 通过id删除文章
     * @param articleId
     * @return
     */
    ServiceResult deleteArticle(Integer articleId);
}
