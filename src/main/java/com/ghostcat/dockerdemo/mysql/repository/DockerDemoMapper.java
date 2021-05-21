package com.ghostcat.dockerdemo.mysql.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ghostcat.dockerdemo.mysql.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DockerDemoMapper extends BaseMapper<Demo> {
}
