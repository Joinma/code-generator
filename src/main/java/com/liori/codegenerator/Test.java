package com.liori.codegenerator;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class Test {

    @Value("{test.key}")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static void main(String[] args) throws IOException, TemplateException {

        System.out.println();

//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
//        cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile("classpath:templates"));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        // step3 创建数据模型
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("user", "com.freemark.hello");
//        dataMap.put("latestProduct", "User");
//        dataMap.put("url", "Id");
//        dataMap.put("name", "userName");
//        // step4 加载模版文件
//        Template temp = cfg.getTemplate("test.ftl");
//        // step5 生成数据
//        File docFile = new File("F:" + "\\" + "User.java");
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
//        // step6 输出文件
//        temp.process(dataMap, out);
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
    }
}