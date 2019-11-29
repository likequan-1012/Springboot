package com.lkq.dao;

import com.lkq.pojo.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrgMapper {

    @Select("select * from t_org")
    List<Org> selectOrg();
}
