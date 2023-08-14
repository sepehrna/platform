package com.platform.apigateway.service.mapper;

import com.platform.apigateway.domain.entities.Microservices;
import com.platform.apigateway.service.dto.MicroservicesProperties;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring"
        , uses = {JaxbApplicationUserDetailMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface MicroservicesMapper {

    Microservices map(MicroservicesProperties microservicesProperties);

}
