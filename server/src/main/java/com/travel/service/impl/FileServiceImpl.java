package com.travel.service.impl;

import com.travel.service.FileService;
import com.travel.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {



    public boolean uploadFile(MultipartFile multipartFile, String fileName)throws IOException {
        File fileDir = new File("G:\\成品项目\\2-SSM\\旅游信息分享管理平台\\项目源码\\service\\image");
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
        File file = new File(fileDir.getAbsolutePath() +"/"+fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
