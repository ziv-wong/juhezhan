package com.ziv.juhezhan.data;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziv.juhezhan.recyclerview.classify.ClassifyListAdapter;
import com.ziv.juhezhan.tool.LogAndToastUtil;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseUtil {

    private static final String       mFilename = "user.json";
    private static       DataBaseUtil instance  = null;
    private static       Classify     mUserData = null;

    private static WeakReference<Context> mWeakReference;

    private DataBaseUtil() {
    }

    public static DataBaseUtil getInstance(Context context) {
        mWeakReference = new WeakReference<>(context);
        if (instance == null) {
            synchronized (DataBaseUtil.class) {
                if (instance == null) {
                    instance = new DataBaseUtil();
                }
            }
        }
        return instance;
    }

    public synchronized void modifyDataBase(String mainName, String subName, int type) throws IOException, JSONException {
        HashMap<String, ArrayList<String>> map = mUserData.getMainToSubMap();
        ArrayList<String> subNames = map.get(mainName);
        if (subNames == null) {
            mUserData.getMainNames().add(mainName);
            subNames = new ArrayList<>();
            map.put(mainName, subNames);
        }
        switch (type) {
            case ClassifyListAdapter.DISCOVER:
                // Check if the subName is in the list
                if (!subNames.contains(subName)) {
                    subNames.add(subName);
                    saveData(mUserData);
                }
                break;
            case ClassifyListAdapter.SETTING:
                if (subNames.contains(subName)) {
                    subNames.remove(subName);
                    if (subNames.size() == 0) {
                        map.remove(mainName);
                        mUserData.getMainNames().remove(mainName);
                    }
                    saveData(mUserData);
                }
                break;
        }
    }

    private void saveData(Classify data) throws JSONException,
            IOException {
        Gson gson = new Gson();
        String saveStr = gson.toJson(data);
        FileOutputStream fileOutputStream = mWeakReference.get().openFileOutput(mFilename,
                Activity.MODE_PRIVATE);
        try {
            fileOutputStream.write(saveStr.getBytes("UTF-8"));
            fileOutputStream.close();
        } catch (Exception e) {
            LogAndToastUtil.ToastOut(mWeakReference.get(), "Save:" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public synchronized Classify getUserData() throws IOException, JSONException {
        mUserData = loadData();
        return mUserData;
    }

    private Classify loadData() throws IOException, JSONException {
        Classify data = null;
        FileInputStream inputStream = null;
        ByteArrayOutputStream arrayOutputStream = null;
        // Check if file exist
        if (!fileIsExists(mFilename)) {
            saveData(new Classify(new ArrayList<String>(), new HashMap<String, ArrayList<String>>()));
        }
        try {
            inputStream = mWeakReference.get().openFileInput(mFilename);
            byte[] bytes = new byte[1024];
            arrayOutputStream = new ByteArrayOutputStream();
            int num = inputStream.read(bytes);
            while (num != -1) {
                arrayOutputStream.write(bytes, 0, num);
                num = inputStream.read(bytes);
            }
            String content = new String(arrayOutputStream.toByteArray(), "UTF-8");
            Gson gson = new Gson();
            data = gson.fromJson(content, new TypeToken<Classify>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (arrayOutputStream != null) {
                arrayOutputStream.close();
            }
        }
        return data;
    }

    private boolean fileIsExists(String strFile) {
        try {
            String path = mWeakReference.get().getFilesDir().getPath() + "//";
            File f = new File(path + strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
