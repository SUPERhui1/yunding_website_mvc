package cn.yunding.website.sameMethod;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


public class File {

    public String getSuffix(MultipartFile newsImage) {
    //获取原始文件的后缀
    String originalFilname = newsImage.getOriginalFilename();
    return originalFilname
            .substring(originalFilname.lastIndexOf(".") + 1);
    }

    public String getUuid(String suffix,String entity,MultipartFile newsImage, String newsRealPath) throws IOException {
        //生成随机文件名
        String uuid = UUID.randomUUID().toString().toLowerCase()
                .replace("-","");
        String filename = uuid + "." + suffix;
        String fileSaveName = newsRealPath + "/"+entity+"/" +filename;
        newsImage.transferTo(new java.io.File(fileSaveName));
        return "../static/upload/" +entity+"/"+ filename;
    }
}
