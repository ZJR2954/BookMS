package com.book_ms.mapper;

import com.book_ms.pojo.Reader;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xiao
 * @Time 2019/11/24
 * @Describe 读者类Mapper接口
 **/
@Component
public interface ReaderMapper {
    @Select("select rdID, rdName, rdSex, rdType, rdDept, rdPhone, rdEmail, rdDateReg, rdPhoto, rdStatus, rdBorrowQty, rdAdminRoles" +
            " from Reader where rdID=#{rdID} ")
    public Reader findReaderByRdID(@Param("rdID") Integer rdID);

    @Select("select rdName from Reader where rdID=#{rdID} ")
    public String findRdNameByRdID(@Param("rdID") Integer rdID);

    @Select("select * from Reader where rdID=#{rdID} and rdPwd=#{rdPwd}")
    public Reader findReaderByRdIdAndRdPwd(@Param("rdID") Integer rdID, @Param("rdPwd") String rdPwd);

    @Select("select rdID, rdName, rdSex, rdType, rdDept, rdPhone, rdEmail, rdDateReg, rdPhoto, rdStatus, rdBorrowQty, rdAdminRoles" +
            " from Reader")
    public List<Reader> findReaders();

    @Select("select rdID, rdName, rdSex, rdType, rdDept, rdPhone, rdEmail, rdDateReg, rdPhoto, rdStatus, rdBorrowQty" +
            " from Reader where rdType=#{rdType} and rdDept=#{rdDept} and rdName=#{rdName}")
    public List<Reader> findReadersByRdDeptAndRdName(@Param("rdType") Integer rdType, @Param("rdDept") String rdDept, @Param("rdName") String rdName);

    @Select("select rdID, rdName, rdSex, rdType, rdDept, rdPhone, rdEmail, rdDateReg, rdPhoto, rdStatus, rdBorrowQty" +
            " from Reader where rdType=#{rdType} and rdDept like '%${rdDept}%' and rdName like '%${rdName}%'")
    public List<Reader> easyFindReaders(@Param("rdType") Integer rdType, @Param("rdDept") String rdDept, @Param("rdName") String rdName);

    @Insert("insert into Reader(rdType, rdDept, rdName, rdSex, rdDateReg, rdPhone, rdEmail, rdPhoto)" +
            " values(#{reader.rdType}," +
            " #{reader.rdDept}," +
            " #{reader.rdName}," +
            " #{reader.rdSex}," +
            " #{reader.rdDateReg}," +
            " #{reader.rdPhone}," +
            " #{reader.rdEmail}," +
            " #{reader.rdPhoto})")
    public void insertReader(@Param("reader") Reader reader);

    @Update("update Reader set rdType=#{reader.rdType}," +
            " rdDept=#{reader.rdDept}, rdName=#{reader.rdName}," +
            " rdSex=#{reader.rdSex}, rdPhone=#{reader.rdPhone}," +
            " rdEmail=#{reader.rdEmail}, rdStatus=#{reader.rdStatus}," +
            " rdPhoto=#{reader.rdPhoto}" +
            " where rdID=#{rdID}")
    public void updateReader(@Param("reader") Reader reader, @Param("rdID") Integer rdID);

    @Update("update Reader set rdBorrowQty=#{rdBorrowQty} where rdID=#{rdID}")
    public void updateRdBorrowQty(@Param("rdBorrowQty") Integer rdBorrowQty,@Param("rdID") Integer rdID);

    @Update("update Reader set rdPwd=#{rdPwd} where rdID=#{rdID}")
    public void updateRdPassword(@Param("rdPwd") String rdPwd ,@Param("rdID") Integer rdID);
}
