package com.book_ms.controller.user;

import com.book_ms.pojo.Reader;
import com.book_ms.pojo.ReaderType;
import com.book_ms.service.readerType.ReaderTypeService;
import com.book_ms.service.user.ReaderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiao
 * @Time 2019/11/26
 * @Describe 用户的Controller层
 **/
@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    ReaderService readerService;
    @Autowired
    ReaderTypeService rdTypeService;

    @RequestMapping("/reader_manager.html")
    public String readerManager(HttpServletRequest request,
                                @RequestParam(value = "start", defaultValue = "0") int start,
                                @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        List<ReaderType> rdTypesList = rdTypeService.findRdTypesList();
        request.setAttribute("rdTypesList", rdTypesList);

        PageHelper.startPage(start, size, "rdID asc");

        List<Reader> readersList = readerService.findReaders();

        PageInfo<Reader> page = new PageInfo<>(readersList);

        request.setAttribute("readersList", readersList);
        request.setAttribute("page", page);

        return "main/content/admin/readerManager";
    }

    @RequestMapping("/find_reader")
    public String findReader(HttpServletRequest request) {
        Integer rdID = null;
        try {
            rdID = Integer.parseInt(request.getParameter("rdID"));
        } catch (NumberFormatException e) {}
        Integer rdType = Integer.parseInt(request.getParameter("rdType"));
        String rdDept = request.getParameter("rdDept");
        String rdName = request.getParameter("rdName");

        List<ReaderType> rdTypesList = rdTypeService.findRdTypesList();

        List<Reader> readersList = readerService.easyFindReaders(rdID, rdType, rdDept, rdName);

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("rdTypesList", rdTypesList);
        request.setAttribute("readersList", readersList);
        return "main/content/admin/readerManager";
    }

    @RequestMapping("/add_reader.html")
    public String addReader(HttpServletRequest request) {
        List<ReaderType> rdTypesList = rdTypeService.findRdTypesList();
        request.setAttribute("rdTypesList", rdTypesList);
        return "main/content/admin/addReader";
    }

    @RequestMapping("/add_find")
    public String addFind(HttpServletRequest request, Reader reader) {
        List<ReaderType> rdTypesList = rdTypeService.findRdTypesList();
        List<Reader> readersList = readerService.findReadersByRdDeptAndRdName(reader.getRdType(), reader.getRdDept(), reader.getRdName());

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("rdTypesList", rdTypesList);
        request.setAttribute("findResult", readersList);
        request.setAttribute("rdTypeName", rdTypeService.findRdTypeNameByRdType(reader.getRdType()));
        return "main/content/admin/addReader";
    }

    @RequestMapping("/add_reader")
    public String addReader(HttpServletRequest request, Reader reader, @RequestParam("rdPho") MultipartFile rdPho) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String rdDateReg = request.getParameter("rdDateR");
            reader.setRdDateReg(format.parse(rdDateReg));
            HashMap data = readerService.addReader(reader, rdPho);
            request.setAttribute("msg", data.get("msg"));
            request.setAttribute("result", data.get("result"));
            return data.get("gotoPath").toString();
        } catch (ParseException e) {
            request.setAttribute("msg", "操作失败，参数录入异常！");
            return "main/common/operateFail";
        }
    }

    @RequestMapping("/reader_detail.html")
    public String ReaderDetail(HttpServletRequest request, @RequestParam("rd_id") int rdID) {
        HashMap data = readerService.findReaderByRdID(rdID);
        List<ReaderType> userTypesList = rdTypeService.findUserTypesList();
        Reader reader = (Reader)data.get("reader");
        request.setAttribute("reader", reader);
        request.setAttribute("userTypesList", userTypesList);
        return "main/content/admin/readerDetail";
    }

    @RequestMapping("/update_reader")
    public String updateReader(HttpServletRequest request, Reader reader, @RequestParam("rdID") Integer rdID, @RequestParam("rdPho") MultipartFile rdPhoto) {
        HashMap data = readerService.updateReader(reader, rdPhoto, rdID);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

    @RequestMapping("/rd_photo")
    public void rdPhoto(HttpServletResponse response, @RequestParam("rd_id") int rdID) {
        try {
            HashMap data = readerService.findReaderByRdID(rdID);
            Reader reader = (Reader)data.get("reader");
            byte[] bytes = reader.getRdPhoto();
            if (bytes != null && bytes.length > 0) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
