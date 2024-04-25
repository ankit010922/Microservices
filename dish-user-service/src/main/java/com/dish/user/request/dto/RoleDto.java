package com.dish.user.request.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    private String roleName;
    private String roleDescription;
    private String roleCode;
    private List<AuthorityDto> authorities;
}