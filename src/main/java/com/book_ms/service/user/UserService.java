package com.book_ms.service.user;

import com.book_ms.mapper.ReaderMapper;
import com.book_ms.mapper.ReaderTypeMapper;
import com.book_ms.pojo.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author xiao
 * @Time 2019/11/26
 * @Describe 用户的Service层
 **/
@Service
public class UserService {
    @Autowired
    ReaderMapper readerMapper;
    @Autowired
    ReaderTypeMapper readerTypeMapper;

    //读者登录service
    public HashMap userLogin(String username, String password){
        Integer rdID = Integer.parseInt(username);
        String rdPwd = password;
        String msg = "";
        String gotoPath = "login/login";
        HashMap<String, Object> data = new HashMap<String, Object>();
        Reader reader = readerMapper.findReaderByRdIdAndRdPwd(rdID, rdPwd);
        data.put("user", reader);
        if(reader != null) {
            if(reader.getRdStatus().equals("有效")) {
                data.put("userType", readerTypeMapper.findRdTypeNameByRdType(reader.getRdType()));
                msg = "登录成功！";
                gotoPath = "main/main";
            }else {
                msg = "非有效借阅证！";
            }
        }else {
            msg = "账号或密码错误！";
        }
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }

}
