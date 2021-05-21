package com.ghostcat.dockerdemo.mysql.vo;

import com.ghostcat.dockerdemo.mysql.entity.Demo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {
    private Demo demo;
}
