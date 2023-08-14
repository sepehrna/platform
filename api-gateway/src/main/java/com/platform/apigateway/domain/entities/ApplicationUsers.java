package com.platform.apigateway.domain.entities;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationUsers extends Entity {

    private Set<ApplicationUserDetail> applicationUserDetails;

}
