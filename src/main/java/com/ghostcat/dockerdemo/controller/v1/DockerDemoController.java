package com.ghostcat.dockerdemo.controller.v1;

import com.ghostcat.dockerdemo.dto.DemoDto;
import com.ghostcat.dockerdemo.service.DockerDemoService;
import com.ghostcat.dockerdemo.vo.DemoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AssGhost
 */
@RestController
@RequestMapping("api/v1/demo")
public class DockerDemoController {

    @Autowired
    private DockerDemoService dockerDemoService;

    @RequestMapping("/")
    public String api() {
        return "Hello Docker World api";
    }

    @RequestMapping("/get")
    public DemoVo select(DemoDto demoDto) {
        return dockerDemoService.selectDemoVo(demoDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DemoVo update(@RequestBody DemoDto demoDto) {
        DemoVo demoVo = dockerDemoService.insertOrUpdateDemo(demoDto);

        return demoVo;
    }
}
