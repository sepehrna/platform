package com.platform.apigateway.service.mapper;


import com.platform.apigateway.domain.entities.ApplicationUsers;
import com.platform.apigateway.service.dto.JaxbApplicationUsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring"
        , uses = {JaxbApplicationUserDetailMapper.class}
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface JaxbApplicationUsersMapper {

    ApplicationUsers map(JaxbApplicationUsersDto jaxbApplicationUsersDto);

}
