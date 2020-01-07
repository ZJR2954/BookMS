package com.book_ms.service.user;

import com.book_ms.mapper.ReaderMapper;
import com.book_ms.pojo.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author xiao
 * @Time 2019/11/30
 * @Describe TODO
 **/
@Service
public class ReaderService {
    @Autowired
    ReaderMapper readerMapper;

    public HashMap findReaderByRdID(Integer rdID) {
        HashMap<String, Object> data = new HashMap<>();
        Reader reader = readerMapper.findReaderByRdID(rdID);
        if(reader != null) {
            data.put("reader", reader);
        }
        return data;
    }

    public List<Reader> findReaders() {
        return readerMapper.findReaders();
    }

    public List<Reader> easyFindReaders(Integer rdID, Integer rdType, String rdDept, String RdName) {
        List<Reader> readersList = new ArrayList<>();
        if ((rdID != null) && (rdID > 0)) {
            Reader reader = readerMapper.findReaderByRdID(rdID);
            if(reader != null) {
                readersList.add(reader);
            }
        }else {
            readersList = readerMapper.easyFindReaders(rdType, rdDept, RdName);
        }
        return readersList;
    }

    public List<Reader> findReadersByRdDeptAndRdName(Integer rdType, String rdDept, String RdName) {
        return readerMapper.findReadersByRdDeptAndRdName(rdType, rdDept, RdName);
    }

    public HashMap addReader(Reader reader, MultipartFile rdPho) {
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        String result = "";
        try {
            if (null != rdPho && !rdPho.isEmpty()) {
                reader.setRdPhoto(rdPho.getBytes());
            }
            List<Reader> readersList = readerMapper.findReadersByRdDeptAndRdName(reader.getRdType(), reader.getRdDept(), reader.getRdName());
            if(readersList.size() > 0 && !readersList.get(0).getRdID().equals(reader.getRdID())) {
                msg += "信息重复，办理失败！";
                gotoPath = "main/common/operateFail";
            }else {
                readerMapper.insertReader(reader);
                msg += "办理成功！";
                readersList = readerMapper.findReadersByRdDeptAndRdName(reader.getRdType(), reader.getRdDept(), reader.getRdName());
                Reader r = readersList.get(readersList.size()-1);
                result += "借阅证号：" + r.getRdID() + " 初始密码为123";
                gotoPath = "main/common/operateSuccess";
            }
        }catch (IOException e) {
            msg += "办理失败，请重新操作！";
            gotoPath = "main/common/operateFail";
            e.printStackTrace();
        }
        data.put("msg", msg);
        data.put("result", result);
        data.put("gotoPath", gotoPath);
        return data;
    }

    public HashMap updateReader(Reader newReader, MultipartFile rdPho, Integer rdID) {
        Reader oldReader = readerMapper.findReaderByRdID(rdID);
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        try {
            if (rdPho != null && !rdPho.isEmpty()) {
                oldReader.setRdPhoto(rdPho.getBytes());
            }
            if(newReader.getRdType() != null) oldReader.setRdType(newReader.getRdType());
            if(newReader.getRdDept() != null) oldReader.setRdDept(newReader.getRdDept());
            if(newReader.getRdName() != null) oldReader.setRdName(newReader.getRdName());
            if(newReader.getRdSex() != null) oldReader.setRdSex(newReader.getRdSex());
            if(newReader.getRdPhone() != null) oldReader.setRdPhone(newReader.getRdPhone());
            if(newReader.getRdEmail() != null) oldReader.setRdEmail(newReader.getRdEmail());
            if(newReader.getRdStatus() != null) oldReader.setRdStatus(newReader.getRdStatus());
            List<Reader> readersList = readerMapper.findReadersByRdDeptAndRdName(newReader.getRdType(), newReader.getRdDept(), newReader.getRdName());
            if(readersList.size() > 0 && !readersList.get(0).getRdID().equals(oldReader.getRdID())) {
                msg += "信息重复，修改失败！";
                gotoPath = "forward:reader_detail.html?rd_id=" + rdID;
            }else {
                readerMapper.updateReader(oldReader, rdID);
                msg += "修改信息成功！";
                gotoPath = "forward:reader_detail.html?rd_id=" + rdID;
            }
        }catch (IOException e) {
            msg += "修改信息失败，请重新操作！";
            gotoPath = "main/common/operateFail";
            e.printStackTrace();
        }
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }

    @Transactional
    public HashMap updateRdPassword(String oldPwd, String newPwd, Integer rdID) {
        HashMap<String, Object> data = new HashMap<>();
        String msg = "";
        Reader reader = readerMapper.findReaderByRdIdAndRdPwd(rdID, oldPwd);
        if(reader != null) {
            readerMapper.updateRdPassword(newPwd, rdID);
            msg="修改成功";
        }else {
            msg="修改失败，用户名或旧密码错误！";
        }
        data.put("msg", msg);
        return data;
    }

    public HashMap reSetRdPassword(Integer rdID) {
        HashMap<String, Object> data = new HashMap<>();
        String msg = "";
        readerMapper.updateRdPassword("123", rdID);
        msg = "重置密码成功！";
        data.put("msg", msg);
        return data;
    }

}
