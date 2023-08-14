package com.platform.apigateway.service.mapper;

import com.platform.apigateway.domain.entities.Server;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface ServerMapper {

    com.platform.apigateway.service.dto.Server map(Server server);

}
