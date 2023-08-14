package com.platform.apigateway.service.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement
@XmlType(propOrder = {"username", "password", "role" })
public class JaxbApplicationUserDetailDto {

    private String username;
    private String password;
    private String role;

    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public void setRole(String role) {
        this.role = role;
    }
}
