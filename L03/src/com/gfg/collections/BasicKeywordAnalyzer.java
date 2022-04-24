package com.gfg.collections;

import java.util.*;

public class BasicKeywordAnalyzer implements KeywordAnalyzerInterface{

    private List<String> keywords;
    private Set<String> keywordSet;

    private Map<String,Integer> keywordFreqMap;

    public BasicKeywordAnalyzer() {
        keywords = new ArrayList<>();
        keywordSet = new LinkedHashSet<>();
//        keywordFreqMap = new HashMap<>();
        keywordFreqMap = new LinkedHashMap<>();
    }


    @Override
    public void recordKeyword(String keyword) {
        keywords.add(keyword);
        keywordSet.add(keyword);
        if(keywordFreqMap.containsKey(keyword)){
            keywordFreqMap.put(keyword,keywordFreqMap.get(keyword)+1);
        }
        else{
            keywordFreqMap.put(keyword,1);
        }
    }

    @Override
    public List<String> getAllKeywords() {
        return keywords;
    }


    @Override
    public Set<String> getUniqueKeywords(){
        return keywordSet;
    }

    @Override
    public List<KeywordFrequency> getKeywordWithFrequency(){
        List<KeywordFrequency> keywordFrequencyList = new ArrayList<>();
        for(String keyword : keywordFreqMap.keySet()){
            keywordFrequencyList.add(new KeywordFrequency(keyword,keywordFreqMap.get(keyword)));
        }
//        Collections.sort(keywordFrequencyList, new Comparator<KeywordFrequency>() {
//            @Override
//            public int compare(KeywordFrequency obj1, KeywordFrequency obj2) {
//                return obj1.getFreq() - obj2.getFreq();
//            }
//        });

//        Collections.sort(keywordFrequencyList,new KeywordComparator().reversed());

//        Collections.sort(keywordFrequencyList, (k1,k2)->k1.getFreq()-k2.getFreq());

        Collections.sort(keywordFrequencyList,new KeywordComparator().reversed());

// Alphabetical order
//        Collections.sort(keywordFrequencyList, (k1,k2)->k1.getKeyword().compareTo(k2.getKeyword()));

        return keywordFrequencyList;
    }



}
