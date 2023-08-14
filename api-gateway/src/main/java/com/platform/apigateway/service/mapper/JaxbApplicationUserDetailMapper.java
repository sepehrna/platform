package com.platform.apigateway.service.mapper;

import com.platform.apigateway.domain.entities.ApplicationUsers;
import com.platform.apigateway.service.dto.JaxbApplicationUserDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface JaxbApplicationUserDetailMapper {

    JaxbApplicationUserDetailDto map(ApplicationUsers applicationUsers);

}
