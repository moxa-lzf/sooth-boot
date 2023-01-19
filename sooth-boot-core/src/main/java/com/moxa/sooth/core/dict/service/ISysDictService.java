package com.moxa.sooth.core.dict.service;

import com.moxa.dream.template.service.IService;
import com.moxa.sooth.core.dict.view.SysDict;


public interface ISysDictService extends IService<SysDict, SysDict> {


    String getDictItemName(String code, String value);
}
