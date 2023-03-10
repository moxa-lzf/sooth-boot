package com.moxa.sooth.module.base.dept.controller;


import com.moxa.sooth.module.base.core.controller.BaseController;
import com.moxa.sooth.module.base.core.entity.Result;
import com.moxa.sooth.module.base.dept.model.SysDeptModel;
import com.moxa.sooth.module.base.dept.service.ISysDeptService;
import com.moxa.sooth.module.base.dept.view.SysDept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController extends BaseController<ISysDeptService, SysDept, SysDeptModel> {
    public SysDeptController() {
        super("部门管理");
    }

    @GetMapping("listTree")
    public Result listTree(SysDeptModel deptModel) {
        return Result.ok(service.listTree(deptModel));
    }
}
