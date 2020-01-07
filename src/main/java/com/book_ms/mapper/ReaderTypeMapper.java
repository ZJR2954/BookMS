package com.book_ms.mapper;

import com.book_ms.pojo.ReaderType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xiao
 * @Time 2019/11/29
 * @Describe TODO
 **/
@Component
public interface ReaderTypeMapper {
    @Select("select rdTypeName from ReaderType where rdType=#{rdType}")
    public String findRdTypeNameByRdType(@Param("rdType") Integer rdType);

    @Select("select * from ReaderType where rdType != 6")
    public List<ReaderType> findRdTypesList();

    @Select("select * from ReaderType")
    public List<ReaderType> findUserTypesList();

    @Select("select * from ReaderType where rdType=#{rdType}")
    public ReaderType findRdTypeByRdType(@Param("rdType") Integer rdType);

    @Select("select * from ReaderType where rdTypeName=#{rdTypeName}")
    public List<ReaderType> findRdTypeByRdTypeName(@Param("rdTypeName") String rdTypeName);

    @Update("update ReaderType set rdTypeName=#{rdType.rdTypeName}," +
            " canLendQty=#{rdType.canLendQty}," +
            " canLendDay=#{rdType.canLendDay}," +
            " canContinueTimes=#{rdType.canContinueTimes}," +
            " punishRate=#{rdType.punishRate}," +
            " dateValid=#{rdType.dateValid}" +
            " where rdType=#{rd_type}")
    public void updateRdType(@Param("rdType") ReaderType rdType, @Param("rd_type") Integer rd_type);

    @Delete("delete from ReaderType where rdType=#{rdType}")
    public void deleteRdTypeByRdType(@Param("rdType") Integer rdType);

    @Insert("insert into ReaderType(rdTypeName, canLendQty, canLendDay, canContinueTimes, punishRate, dateValid)" +
            " values(#{rdType.rdTypeName}," +
            " #{rdType.canLendQty}," +
            " #{rdType.canLendDay}," +
            " #{rdType.canContinueTimes}," +
            " #{rdType.punishRate}," +
            " #{rdType.dateValid})")
    public void insertRdType(@Param("rdType") ReaderType rdType);
}
