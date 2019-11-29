package com.lkq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lkq.dao.AddressMapper;
import com.lkq.dao.OrgMapper;
import com.lkq.dao.UserMapper;
import com.lkq.pojo.Address;
import com.lkq.pojo.Org;
import com.lkq.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public PageInfo<User> getList(User user,int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> users = userMapper.selectObject(user);
        return new PageInfo<>(users);
    }

    @Override
    public List<Address> getAddress(Integer code) {

        return addressMapper.selectAddress(code);
    }


    @Override
    public int save(User user) {
        if (user.getId()==null){
           return userMapper.insert(user);
        }else {

            return userMapper.update(user);
        }
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<Org> selectOrg() {
        return orgMapper.selectOrg();
    }

    @Override
    public int deletes(String ids) {
        return userMapper.deletes(ids);
    }
}
