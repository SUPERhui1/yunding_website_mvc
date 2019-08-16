package cn.yunding.website.mapper;

import cn.yunding.website.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author supperhui
 */
public interface ArticleMapper {

    /**
     * 获取文章总数
     * @return
     */
    int getArticleNum();

    /**
     * 获取指定默认下标的几篇文章
     * @param beginIndex
     * @param number
     * @return
     */
    List<Article> getArticle(@Param("beginIndex") int beginIndex,
                             @Param("number") int number);

    /**
     *获取指定文章的总数
     * @param articleCategoryId
     * @return
     */
    int getArticleNumCat(@Param("articleCategoryId") int articleCategoryId);

    /**
     * 通过下标获取指定类的文章类
     * @param beginIndex
     * @param number
     * @param articleCategoryId
     * @return
     */
    List<Article> getArticleCat(@Param("beginIndex") int beginIndex,
                                @Param("number") int number,
                                @Param("articleCategoryId") int articleCategoryId);

    /**
     * 通过文章id不显示文章
     * @param articleId
     * @return
     */
    boolean deleteArticleById(@Param("articleId") int articleId);

    /**
     * 插入文章
     * @param article
     * @return
     */
    boolean updateArticle(Article article);

    /**
     * 通过id修改文章
     * @param article
     * @return
     */
    boolean changeArticle(Article article);

    /**
     * 通过id获取文章
     * @param articleId
     * @return
     */
    Article getArticleById(@Param("articleId") int articleId);

    /**
     * 通过标题模糊获取文章
     * @param articleIntroduce
     * @return
     */
    List<Article> getArticleByIntroduce(@Param("articleIntroduce") String articleIntroduce);

    /**
     * 通过id增加点赞数
     * @param articleId
     * @return
     */
    boolean upPraise(@Param("articleId") int articleId);

    /**
     * 通过id增加浏览量
     * @param articleId
     * @return
     */
    boolean upViews(@Param("articleId") int articleId);

    /**
     * 通过id减少点赞数
     * @param articleId
     * @return
     */
    boolean downPraise(@Param("articleId") int articleId);

    /**
     * 获得指定下标文章并按热度排
     * @param beginIndex
     * @param number
     * @return
     */
    List<Article> getArticleByHot(@Param("beginIndex") int beginIndex,
                                  @Param("number") int number);

    /**
     * 获取指定下标的特定类文章并按热度排
     * @param beginIndex
     * @param number
     * @param articleCategoryId
     * @return
     */
    List<Article> getCatArticleByHot(@Param("beginIndex") int beginIndex,
                                     @Param("number") int number,
                                     @Param("articleCategoryId") int articleCategoryId);

    /**
     * 通过id彻底删除文章
     * @param articleId
     * @return
     */
    boolean deleteArticle(int articleId);
}
