package com.yjs.cloud.learning.setting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 * @author Bill.lai
 * @since 2019/8/21
 */
@SpringBootApplication
@MapperScan("com.yjs.cloud.learning.setting.**.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
public class SettingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingServiceApplication.class, args);
	}

}
