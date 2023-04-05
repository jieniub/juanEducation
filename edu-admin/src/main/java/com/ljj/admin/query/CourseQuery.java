package com.ljj.admin.query;

import lombok.Data;

@Data
public class CourseQuery {
    private Integer startNumSort;
    private Integer gmtCreateSort;
    private String subjectId;
    private String subjectParentId;
}
