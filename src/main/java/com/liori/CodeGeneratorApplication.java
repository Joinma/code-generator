package com.liori;

import com.liori.utils.generate.GeneratorUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
        // Freemark 生成文件
        GeneratorUtil.generate();
    }
}

