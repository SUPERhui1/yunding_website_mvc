package cn.yunding.website.service.impl;

import cn.yunding.website.dto.ServiceResult;
import cn.yunding.website.entity.Member;
import cn.yunding.website.entity.QueryVo;
import cn.yunding.website.entity.User;
import cn.yunding.website.entity.Work;
import cn.yunding.website.mapper.MemberMapper;
import cn.yunding.website.mapper.WorkMapper;
import cn.yunding.website.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author liyuanxuan
 */
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
     private WorkMapper workMapper;
    @Autowired
     private MemberMapper memberMapper;
    /**
     * 存图片方法
     * @param pic
     * @param igrealPath
     * @return
     * @throws IOException
     */
    @Override
    public ServiceResult saveWork(MultipartFile pic, String igrealPath) throws IOException {
        String suffix = suffix(pic);
        String filename=file(pic);

        //判断是否为可传
        if (!isig(suffix)) {
            return ServiceResult.failure("请使用可上传的图片格式");
        } else {
            File file1 = new File(igrealPath);
            String fileSaveName = igrealPath + "/" + filename;
            String Realpath = "../static/upload/works/" + filename;
            File file2 = new File(Realpath);
            if (!file2.exists()) {
                file1.mkdirs();
                pic.transferTo(new File(fileSaveName));
            }
            return ServiceResult.success(Realpath);
        }
    }

    /**
     * 规定图片后缀
     *
     * @param suffix
     * @return
     */
    private boolean isig(String suffix) {
        if (suffix.equalsIgnoreCase("jpg") || suffix.equals("png") ||
                suffix.equals("gif") || suffix.equals("tiff") ||
                suffix.equals("JPEG") || suffix.equals("bmp") ||
                suffix.equals("pcx") || suffix.equals("tga") ||
                suffix.equals("exif") || suffix.equals("svg") ||
                suffix.equals("psd") || suffix.equals("cdr") ||
                suffix.equals("pcd") || suffix.equals("dxf") ||
                suffix.equals("ufo") || suffix.equals("eps") ||
                suffix.equals("ai") || suffix.equals("raw") ||
                suffix.equals("WMF") || suffix.equals("webp")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 读取文件后缀
     */
    private String suffix(MultipartFile file) {
        // 获取原始文件的后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1);
        return suffix;
    }

    /**
     * 生成随机文件名
     * @param ig
     * @return
     */
    private String file(MultipartFile ig) {
        //获取文件后缀
        String suffix = suffix(ig);
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;
        return filename;
    }

    /**
     * 上传作品
     * @param work
     * @return
     */
    @Override
    public ServiceResult workUpload(Work work) {
            work.setWorkCreatedAt(new Date());
            work.setWorkUpdatedAt(new Date());
        try {
            workMapper.insert(work);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.failure("上传失败");
        }
        return ServiceResult.success("上传成功");
    }

    /**
     * 删除作品
     * @param work
     * @return
     */
    @Override
    public ServiceResult deleteWorks(Work work) {
        try {
            Work works=workMapper.selectById(work.getWorkId());
            if(works.getPermission()!=0){
                work.setPermission(0);
            }else {
                return ServiceResult.failure("此作品已删除");
            }
            workMapper.deleteWorks(work);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return ServiceResult.success("删除成功");
    }

    /**
     * 通过id修改作品
     * @param work
     * @return
     */
    @Override
    public ServiceResult updateWorks(Work work) {
        try {
            work.setWorkUpdatedAt(new Date());
            workMapper.updateWorks(work);
            return ServiceResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 查询全部作品
     * @return
     */
    @Override
    public ServiceResult queryAllWorks(Integer permission) {
        try {
            List<Work> workList = workMapper.selectAll(permission);
            if (workList.size() != 0) {
                return ServiceResult.success(workList);
            } else {
                return ServiceResult.failure("未查询到作品");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 分页方法子方法
     */
    //定义分页每页容量
    private static final int LIMIT =4;
    //获得作品总数
    public  int getWorksSum(){
        try {
            return workMapper.getWorksSum();
        }catch (Exception e){
            e.printStackTrace();
            return workMapper.getWorksSum();
        }
    }

    @Override
    public int getPagesSum() {
        //获得作品总数
        int worksSum = getWorksSum();
        return worksSum  % LIMIT == 0 ? worksSum  / LIMIT : worksSum  / LIMIT + 1;
    }

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @Override
    public ServiceResult selectWorksByPageNum(Integer pageNum) {
        int pageSum = getPagesSum();
        if (pageNum > pageSum) {
            pageNum = pageSum;
        } else if (pageNum <= 0) {
            pageNum = 1;
        }
        //作品总数
        int worksSum = getWorksSum();
        //当前页第一篇作品的下标从0开始的时候
        int beginIndex = LIMIT * (pageNum - 1);

        try {
            List<Work> worksList = workMapper.getWorksByPageNum(beginIndex, LIMIT);
            if(worksList!=null){
                return ServiceResult.success(worksList);
            }else {
                return ServiceResult.failure("分页查询失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询作品
     * @param workId
     * @return
     */
    @Override
    public ServiceResult selectById(Work work,Integer workId) {
        try {
            Work workById = workMapper.selectById(workId);
            if (workById != null) {
                if (workById.getPermission() != 0) {
                    String work_introduce = workById.getWorkIntroduce();
                    List idList = Arrays.asList(work_introduce.split(","));
                    List<Member> members = memberMapper.selectUserByIds(idList);
                    QueryVo queryVo = new QueryVo();
                    queryVo.setWork(workById);
                    queryVo.setMembers(members);
                    queryVo.setIdsList(idList);
                    return ServiceResult.success(queryVo);
                }else {
                    return ServiceResult.failure("id为" + workId + "的作品以被删除");
                }
            } else {
                return ServiceResult.failure("未找到id为" + workId + "的作品");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.failure("作品获取失败");
        }
    }
}


