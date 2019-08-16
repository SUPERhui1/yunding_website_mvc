package cn.yunding.website.service;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 新闻相关服务
 */

public interface NewsService {
    /**
     * 检查该place是否有news
     * @param news
     * @return
     */
    News checkNews(News news);

    /**
     * 保存文件
     * @param image
     * @param imageRealPath
     * @return
     */
    ServiceResult saveImage(MultipartFile image, String imageRealPath) throws IOException;

    /**
     * 上传新闻
     * @param news
     * @return
     */
    ServiceResult uploadNews(News news);

    /**
     * 更新旧新闻的newsPlace
     * @param place_news
     */
    void updateNews(News place_news);

    /**
     * 获得所有新闻
     * @return
     */
    ServiceResult selectNews();

    /**
     * 分页获取所有新闻id+title(不包括被删除状态的)
     * @param pageNum
     * @return
     */
    ServiceResult selectNewsByPageNum(Integer pageNum);

    /**
     * 根据id，获取对应新闻
     * @param news
     * @return
     */
    ServiceResult selectNewsById(News news);

    /**
     * 获取首页新闻
     */
    ServiceResult selectPlaceNews();

    /**
     * 通过place获取新闻
     * @param news
     * @return
     */
    ServiceResult selectNewsByPlace(News news);

    /**
     * 获取分页页数
     * @return
     */
    int getPagesSum();

    /**
     * 通过id删除新闻
     * @param news
     * @return
     */
    ServiceResult deleteNews(News news);

}

