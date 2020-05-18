package com.zjmzxfzhl.modules.flowable.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.Result;
import com.zjmzxfzhl.common.aspect.annotation.SysLogAuto;
import com.zjmzxfzhl.common.base.BaseController;
import com.zjmzxfzhl.modules.flowable.entity.FlowableForm;
import com.zjmzxfzhl.modules.flowable.service.FlowableFormService;

/**
 * 流程Controller
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/flowable/form")
public class FlowableFormController extends BaseController {
    @Autowired
    private FlowableFormService flowableFormService;

    /**
     * 自定义查询列表
     * 
     * @param flowableForm
     * @param current
     * @param size
     * @return
     */
    @RequiresPermissions("flowable:form:list")
    @GetMapping(value = "/list")
    public Result list(FlowableForm flowableForm, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<FlowableForm> pageList = flowableFormService.list(new Page<FlowableForm>(current, size), flowableForm);
        return Result.ok(pageList);
    }

    @RequiresPermissions("flowable:form:list")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        FlowableForm flowableForm = flowableFormService.getById(id);
        return Result.ok(flowableForm);
    }

    /**
     * @功能：新增
     * @param flowableForm
     * @return
     */
    @SysLogAuto(value = "新增流程表单")
    @RequiresPermissions("flowable:form:save")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.save(flowableForm);
        return Result.ok();
    }

    /**
     * @功能：修改
     * @param flowableForm
     * @return
     */
    @SysLogAuto(value = "修改流程表单")
    @RequiresPermissions("flowable:form:update")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.updateById(flowableForm);
        return Result.ok();
    }

    /**
     * @功能：批量删除
     * @param ids
     * @return
     */
    @SysLogAuto(value = "删除流程表单")
    @RequiresPermissions("flowable:form:delete")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            flowableFormService.removeByIds(Arrays.asList(idsArr));
        } else {
            flowableFormService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
