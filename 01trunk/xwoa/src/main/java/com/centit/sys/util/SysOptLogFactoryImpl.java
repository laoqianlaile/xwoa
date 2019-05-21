package com.centit.sys.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class SysOptLogFactoryImpl {

    /**
     * 获取系统日志接口
     * 
     * @param optId
     *            F_OPTINFO表中 OPTID 字段，对应此Action操作
     * @return
     */
    public static ISysOptLog getSysOptLog(String optId) {

        return new SysOptLogger(optId);
    }

    public static String getFileUrl(HttpServletRequest request,
            final byte[] bt, String fileid) {
        ByteArrayInputStream is = new ByteArrayInputStream(bt);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(is);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String filepath = request.getSession().getServletContext()
                .getRealPath(File.separator)
                + "output" + File.separator;
        delAllFile(filepath);
        File file = new File(filepath);

        if (!file.exists()) {
            // 创建文件夹、file.mkdirs();多层结构 file.mkdir();单层结构
            file.mkdirs();
        }
        File w2 = new File(filepath + fileid + ".jpg");
        try {
            ImageIO.write(bi, "jpg", w2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return w2.getPath();
    }

    // param path 文件夹完整绝对路径

    public static boolean delAllFile(String path) {

        boolean flag = false;

        File file = new File(path);

        if (!file.exists()) {

            return flag;

        }

        if (!file.isDirectory()) {

            return flag;

        }

        String[] tempList = file.list();

        File temp = null;

        for (int i = 0; i < tempList.length; i++) {

            if (path.endsWith(File.separator)) {

                temp = new File(path + tempList[i]);

            } else {

                temp = new File(path + File.separator + tempList[i]);

            }

            if (temp.isFile()) {
                Date nowDate = new Date();
                if (nowDate.getTime() - temp.lastModified() > 100000) {
                    temp.delete();
                }
            }

            if (temp.isDirectory()) {

                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件

                // delFolder(path + "/" + tempList[i]);//再删除空文件夹

                flag = true;

            }

        }

        return flag;

    }

}
