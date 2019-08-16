package cn.yunding.website.mapper;

import cn.yunding.website.entity.Work;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author liyuanxuan
 */
public interface WorkMapper {


    /**
     * 作品上传
     * @param work
     */
    void insert(Work work);

    /**
     * 通过id删除作品
     * @param work
     * @return
     */
    int deleteWorks(Work work);

    /**
     * 通过id修改作品
     * @param work
     * @return
     */
    int updateWorks(Work work);


    /**
     * 查询全部作品
     * @return
     */
    List<Work> selectAll(Integer permission);

    /**
     * 分页获取所有作品的id+name+image+introduce(不包括被删除状态的)
     * @param beginIndex
     * @param limit
     * @return
     */
    List<Work> getWorksByPageNum(@Param("beginIndex") Integer beginIndex,
                                @Param("limit") Integer limit);

    /**
     * 分页查询子方法获得所有新闻个数
     * @return
     */
    int getWorksSum();

    /**
     * 查询作品
     * @param workId
     * @return
     */
    Work selectById(Integer workId);
}
