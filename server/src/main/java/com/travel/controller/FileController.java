package com.travel.controller;

import com.travel.service.FileService;
import com.travel.utils.ResultSet;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片上传和下载入口
 */
@RestController
@RequestMapping("/files")
public class FileController {


    private final static String baseUrl = "http://localhost:5888/files";

    @Autowired
    private FileService fileService;

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/file")
    public ResultSet uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String uuid= System.currentTimeMillis()+".";
        String originalFilename = multipartFile.getOriginalFilename();

        String fileName= uuid + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;

        try {
            if (fileService.uploadFile(multipartFile,fileName)) {
                return ResultSet.ok().data("row",baseUrl+"/image?imageName="+fileName);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return ResultSet.error();
    }

    /**
     * 图片下载
     * @param imageName
     * @param response
     * @throws IOException
     */
    @GetMapping("/image")
    public void getImage(@RequestParam("imageName") String imageName,
                         HttpServletResponse response) throws IOException {
        File image=new File("G:\\成品项目\\2-SSM\\旅游信息分享管理平台\\项目源码\\service\\image" +"/"+imageName);
        if (image.exists()){
            FileInputStream fileInputStream=new FileInputStream(image);
            byte[] bytes=new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes)>0){
                OutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }

}
