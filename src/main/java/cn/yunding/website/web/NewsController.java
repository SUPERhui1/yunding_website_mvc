package cn.yunding.website.web;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.News;
import cn.yunding.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author leeyf
 */
@Controller
@RequestMapping("/api/news")
public class NewsController {
    //配图真实地址
    private static final String ImageRealPath="static/upload/newsImage/";

    @Autowired
    private NewsService newsService;

    /**
     * 上传新闻
     */
    @ResponseBody
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public RequestResult upload(News news,
                                MultipartFile pic,
                                HttpServletRequest request
                                )  {
        try{
        if(pic!=null) {
            //判断要上传位置是否有新闻
            News place_news = newsService.checkNews(news);
            //如果有新闻
            if (place_news != null) {
                //修改place：将新闻的place改为0
                place_news.setNewsPlace(0);
                //更新旧新闻的place
                newsService.updateNews(place_news);
            }
            //储存图片：uuid命名
            //图片真实地址
            String imageRealPath = request.getServletContext()
                    .getRealPath(ImageRealPath);
            //result_uploadNews图片上传后的位置
            ServiceResult uploadNewsAdress =
                    null;
            try {
                uploadNewsAdress = newsService.
                        saveImage(pic, imageRealPath);
                if (uploadNewsAdress.isSuccess()) {
                    //将图片位置存入对象
                    news.setNewsImage((String) uploadNewsAdress.getData());
                } else {
                    return RequestResult.failure(uploadNewsAdress.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //上传news
            ServiceResult uploadNews = newsService.uploadNews(news);

            if (uploadNews.isSuccess()) {
                return RequestResult.success("请求上传新闻成功！");
            } else {
                return RequestResult.failure(uploadNews.getMessage());
            }
        }else {
            return RequestResult.failure("上传图片不能为空");
        }}catch (Exception e){
            return RequestResult.failure("请求上传新闻失败");
        }
    }

    /**
     * 获得所有未删除状态新闻
     */
    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public RequestResult selectNews(){
        try {
            ServiceResult selectNewsResult = newsService.selectNews();
            if(selectNewsResult.isSuccess()){
                return RequestResult.success(selectNewsResult.getData());
            }else {
                return RequestResult.failure(selectNewsResult.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("获取所有新闻失败！");
        }

    }

    /**
     * 分页获取所有新闻id+title(不包括被删除状态的)
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pageNum={pageNum}",method = RequestMethod.GET)
    public RequestResult selectNewsByPageNum(@PathVariable("pageNum") Integer pageNum){
        try {
            ServiceResult selectNewsByPageNum = newsService.selectNewsByPageNum(pageNum);
            if(selectNewsByPageNum.isSuccess()){
                return RequestResult.success(selectNewsByPageNum);
            }else {
                return RequestResult.failure(selectNewsByPageNum.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("请求分页获取新闻失败！");
        }

    }

    /**
     * 根据id,更换新闻place
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exchange",method = RequestMethod.POST)
    public  RequestResult exchange(News news){
        try {
            //判断该新闻是否存在
            ServiceResult selectNewsById = newsService.selectNewsById(news);
            if(selectNewsById.isSuccess()) {
                //判断要上传位置是否有新闻
                News place_news = newsService.checkNews(news);
                //如果有新闻
                if (place_news != null) {
                    //修改place：将新闻的place改为0
                    place_news.setNewsPlace(0);
                    //更新旧新闻的place
                    newsService.updateNews(place_news);
                }
                //更新新新闻的place
                newsService.updateNews(news);
                //获取该place的news
                News checkNews = newsService.checkNews(news);
                if (checkNews.getNewsId().equals(news.getNewsId())) {
                    return RequestResult.success("更换成功！");
                } else {
                    return RequestResult.success("更换失败！");
                }
            }else {
                return RequestResult.failure(selectNewsById.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("请求更换place失败！");
        }

    }

    /**
     * 根据id，获取对应新闻的
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/id" ,method = RequestMethod.GET)
    public RequestResult selectNewById(News news){
        ServiceResult selectNewsById= newsService.selectNewsById(news);
        if(selectNewsById.isSuccess()){
            return RequestResult.success(selectNewsById);
        }else {
            return RequestResult.failure("请求由id获得新闻失败！");
        }
    }

    /**
     * 根据id更新新闻内容
     * @param news
     * @param pic
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RequestResult updateNewsById(News news,
                                        MultipartFile pic,
                                        HttpServletRequest request
                                        ) {
        //判断该新闻是否存在
        ServiceResult selectNewsById = newsService.selectNewsById(news);
        if(selectNewsById.isSuccess()) {
        //储存图片：uuid命名
        //图片真实地址
        String imageRealPath = request.getServletContext()
                .getRealPath(ImageRealPath);
        //result_uploadNews图片上传后的位置
        ServiceResult uploadNewsAdress =
                null;
        try {
            uploadNewsAdress = newsService.
                    saveImage(pic, imageRealPath);
            if (uploadNewsAdress.isSuccess()) {
                //将图片位置存入对象
                news.setNewsImage((String) uploadNewsAdress.getData());
                System.out.println("0000"+uploadNewsAdress.getData());
            } else {
                return RequestResult.failure(uploadNewsAdress.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据id跟换news内容
        try {
            //更改位置
            //判断要上传位置是否有新闻
            News place_news = newsService.checkNews(news);
            //如果有新闻
            if (place_news != null) {
                //修改place：将新闻的place改为0
                place_news.setNewsPlace(0);
                //更新旧新闻的place
                newsService.updateNews(place_news);
            }
            //更新新新闻的place
            newsService.updateNews(news);
            return RequestResult.success("更新新闻内容成功！");
        }catch (Exception e){
            return RequestResult.failure("更新新闻内容失败！");
        }
        }else {
            return RequestResult.failure("新闻id不存在！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectPlaceNews",method = RequestMethod.GET)
    public RequestResult selectPlaceNews(){
        try {
            ServiceResult selectPlaceNews = newsService.selectPlaceNews();
            if(selectPlaceNews.isSuccess()){
                return RequestResult.success(selectPlaceNews.getData());
            }else {
                return RequestResult.failure(selectPlaceNews.getMessage());
            }
        }catch (Exception e){
            return RequestResult.failure("请求获取首页新闻失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/place")
    public RequestResult selectNewsByPlace(News news){
        try {
            if (news!=null){
                ServiceResult selectNewsByPlace= newsService.selectNewsByPlace(news);
                if(selectNewsByPlace.isSuccess()){
                    return RequestResult.success(selectNewsByPlace.getData());
                }else {
                    return RequestResult.failure(selectNewsByPlace.getMessage());
                }
            }else {
                return RequestResult.failure("未获取到newsPlace");
            }
        }catch (Exception e ){
            return RequestResult.failure("请求通过place获取新闻失败！");
        }

    }

    //获取分页总页数
    @ResponseBody
    @RequestMapping(value = "/pageSum")
    public RequestResult pageSum(){
        try {
            int selectpageSum= newsService.getPagesSum();
            if(selectpageSum>0){
                return RequestResult.success(selectpageSum);
            }else {
                return RequestResult.failure("未获取到页数！");
            }
        }catch (Exception e){
            return RequestResult.failure("获取新闻总页数失败！");
        }
    }

    //新闻删除
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RequestResult deleteNews(News news){
        try {
            if(newsService.selectNewsById(news).isSuccess()){
                ServiceResult deleteNews= newsService.deleteNews(news);
                if(deleteNews.isSuccess()){
                return RequestResult.success(deleteNews.getData());}
                else {
                    return RequestResult.failure(deleteNews.getMessage());
                }
            }else {
                return RequestResult.failure("ID为："+news.getNewsId()+"的新闻不存在");
            }
        }catch (Exception e){
            return RequestResult.failure("请求删除新闻失败！");
        }
    }
}
