package com.huguo.mapper;

import com.huguo.entity.User;
import com.huguo.utils.common.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    /*public int deleteByPrimaryKey(String userid);

    public int insert(User record);

    //这个方式我自己加的
    public List<User> selectAll();

    public int insertSelective(User record);

    public User selectByPrimaryKey(String userid);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);*/
}