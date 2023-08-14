package com.platform.apigateway.service.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "application-users")
public class JaxbApplicationUsersDto {

    private Set<JaxbApplicationUserDetailDto> applicationUserDetails;

    @XmlElement(name = "user")
    public void setApplicationUserDetails(Set<JaxbApplicationUserDetailDto> applicationUserDetails) {
        this.applicationUserDetails = applicationUserDetails;
    }
}
