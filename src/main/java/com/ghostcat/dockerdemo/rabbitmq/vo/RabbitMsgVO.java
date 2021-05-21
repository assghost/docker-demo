package com.ghostcat.dockerdemo.rabbitmq.vo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RabbitMsgVO implements Serializable {
    private String msg;

    private LocalDateTime sendTime;

    private String msgId;
}
