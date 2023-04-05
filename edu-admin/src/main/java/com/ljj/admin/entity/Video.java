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
 * 课程视频
 * </p>
 *
 * @author ljj
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String courseId;
    private String chapterId;
    private String title;
    private String videoSourceId;

    private String videoOriginalName;
    private Integer sort;
    private Long playCount;
    private Boolean isFree;
    @TableField(fill = FieldFill.DEFAULT)
    private Float duration;
    private String status;
    private Long size;
    private Long version;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
