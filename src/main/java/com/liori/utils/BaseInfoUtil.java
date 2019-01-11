package com.liori.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseInfoUtil {

    public static String SERVICE_TEMP_PATH_NAME = "ServiceTemplate.ftl";

    public static String SERVICE_IMPL_TEMP_PATH_NAME = "ServiceImplTemplate.ftl";

    public static String AUTHOR;

    public static String VERSION;

    public static String ENTITY_NAME;

    public static String PATH;

    public static String DESCRIPTION;

    @Value("${generator.author}")
    public void setAuthor(String author) {
        AUTHOR = author;
    }

    @Value("${generator.version}")
    public void setVersion(String version) {
        VERSION = version;
    }

    @Value("${generator.entityName}")
    public void setEntityName(String entityName) {
        ENTITY_NAME = entityName;
    }

    @Value("${generator.path}")
    public void setPath(String path) {
        PATH = path;
    }

    @Value("${generator.description}")
    public void setDescription(String description) {
        DESCRIPTION = description;
    }

}
