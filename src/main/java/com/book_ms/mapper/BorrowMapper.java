package com.book_ms.mapper;

import com.book_ms.pojo.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author xiao
 * @Time 2019/12/15
 * @Describe TODO
 **/
@Component
public interface BorrowMapper {

    @Select("select * from Borrow where rdID=#{rdID} and IsHasReturn='False'")
    public List<Borrow> findBorrowsListByRdID(@Param("rdID") Integer rdID);

    @Select("select * from Borrow where borrowID=#{borrowID}")
    public Borrow findBorrowByBorrowID(@Param("borrowID") Integer borrowID);

    @Select("select case when exists" +
            "(select 1 from Borrow where exists" +
            "(select * from Borrow where rdID = #{rdID} and IdOverDay>0 and IsHasReturn='False'" +
            ")" +
            ") then 0 else 1 end rdCanBorrow")
    public Boolean findRdCanBorrowByRdID(@Param("rdID") Integer rdID);

    @Insert("insert into Borrow(rdID, bkID, IdDateOut, IdDateRetPlan, OperatorLend)" +
            " values(#{rdID}, #{bkID}, #{IdDateOut}, #{IdDateRetPlan}, #{adminID})")
    public void addBorrow(@Param("rdID") Integer rdID,
                          @Param("bkID") Integer bkID,
                          @Param("IdDateOut") Date idDateOut,
                          @Param("IdDateRetPlan") Date idDateRetPlan,
                          @Param("adminID") Integer adminID);


    @Update("update Borrow set IdDateRetAct=#{IdDateRetAct}," +
            " IdOverDay=#{IdOverDay}," +
            " IdOverMoney=#{IdOverMoney}," +
            " IdPunishMoney=#{IdPunishMoney}," +
            " IsHasReturn='True'," +
            " OperatorRet=#{adminID}" +
            " where borrowID=#{borrowID}")
    public void returnBorrow(@Param("borrowID") Integer borrowID,
                             @Param("IdDateRetAct") Date idDateRetAct,
                             @Param("IdOverDay") Integer idOverDay,
                             @Param("IdOverMoney") Float idOverMoney,
                             @Param("IdPunishMoney") Float idPunishMoney,
                             @Param("adminID") Integer adminID);
    @Update("update Borrow set IdContinueTimes=#{IdContinueTimes}," +
            " IdDateRetPlan=#{IdDateRetPlan}," +
            " OperatorLend=#{adminID}" +
            " where borrowID=#{borrowID}")
    public void continueBorrow(@Param("borrowID") Integer borrowID,
                               @Param("IdContinueTimes") Integer idContinueTimes,
                               @Param("IdDateRetPlan") Date idDateRetPlan,
                               @Param("adminID") Integer adminID);
}
