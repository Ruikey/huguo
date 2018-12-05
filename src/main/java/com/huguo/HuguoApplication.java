package com.huguo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.huguo.mapper")//这个对应了项目中mapper（dao）所对应的包路径,这里要引用tk.mybatis.spring.annotation.MapperScan这个包
public class HuguoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuguoApplication.class, args);
    }
}
