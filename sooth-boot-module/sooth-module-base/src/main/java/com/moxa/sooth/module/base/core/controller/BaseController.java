package com.moxa.sooth.module.base.core.controller;

import cn.hutool.core.util.StrUtil;
import com.moxa.dream.system.config.Page;
import com.moxa.dream.template.service.IService;
import com.moxa.sooth.module.base.core.annotation.AutoLog;
import com.moxa.sooth.module.base.core.entity.PageModel;
import com.moxa.sooth.module.base.core.entity.Result;
import com.moxa.sooth.module.base.core.exception.SoothException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<Service extends IService, EditView, SearchModel> implements ModuleController {
    @Autowired
    protected Service service;
    private String bizModule;

    public BaseController(String bizModule) {
        if (StrUtil.isBlank(bizModule)) {
            throw new SoothException("业务模块名不能为空");
        }
        this.bizModule = bizModule;
    }

    //    @InterfacePermission("主键查询")
    @ResponseBody
    @GetMapping(value = "/get")
    public Result get(@RequestParam(name = "id") Long id) {
        Object result = service.selectById(id);
        return Result.ok(result);
    }

    //    @InterfacePermission("查询单条")
    @ResponseBody
    @GetMapping("getOne")
    public Result getOne(SearchModel searchModel) {
        Object result = service.selectOne(searchModel);
        return Result.ok(result);
    }

    //    @InterfacePermission("分页查询")
    @ResponseBody
    @GetMapping("page")
    public Result selectPage(SearchModel searchModel, PageModel pageModel) {
        Page page = service.selectPage(searchModel, pageModel.toPage());
        return Result.ok(page);
    }

    //    @InterfacePermission("查询")
    @ResponseBody
    @GetMapping("list")
    public Result selectList(SearchModel searchModel) {
        List resultList = service.selectList(searchModel);
        return Result.ok(resultList);
    }

    //    @InterfacePermission("新增")
    @AutoLog(value = "新增")
    @ResponseBody
    @PostMapping(value = "/save")
    public Result save(@RequestBody EditView editView) {
        if (retBool(service.insert(editView))) {
            return Result.ok(null, "添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    //    @InterfacePermission("非空修改")
    @AutoLog(value = "非空修改")
    @ResponseBody
    @PutMapping(value = "/edit")
    public Result putEdit(@RequestBody EditView editView) {
        if (retBool(service.updateNonById(editView))) {
            return Result.ok(null, "编辑成功");
        } else {
            return Result.error("编辑失败");
        }
    }

    //    @InterfacePermission("修改")
    @AutoLog(value = "修改")
    @ResponseBody
    @PostMapping(value = "/edit")
    public Result postEdit(@RequestBody EditView editView) {
        if (retBool(service.updateById(editView))) {
            return Result.ok(null, "编辑成功");
        } else {
            return Result.error("编辑失败");
        }
    }

    //    @InterfacePermission("删除")
    @AutoLog(value = "删除")
    @ResponseBody
    @DeleteMapping(value = "/remove")
    public Result remove(@RequestParam(name = "id") Long id) {
        if (retBool(service.deleteById(id))) {
            return Result.ok(null, "删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    //    @InterfacePermission("批量删除")
    @AutoLog(value = "批量删除")
    @ResponseBody
    @DeleteMapping(value = "/removeBatch")
    public Result removeBatch(@RequestBody List<Long> ids) {
        if (retBool(service.deleteByIds(ids))) {
            return Result.ok(null, "批量删除成功");
        } else {
            return Result.error("批量删除失败");
        }
    }

    protected boolean retBool(int value) {
        return value > 0;
    }

    @Override
    public String getBizModule() {
        return bizModule;
    }
}
