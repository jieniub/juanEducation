package com.ljj.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class CourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.INPUT)
    private String id;


    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
