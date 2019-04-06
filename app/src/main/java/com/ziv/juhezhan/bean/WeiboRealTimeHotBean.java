package com.ziv.juhezhan.bean;

public class WeiboRealTimeHotBean implements BaseBean {


    /**
     * title : 我羽绒服都穿了 你给我看这个
     * search_index : 933892
     * href : http://s.weibo.com/weibo/%25E6%2588%2591%25E7%25BE%25BD%25E7%25BB%2592%25E6%259C%258D%25E9%2583%25BD%25E7%25A9%25BF%25E4%25BA%2586%2B%25E4%25BD%25A0%25E7%25BB%2599%25E6%2588%2591%25E7%259C%258B%25E8%25BF%2599%25E4%25B8%25AA&Refer=top
     */

    private String title;
    private String search_index;
    private String href;

    @Override
    public int getType() {
        return WEIBO_REALTIMEHOT;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearchIndex() {
        return search_index;
    }

    public void setSearchIndex(String search_index) {
        this.search_index = search_index;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

