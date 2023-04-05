package com.ljj.front.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {

    private String id;
    private String title;
    private List<String> children = new ArrayList<>();
}
