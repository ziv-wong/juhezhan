package com.ziv.juhezhan.bean;


public class DouyuWzryBean implements BaseBean {

    /**
     * img : https://rpic.douyucdn.cn/acrpic/170919/890456_1948.jpg
     * href : http://www.douyu.com/890456
     * author : 峡谷咸鱼F4毒神
     * num : 1310
     * title : 丐帮招收新成员，办卡入会啦
     */

    private String img;
    private String href;
    private String author;
    private String num;
    private String title;

    @Override
    public int getType() {
        return DOUYU_WZRY;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
