package com.liori.utils.baseinfo;

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

    public static final String SERVICE_TEMP_PATH_NAME = "ServiceExtendsBaseTemplate.ftl";

    public static final String SERVICE_IMPL_TEMP_PATH_NAME = "ServiceImplExtendsBaseTemplate.ftl";

    public static final String CONTROLLER_TEMP_PATH_NAME = "ControllerExtendsTemplate.ftl";

    public static final String MODEL_TEMP_PATH_NAME = "ModelTemplate.ftl";

    public static final String MAPPER_TEMP_PATH_NAME = "MapperTemplate.ftl";

    public static String AUTHOR;

    public static String VERSION;

    public static String ENTITY_NAME;

    public static String PATH;

    public static String DESCRIPTION;

    public static String DATABASE_USERNAME;

    public static String DATABASE_PASSWORD;

    public static String DATABASE_CATALOG;

    public static String DATABASE_TABLE_NAME;

    public static String DATABASE_HOST;

    public static String DATABASE_PORT;

    public static String GENERATE_TYPE;


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

    @Value("${database.username}")
    protected void setDatabaseUsername(String username) {
        DATABASE_USERNAME = username;
    }

    @Value("${database.password}")
    protected void setDatabasePassword(String password) {
        DATABASE_PASSWORD = password;
    }

    @Value("${database.catalog}")
    protected void setDatabaseCatalog(String catalog) {
        DATABASE_CATALOG = catalog;
    }

    @Value("${database.tableName}")

    protected void setDatabaseTableName(String tableName) {
        DATABASE_TABLE_NAME = tableName;
    }

    @Value("${database.host}")
    protected void setDatabaseHost(String host) {
        DATABASE_HOST = host;
    }

    @Value("${database.port}")
    protected void setDatabasePort(String port) {
        DATABASE_PORT = port;
    }

    @Value("${generator.generateType}")
    protected void setGenerateType(String generateType) {
        GENERATE_TYPE = generateType;
    }

}
