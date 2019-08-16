package cn.yunding.website.mapper;


import cn.yunding.website.entity.News;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @auther leeyf
 */

public interface NewsMapper {
   /**
    * 通过newsPlace查询 是否有news
    * @param news
    * @return
    */
   News checkNewsByPlace(News news);

   /**
    * 上传新闻
    * @return
    */
   void insertNews(News news);

   /**
    * 更新新闻的place属性
    * @param place_news
    */
   void updateNews(News place_news);

   /**
    * 查询所有的新闻
    * @return
    */
   List<News> selectNews();

   /**
    * 分页获取所有新闻id+title(不包括被删除状态的)
    * @param beginIndex
    * @param limit
    * @return
    */
   List<News> getNewsByPageNum(@Param("beginIndex") Integer beginIndex,
                               @Param("limit") Integer limit);

   /**
    * 分页查询子方法获得所有新闻个数
    * @return
    */
   int getNewsSum();

   /**
    * 根据id，获取对应新闻
    * @param news
    * @return
    */
   News selectNewsById(News news);

   List<News> selectPlaceNews();

    News selectNewsByPlace(News news);

    void deleteNews(News news);
}
















