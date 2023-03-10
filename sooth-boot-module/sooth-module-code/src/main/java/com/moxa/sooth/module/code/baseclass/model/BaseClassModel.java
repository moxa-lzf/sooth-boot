package com.moxa.sooth.module.code.baseclass.model;

import com.moxa.dream.template.annotation.Conditional;
import com.moxa.dream.template.condition.ContainsCondition;
import lombok.Data;

@Data
public class BaseClassModel {
    @Conditional(ContainsCondition.class)
    private String className;

}
