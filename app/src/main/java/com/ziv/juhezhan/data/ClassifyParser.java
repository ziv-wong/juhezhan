package com.ziv.juhezhan.data;


import com.ziv.juhezhan.bean.BeanHelper;
import com.ziv.juhezhan.setting.CatalogMainSetting;
import com.ziv.juhezhan.setting.CatalogSubSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClassifyParser {

    public static ArrayList<CatalogMainSetting> parseToCatalogMainSettings(Classify userClassify) {
        ArrayList<CatalogMainSetting> mCatalogMainSettings = new ArrayList<>();
        ArrayList<String> mainNames = userClassify.getMainNames();
        HashMap<String, ArrayList<String>> map = userClassify.getMainToSubMap();
        for (String mainName : mainNames) {
            ArrayList<String> subNames = map.get(mainName);
            ArrayList<CatalogSubSetting> settings = new ArrayList<>();
            for (String subName : subNames) {
                String queryName = mainName + "/" + subName;
                int type = BeanHelper.getTypeByName(queryName);
                String url = BeanHelper.getUrlByType(type);
                CatalogSubSetting subSetting = new CatalogSubSetting(subName, url, type);
                settings.add(subSetting);
            }
            CatalogMainSetting catalogMainSetting = new CatalogMainSetting(mainName, "", settings);
            mCatalogMainSettings.add(catalogMainSetting);
        }
        return mCatalogMainSettings;
    }

    // Return Elements that A has but B hasn't
    public static Classify parseToDifferClassify(Classify classifyA, Classify classifyB) {
        ArrayList<String> diffMainNames = new ArrayList<>();
        ArrayList<String> mainNamesA = classifyA.getMainNames();
        HashMap<String, ArrayList<String>> diffMap = new HashMap<>();
        HashMap<String, ArrayList<String>> mapA = classifyA.getMainToSubMap();
        HashMap<String, ArrayList<String>> mapB = classifyB.getMainToSubMap();
        for (String mainNameA : mainNamesA) {
            ArrayList<String> subNamesB = mapB.get(mainNameA);
            if (subNamesB == null) {
                diffMainNames.add(mainNameA);
                diffMap.put(mainNameA, mapA.get(mainNameA));
            } else {
                ArrayList<String> subNamesA = mapA.get(mainNameA);
                ArrayList<String> diffSubNames = new ArrayList<>();
                HashSet<String> setB = new HashSet<>(subNamesB);
                for (String s : subNamesA) {
                    if (!setB.contains(s)) {
                        diffSubNames.add(s);
                    }
                }
                if (diffSubNames.size() != 0) {
                    diffMainNames.add(mainNameA);
                    diffMap.put(mainNameA, diffSubNames);
                }
            }
        }
        return new Classify(diffMainNames, diffMap);
    }

}
