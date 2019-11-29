package com.lkq.service;

import com.github.pagehelper.PageInfo;
import com.lkq.pojo.Address;
import com.lkq.pojo.Org;
import com.lkq.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {

    /**
     * 分页查询
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<User> getList(User user,int pageNo,int pageSize);

    /**
     * 查询地区
     * @param code
     * @return
     */
    List<Address> getAddress(Integer code);

    /**
     * 保存
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 查询部门
     * @return
     */
    List<Org> selectOrg();

    int deletes(String ids);
}
