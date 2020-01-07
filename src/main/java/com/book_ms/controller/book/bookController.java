package com.book_ms.controller.book;

import com.book_ms.pojo.Book;
import com.book_ms.service.book.BookService;
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
 * @Time 2019/11/30
 * @Describe TODO
 **/
@Controller
@RequestMapping("/book")
public class bookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/book_manager.html")
    public String bookManager(HttpServletRequest request,
                              @RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "size", defaultValue = "3") int size
    ) {
        PageHelper.startPage(start, size, "bkID asc");

        List<Book> bookList = bookService.findBooksList();

        PageInfo<Book> page = new PageInfo<>(bookList);

        request.setAttribute("booksList", bookList);
        request.setAttribute("page", page);
        return "main/content/admin/bookManager";
    }

    @RequestMapping("/find_book")
    public String findReader(HttpServletRequest request, Book book) {
        Integer bkID = null;
        try {
            bkID = Integer.parseInt(request.getParameter("bk_id"));
        } catch (NumberFormatException e) {}
        book.setBkID(bkID);

        List<Book> booksList = bookService.easyFindBooks(book);

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("booksList", booksList);
        return "main/content/admin/bookManager";
    }

    @RequestMapping("/add_book.html")
    public String addReader() {
        return "main/content/admin/addBook";
    }

    @RequestMapping("/add_find")
    public String addFind(HttpServletRequest request, Book book) {
        List<Book> booksList = bookService.findBooksByBkNameAndBkAuthor(book.getBkCatalog(), book.getBkName(), book.getBkAuthor(), book.getBkPress());

        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
        request.setAttribute("findResult", booksList);
        return "main/content/admin/addBook";
    }

    @RequestMapping("/add_book")
    public String addBook(HttpServletRequest request, Book book, @RequestParam("bkCov") MultipartFile bkCov) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String bkDatePress = request.getParameter("bkDatePre");
            String bkDateIn = request.getParameter("bkDateI");
            book.setBkDatePress(format.parse(bkDatePress));
            book.setBkDateIn(format.parse(bkDateIn));
            HashMap data = bookService.addBook(book, bkCov);
            request.setAttribute("msg", data.get("msg"));
            request.setAttribute("result", data.get("result"));
            return data.get("gotoPath").toString();
        } catch (ParseException e) {
            request.setAttribute("msg", "操作失败，参数录入异常！");
            return "main/common/operateFail";
        }
    }

    @RequestMapping("/book_detail.html")
    public String BookDetail(HttpServletRequest request, @RequestParam("bk_id") int bkID) {
        HashMap data = bookService.findBookByBkID(bkID);
        Book book = (Book)data.get("book");
        request.setAttribute("book", book);
        return "main/content/admin/bookDetail";
    }

    @RequestMapping("/update_book")
    public String updateBook(HttpServletRequest request, Book book, @RequestParam("bkID") Integer bkID, @RequestParam("bkCov") MultipartFile bkCover) {
        HashMap data = bookService.updateBook(book, bkCover, bkID);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }

    @RequestMapping("/bk_cover")
    public void rdPhoto(HttpServletResponse response, @RequestParam("bk_id") int bkID) {
        try {
            HashMap data = bookService.findBookByBkID(bkID);
            Book book = (Book)data.get("book");
            byte[] bytes = book.getBkCover();
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

    @RequestMapping("/delete_book")
    public String deleteBook(HttpServletRequest request, @RequestParam("bk_id") int bkID) {
        HashMap data = bookService.deleteBook(bkID);
        request.setAttribute("msg", data.get("msg"));
        return data.get("gotoPath").toString();
    }
}
