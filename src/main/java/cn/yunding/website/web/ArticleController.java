package cn.yunding.website.web;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Article;
import cn.yunding.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.yunding.website.sameMethod.File;
import javax.servlet.http.HttpServletRequest;



/**
 * @author supperhui
 */
@Controller
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/uploadImage")
    public RequestResult uploadImage(HttpServletRequest request,
                           MultipartFile upload){
        try {
            if (upload == null) {
                throw new RuntimeException("上传失败，必须上传图片");
            }
            String articlePhoto ;
            String realPath = request.getServletContext()
                    .getRealPath("/static/upload");
            File f = new File();
            String suffix = f.getSuffix(upload);
            if (suffix.equals("jpg") || suffix.equals("png")) {
                articlePhoto = f.getUuid(suffix, "image", upload, realPath);
            } else {
                throw new RuntimeException("上传失败，请选择正确的图片格式");
            }
            if(articlePhoto!=null){
                return RequestResult.success("上传图片成功");
            }else{
                return RequestResult.failure("上传图片失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure(e.getMessage());
        }

    }

    /**
     * 分页获取文章
     *
     * @param pageNum
     * @param articleCategoryId
     * @param hotOrTime
     * @return
     */
    @ResponseBody
    @GetMapping("/get")
    public RequestResult articleGet(Integer pageNum, Integer articleCategoryId, String hotOrTime) {
        try {
            ServiceResult request = articleService.getArticleByNum(pageNum, articleCategoryId, hotOrTime);
            if (request.isSuccess()) {
                return RequestResult.success(request.getData());
            } else {
                return RequestResult.failure(request.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @GetMapping("/delete")
    public RequestResult articleDelete(Integer articleId) {
        try {
            ServiceResult request = articleService.deleteArticleById(articleId);
            if (request.isSuccess()) {
                return RequestResult.success(request.getData());
            } else {
                return RequestResult.failure(request.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @PostMapping("/upload")
    public RequestResult articleUpload(Article article,
                                       MultipartFile pic,
                                       HttpServletRequest request) {

        try {
            if (pic == null) {
                throw new RuntimeException("上传失败，必须上传图片");
            }
            String articlePhoto ;
            String realPath = request.getServletContext()
                    .getRealPath("/static/upload");
            File f = new File();
            String suffix = f.getSuffix(pic);
            if (suffix.equals("jpg") || suffix.equals("png")) {
                articlePhoto = f.getUuid(suffix, "article", pic, realPath);
            } else {
                throw new RuntimeException("上传失败，请选择正确的图片格式");
            }

            ServiceResult result = articleService.updateArticle(article, articlePhoto);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @PostMapping("/change")
    public RequestResult articleChange(Article article,
                                       MultipartFile pic,
                                       HttpServletRequest request) {
        try {
            if (articleService.getArticleById(article.getArticleId()) != null) {
                String articlePhoto = null;
                if (pic != null) {
                    String realPath = request.getServletContext()
                            .getRealPath("/static/upload");
                    File f = new File();
                    String suffix = f.getSuffix(pic);
                    if (suffix.equals("jpg") || suffix.equals("png")) {
                        articlePhoto = f.getUuid(suffix, "article", pic, realPath);
                    } else {
                        throw new RuntimeException("修改失败，请选择正确的图片格式");
                    }
                    ServiceResult result = articleService.changeArticle(article, articlePhoto);
                    if (result.isSuccess()) {
                        return RequestResult.success(result.getData());
                    } else {
                        return RequestResult.failure(result.getMessage());
                    }
                } else {
                    ServiceResult result = articleService.changeArticle(article, articlePhoto);
                    if (result.isSuccess()) {
                        return RequestResult.success(result.getData());
                    } else {
                        return RequestResult.failure(result.getMessage());
                    }
                }
            } else {
                return RequestResult.failure("修改失败，无此文章");
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @GetMapping("/getById")
    public RequestResult articleGetById(Integer articleId) {
        try {
            ServiceResult result = articleService.getArticleById(articleId);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }

    /**
     * 通过标题模糊搜索文章
     *
     * @param articleIntroduce
     * @return
     */
    @ResponseBody
    @GetMapping("/getByIntroduce")
    public RequestResult articleGetByIntroduce(String articleIntroduce) {
        try {
            ServiceResult result = articleService.getArticleByIntroduce(articleIntroduce);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @GetMapping("/changePraise")
    public RequestResult articleChangePraise(Integer articleId, Integer clickNum) {
        try {
            ServiceResult result = articleService.changePraise(articleId, clickNum);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @GetMapping(value = "/upViews")
    public RequestResult articleUpViews(Integer articleId) {
        try {
            ServiceResult result = articleService.upViews(articleId);
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }
    @ResponseBody
    @GetMapping("/getPageNum")
    public RequestResult getPageNum(Integer articleCategoryId) {
        try {
            ServiceResult result;
            if (articleCategoryId != null) {
                result = articleService.getArticlePageCat(articleCategoryId);
            } else {
                result = articleService.getArticlePage();
            }
            if (result.isSuccess()) {
                return RequestResult.success(result.getData());
            } else {
                return RequestResult.failure(result.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure(e.getMessage());
        }
    }

    /**
     * @author leeyf
     * 获取文章页数
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/pageSum")
    public RequestResult articlePageSum() {
        try {
            ServiceResult pageSum = articleService.getArticlePage();
            if (pageSum.isSuccess()) {
                return RequestResult.success(pageSum.getData());
            } else {
                return RequestResult.failure(pageSum.getMessage());
            }
        } catch (Exception e) {
            return RequestResult.failure("获取文章总页数失败！");
        }

    }

    /**
     * 通过id删除文章
     * @param articleId
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteById")
    public RequestResult deleteArticle(Integer articleId){
        try{ServiceResult result=articleService.deleteArticle(articleId);
            if(result.isSuccess()){
                return RequestResult.success(result.getData());
            }else{
                return  RequestResult.failure(result.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure(e.getMessage());
        }
    }
}
