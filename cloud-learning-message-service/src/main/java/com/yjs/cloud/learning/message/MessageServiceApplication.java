package com.yjs.cloud.learning.message;

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
@MapperScan("com.yjs.cloud.learning.message.**.mapper")
@EnableTransactionManagement
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

}
