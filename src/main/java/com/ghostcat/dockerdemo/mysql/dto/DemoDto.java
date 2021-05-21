package com.ghostcat.dockerdemo.mysql.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDto {
    private Integer id;

    private String demoName;
}
