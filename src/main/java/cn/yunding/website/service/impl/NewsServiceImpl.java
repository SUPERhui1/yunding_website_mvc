package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.News;
import cn.yunding.website.mapper.NewsMapper;
import cn.yunding.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @auther leeyf
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public News checkNews(News news) {
        News result=newsMapper.checkNewsByPlace(news);
        return result;
    }

    @Override
    public ServiceResult saveImage(MultipartFile image, String imageRealPath) throws IOException {
        //调用suffix方法获得文件后缀
        String suffix =suffix(image);

        // 生成随机文件名
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;

        //判断是否为图片
        if(!isImage(suffix)){
            return ServiceResult.failure("可上传图片类型：jpg,png,gif,tif,JPEG,bmp");
        }else {
            File file1=new File(imageRealPath);
            String fileSaveName = imageRealPath + "/" + filename;
            String Realpath="../static/upload/newsImage/"+filename;
            File file2=new File(Realpath);
            if (!file2.exists()){
                file1.mkdirs();
                image.transferTo(new File(fileSaveName));
            }
            return ServiceResult.success(Realpath);
        }
    }

    @Override
    public ServiceResult uploadNews(News news) {
        news.setNewsCreatedAt(new Date());
        news.setNewsUpdatedAt(new Date());
        try{
            newsMapper.insertNews(news);
            return ServiceResult.success("上传新闻成功！");
        }catch (Exception e){
            return ServiceResult.failure("上传新闻失败！请检查上传参数是否正确！");
        }
    }

    @Override
    public void updateNews(News place_news) {
        place_news.setNewsUpdatedAt(new Date());
        newsMapper.updateNews(place_news);
    }

    @Override
    public ServiceResult selectNews() {
        List<News> newsList = newsMapper.selectNews();
        if(newsList.size()!=0){
            return ServiceResult.success(newsList);
        }else {
            return ServiceResult.failure("未查询到新闻，请先添加！");
        }

    }

    @Override
    public ServiceResult selectNewsByPageNum(Integer pageNum) {
        int pageSum = getPagesSum();
        if (pageNum > pageSum) {
            pageNum = pageSum;
        } else if (pageNum <= 0) {
            pageNum = 1;
        }
        //新闻总数
        int newsSum = getNewsSum();
        //当前页第一篇新闻的下标从0开始的时候
        int beginIndex = LIMIT * (pageNum - 1);

        try {
            List<News> newsList = newsMapper.getNewsByPageNum(beginIndex, LIMIT);
            if(newsList!=null){
                return ServiceResult.success(newsList);
            }else {
                return ServiceResult.failure("分页查询失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("未知错误");
        }

    }

    @Override
    public ServiceResult selectNewsById(News news) {
        News newsById=newsMapper.selectNewsById(news);
        if(newsById!=null){
            return ServiceResult.success(newsById);
        }else {
            return ServiceResult.failure("未查找到ID:"+news.getNewsId()+" 的新闻");
        }
    }

    @Override
    public ServiceResult selectPlaceNews() {
        try {
            List<News> selectPlaceNews= newsMapper.selectPlaceNews();
            if(selectPlaceNews!=null){
                return ServiceResult.success(selectPlaceNews);
            }else {
                return ServiceResult.failure("未查找到首页新闻");
            }
        }catch (Exception e){
            throw  new RuntimeException("service获取首页新闻失败！");
        }

    }

    @Override
    public ServiceResult selectNewsByPlace(News news) {
        try {
            News selectNewsByPlace = newsMapper.selectNewsByPlace(news);
            if(selectNewsByPlace!=null){
                return ServiceResult.success(selectNewsByPlace);
            }else {
                return ServiceResult.failure("位置为："+news.getNewsPlace()+"处没有新闻");
            }
        }catch (Exception e){
            return ServiceResult.failure("通过Place获取新闻失败！");
        }

    }



    /**
     * 分页方法子方法
     */
    //定义分页每页容量
    private static final int LIMIT =6;
    //获得文章总数

    public  int getNewsSum(){
        try {
            return newsMapper.getNewsSum();
        }catch (Exception e){
            e.printStackTrace();
            return newsMapper.getNewsSum();
        }
    }

     @Override
     public int getPagesSum(){
        //获得文章总数
        int newsSum= getNewsSum();
        return newsSum% LIMIT ==0? newsSum/ LIMIT :newsSum/ LIMIT +1;
    }

    @Override
    public ServiceResult deleteNews(News news) {
        try {
            newsMapper.deleteNews(news);
            return ServiceResult.success("删除新闻成功！");
        }catch (Exception e){
            return ServiceResult.failure("删除新闻失败！");
        }
    }

    //判断后缀是否为图片后缀
    private boolean isImage(String suffix) {
        if(suffix.equals("jpg") ||
                suffix.equals("png") ||
                suffix.equals("gif") ||
                suffix.equals("tif") ||
                suffix.equals("JPEG") ||
                suffix.equals("bmp") ){
            return true;
        }else {
            return false;
        }
    }

    //获得文件后缀
    private String suffix(MultipartFile file){
        // 获取原始文件的后缀

            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename
                    .substring(originalFilename.lastIndexOf(".") + 1);
            return suffix;






    }


}
