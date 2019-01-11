package com.liori.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeneratorUtil {

    public static void testGetKey() {
        verifyBaseInfo();

        try {
            generateService();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        System.out.println("author: " + BaseInfoUtil.AUTHOR);
        System.out.println("version: " + BaseInfoUtil.VERSION);
        System.out.println("entityName: " + BaseInfoUtil.ENTITY_NAME);
        System.out.println("path: " + BaseInfoUtil.PATH);
    }


    private static void generateService() throws IOException, TemplateException {
        String fileName = BaseInfoUtil.ENTITY_NAME.trim() + "Service.java";

        // 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("user", "com.freemark.hello");
        dataMap.put("latestProduct", "User");
        dataMap.put("url", "Id");
        dataMap.put("name", "userName");
        // 加载模版文件
        Template temp = getTemplate(BaseInfoUtil.SERVICE_TEMP_PATH_NAME);
        //  生成数据
        File docFile = new File(BaseInfoUtil.PATH + fileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        // 输出文件
        temp.process(dataMap, out);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + fileName + " 文件创建成功 !");
    }

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

        if (StringUtils.isEmpty(BaseInfoUtil.PATH)) {
            throw new RuntimeException("请输入 path");
        }
    }
}
