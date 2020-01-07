package com.book_ms.service.borrow;

import com.book_ms.mapper.BookMapper;
import com.book_ms.mapper.BorrowMapper;
import com.book_ms.mapper.ReaderMapper;
import com.book_ms.mapper.ReaderTypeMapper;
import com.book_ms.pojo.Book;
import com.book_ms.pojo.Borrow;
import com.book_ms.pojo.Reader;
import com.book_ms.pojo.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author xiao
 * @Time 2019/12/15
 * @Describe TODO
 **/
@Service
public class BorrowService {
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    ReaderMapper readerMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReaderTypeMapper rdTypeMapper;

    public List<Borrow> findBorrowsByRdID(Integer rdID) {
        return borrowMapper.findBorrowsListByRdID(rdID);
    }

    public HashMap findBorrowByBorrowID(Integer borrowID) {
        HashMap<String, Object> data = new HashMap<>();
        Borrow borrow = borrowMapper.findBorrowByBorrowID(borrowID);
        if(borrow != null) {
            Reader reader = readerMapper.findReaderByRdID(borrow.getRdID());
            Book book = bookMapper.findBookByBkID(borrow.getBkID());
            ReaderType rdType = rdTypeMapper.findRdTypeByRdType(reader.getRdType());
            String rdName = readerMapper.findRdNameByRdID(borrow.getRdID());
            try {
                long temp = (new Date().getTime() - borrow.getIdDateRetPlan().getTime())/(24*60*60*1000);
                if(temp > 0) {
                    borrow.setIdOverDay((int)temp);
                    borrow.setIdOverMoney((int)temp*rdType.getPunishRate());
                }
            } catch (Exception e) {}
            data.put("borrow", borrow);
            data.put("book", book);
            data.put("rdName", rdName);
        }
        return data;
    }

    public HashMap findBookAndCanBorrowByBkIDAndRdID(Integer rdID, Integer bkID) {
        HashMap<String, Object> data = new HashMap<>();
        Boolean rdCanBorrow = false;
        Reader reader = readerMapper.findReaderByRdID(rdID);
        data.put("reader", reader);
        Book book = bookMapper.findBookByBkID(bkID);
        data.put("book", book);
        if(reader != null){
            if(reader.getRdStatus().equals("有效") &&
                    reader.getRdBorrowQty() < rdTypeMapper.findRdTypeByRdType(reader.getRdType()).getCanLendQty() &&
                    borrowMapper.findRdCanBorrowByRdID(rdID)
                    ) {
                rdCanBorrow = true;
            }
        }
        data.put("rdCanBorrow", rdCanBorrow);
        return data;
    }

    @Transactional
    public HashMap addBorrow(Integer rdID, Integer bkID, Integer adminID) {
        HashMap<String, Object> data = new HashMap<>();
        String msg = "";
        try {
            Reader reader = readerMapper.findReaderByRdID(rdID);
            Book book = bookMapper.findBookByBkID(bkID);
            ReaderType rdType = rdTypeMapper.findRdTypeByRdType(reader.getRdType());
            Calendar calendar = Calendar.getInstance();
            Date idDateOut = new Date();
            calendar.setTime(idDateOut);
            calendar.add(Calendar.DATE, rdType.getCanLendDay());
            Date idDateRetPlan = calendar.getTime();
            if(book.getBkStatus().equals("在馆")) {
                book.setBkStatus("借出");
                readerMapper.updateRdBorrowQty(reader.getRdBorrowQty()+1, reader.getRdID());
                bookMapper.updateBook(book, book.getBkID());
                borrowMapper.addBorrow(rdID, bkID, idDateOut, idDateRetPlan, adminID);
                msg = "借阅成功！";
            }else {
                msg = "借阅失败！";
            }
            data.put("reader", reader);
        } catch (Exception e) {
            msg = "借阅失败！";
        }
        data.put("msg", msg);
        return data;
    }

    @Transactional
    public HashMap returnBorrow(Integer borrowID, Integer rdID, Integer bkID, Float idPunishMoney, Integer adminID) {
        HashMap<String, Object> data = new HashMap<>();
        String msg = "";
        try {
            Reader reader = readerMapper.findReaderByRdID(rdID);
            Book book = bookMapper.findBookByBkID(bkID);
            ReaderType rdType = rdTypeMapper.findRdTypeByRdType(reader.getRdType());
            Borrow borrow = borrowMapper.findBorrowByBorrowID(borrowID);
            Date date = new Date();
            borrow.setIdDateRetAct(date);
            borrow.setIdPunishMoney(idPunishMoney);
            long temp = (date.getTime() - borrow.getIdDateRetPlan().getTime())/(24*60*60*1000);
            if(temp > 0) {
                borrow.setIdOverDay((int) temp);
                borrow.setIdOverMoney((int) temp * rdType.getPunishRate());
            }
            if(book.getBkStatus().equals("借出")) {
                book.setBkStatus("在馆");
                bookMapper.updateBook(book, book.getBkID());
                readerMapper.updateRdBorrowQty(reader.getRdBorrowQty()-1, reader.getRdID());
                borrowMapper.returnBorrow(borrowID, borrow.getIdDateRetAct(), borrow.getIdOverDay(), borrow.getIdOverMoney(), borrow.getIdPunishMoney(), adminID);
                msg = "归还成功！";
            }else {
                msg = "归还失败！";
            }
        } catch (Exception e) {
            msg = "归还失败！";
            e.printStackTrace();
            throw new RuntimeException();
        }
        data.put("msg", msg);
        return data;
    }

    @Transactional
    public HashMap continueBorrow(Integer borrowID, Integer adminID) {
        HashMap<String, Object> data = new HashMap<>();
        String msg = "";
        try {
            Borrow borrow = borrowMapper.findBorrowByBorrowID(borrowID);
            Reader reader = readerMapper.findReaderByRdID(borrow.getRdID());
            ReaderType rdType = rdTypeMapper.findRdTypeByRdType(reader.getRdType());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrow.getIdDateOut());
            calendar.add(Calendar.DATE, (borrow.getIdContinueTimes()+2)*rdType.getCanLendDay());
            Date idDateRetPlan = calendar.getTime();
            if(rdType.getCanContinueTimes() > borrow.getIdContinueTimes()) {
                borrowMapper.continueBorrow(borrow.getBorrowID(),borrow.getIdContinueTimes()+1, idDateRetPlan, adminID);
                msg = "续借成功！";
            }else {
                msg = "达到续借次数上限！";
            }
            data.put("reader", reader);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "续借失败！";
        }
        data.put("msg", msg);
        return data;
    }

}
