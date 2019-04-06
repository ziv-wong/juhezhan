package com.ziv.juhezhan.bean;

public class DoubanMovieBean implements BaseBean {

    /**
     * actors : 汤姆·霍兰德 / 小罗伯特·唐尼 / 玛丽莎·托梅
     * score : 7.7
     * title : 蜘蛛侠：英雄归来
     * director : 乔·沃茨
     */

    private String actors;
    private String score;
    private String title;
    private String director;
    private String img;
    private String href;

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    @Override
    public int getType() {
        return DOUBAN_MOVIE;
    }
}

