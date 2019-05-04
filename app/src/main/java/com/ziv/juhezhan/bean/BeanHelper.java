package com.ziv.juhezhan.bean;


public class BeanHelper {

    public static int getTypeByName(String name) {
        int result = -1;

        switch (name) {
            case "虎扑/话题":
                result = BaseBean.HUPU_VOTE;
                break;
            case "虎扑/步行街":
                result = BaseBean.HUPU_BXJ;
                break;
            case "豆瓣/新电影":
                result = BaseBean.DOUBAN_MOVIE;
                break;
            case "豆瓣/新书":
                result = BaseBean.DOUBAN_NEWBOOK;
                break;
            case "豆瓣/热门书籍":
                result = BaseBean.DOUBAN_POPULARBOOK;
                break;
            case "斗鱼/所有游戏":
                result = BaseBean.DOUYU_GAME;
                break;
            case "斗鱼/王者荣耀":
                result = BaseBean.DOUYU_WZRY;
                break;
            case "微博/实时热搜":
                result = BaseBean.WEIBO_REALTIMEHOT;
                break;
            case "微博/新鲜事":
                result = BaseBean.WEIBO_NOVELTY;
                break;
            case "知乎/日报":
                result = BaseBean.ZHIHU_DAILY;
                break;
        }

        return result;
    }

    public static String getUrlByType(int type) {
        String result = "";

        switch (type) {
            case BaseBean.HUPU_VOTE:
                result = "hupu/vote";
                break;
            case BaseBean.HUPU_BXJ:
                result = "hupu/bxj";
                break;
            case BaseBean.DOUBAN_MOVIE:
                result = "douban/movie";
                break;
            case BaseBean.DOUBAN_NEWBOOK:
                result = "douban/newbook";
                break;
            case BaseBean.DOUBAN_POPULARBOOK:
                result = "douban/popularbook";
                break;
            case BaseBean.DOUYU_GAME:
                result = "douyu/game";
                break;
            case BaseBean.DOUYU_WZRY:
                result = "douyu/wzry";
                break;
            case BaseBean.WEIBO_REALTIMEHOT:
                result = "weibo/realtimehot";
                break;
            case BaseBean.WEIBO_NOVELTY:
                result = "weibo/novelty";
                break;
            case BaseBean.ZHIHU_DAILY:
                result = "zhihu/daily";
                break;
        }

        return "api/"+result;
    }

}
