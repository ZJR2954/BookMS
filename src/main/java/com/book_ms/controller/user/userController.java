package com.book_ms.controller.user;

import com.book_ms.pojo.Reader;
import com.book_ms.pojo.ReaderType;
import com.book_ms.service.readerType.ReaderTypeService;
import com.book_ms.service.user.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiao
 * @Time 2019/11/30
 * @Describe TODO
 **/
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    ReaderService readerService;
    @Autowired
    ReaderTypeService rdTypeService;

    @RequestMapping("/user_manager.html")
    public String userManager(HttpServletRequest request) {
        List<ReaderType> userTypesList = rdTypeService.findUserTypesList();
        request.setAttribute("userTypesList", userTypesList);
        return "main/content/admin/userManager";
    }

    @RequestMapping("/profile.html")
    public String userProfile() {
        return "main/content/reader/profile";
    }

    @RequestMapping("/find_user")
    public String find(HttpServletRequest request) {
        Integer rdID = null;
        try {
            rdID = Integer.parseInt(request.getParameter("rdID"));
        } catch (NumberFormatException e) {}
        Integer rdType = Integer.parseInt(request.getParameter("rdType"));
        String rdDept = request.getParameter("rdDept");
        String rdName = request.getParameter("rdName");

        List<ReaderType> userTypesList = rdTypeService.findUserTypesList();

        List<Reader> readersList = readerService.easyFindReaders(rdID, rdType, rdDept, rdName);

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("userTypesList", userTypesList);
        request.setAttribute("readersList", readersList);
        return "main/content/admin/userManager";
    }

    @RequestMapping("/update_password.html")
    public String updatePassword() {
        return "main/content/reader/updatePassword";
    }

    @RequestMapping("update_password")
    public String updateRdPassword(HttpServletRequest request) {
        try {
            Integer rdID = Integer.parseInt(request.getParameter("rdID"));
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");

            HashMap<String, Object> data = readerService.updateRdPassword(oldPwd, newPwd, rdID);
            request.setAttribute("msg", data.get("msg"));
        } catch (NumberFormatException e) {
            request.setAttribute("msg", "参数错误！");
            e.printStackTrace();
        }
        return "redirect:/logout";
    }

    @RequestMapping("reset_password")
    public String reSetRdPassword(HttpServletRequest request, @RequestParam("rd_id") Integer rdID) {
        HashMap<String, Object> data = readerService.reSetRdPassword(rdID);
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("msg", data.get("msg"));
        return "forward:/user/user_manager.html";
    }

}
