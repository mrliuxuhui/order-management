package com.flyingwillow.restaurant.util.web;

import com.flyingwillow.restaurant.util.Config;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/19.
 */
public class FileUploadUtil {

    public static String saveFile(MultipartFile file) throws IOException {

        if(null==file){
            return null;
        }

        String result = Config.gets("dir.images.base");

        String dstDir = Config.getDir("dir.images.base");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        dstDir = dstDir + "/" + sdf.format(new Date());

        result = result + "/" + sdf.format(new Date());

        File dir = new File(dstDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        String filename = file.getName();
        String suffix = filename.substring(filename.lastIndexOf('.')+1);
        String dstFileName = UUIDUtil.getRandomUUID()+"."+suffix;

        result = result + "/" + dstFileName;

        File dstFile = new File(dir,dstFileName);

        file.transferTo(dstFile);

        return result;

    }
}
