package com.example.L14resttemplatedemo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BlogResponseBody implements Serializable {
    private String title;
    private String author;
    private String content;
    private Date publishDate;
}
