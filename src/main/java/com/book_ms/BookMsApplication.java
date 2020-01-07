package com.book_ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.book_ms.mapper")//批量扫描mapper
@EnableTransactionManagement//事务管理器
@SpringBootApplication
public class BookMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookMsApplication.class, args);
	}

}
