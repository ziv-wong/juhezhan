package com.ziv.juhezhan.bean;

public class WeiboNoveltyBean implements BaseBean {


    /**
     * title : 比尔盖茨书房首度全景公开，戳↓第一视角感受
     * href : http://weibo.com/a/hot/7543341736007681_1.html?type=new
     * img : http://tva3.sinaimg.cn/crop.0.0.2553.1438/90eb2137ly1fivsn5xrjpj21z113ye81.jpg
     */

    private String title;
    private String href;
    private String img;

    @Override
    public int getType() {
        return WEIBO_NOVELTY;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
