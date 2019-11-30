package com.example.showscreenshowondialog.FunctionClass;

/**
 * Create by $冯日天 on 2019/11/6
 */

//定义一个工具类——Picture，便于在自定义的Adapter中对图片路径及名称的保存与读取
public class Picture {

    private String title;
    private String imageId;

    public Picture(String title,String imageId){
        this.title=title;
        this.imageId=imageId;
    }

    public Picture(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
