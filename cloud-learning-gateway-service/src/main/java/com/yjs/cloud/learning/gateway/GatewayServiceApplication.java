package com.yjs.cloud.learning.gateway;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * 启动入口
 * @author Bill.lai
 * @since 2019/8/21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client ->
						client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000))
				.tcpConfiguration(client ->
						client.doOnConnected(conn -> conn
								.addHandlerLast(new ReadTimeoutHandler(10))
								.addHandlerLast(new WriteTimeoutHandler(10))));

		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
}
