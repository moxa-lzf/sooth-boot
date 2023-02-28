package com.moxa.sooth.core.menu.model;

import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.EqCondition;
import lombok.Data;

@Data
public class SysMenuTypeModel {
    @Conditional(value = EqCondition.class, filterNull = false)
    private Integer menuType;

}