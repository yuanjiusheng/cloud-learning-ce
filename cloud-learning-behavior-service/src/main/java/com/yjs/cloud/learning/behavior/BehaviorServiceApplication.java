package com.yjs.cloud.learning.behavior;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 * @author Bill.lai
 * @since 2019/8/21
 */
@SpringBootApplication
@MapperScan("com.yjs.cloud.learning.behavior.**.mapper")
@EnableTransactionManagement
public class BehaviorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BehaviorServiceApplication.class, args);
	}

}
