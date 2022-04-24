package com.gfg.collections;

import java.util.Date;

public class KeywordFrequency  implements Comparable{

    private String keyword;
    private Integer freq;
//    private String IP;
//    private Date lastTime;

    public KeywordFrequency(String keyword, Integer freq) {
        this.keyword = keyword;
        this.freq = freq;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "KeywordFrequency{" +
                "keyword='" + keyword + '\'' +
                ", freq=" + freq +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return freq-((KeywordFrequency)o).getFreq();
    }
}
