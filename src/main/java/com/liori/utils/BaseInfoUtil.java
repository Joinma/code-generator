package com.liori.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p><b>基础信息工具类</b></p>
 * <p>freemark 模板文件的位置名称</p>
 * <p>从 yml 配置文件中获取信息，给 freemark 生成文件时用</p>
 *
 * @author liori
 * @since 0.0.1
 */
@Component
public class BaseInfoUtil {

    protected static final String SERVICE_TEMP_PATH_NAME = "ServiceTemplate.ftl";

    protected static final String SERVICE_IMPL_TEMP_PATH_NAME = "ServiceImplTemplate.ftl";

    protected static final String CONTROLLER_TEMP_PATH_NAME = "ControllerTemplate.ftl";

    protected static String AUTHOR;

    protected static String VERSION;

    protected static String ENTITY_NAME;

    protected static String PATH;

    protected static String DESCRIPTION;

    @Value("${generator.author}")
    protected void setAuthor(String author) {
        AUTHOR = author;
    }

    @Value("${generator.version}")
    protected void setVersion(String version) {
        VERSION = version;
    }

    @Value("${generator.entityName}")
    protected void setEntityName(String entityName) {
        ENTITY_NAME = entityName.trim();
    }

    @Value("${generator.path}")
    protected void setPath(String path) {
        PATH = path;
    }

    @Value("${generator.description}")
    protected void setDescription(String description) {
        DESCRIPTION = description;
    }

}
