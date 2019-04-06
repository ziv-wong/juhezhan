package com.ziv.juhezhan.bean;


public class DouyuGameBean implements BaseBean {


    /**
     * img : https://rpic.douyucdn.cn/acrpic/170919/469012_1942.jpg
     * href : http://www.douyu.com/saro
     * author : SARO๑
     * num : 3617
     * title : ❤️致郁系播音员(ΦωΦ)
     */

    private String img;
    private String href;
    private String author;
    private String num;
    private String title;

    @Override
    public int getType() {
        return DOUYU_GAME;
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
