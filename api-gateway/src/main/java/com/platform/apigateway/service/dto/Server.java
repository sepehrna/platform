package com.platform.apigateway.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Server extends DataTransferObject {

    private String name;
    private String protocol;
    private String address;
    private String port;
    private String descriptorPath;

}
