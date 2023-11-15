package cn.wule.requestsecurity.dao;

import cn.wule.requestsecurity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wule
 */
@Mapper
public interface UserDao
{
    /**
     * 根据用户id查询用户信息
     * @param userId  用户名
     * @return 用户信息
     */
    User getUserById(@Param("userId") String userId);

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByUsername(@Param("userName") String userName);
}