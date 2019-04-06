package com.ziv.juhezhan.bean;

public class DoubanNewBookBean implements BaseBean {

    /**
     * title : 这里
     * score :  9.5
     * author :  [美] 理查德·麦奎尔 / 后浪丨北京联合出版公司 / 2017-10
     * href : https://book.douban.com/subject/27108544/
     * img : https://img1.doubanio.com/mpic/s29512259.jpg
     */

    private String title;
    private String score;
    private String author;
    private String href;
    private String img;

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

    @Override
    public int getType() {
        return DOUBAN_NEWBOOK;
    }
}
