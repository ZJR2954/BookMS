package com.book_ms.service.readerType;

import com.book_ms.mapper.ReaderTypeMapper;
import com.book_ms.pojo.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author xiao
 * @Time 2019/11/29
 * @Describe TODO
 **/
@Service
public class ReaderTypeService {
    @Autowired
    ReaderTypeMapper rdTypeMapper;

    public String findRdTypeNameByRdType(Integer rdType) {
        String type = "身份不明";
        if(rdTypeMapper.findRdTypeNameByRdType(rdType) != null){
            type = rdTypeMapper.findRdTypeNameByRdType(rdType);
        }
        return type;
    }

    public List<ReaderType> findRdTypesList() {
        return rdTypeMapper.findRdTypesList();
    }

    public List<ReaderType> findUserTypesList() {
        return rdTypeMapper.findUserTypesList();
    }

    public ReaderType findRdTypeByRdType(Integer rdType) {
        return rdTypeMapper.findRdTypeByRdType(rdType);
    }

    public HashMap updateRdType(ReaderType newRdType, Integer rd_type) {
        ReaderType oldRdType = rdTypeMapper.findRdTypeByRdType(rd_type);
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";

        if(newRdType.getRdTypeName() != null) oldRdType.setRdTypeName(newRdType.getRdTypeName());
        if(newRdType.getCanLendQty() != null) oldRdType.setCanLendQty(newRdType.getCanLendQty());
        if(newRdType.getCanLendDay() != null) oldRdType.setCanLendDay(newRdType.getCanLendDay());
        if(newRdType.getCanContinueTimes() != null) oldRdType.setCanContinueTimes(newRdType.getCanContinueTimes());
        if(newRdType.getPunishRate() != null) oldRdType.setPunishRate(newRdType.getPunishRate());
        if(newRdType.getDateValid() != null) oldRdType.setDateValid(newRdType.getDateValid());
        rdTypeMapper.updateRdType(newRdType, rd_type);
        msg += "修改信息成功！";
        gotoPath = "forward:rd_type_detail.html?rd_type=" + rd_type;
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }

    public HashMap addRdType(ReaderType rdType) {
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        List<ReaderType> rdTypesList = rdTypeMapper.findRdTypeByRdTypeName(rdType.getRdTypeName());
        if(rdTypesList.size() > 0) {
            msg += "类型名重复，添加失败！";
            gotoPath = "forward:add_rd_type.html";
        }else {
            rdTypeMapper.insertRdType(rdType);
            gotoPath = "redirect:/user/user_manager.html";
        }
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }

    public HashMap deleteRdType(Integer rd_type) {
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";

        try {
            rdTypeMapper.deleteRdTypeByRdType(rd_type);
            msg += "删除读者类型成功！";
            gotoPath = "main/common/operateSuccess";
        } catch (Exception e) {
            msg += "删除读者类型失败，该读者类型正在被使用！";
            gotoPath = "main/common/operateFail";
        }
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }

}
