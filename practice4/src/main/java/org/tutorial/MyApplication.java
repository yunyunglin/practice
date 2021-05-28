package org.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@EnableAutoConfiguration 讓springboot去找dependencies及其他配置
@SpringBootApplication //包含ComponentScan功能，會掃所有標示@Component, @Service, @Repository, @Controller的檔案
public class MyApplication {

	public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
