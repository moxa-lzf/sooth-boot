package com.moxa.sooth.module.code.gen.util;

import cn.hutool.core.io.IoUtil;
import com.moxa.sooth.module.base.core.exception.SoothException;
import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * 模板工具类
 */
public class TemplateUtil {
    /**
     * 获取模板渲染后的内容
     *
     * @param content   模板内容
     * @param dataModel 数据模型
     */
    public static String getContent(String content, Map<String, Object> dataModel) {
        if (dataModel.isEmpty()) {
            return content;
        }

        StringReader reader = new StringReader(content);
        StringWriter sw = new StringWriter();
        try {
            // 渲染模板
            String name = dataModel.get("name").toString();
            Template template = new Template(name, reader, null, "utf-8");
            template.process(dataModel, sw);
            return sw.toString();
        } catch (Exception e) {
            throw new SoothException("渲染模板失败，请检查模板语法：" + e.getMessage(), e);
        } finally {
            IoUtil.close(reader);
            IoUtil.close(sw);
        }
    }
}
