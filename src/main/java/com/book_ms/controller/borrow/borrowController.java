package com.book_ms.controller.borrow;

import com.book_ms.pojo.Book;
import com.book_ms.pojo.Borrow;
import com.book_ms.pojo.Reader;
import com.book_ms.service.book.BookService;
import com.book_ms.service.borrow.BorrowService;
import com.book_ms.service.user.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiao
 * @Time 2019/11/30
 * @Describe TODO
 **/
@Controller
@RequestMapping("/borrow")
public class borrowController {
    @Autowired
    BorrowService borrowService;
    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

    @RequestMapping("/borrow_manager.html")
    public String borrowManager() {
        return "main/content/admin/borrowManager";
    }

    @RequestMapping("/find_reader_by_rd_id")
    public String findReaderByRdID(HttpServletRequest request) {
        Integer rdID = null;
        try {
            rdID = Integer.parseInt(request.getParameter("rdID"));
        } catch (NumberFormatException e) {}

        HashMap data = readerService.findReaderByRdID(rdID);
        Reader reader = (Reader)data.get("reader");

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("reader", reader);
        return "main/content/admin/borrowManager";
    }

    @RequestMapping("/rd_borrow_detail.html")
    public String rdBorrowDetail(HttpServletRequest request, @RequestParam("rd_id") Integer rdID) {
        List<Borrow> borrowsList = borrowService.findBorrowsByRdID(rdID);
        request.setAttribute("borrowsList", borrowsList);
        return "main/content/admin/rdBorrowDetail";
    }

    @RequestMapping("/borrow_detail.html")
    public String borrowDetail(HttpServletRequest request, @RequestParam("borrow_id") Integer borrowID) {
        HashMap data = borrowService.findBorrowByBorrowID(borrowID);
        Borrow borrow = (Borrow)data.get("borrow");
        Book book = (Book)data.get("book");
        String rdName = (String)data.get("rdName");
        request.setAttribute("borrow", borrow);
        request.setAttribute("book", book);
        request.setAttribute("rdName", rdName);
        return "main/content/admin/borrowDetail";
    }

    @RequestMapping("/find_book_by_bk_id")
    public String findReader(HttpServletRequest request) {
        Integer rdID = null;
        Integer bkID = null;
        try {
            rdID = Integer.parseInt(request.getParameter("rdID"));
        } catch (NumberFormatException e) {e.printStackTrace();}
        try {
            bkID = Integer.parseInt(request.getParameter("bkID"));
        } catch (NumberFormatException e) {e.printStackTrace();}
        HashMap data = borrowService.findBookAndCanBorrowByBkIDAndRdID(rdID, bkID);
        Reader reader = (Reader)data.get("reader");
        Book book = (Book)data.get("book");
        Boolean rdCanBorrow = (Boolean)data.get("rdCanBorrow");
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("reader", reader);
        request.setAttribute("book", book);
        request.setAttribute("rdCanBorrow", rdCanBorrow);
        return "main/content/admin/borrowManager";
    }

    @RequestMapping("/borrow_book")
    public String borrowBook(HttpServletRequest request, HttpSession session, @RequestParam("rd_id") Integer rdID, @RequestParam("bk_id") Integer bkID) {
        Reader admin = (Reader)session.getAttribute("user");
        Integer adminID = admin.getRdID();
        HashMap data = borrowService.addBorrow(rdID, bkID, adminID);
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("reader", data.get("reader"));
        request.setAttribute("msg", data.get("msg"));
        return "main/content/admin/borrowManager";
    }

    @RequestMapping("/return_book")
    public String returnBook(HttpServletRequest request, HttpSession session) {
        HashMap data = null;
        try {
            Reader admin = (Reader)session.getAttribute("user");
            Integer borrowID = Integer.parseInt(request.getParameter("borrowID"));
            Integer rdID = Integer.parseInt(request.getParameter("rdID"));
            Integer bkID = Integer.parseInt(request.getParameter("bkID"));
            Float idPunishMoney = Float.parseFloat(request.getParameter("idPunishMoney"));
            Integer adminID = admin.getRdID();
            data = borrowService.returnBorrow(borrowID, rdID, bkID, idPunishMoney, adminID);
            request.setAttribute("msg", data.get("msg"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("msg", "请规范填写表单内容！");
        }
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        return "main/content/admin/borrowManager";
    }

    @RequestMapping("/continue_borrow")
    public String continueBorrow(HttpServletRequest request, HttpSession session, @RequestParam("borrow_id") Integer borrowID) {
        Reader admin = (Reader)session.getAttribute("user");
        Integer adminID = admin.getRdID();
        HashMap data = borrowService.continueBorrow(borrowID, adminID);
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("reader", data.get("reader"));
        request.setAttribute("msg", data.get("msg"));
        return "main/content/admin/borrowManager";
    }

}
