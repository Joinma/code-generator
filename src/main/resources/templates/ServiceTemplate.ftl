package com.liori.service.user;

import com.github.pagehelper.PageInfo;
import com.liori.model.user.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

User login(String code);

int addUser(User user);

User getUser(Long id);

PageInfo getUsersByExample(User user, Integer pageNum, Integer pageSize);

Integer updateUser(User user);

}
