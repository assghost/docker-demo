package com.ghostcat.dockerdemo.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dkd_demo")
public class Demo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("demo_name")
    private String demoName;

    @JsonIgnore
    @TableField("update_time")
    private LocalDateTime updateTime;

    @JsonIgnore
    private String editor;

    @JsonIgnore
    @TableField("create_time")
    private LocalDateTime createTime;

    @JsonIgnore
    private String creator;
}
