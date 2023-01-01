package com.moxa.sooth.core.dict.view;

import com.moxa.dream.system.annotation.Extract;
import com.moxa.dream.system.annotation.View;
import com.moxa.sooth.core.base.common.aspect.annotation.Dict;
import com.moxa.sooth.core.base.dream.DictExtractor;
import com.moxa.sooth.core.base.entity.BaseDict;
import com.moxa.sooth.core.dict.table.$SysDictItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@View($SysDictItem.class)
public class SysDictItem extends BaseDict {
    /**
     * id
     */
    private Long id;

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典项文本
     */

    private String itemText;

    /**
     * 字典项值
     */

    private String itemValue;

    /**
     * 描述
     */

    private String description;

    /**
     * 排序
     */

    private Double orderNo;
}
