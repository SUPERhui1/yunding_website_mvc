package cn.yunding.website.service;


import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Work;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author liyuanxuan
 * 作品相关服务
 */

public interface WorkService {

    /**
     * 作品上传
     * @param work
     * @return
     */
    ServiceResult workUpload(Work work);

    /**
     * 图片上传
     * @param file
     * @param igRealPath
     * @return
     * @throws IOException
     */
    ServiceResult saveWork(MultipartFile file,String igRealPath) throws IOException;

    /**
     * 删除作品
     * @param work
     * @return
     */
    ServiceResult deleteWorks(Work work);

    /**
     * 修改作品
     * @param work
     * @return
     */
    ServiceResult updateWorks(Work work);

    /**
     * 查询全部作品
     * @return
     */
    ServiceResult queryAllWorks(Integer permission);

    /**
     * 分页获取所有作品的id+name+image+introduce(不包括被删除状态的)
     * @param pageNum
     * @return
     */
    ServiceResult selectWorksByPageNum(Integer pageNum);

    /**
     * 查询作品
     * @param work
     * @param workId
     * @return
     */
    ServiceResult selectById(Work work,Integer workId);

    int getPagesSum();

}