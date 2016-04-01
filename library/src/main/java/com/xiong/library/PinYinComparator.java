package com.xiong.library;

import java.util.Comparator;


public class PinYinComparator implements Comparator<SortModel> {
    @Override
    public int compare(SortModel lhs, SortModel rhs) {
        return lhs.getSortLetters().compareTo(rhs.getSortLetters());
    }
}
