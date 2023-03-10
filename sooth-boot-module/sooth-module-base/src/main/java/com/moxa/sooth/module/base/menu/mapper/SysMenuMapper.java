package com.moxa.sooth.module.base.menu.mapper;

import com.alibaba.fastjson.JSONArray;
import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Param;
import com.moxa.sooth.module.base.menu.mapper.provider.SysMenuProvider;
import com.moxa.sooth.module.base.menu.model.SysMenuModel;
import com.moxa.sooth.module.base.menu.view.SysMenuListView;

import java.util.List;

@Mapper(SysMenuProvider.class)
public interface SysMenuMapper {

    JSONArray getMenu(Long userId);

    List<SysMenuListView> listMenuTree(@Param("model") SysMenuModel sysMenuModel);

}
