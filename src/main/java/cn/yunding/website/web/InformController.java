package cn.yunding.website.web;

import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Inform;
import cn.yunding.website.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leeyf
 */
@Controller
@RequestMapping(value = "/api/inform")
public class InformController {
    @Autowired
    private InformService informService;

    /**
     * 上传通知消息
     * @param inform
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public RequestResult saveInform(Inform inform){
        try {
            if(inform!=null){
                ServiceResult serviceResult = informService.saveInform(inform);
                if(serviceResult.isSuccess()){
                    return RequestResult.success("上传成功！");
                }else {
                    return RequestResult.failure(serviceResult.getMessage());
                }
            }else {
                return RequestResult.failure("请上传必要参数！");
            }
        }catch (Exception e){
            throw new RuntimeException("请求上传通知消息失败！");
        }
    }

    /**
     * 分页获取通知消息
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pageNum={pageNum}",method = RequestMethod.GET)
    public RequestResult selectInform(@PathVariable("pageNum") Integer pageNum){
        try {
            if(pageNum==null){
                return RequestResult.failure("请输入正确页数");
            }else {
            ServiceResult selectNewsByPageNum = informService.selectInformByPageNum(pageNum);
            if(selectNewsByPageNum.isSuccess()){
                return RequestResult.success(selectNewsByPageNum);
            }else {
                return RequestResult.failure(selectNewsByPageNum.getMessage());
            }
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("请求分页获取通知失败！");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public RequestResult deleteInform(Inform inform){
        try {
            //判断该id是否有通知
             ServiceResult selectInformByid=informService.selectInformByid(inform.getInformId());
             if(selectInformByid.getData()!=null){
             ServiceResult deleteInform= informService.deleteInform(inform);
             if(deleteInform.isSuccess()){
                 return RequestResult.success(deleteInform.getData());
             }else {
                 return RequestResult.failure(deleteInform.getMessage());
             }}
             else {
                 return RequestResult.failure(selectInformByid.getMessage());
             }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("请求删除通知消息失败！");

        }
    }

    //获取分页总页数
    @ResponseBody
    @RequestMapping(value = "/pageSum")
    public RequestResult pageSum(){
        try {
            int selectpageSum= informService.getPagesSum();
            if(selectpageSum>0){
                return RequestResult.success(selectpageSum);
            }else {
                return RequestResult.failure("未获取到页数！");
            }
        }catch (Exception e){
            return RequestResult.failure("获取新闻总页数失败！");
        }
    }
}
