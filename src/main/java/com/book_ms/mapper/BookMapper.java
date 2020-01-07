package com.book_ms.mapper;

import com.book_ms.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xiao
 * @Time 2019/12/13
 * @Describe 图书类Mapper接口
 **/
@Component
public interface BookMapper {
    @Select("select * from Book where bkID=#{bkID} ")
    public Book findBookByBkID(@Param("bkID") Integer bkID);

    @Select("select * from Book where bkCode=#{bkCode} ")
    public Book findBookByBkCode(@Param("bkID") Integer bkCode);

    @Select("select * from Book")
    public List<Book> findBooksList();

    @Select("select * from Book where bkCatalog=#{bkCatalog}" +
            " and bkName=#{bkName}" +
            " and bkAuthor=#{bkAuthor}" +
            " and bkPress=#{bkPress}")
    public List<Book> findBooksByBkNameAndBkAuthor(@Param("bkCatalog") String bkCatalog,
                                                   @Param("bkName") String bkName,
                                                   @Param("bkAuthor") String bkAuthor,
                                                   @Param("bkPress") String bkPress);

    @Select("select * from Book where bkCatalog=#{book.bkCatalog}" +
            " and bkCode like '%${book.bkCode}%'" +
            " and bkName like '%${book.bkName}%'" +
            " and bkAuthor like '%${book.bkAuthor}%'" +
            " and bkPress like '%${book.bkPress}%'")
    public List<Book> easyFindBooks(@Param("book") Book book);

    @Insert("insert into Book(bkCode, bkName, bkAuthor, bkPress, bkDatePress, bkISBN, bkCatalog," +
            " bkLanguage, bkPages, bkPrice, bkDateIn, bkBrief, bkCover, bkStatus)" +
            " values(#{book.bkCode}, #{book.bkName}," +
            " #{book.bkAuthor}, #{book.bkPress}," +
            " #{book.bkDatePress}, #{book.bkISBN}," +
            " #{book.bkCatalog}, #{book.bkLanguage}," +
            " #{book.bkPages}, #{book.bkPrice}," +
            " #{book.bkDateIn}, #{book.bkBrief}," +
            " #{book.bkCover}, #{book.bkStatus})")
    public void insertBook(@Param("book") Book book);

    @Insert("update Book set bkCatalog=#{book.bkCatalog}, bkCode=#{book.bkCode}," +
            " bkISBN=#{book.bkISBN}, bkLanguage=#{book.bkLanguage}," +
            " bkPages=#{book.bkPages}, bkPrice=#{book.bkPrice}," +
            " bkBrief=#{book.bkBrief}, bkCover=#{book.bkCover}," +
            " bkStatus=#{book.bkStatus}" +
            " where bkID=#{bkID}")
    public void updateBook(@Param("book") Book book, @Param("bkID") Integer bkID);

    @Delete("delete from Book where bkID=#{bkID}")
    public void deleteBookByBkID(@Param("bkID") Integer bkID);
}
