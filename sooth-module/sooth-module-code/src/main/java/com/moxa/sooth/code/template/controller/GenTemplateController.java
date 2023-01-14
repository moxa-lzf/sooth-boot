package com.moxa.sooth.code.template.controller;


import com.moxa.sooth.code.template.model.GenTemplateModel;
import com.moxa.sooth.code.template.service.IGenTemplateService;
import com.moxa.sooth.code.template.view.GenTemplate;
import com.moxa.sooth.core.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/generate/template")
public class GenTemplateController extends BaseController<IGenTemplateService, GenTemplate, GenTemplateModel> {
    public GenTemplateController() {
        super("模板配置");
    }
}
