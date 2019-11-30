package com.example.showscreenshowondialog.FunctionClass;

import java.io.File;

/**
 * Create by $冯日天 on 2019/11/6
 */
public class FileUtil {
    public static String[] getImageNames(String folderPath){
        File file0=new File(folderPath);

        String[] files01=file0.list();

        int imageFileNums=0;
        for (int i=0;i<files01.length;i++){
            File file02=new File(folderPath+File.separator+files01[i]);
            if (!file02.isDirectory()){
                if (isImageFile(file02.getName())){
                    imageFileNums++;
                }
            }
        }

        String[] files02=new String[imageFileNums];
        int j=0;
        for (int i=0;i<files01.length;i++){
            File file02=new File(folderPath+File.separator+files01[i]);
            if ((!file02.isDirectory())){
                if (isImageFile(file02.getName())){
                    files02[j]=file02.getName();
                    j++;
                }
            }
        }

        return files02;
    }



    private static boolean isImageFile(String fileName){
        String fileEnd=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        if (fileEnd.equalsIgnoreCase("jpg")) {
            return true;
        }
        return false;
    }

}
