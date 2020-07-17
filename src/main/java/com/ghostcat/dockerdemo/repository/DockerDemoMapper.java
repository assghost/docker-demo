package com.ghostcat.dockerdemo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ghostcat.dockerdemo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DockerDemoMapper extends BaseMapper<Demo> {
}
