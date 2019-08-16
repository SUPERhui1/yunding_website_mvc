package cn.yunding.website.web;


import cn.yunding.website.dto.RequestResult;
import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Work;
import cn.yunding.website.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author liyuanxuan
 */
@Controller
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * 图片真实路径
     */
    private static final String imageRealPath = "static/upload/works/";

    /**
     * 作品上传
     *
     * @param work
     * @param pic
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RequestResult workUpload(Work work,
                                    MultipartFile pic,
                                    HttpServletRequest request) {
        if (pic!=null){
                String igRealPath = request.getServletContext().getRealPath(imageRealPath);
                ServiceResult workAddress = null;
                try{
                workAddress = workService.saveWork(pic, igRealPath);
                if (workAddress.isSuccess()) {
                    work.setWorkImage((String) workAddress.getData());
                } else {
                    return RequestResult.failure("此格式图片不支持上传");
                }
            }catch (IOException e){
                    e.printStackTrace();
                }
            ServiceResult result = workService.workUpload(work);
            if (result.isSuccess()) {
                return RequestResult.success("上传成功");
            } else {
                return RequestResult.failure("有必填项未填");
            }
        } else {
            return RequestResult.failure("请填写必填信息");
        }
    }

    /**
     * 删除作品
     * @param work
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RequestResult workDelete(Work work) {
        try {
            work.setWorkUpdatedAt(new Date());
            ServiceResult deleteWorks = workService.deleteWorks(work);
            if (deleteWorks.isSuccess()) {
                return RequestResult.success(deleteWorks.getData());
            } else {
                return RequestResult.failure(deleteWorks.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("删除作品失败");
        }
    }

    /**
     * 通过id修改作品
     *
     * @param work
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public RequestResult workChange(Work work, Integer workId,MultipartFile ig, HttpServletRequest request){
        try {
            ServiceResult queryById = workService.selectById(work,workId);
            if (queryById.isSuccess()){
                if (ig!=null){
                try{
                String igRealPath = request.getServletContext().getRealPath(imageRealPath);
                ServiceResult workAddress = null;
                    workAddress = workService.saveWork(ig, igRealPath);
                    if (workAddress.isSuccess()) {
                        work.setWorkImage((String) workAddress.getData());
                        ServiceResult result = workService.updateWorks(work);
                        if (result.isSuccess()) {
                            return RequestResult.success("上传成功");
                        } else {
                            return RequestResult.failure("上传失败");
                        }
                    } else {
                        return RequestResult.failure("图片更新失败");
                    }
            }catch (Exception e){
                    e.printStackTrace();
                    return RequestResult.failure("更新作品失败");
                }
            } else {
                    try{
                work.setWorkUpdatedAt(new Date());
                ServiceResult updateWorks = workService.updateWorks(work);
                if (updateWorks.isSuccess()) {
                    return RequestResult.success(updateWorks.getData());
                } else {
                    return RequestResult.failure(updateWorks.getMessage());
                }
            }catch (Exception e){
                        e.printStackTrace();
                        return RequestResult.failure("更新作品失败");
                    }
                }
        } else {
                return RequestResult.failure("无此作品");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return RequestResult.failure("更新作品失败");
        }
    }



    /**
     * 查询全部未删除作品
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public RequestResult queryAllWorks(Integer permission){
        try{
            ServiceResult allWorks=workService.queryAllWorks(permission);
            if (allWorks.isSuccess()){
                return RequestResult.success(allWorks.getData());
            }else {
                return RequestResult.failure("目前没有作品");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RequestResult.failure("查询全部作品失败");
        }
    }

    /**
     * 分页获取所有作品的id+name+image+introduce(不包括被删除状态的)
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pageNum={pageNum}",method = RequestMethod.GET)
    public RequestResult selectNewsByPageNum(@PathVariable("pageNum") Integer pageNum){
        ServiceResult selectWorksByPageNum = workService.selectWorksByPageNum(pageNum);
        if(selectWorksByPageNum.isSuccess()){
            return RequestResult.success(selectWorksByPageNum);
        }else {
            return RequestResult.failure(selectWorksByPageNum.getMessage());
        }
    }

    /**
     * 查询作品
     * @param work
     * @param workId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectById",method = RequestMethod.POST)
    public RequestResult selectWorkById(Work work,Integer workId){
        try {
            ServiceResult selectWorkById=workService.selectById(work,workId);
            if (selectWorkById.isSuccess()){
                return RequestResult.success(selectWorkById.getData());
            }else {
                return RequestResult.failure(selectWorkById.getMessage());
            }

        }catch (Exception e){
            return RequestResult.failure(e.getMessage());
        }
    }

    //获取分页总页数
    @ResponseBody
    @RequestMapping(value = "/pageSum")
    public RequestResult pageSum(){
        try {
            int selectpageSum= workService.getPagesSum();
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
