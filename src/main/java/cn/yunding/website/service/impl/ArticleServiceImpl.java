package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Article;
import cn.yunding.website.mapper.ArticleMapper;
import cn.yunding.website.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author supperhui
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    /**
     * 一页展示的的成员数
     */
    private static final int PNUM = 6;
    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 通过页数获取文章
     * @param pageNum
     * @param articleCategoryId
     * @param hotOrTime
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceResult getArticleByNum(Integer pageNum,Integer articleCategoryId,String hotOrTime) {
        try {
            List<Article> list = new ArrayList<>();
            ServiceResult result;
            if(articleCategoryId!=null){
                result=getArticlePageCat(articleCategoryId);
            }else {
                result = getArticlePage();
            }
            if(result.getData()==null||(int)result.getData()==0){
                return ServiceResult.failure("文章总数为0");
            }else {
                int pageSum = (int) result.getData();
                if (pageNum >= pageSum) {
                    pageNum = pageSum;
                } else if (pageNum <= 0) {
                    pageNum = 1;
                }
                /**
                 * 获取当前页第一篇文章的下标（0开始）
                 */
                int beginIndex = PNUM * (pageNum - 1);

                if (("hot").equals(hotOrTime)) {
                    if (articleCategoryId != null) {
                        list = articleMapper.getCatArticleByHot(beginIndex, PNUM, articleCategoryId);
                    } else {
                        list = articleMapper.getArticleByHot(beginIndex, PNUM);
                    }
                } else {
                    if (articleCategoryId != null) {
                        list = articleMapper.getArticleCat(beginIndex, PNUM, articleCategoryId);
                    } else {
                        list = articleMapper.getArticle(beginIndex, PNUM);
                    }
                }
            }
            if (list.size() > 0) {
                return ServiceResult.success(list);
            } else {
                return ServiceResult.failure("查询到的条数为0");
            }
        } catch (Exception e) {
            throw new RuntimeException("文章获取失败");
        }
    }

    /**
     * 通过文章id删除文章
     *
     * @param articleId
     * @return
     */
    @Override
    public ServiceResult deleteArticleById(Integer articleId) {
        try {
            if (articleMapper.deleteArticleById(articleId)) {
                return ServiceResult.success("删除文章成功");
            } else {
                return ServiceResult.failure("该文章不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException("文章删除失败");
        }

    }

    /**
     * 插入文章
     *
     * @param article
     * @param articlePhoto
     * @return
     */
    @Override
    public ServiceResult updateArticle(Article article, String articlePhoto) {
        try {
            if (articlePhoto != null) {

                article.setArticleImage(articlePhoto);
            }
            article.setArticlePraise(0);
            article.setArticleViews(0);
            article.setArticleCreatedAt(new Date());
            article.setArticleUpdatedAt(new Date());
            if (articleMapper.updateArticle(article)) {
                return ServiceResult.success("文章发布成功");
            } else {
                return ServiceResult.failure("文章发布失败，尝试重新填写");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文章上传失败，各项不能为空");
        }
    }

    /**
     * 修改文章
     * @param article
     * @param articlePhoto
     * @return
     */
    @Override
    public ServiceResult changeArticle(Article article, String articlePhoto) {
        try {
            if(articlePhoto!=null){
                article.setArticleImage(articlePhoto);
            }
            article.setArticleUpdatedAt(new Date());
            if(articleMapper.changeArticle(article)){
                return ServiceResult.success("文章修改成功");
            }else{
                return ServiceResult.failure("文章修改失败，该文章以被删除");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("文章修改失败，各项不能为空");
        }
    }

    /**
     * 通过id获取文章
     * @param articleId
     * @return
     */
    @Override
    public ServiceResult getArticleById(Integer articleId) {
        try {
            Article article = articleMapper.getArticleById(articleId);
            if(article!=null){
                return ServiceResult.success(article);
            }else{
                return ServiceResult.failure("无对应文章");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("获取对应文章失败");
        }
    }
    @Override
    public ServiceResult getArticleByIntroduce(String articleIntroduce) {
        try {
            List<Article> list=articleMapper.getArticleByIntroduce(articleIntroduce);
            if(list.size()>0){
                return ServiceResult.success(list);
            }else {
                return ServiceResult.failure("无对应文章");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取对应文章失败");
        }
    }

    @Override
    public ServiceResult getArticlePage() {
        try {
            int ArticleNum = articleMapper.getArticleNum();
            int pageSum;
            if (ArticleNum % PNUM == 0) {
                pageSum = ArticleNum / PNUM;
            } else {
                pageSum = ArticleNum / PNUM + 1;
            }
            if(pageSum!=0){
                return ServiceResult.success(pageSum);
            }else {
                return ServiceResult.failure("文章总数为0");
            }

            }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取文章总数失败");
        }
    }

    @Override
    public ServiceResult getArticlePageCat(Integer articleCategoryId) {
        try {
            int ArticleNum = articleMapper.getArticleNumCat(articleCategoryId);
            int pageSum;
            if (ArticleNum % PNUM == 0) {
                pageSum = ArticleNum / PNUM;
            } else {
                pageSum = ArticleNum / PNUM + 1;
            }
            if(pageSum!=0){
                return ServiceResult.success(pageSum);
            }else {
                return ServiceResult.failure("文章总数为0");
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取文章总数失败");
        }
    }

    @Override
    public ServiceResult changePraise(Integer articleId, Integer clickNum) {
        try {
            boolean a;
            if(clickNum%2==1){
                a=articleMapper.upPraise(articleId);
            }else {
                a=articleMapper.downPraise(articleId);
            }
            if(a) {
                return ServiceResult.success("点赞成功");
            }else{
                return ServiceResult.failure("点赞失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("点赞失败");
        }
    }

    @Override
    public ServiceResult upViews(Integer articleId) {
        try{
            if(articleMapper.upViews(articleId)) {
                return ServiceResult.success("浏览量增加成功");
            }else {
                return ServiceResult.failure("浏览量增加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("浏览量增加失败");
        }
    }

    @Override
    public ServiceResult deleteArticle(Integer articleId) {
        try{
            if(articleMapper.deleteArticle(articleId)){
                return ServiceResult.success("删除文章成功");
            }else{
                return ServiceResult.failure("删除文章失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("文章删除失败");
        }
    }


}
