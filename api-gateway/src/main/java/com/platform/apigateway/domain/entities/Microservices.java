package com.platform.apigateway.domain.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Microservices extends Entity {

    private List<Server> servers;

}
