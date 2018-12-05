package com.huguo.service;

import com.github.pagehelper.PageHelper;
import com.huguo.entity.User;
import com.huguo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user service
 */
@Service
public interface UserService {

    public int insertUser(User user);

    public List<User> searchAllUser(int pageNum, int pageSize);



}
