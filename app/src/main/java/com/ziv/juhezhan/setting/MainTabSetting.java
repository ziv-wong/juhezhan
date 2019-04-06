package com.ziv.juhezhan.setting;

import java.io.Serializable;

public interface MainTabSetting extends Serializable {

    int SUBSCRIBE = 1;
    int DISCOVER  = 2;
    int SETTING   = 3;

    int getCategoryType();

}
