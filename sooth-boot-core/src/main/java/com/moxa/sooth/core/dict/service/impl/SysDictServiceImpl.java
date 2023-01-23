package com.moxa.sooth.core.dict.service.impl;

import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.sooth.core.base.exception.SoothBootException;
import com.moxa.sooth.core.dict.mapper.SysDictMapper;
import com.moxa.sooth.core.dict.model.SysDictCodeExistModel;
import com.moxa.sooth.core.dict.service.ISysDictItemService;
import com.moxa.sooth.core.dict.service.ISysDictService;
import com.moxa.sooth.core.dict.view.SysDict;
import com.moxa.sooth.core.dict.view.SysDictItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDict, SysDict> implements ISysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private ISysDictItemService dictItemService;

    @Override
    public String getDictItemName(String code, Object value) {
        String val;
        if(value instanceof Boolean){
            Boolean bool=(Boolean) value;
            val=bool?"1":"0";
        }else{
            val=String.valueOf(value);
        }
        return sysDictMapper.getDictItemName(code, val);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Object id) {
        dictItemService.deleteByDictId(id);
        return super.deleteById(id);
    }

    private void checkCodeExist(SysDict sysDict) {
        SysDictCodeExistModel dictCodeExistModel = new SysDictCodeExistModel();
        dictCodeExistModel.setCode(sysDict.getCode());
        if (templateMapper.exist(SysDict.class, dictCodeExistModel)) {
            throw new SoothBootException("字典编码" + sysDict.getCode() + "已经存在");
        }
    }

    @Override
    public int insert(SysDict sysDict) {
        checkCodeExist(sysDict);
        return super.insert(sysDict);
    }
}
