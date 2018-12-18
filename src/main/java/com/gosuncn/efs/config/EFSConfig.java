package com.gosuncn.efs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: HuWeiJian
 * @Date: 2018/11/26 19:33
 * @Description:
 */
@ConfigurationProperties(prefix = "efs")
@Component
public class EFSConfig {
    private String serviceCode;
    private Store store;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public static class Store {
        private String diskRootDir;


        private String accessRootDir;

        public String getAccessRootDir() {
            return accessRootDir;
        }

        public void setAccessRootDir(String accessRootDir) {
            this.accessRootDir = accessRootDir;
        }

        public String getDiskRootDir() {
            return diskRootDir;
        }

        public void setDiskRootDir(String diskRootDir) {
            this.diskRootDir = diskRootDir;
        }
    }
}
