package com.ziv.juhezhan.bean;

public class ZhihuDailyBean implements BaseBean {

    /**
     * img : https://pic3.zhimg.com/v2-4dacb048d6acbf43d6670f6fd0e406b6.jpg
     * title : 后来，只要他们出现，人们便会说：「看啊，传奇的一代」
     * href : https://daily.zhihu.com//story/9604750
     */

    private String img;
    private String title;
    private String href;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int getType() {
        return ZHIHU_DAILY;
    }
}
