package com.platform.apigateway.domain.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Server extends Entity {

    private String name;
    private String protocol;
    private String address;
    private String port;
    private String descriptorPath;
    private String openApiVersion;
    private Boolean isMandatory = true;

}
