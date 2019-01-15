package com.liori.utils.generate;

import com.liori.utils.baseinfo.BaseInfoUtil;
import com.liori.utils.database.DatabaseUtil;
import com.liori.utils.word.CamelCaseUtil;
import com.liori.utils.word.SingularPluralConversionUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeneratorUtil {


    public static void generate() {
        verifyBaseInfo();
        try {
            generateService();
            generateServiceImpl();
            generateController();
            if ("1".equals(BaseInfoUtil.GENERATE_MODEL_AND_MAPPER)) {
                System.out.println("------------生成 Model、Mapper------------");
                generateModel();
                generateMapper();
            } else {
                System.out.println("------------不生成 Model、Mapper------------");
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验基础信息
     */
    private static void verifyBaseInfo() {
        if (StringUtils.isEmpty(BaseInfoUtil.AUTHOR)) {
            throw new RuntimeException("请输入 generator.author");
        }

        if (StringUtils.isEmpty(BaseInfoUtil.VERSION)) {
            throw new RuntimeException("请输入 generator.version");
        }

        if (StringUtils.isEmpty(BaseInfoUtil.ENTITY_NAME)) {
            throw new RuntimeException("请输入 generator.entityName");
        }

        if (StringUtils.isEmpty(BaseInfoUtil.DESCRIPTION)) {
            throw new RuntimeException("请输入 generator.description");
        }

        if (StringUtils.isEmpty(BaseInfoUtil.PATH)) {
            throw new RuntimeException("请输入 generator.path");
        }

        if (StringUtils.isEmpty(BaseInfoUtil.GENERATE_MODEL_AND_MAPPER)) {
            throw new RuntimeException("请输入 generator.generateModelAndMapper");
        }
    }

    /**
     * 创建 Service 文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateService() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME + "Service.java";
        String path = BaseInfoUtil.PATH + "\\service\\" + BaseInfoUtil.ENTITY_NAME.toLowerCase();
        createTemplateFile(fileName, path, BaseInfoUtil.SERVICE_TEMP_PATH_NAME);
        System.out.println(fileName + " 文件创建成功 !");
    }

    /**
     * 创建 ServiceImple 文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateServiceImpl() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME + "ServiceImpl.java";
        String path = BaseInfoUtil.PATH + "\\" + "service\\" + BaseInfoUtil.ENTITY_NAME.toLowerCase() + "\\impl";
        createTemplateFile(fileName, path, BaseInfoUtil.SERVICE_IMPL_TEMP_PATH_NAME);
        System.out.println(fileName + " 文件创建成功 !");
    }

    /**
     * 创建 Controller 文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateController() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME.trim() + "Controller.java";
        String path = BaseInfoUtil.PATH + "\\" + "controller\\" + BaseInfoUtil.ENTITY_NAME.toLowerCase();
        createTemplateFile(fileName, path, BaseInfoUtil.CONTROLLER_TEMP_PATH_NAME);
        System.out.println(fileName + " 文件创建成功 !");
    }

    /**
     * 创建 Model 文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateModel() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME.trim() + ".java";
        String path = BaseInfoUtil.PATH + "\\" + "model\\" + BaseInfoUtil.ENTITY_NAME.toLowerCase();
        createTemplateFile(fileName, path, BaseInfoUtil.MODEL_TEMP_PATH_NAME);
        System.out.println(fileName + " 文件创建成功 !");
    }

    /**
     * 创建 Mapper 文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void generateMapper() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME.trim() + "Mapper.java";
        String path = BaseInfoUtil.PATH + "\\" + "mapper\\" + BaseInfoUtil.ENTITY_NAME.toLowerCase();
        createTemplateFile(fileName, path, BaseInfoUtil.MAPPER_TEMP_PATH_NAME);
        System.out.println(fileName + " 文件创建成功 !");
    }

    /**
     * 生成模板文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    private static void createTemplateFile(String fileName, String path, String serviceImplTempPathName) throws TemplateException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path + "不存在，创建");
            boolean isSuccess = file.mkdirs();
            if (!isSuccess) {
                System.out.println("目录创建失败");
            }
        }

        if (!path.endsWith("\\") || path.endsWith("/")) {
            path = path + "\\";
        }
        File docFile = new File(path + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(docFile);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
        Writer out = new BufferedWriter(outputStreamWriter);
        Map<String, Object> dataMap = getDataMap();
        Template temp = getTemplate(serviceImplTempPathName);
        temp.process(dataMap, out);
    }

    /**
     * 获取数据模型
     *
     * @return 数据模型
     */
    private static Map<String, Object> getDataMap() {
        Map<String, Object> dataMap = new HashMap<>(20);
        dataMap.put("entityNameLowerCase", BaseInfoUtil.ENTITY_NAME.trim().toLowerCase());
        dataMap.put("entityNameLowerCasePlural", SingularPluralConversionUtil.changeSinglarToPlural(BaseInfoUtil.ENTITY_NAME.trim().toLowerCase()));
        dataMap.put("entityName", BaseInfoUtil.ENTITY_NAME.trim());
        String entityNamePlural = SingularPluralConversionUtil.changeSinglarToPlural(BaseInfoUtil.ENTITY_NAME.trim());
        dataMap.put("entityNamePlural", entityNamePlural);
        String entityNameCamelCase = CamelCaseUtil.changeUnderlineToCamelCaseFirstLowerCase(BaseInfoUtil.ENTITY_NAME.trim());
        dataMap.put("entityNameCamelCase", entityNameCamelCase);
        String entityNameCamelCasePlural = SingularPluralConversionUtil.changeSinglarToPlural(entityNameCamelCase);
        dataMap.put("entityNameCamelCasePlural", entityNameCamelCasePlural);
        dataMap.put("author", BaseInfoUtil.AUTHOR);
        dataMap.put("version", BaseInfoUtil.VERSION);
        dataMap.put("description", BaseInfoUtil.DESCRIPTION);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(new Date());
        dataMap.put("createTime", dateTime);
        if ("1".equals(BaseInfoUtil.GENERATE_MODEL_AND_MAPPER)) {
            dataMap.put("tableColumns", DatabaseUtil.getTableColumns());
        }

        return dataMap;
    }

    /**
     * 获取模板
     *
     * @param templatePathName 模板的路径及名称
     * @return Template
     */
    private static Template getTemplate(String templatePathName) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        Template temp = null;
        try {
            String resourceLocation = "classpath:templates";
            cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile(resourceLocation));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            temp = cfg.getTemplate(templatePathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
