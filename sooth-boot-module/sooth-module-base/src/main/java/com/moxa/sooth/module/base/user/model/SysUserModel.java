package com.moxa.sooth.module.base.user.model;

import lombok.Data;

import java.util.List;

@Data
public class SysUserModel {
    private String username;
    private String realname;
    private String phone;
    private List<Long> deptIds;
}
