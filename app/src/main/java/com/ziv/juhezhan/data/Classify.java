package com.ziv.juhezhan.data;

import java.util.ArrayList;
import java.util.HashMap;

public class Classify {

    private ArrayList<String> mainNames;

    private HashMap<String, ArrayList<String>> mainToSubMap;

    public Classify(ArrayList<String> mainNames, HashMap<String, ArrayList<String>> mainToSubMap) {
        this.mainNames = mainNames;
        this.mainToSubMap = mainToSubMap;
    }

    public ArrayList<String> getMainNames() {
        return mainNames;
    }

    public void setMainNames(ArrayList<String> mainNames) {
        this.mainNames = mainNames;
    }

    public HashMap<String, ArrayList<String>> getMainToSubMap() {
        return mainToSubMap;
    }

    public void setMainToSubMap(HashMap<String, ArrayList<String>> mainToSubMap) {
        this.mainToSubMap = mainToSubMap;
    }
}
