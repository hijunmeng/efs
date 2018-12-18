package com.gosuncn.efs.service;

import com.gosuncn.efs.bean.UploadResult;
import com.gosuncn.efs.common.BusinessException;
import com.gosuncn.efs.common.ResultCode;
import com.gosuncn.efs.config.EFSConfig;
import com.gosuncn.efs.utils.FileUtils;
import com.gosuncn.efs.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

/**
 * @Auther: HuWeiJian
 * @Date: 2018/11/16 15:06
 * @Description:
 */
@Service
public class FileService {


    @Autowired
    EFSConfig efsConfig;
    @Value("${server.address}")
    String address;
    @Value("${server.port}")
    String port;
    /**
     * 保存图片并返回相对路径
     *
     * @param file
     * @param module 模块名称，用户自定义，为空则默认为default
     * @return
     */
    public UploadResult storeFile(MultipartFile file, @Nullable String module) {
        UploadResult uploadResult = new UploadResult();
        uploadResult.setFileSize(file.getSize());
        uploadResult.setContentType(file.getContentType());
        try {
            uploadResult.setOriginalFilename(URLDecoder.decode(file.getOriginalFilename(),"utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            uploadResult.setOriginalFilename(file.getOriginalFilename());
        }

        uploadResult.setServiceCode(efsConfig.getServiceCode());
        Random random = new Random();
        String filename = System.currentTimeMillis() + "_" + random.nextInt(9999999);

        if (module == null) {
            module = "default";
        }
        String extname = FileUtils.getExtensionName(file);
        uploadResult.setStoreFilename(filename + "." + extname);
        boolean success = FileUtils.storeFile(file, efsConfig.getStore().getDiskRootDir() + module, filename);
        if (!success) {
            Log.error("文件保存失败:uploadResult=" + uploadResult.toString());
            throw new BusinessException(ResultCode.FAILED);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/").append(efsConfig.getStore().getAccessRootDir())
                .append("/").append(module)
                .append("/").append(filename)
                .append(".").append(extname)
                .append("?serviceCode=").append(efsConfig.getServiceCode());
        //String url="/" + efsConfig.getStore().getAccessRootDir() + "/" + module + "/" + filename + "." + extname+"?serviceId="+efsConfig.getServiceId();
        uploadResult.setAccessUrl(sb.toString());
        uploadResult.setFullUrl("http://"+address+":"+port+sb.toString());

        Log.info("文件保存成功:uploadResult=" + uploadResult.toString());
        return uploadResult;
    }

    /**
     * 删除指定文件
     * @param url
     */
    public boolean removeFile(String url) {
        Log.info("url=" + url);
        String prefix="/"+efsConfig.getStore().getAccessRootDir()+"/";
        if(!url.startsWith(prefix)){
            return false;
        }
        String newUrl = url.replaceFirst(prefix, "");
        if(newUrl.contains("?")){
            newUrl= newUrl.split("\\?")[0];
        }
        Log.info("newUrl=" + newUrl);
        String rootDir = efsConfig.getStore().getDiskRootDir();
        String filePath = rootDir + newUrl;
        Log.info("filePath=" + filePath);
        return FileUtils.deleteFile(filePath);
    }


}
