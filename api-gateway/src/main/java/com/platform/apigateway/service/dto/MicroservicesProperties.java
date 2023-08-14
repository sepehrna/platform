package com.platform.apigateway.service.dto;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Component
@ConfigurationProperties(prefix = "microservices")
public class MicroservicesProperties extends DataTransferObject {

    private List<Server> servers;

}
