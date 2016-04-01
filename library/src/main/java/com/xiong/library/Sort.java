package com.xiong.library;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hui.xiong on 2016/3/31.
 */
public class Sort {

    public static List<SortModel> sortData(List<String> list){
        List<SortModel> sortModels = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setContent(list.get(i));
            sortModel.setSortLetters(getPingYin(list.get(i)));
            sortModels.add(sortModel);
        }
        Collections.sort(sortModels,new PinYinComparator());
        return sortModels;
    }

    public static List<SortModel> filterData(String filterStr,List<SortModel> sortModels) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = sortModels;
        } else {
            for (SortModel sortModel : sortModels) {
                String name = sortModel.getContent();
                if (getAlpha(name).contains(filterStr.toUpperCase()) || sortModel.getSortLetters().startsWith(getPingYin(filterStr))) {
                    filterDateList.add(sortModel);
                }
            }
        }
        return filterDateList;
    }

    private static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                            input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toUpperCase();
    }
    /**
     * get the first alpha of every chinese
     * */
    private static String getAlpha(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (nameChar != null && defaultFormat != null) {
            for (int i = 0; i < nameChar.length; i++) {
                if (nameChar[i] > 128) {
                    try {
                        pinyinName += PinyinHelper.toHanyuPinyinStringArray(
                                nameChar[i], defaultFormat)[0].charAt(0);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                } else {
                    pinyinName += nameChar[i];
                }
            }
        }
        return pinyinName.toUpperCase();
    }
}
