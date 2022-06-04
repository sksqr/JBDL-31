package com.example.L14redisdemo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {
    private String title;
    private String author;
    private String content;
    private Date publishDate;
}
