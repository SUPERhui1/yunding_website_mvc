package cn.yunding.website.mapper;


import cn.yunding.website.entity.Inform;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author leeyf
 */
public interface InformMapper {
    /**
     * 上传通知消息
     * @param inform
     * @return
     */
    void saveInform(Inform inform);

    /**
     * 分页获取通知
     * @param beginIndex
     * @param limit
     * @return
     */
    List<Inform> getInformByPageNum(@Param("beginIndex") Integer beginIndex,
                                    @Param("limit") Integer limit);

    /**
     * 获取所有通知数
     * @return
     */
    int getInformSum();

    /**
     * 通过id删除通知
     * @param inform
     */
    void deleteInform(Inform inform);

    Inform selectInformById(Integer informId);
}
