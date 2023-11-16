package cn.wule.requestsecurity.service.Impl;
//汉江师范学院 数计学院 吴乐创建于2023/11/13 21:12

import cn.wule.requestsecurity.dao.UserDao;
import cn.wule.requestsecurity.model.User;
import cn.wule.requestsecurity.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wule
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUserName(username);
    }
}