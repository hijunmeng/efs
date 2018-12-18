package com.gosuncn.efs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: HuWeiJian
 * @Date: 2018/11/26 19:59
 * @Description:
 */
@ApiModel
public class UploadResult implements Serializable {

    @ApiModelProperty(value = "相对地址，用于数据保存",required = true)
    private String accessUrl;

    @ApiModelProperty(value = "完整访问路径，用于前端展示",required = true)
    private String fullUrl;

    @ApiModelProperty(value = "源文件名,包含后缀",required = true)
    private String originalFilename;

    @ApiModelProperty(value = "存储文件名（由服务生成唯一文件名）,包含后缀",required = true)
    private String storeFilename;

    @ApiModelProperty(value = "服务编码",required = true)
    private String serviceCode;

    @ApiModelProperty(value = "文件大小,字节",required = false)
    private Long fileSize;

    @ApiModelProperty(value = "Content-Type",required = false)
    private String  contentType;


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getStoreFilename() {
        return storeFilename;
    }

    public void setStoreFilename(String storeFilename) {
        this.storeFilename = storeFilename;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "accessUrl='" + accessUrl + '\'' +
                ", fullUrl='" + fullUrl + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", storeFilename='" + storeFilename + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", fileSize=" + fileSize +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
