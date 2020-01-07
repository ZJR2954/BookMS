package com.book_ms.service.book;

import com.book_ms.mapper.BookMapper;
import com.book_ms.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author xiao
 * @Time 2019/12/13
 * @Describe 图书的Service层
 **/
@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public HashMap findBookByBkID(Integer bkID) {
        HashMap<String, Object> data = new HashMap<>();
        Book book = bookMapper.findBookByBkID(bkID);
        if(book != null) {
            data.put("book", book);
        }
        return data;
    }

    public List<Book> findBooksList() {
        return bookMapper.findBooksList();
    }

    public List<Book> easyFindBooks(Book book) {
        List<Book> booksList = new ArrayList<>();
        if ((book.getBkID() != null) && (book.getBkID() != 0)) {
            Book b = bookMapper.findBookByBkID(book.getBkID());
            if(b != null) {
                booksList.add(b);
            }
        }else {
            booksList = bookMapper.easyFindBooks(book);
        }
        return booksList;
    }

    public List<Book> findBooksByBkNameAndBkAuthor(String bkCatalog, String bkName, String bkAuthor, String bkPress) {
        return bookMapper.findBooksByBkNameAndBkAuthor(bkCatalog, bkName, bkAuthor, bkPress);
    }

    public HashMap addBook(Book book, MultipartFile bkCov) {
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        String result = "";
        try {
            if (null != bkCov && !bkCov.isEmpty()) {
                book.setBkCover(bkCov.getBytes());
            }
            List<Book> booksList = bookMapper.findBooksByBkNameAndBkAuthor(book.getBkCatalog(), book.getBkName(), book.getBkAuthor(), book.getBkPress());
            if(booksList.size() > 0 && !booksList.get(0).getBkID().equals(book.getBkID())) {
                msg += "信息重复，添加失败！";
                gotoPath = "main/common/operateFail";
            }else {
                bookMapper.insertBook(book);
                msg += "添加成功！";
                booksList = bookMapper.findBooksByBkNameAndBkAuthor(book.getBkCatalog(), book.getBkName(), book.getBkAuthor(), book.getBkPress());
                Book b = booksList.get(booksList.size()-1);
                result += "图书ID：" + b.getBkID();
                gotoPath = "main/common/operateSuccess";
            }
        }catch (IOException e) {
            msg += "添加失败，请重新操作！";
            gotoPath = "main/common/operateFail";
            e.printStackTrace();
        }
        data.put("msg", msg);
        data.put("result", result);
        data.put("gotoPath", gotoPath);
        return data;
    }

    public HashMap updateBook(Book newBook, MultipartFile bkCov, Integer bkID) {
        Book oldBook = bookMapper.findBookByBkID(bkID);
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        try {
            if (bkCov != null && !bkCov.isEmpty()) {
                oldBook.setBkCover(bkCov.getBytes());
            }
            if(newBook.getBkCatalog() != null) oldBook.setBkCatalog(newBook.getBkCatalog());
            if(newBook.getBkCode() != null) oldBook.setBkCode(newBook.getBkCode());
            if(newBook.getBkISBN() != null) oldBook.setBkISBN(newBook.getBkISBN());
            if(newBook.getBkLanguage() != null) oldBook.setBkLanguage(newBook.getBkLanguage());
            if(newBook.getBkPages() != null) oldBook.setBkPages(newBook.getBkPages());
            if(newBook.getBkPrice() != null) oldBook.setBkPrice(newBook.getBkPrice());
            if(newBook.getBkBrief() != null) oldBook.setBkBrief(newBook.getBkBrief());
            if(newBook.getBkStatus() != null) oldBook.setBkStatus(newBook.getBkStatus());
            List<Book> booksList = bookMapper.findBooksByBkNameAndBkAuthor(newBook.getBkCatalog(), oldBook.getBkName(), oldBook.getBkAuthor(), oldBook.getBkPress());
            if(booksList.size() > 0 && !booksList.get(0).getBkID().equals(oldBook.getBkID())) {
                msg += "信息重复，修改失败！";
                gotoPath = "forward:book_detail.html?bk_id=" + bkID;
            }else {
                bookMapper.updateBook(oldBook, bkID);
                msg += "修改信息成功！";
                gotoPath = "forward:book_detail.html?bk_id=" + bkID;
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

    public HashMap deleteBook(Integer bkID) {
        HashMap<String, Object> data = new HashMap<>();
        String gotoPath = "login/login";
        String msg = "";
        try {
            bookMapper.deleteBookByBkID(bkID);
            msg += "删除图书成功！";
            gotoPath = "main/common/operateSuccess";
        } catch (Exception e) {
            msg += "删除图书失败，该图书正在被借阅！";
            gotoPath = "main/common/operateFail";
        }
        data.put("msg", msg);
        data.put("gotoPath", gotoPath);
        return data;
    }
}
