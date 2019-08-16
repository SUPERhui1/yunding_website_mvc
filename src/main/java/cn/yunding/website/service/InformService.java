package cn.yunding.website.service;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Inform;

/**
 * @author leeyf
 */
public interface InformService {
    /**
     * 上传新闻通知
     * @param inform
     * @return
     */
    ServiceResult saveInform(Inform inform);

    /**
     * 分页获取通知
     * @param pageNum
     * @return
     */
    ServiceResult selectInformByPageNum(Integer pageNum);

    ServiceResult deleteInform(Inform inform);

    ServiceResult selectInformByid(Integer informId);

    int getPagesSum();
}
