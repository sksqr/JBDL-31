package com.gfg.collections;

import java.util.List;
import java.util.Set;

public class UseLibDemo {

    public static void main(String[] args) {

        //To record keyword in Lib
        String keyword = "Java";
        KeywordAnalyzerInterface keywordAnalyzerInterface = new BasicKeywordAnalyzer();
        keywordAnalyzerInterface.recordKeyword(keyword);
        keywordAnalyzerInterface.recordKeyword("HTML");
        keywordAnalyzerInterface.recordKeyword("C++");
        keywordAnalyzerInterface.recordKeyword("C");
        keywordAnalyzerInterface.recordKeyword("Java");
        keywordAnalyzerInterface.recordKeyword("C");
        keywordAnalyzerInterface.recordKeyword("HTML");

        //Read all keywords
        List<String> keywords = keywordAnalyzerInterface.getAllKeywords();
        for(String kWord : keywords){
            System.out.println(kWord);
        }

        //Read unique keywords
        System.out.println("Unique:");
        Set<String> keywordsSet = keywordAnalyzerInterface.getUniqueKeywords();
        for(String kWord : keywordsSet){
            System.out.println(kWord);
        }


        System.out.println("keywords with frequency:");
        List<KeywordFrequency> keywordFrequencyList = keywordAnalyzerInterface.getKeywordWithFrequency();
        for(KeywordFrequency keywordFrequency: keywordFrequencyList){
            System.out.println(keywordFrequency);
        }
    }

}
