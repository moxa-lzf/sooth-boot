package com.moxa.sooth.core.menu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.sooth.core.menu.mapper.SysMenuMapper;
import com.moxa.sooth.core.menu.model.SysMenuEditModel;
import com.moxa.sooth.core.menu.model.SysMenuModel;
import com.moxa.sooth.core.menu.model.SysRolePermissionModel;
import com.moxa.sooth.core.menu.service.ISysMenuService;
import com.moxa.sooth.core.menu.service.ISysRolePermissionService;
import com.moxa.sooth.core.menu.table.SysRolePermission;
import com.moxa.sooth.core.menu.view.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenu, SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private ISysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysMenu> selectAuths(Long userId) {
        return sysMenuMapper.selectAuths(userId);
    }

    @Override
    public JSONArray getMenu(Long userId) {
        return sysMenuMapper.getMenu(userId);
    }

    @Override
    public List<SysMenu> selectTree(SysMenuModel sysMenuModel) {
        return templateMapper.selectTree(SysMenu.class, sysMenuModel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRolePermission(SysMenuEditModel sysMenuEditModel) {
        Long roleId = sysMenuEditModel.getRoleId();
        List<Long> permissionIds = sysMenuEditModel.getPermissionIds();
        SysRolePermissionModel sysRolePermissionModel = new SysRolePermissionModel();
        sysRolePermissionModel.setRoleId(roleId);
        Map<Long, Long> permissionMap = sysRolePermissionService.selectList(sysRolePermissionModel).stream()
                .collect(Collectors.toMap(SysRolePermission::getPermissionId, SysRolePermission::getId));
        if (permissionIds == null) {
            permissionIds = new ArrayList<>();
        }
        List<Long> newPermissionIds = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            if (permissionMap.remove(permissionId) == null) {
                newPermissionIds.add(permissionId);
            }
        }
        if (!newPermissionIds.isEmpty()) {
            List<SysRolePermission> sysRolePermissionList = newPermissionIds.stream().map(permissionId -> {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(roleId);
                sysRolePermission.setPermissionId(permissionId);
                return sysRolePermission;
            }).collect(Collectors.toList());
            sysRolePermissionService.batchInsert(sysRolePermissionList);
        }
        if (!permissionMap.isEmpty()) {
            sysRolePermissionService.deleteByIds(permissionMap.values().stream().collect(Collectors.toList()));
        }
    }
}
