package com.liori.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liori.common.constants.Constants;
import com.liori.common.utils.wechat.WechatUtil;
import com.liori.mapper.user.UserMapper;
import com.liori.model.user.User;
import com.liori.model.user.UserExample;
import com.liori.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String code) {
        String openId = WechatUtil.getOpenId(code);
        User user = userMapper.findUserByOpenId(openId);
        if (ObjectUtils.isEmpty(user)) {
            user = new User();
            user.setOpenId(openId);
            addUser(user);

            // TODO: 2019/1/8 绑定默认消费分类
            // TODO: 2019/1/8 绑定默认账户
        } else {
            // no need to do anything...
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        Long currentTimeMillis = System.currentTimeMillis();
        user.setCreateTime(currentTimeMillis);
        user.setUpdateTime(currentTimeMillis);
        user.setEnabled(Constants.ENABLED);
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUser(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        user.setUpdateTime(System.currentTimeMillis());
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public PageInfo getUsersByExample(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(user.getNickName())) {
            criteria.andNickNameEqualTo(user.getNickName());
        }
        List<User> users = userMapper.selectByExample(userExample);
        return new PageInfo(users);
    }
}
