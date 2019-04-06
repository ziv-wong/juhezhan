package com.ziv.juhezhan.bean;

public class DoubanPopularBookBean implements BaseBean {


    /**
     * title : 不可思议的朋友
     * score :  9.2
     * author :  作者：[日] 田岛征彦
     * href : https://book.douban.com/subject/27069925/?icn=index-topchart-subject
     * img : https://img3.doubanio.com/mpic/s29499792.jpg
     */

    private String title;
    private String score;
    private String author;
    private String href;
    private String img;

    @Override
    public int getType() {
        return DOUBAN_POPULARBOOK;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
