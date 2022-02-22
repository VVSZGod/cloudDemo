package com.cloud.ribbon.consumer.util;

import java.io.File;
import java.util.List;

public class FileSerach {
    public static List<String> search(String path, List<String> list) {
        File dir = new File(path);
        File[] subFiles = dir.listFiles();       //获取e盘下所有的文件或文件夹对象
        if (null != subFiles) {
            for (File subFile : subFiles) {
                String fileName = "";
                if (subFile.isDirectory())        //文件夹则递归寻找，后缀为jpg文件则输出名字
                    search(subFile.getAbsolutePath(), list);
                else if (subFile.isFile() && subFile.getName().endsWith(".mp4")) {
                    fileName = subFile.getName();
                    //System.out.println(fileName);
                    list.add(fileName);
                }
            }
        }
        return list;
    }

    public static List<String> searchJpg(String path, List<String> list) {
        File dir = new File(path);
        File[] subFiles = dir.listFiles();       //获取e盘下所有的文件或文件夹对象
        if (null != subFiles) {
            for (File subFile : subFiles) {
                String fileName = "";
                if (subFile.isDirectory())        //文件夹则递归寻找，后缀为jpg文件则输出名字
                    searchJpg(subFile.getAbsolutePath(), list);
                else if (subFile.isFile() && subFile.getName().endsWith(".jpg")) {
                    fileName = subFile.getName();
                    //System.out.println(fileName);
                    list.add(fileName);
                }
            }
        }
        return list;
    }
}
