package com.ghostcat.dockerdemo.service;

import com.ghostcat.dockerdemo.dto.DemoDto;
import com.ghostcat.dockerdemo.entity.Demo;
import com.ghostcat.dockerdemo.repository.DockerDemoMapper;
import com.ghostcat.dockerdemo.vo.DemoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DockerDemoService {

    @Autowired
    private DockerDemoMapper dockerDemoMapper;

    public DemoVo selectDemoVo(DemoDto demoDto) {
        DemoVo demoVo = DemoVo.builder().build();
        Integer demoId = demoDto.getId();
        if (null != demoId) {
            Demo demo = dockerDemoMapper.selectById(demoId);
            demoVo.setDemo(demo);
        }
        return demoVo;
    }

    public DemoVo insertOrUpdateDemo(DemoDto demoDto) {
        DemoVo demoVo = DemoVo.builder().build();
        Integer demoId = demoDto.getId();
        if (null != demoId) {
            Demo newDemo = dockerDemoMapper.selectById(demoId);

            if (null != newDemo) {
                newDemo.setDemoName(demoDto.getDemoName());
                dockerDemoMapper.updateById(newDemo);
            }

            demoVo.setDemo(newDemo);
        } else {
            Demo newDemo = insertDemo(demoDto);
            demoVo.setDemo(newDemo);
        }

        return demoVo;
    }

    public Demo insertDemo(DemoDto demoDto) {

        String demoName = demoDto.getDemoName();
        if (!StringUtils.isEmpty(demoName)) {
            Demo newDemo = Demo.builder()
                    .demoName(demoName)
                    .creator("sys")
                    .editor("sys")
                    .build();
            dockerDemoMapper.insert(newDemo);
            return newDemo;
        }

        return null;
    }
}
