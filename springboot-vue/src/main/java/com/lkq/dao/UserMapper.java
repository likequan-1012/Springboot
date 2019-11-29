package com.lkq.dao;

import com.lkq.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询列表
     * @param user
     * @return
     */
    List<User> selectObject(@Param("user")User user);

    /**
     * 删除
     * @param ids
     * @return
     */
    @Delete("delete from t_USER where id in (${ids})")
    int deletes(@Param("ids") String ids);
    /**
     * 查询省
     * @param code
     * @return
     */
    @Select("select * from t_address WHERE `code` = #{code}")
    List<User> selectAddress(Integer code);

    /**
     * 添加
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectById(Integer id);


}
