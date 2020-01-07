package com.book_ms;

import com.book_ms.mapper.ReaderMapper;
import com.book_ms.pojo.Reader;
import com.book_ms.service.readerType.ReaderTypeService;
import com.book_ms.service.user.ReaderService;
import com.book_ms.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMsApplicationTests {
	@Autowired
	ReaderMapper readerMapper;
	@Autowired
	UserService userService;
	@Autowired
	ReaderTypeService readerTypeService;
	@Autowired
	ReaderService readerService;

	@Test
	public void testReaderMapper() {
		Reader reader = readerMapper.findReaderByRdID(12);
//		Reader reader = readerMapper.findReaderByRdIdAndRdPwd(1, "123");
		System.out.println(reader);
	}

	@Test
	public void testUserService() {
		System.out.println(userService.userLogin("1", "1234"));
	}

	@Test
	public void testReaderTypeService() {
		System.out.println(readerTypeService.findRdTypeNameByRdType(1));
	}

	@Test
	@Transactional
	public void testTransactional() {
		try {
			Reader reader = readerMapper.findReaderByRdID(1);
			reader.setRdName("aaa");
			readerMapper.updateReader(reader, 1);
			reader.setRdEmail("1111");
			readerMapper.updateReader(reader, 1);
			InputStream in = new FileInputStream("aaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws ParseException {
		String date = "2019-12-13";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(format.parse(date));
		System.out.println(Date.valueOf(date).toString());
	}

}
