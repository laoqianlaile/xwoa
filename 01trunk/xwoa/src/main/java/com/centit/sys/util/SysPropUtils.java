package com.centit.sys.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.centit.support.utils.FileSystemOpt;

public class SysPropUtils {
    private static final Log log = LogFactoryImpl.getLog(SysPropUtils.class);

    private static Properties prop = null;

    public static String getMainHome() {
        String home = getResource().getProperty("home");
        FileSystemOpt.createDirect(home);
        return home;
    }

    public static String getLogHome() {
        String logHome = getMainHome() + getResource().getProperty("log.home");
        FileSystemOpt.createDirect(logHome);
        return logHome;
    }

    private static Properties getResource() {
        if (null == prop) {
            prop = new Properties();

            try {
                Resource resource = new ClassPathResource("system.properties");
                InputStream in = resource.getInputStream();
                prop.load(in);
            } catch (IOException e) {
                log.error("读取系统配置文件出错", e);
            }
        }

        return prop;
    }

    public static void main(String[] args) {
        System.out.println(getResource());
    }

}
