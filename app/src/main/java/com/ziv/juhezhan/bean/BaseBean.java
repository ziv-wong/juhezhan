package com.ziv.juhezhan.bean;

public interface BaseBean {
    int HUPU_VOTE = 0x01;
    int HUPU_BXJ  = 0x02;

    int DOUBAN_MOVIE       = 0x10;
    int DOUBAN_NEWBOOK     = 0x11;
    int DOUBAN_POPULARBOOK = 0x12;

    int DOUYU_GAME = 0x20;
    int DOUYU_WZRY = 0x21;

    int WEIBO_REALTIMEHOT = 0x30;
    int WEIBO_NOVELTY     = 0x31;

    int ZHIHU_DAILY = 0x40;

    int getType();

    String getHref();
}
