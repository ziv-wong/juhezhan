package com.ziv.juhezhan.bean;

public class HupuVoteBean implements BaseBean {

    /**
     * title : 湿乎乎的话题区版规(严禁黑蜜论群体攻击，恶意挖坟永久)
     */

    private String title;
    private String href;

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
        return HUPU_VOTE;
    }
}
