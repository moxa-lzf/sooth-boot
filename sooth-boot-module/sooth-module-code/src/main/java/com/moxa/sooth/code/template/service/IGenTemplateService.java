package com.moxa.sooth.code.template.service;

import com.moxa.dream.template.service.IService;
import com.moxa.sooth.code.template.view.GenTemplate;
import com.moxa.sooth.code.template.view.GenTemplateList;
import com.moxa.sooth.core.base.entity.Result;

import java.util.List;
import java.util.Map;


public interface IGenTemplateService extends IService<GenTemplateList, GenTemplate> {

    void preview(long tableId);

    List<GenTemplate> selectByGroupId(Long groupId);

}
