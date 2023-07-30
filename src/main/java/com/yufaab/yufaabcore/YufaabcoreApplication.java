package com.yufaab.yufaabcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.yufaab.yufaabcore"})
@EntityScan({"com.yufaab.yufaabcore"})
@EnableMongoRepositories({"com.yufaab.yufaabcore"})
@EnableFeignClients({"com.yufaab.yufaabcore"})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class YufaabcoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(YufaabcoreApplication.class, args);
	}

}
