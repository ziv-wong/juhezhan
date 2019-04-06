package com.ziv.juhezhan.bean;

public class HupuBxjBean implements BaseBean {


    /**
     * title : 转八组：李雨桐流产的时候孩子七个月！
     * comment : 25亮616回复
     * href : https://bbs.hupu.com/20292986.html
     */

    private String title;
    private String comment;
    private String href;

    @Override
    public int getType() {
        return HUPU_BXJ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
