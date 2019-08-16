package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Inform;
import cn.yunding.website.entity.News;
import cn.yunding.website.mapper.InformMapper;
import cn.yunding.website.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author leeyf
 */
@Service
public class InformServiceImpl implements InformService {
    @Autowired
    private InformMapper informMapper;
    @Override
    public ServiceResult saveInform(Inform inform) {
        try {
            if(inform.getInformState()==null){
                inform.setInformState(1);
            };
            if(inform.getInformCreatedAt()==null){
            inform.setInformCreatedAt(new Date());};
            if(inform.getInformUpdatedAt()==null){
                inform.setInformUpdatedAt(new Date());
            }

             informMapper.saveInform(inform);
             return ServiceResult.success("上传通知消息成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ServiceResult.failure("上传通知消息失败！");
        }
    }

    @Override
    public ServiceResult selectInformByPageNum(Integer pageNum) {

        int pageSum = getPagesSum();
        if (pageNum > pageSum) {
            pageNum = pageSum;
        } else if (pageNum <= 0) {
            pageNum = 1;
        }
        //新闻总数
        int informSum = getInformSum();
        //当前页第一篇新闻的下标从0开始的时候
        int beginIndex = LIMIT * (pageNum - 1);


        try {
            System.out.println(beginIndex+"111111");
            List<Inform> informList = informMapper.getInformByPageNum(beginIndex, LIMIT);
            if(informList!=null){
                return ServiceResult.success(informList);
            }else {
                return ServiceResult.failure("分页查询失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("未知错误");
        }
    }

    @Override
    public ServiceResult deleteInform(Inform inform) {
        try {
            if(inform.getInformId()!=null){
                informMapper.deleteInform(inform);
                return ServiceResult.success("删除成功！");
            }else {
                return ServiceResult.failure("id为空，请输入正确值！");
            }
        }catch (Exception e){
            return ServiceResult.failure("删除失败！");
        }

    }

    @Override
    public ServiceResult selectInformByid(Integer informId) {
        try {

            Inform inform=informMapper.selectInformById(informId);
            if(inform!=null) {
                if (inform.getInformState() == 0) {
                    return ServiceResult.failure("未查询到id为：" + informId + "的通知消息");

                } else {
                    return ServiceResult.success(inform);

                }
            }else {
                return ServiceResult.failure("未查询到id为：" + informId + "的通知消息");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("通过id查通知失败！");
        }
    }

    /**
     * 分页方法子方法
     */
    //定义分页每页容量
    private static final int LIMIT =4;
    //获得文章总数
    public  int getInformSum(){
        try {
            return informMapper.getInformSum();
        }catch (Exception e){
            e.printStackTrace();
            return informMapper.getInformSum();
        }
    }

    @Override
    public int getPagesSum(){
        //获得总页数
        int informSum= getInformSum();
        return informSum% LIMIT ==0? informSum/ LIMIT :informSum/ LIMIT +1;
    }


}
