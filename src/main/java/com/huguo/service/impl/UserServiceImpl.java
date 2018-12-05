package com.huguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.huguo.entity.User;
import com.huguo.mapper.UserMapper;
import com.huguo.service.UserService;
import com.huguo.utils.common.IDUtils;
import com.huguo.utils.common.MyMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /*@Autowired
    private MyMapper myMapper;*/

    public int insertUser(User user){
        User chackuser = new User();
        chackuser.setWxopenid(user.getWxopenid());
        User cuser = (User) userMapper.selectOne(chackuser);
        int i = 0;
        if(cuser==null){
            user.setUserid(IDUtils.getIncreaseIdByNanoTime());
            i = userMapper.insertSelective(user);
        }else{
            if(!user.getWxnickname().equals(cuser.getWxnickname())||!user.getProtraiturl().equals(cuser.getProtraiturl())){
                User updateUser = new User();
                updateUser.setUserid(user.getUserid());
                updateUser.setWxnickname(user.getWxnickname());
                updateUser.setProtraiturl(user.getProtraiturl());
                i = userMapper.updateByPrimaryKeySelective(updateUser);
            }
        }
        return i;
    }

    public List<User> searchAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAll();
    }
}
