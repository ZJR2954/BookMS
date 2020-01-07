package com.book_ms.controller.readerType;

import com.book_ms.pojo.ReaderType;
import com.book_ms.service.readerType.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiao
 * @Time 2019/12/1
 * @Describe TODO
 **/
@Controller
@RequestMapping("/reader_type")
public class readerTypeController {
    @Autowired
    ReaderTypeService rdTypeService;

    @RequestMapping("/rd_type_detail.html")
    public String RdTypeDetail(HttpServletRequest request, @RequestParam("rd_type") int rdType) {
        request.setAttribute("rdType", rdTypeService.findRdTypeByRdType(rdType));
        return "main/content/admin/rdTypeDetail";
    }

    @RequestMapping("/update_rd_type")
    public String updateRdType(HttpServletRequest request, ReaderType rdType, @RequestParam("rd_type") int rd_type) {
        HashMap data = rdTypeService.updateRdType(rdType, rd_type);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

    @RequestMapping("/add_rd_type.html")
    public String addRdType() {
        return "main/content/admin/addRdType";
    }

    @RequestMapping("/add_rd_type")
    public String addRdType(HttpServletRequest request, ReaderType rdType) {
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        HashMap data = rdTypeService.addRdType(rdType);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

    @RequestMapping("/delete_rd_type")
    public String deleteRdType(HttpServletRequest request, @RequestParam("rd_type") int rd_type) {
        HashMap data = rdTypeService.deleteRdType(rd_type);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

}
