package com.lkq.dao;

import com.lkq.pojo.Address;
import com.lkq.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {


    @Select("select * from t_address WHERE `code` = #{code}")
    List<Address> selectAddress(Integer code);


}
