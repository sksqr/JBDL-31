package com.gfg;

import com.gfg.collections.BasicKeywordAnalyzer;
import com.gfg.collections.KeywordAnalyzerInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KeywordSearchDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private KeywordAnalyzerInterface keywordAnalyzerInterface;

    public KeywordSearchDemo(KeywordAnalyzerInterface keywordAnalyzerInterface) {
        this.keywordAnalyzerInterface = keywordAnalyzerInterface;
    }

    public static void main(String[] args) {

        KeywordAnalyzerInterface keywordAnalyzerInterface = new BasicKeywordAnalyzer();
        KeywordSearchDemo keywordSearchDemo = new KeywordSearchDemo(keywordAnalyzerInterface);

        keywordSearchDemo.search("Mobile");
        keywordSearchDemo.search("cap");

        System.out.println(keywordAnalyzerInterface.getAllKeywords());

    }

    public String search(String keyword){

        executorService.submit(()->{keywordAnalyzerInterface.recordKeyword(keyword);});

        return "result";
    }
}
