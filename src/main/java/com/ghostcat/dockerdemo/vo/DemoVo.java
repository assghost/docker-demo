package com.ghostcat.dockerdemo.vo;

import com.ghostcat.dockerdemo.entity.Demo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {
    private Demo demo;
}
