package cn.wule.requestsecurity.service;
//汉江师范学院 数计学院 吴乐创建于2023/11/13 21:27

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wule
 */
public interface UserSecurityService
{
    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    UserDetails loadUserByUserId(String userId);
}