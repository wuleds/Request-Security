package cn.wule.requestsecurity.service;
//汉江师范学院 数计学院 吴乐创建于2023/11/13 20:56
import cn.wule.requestsecurity.model.User;

/**
 * @author wule
 */
public interface UserService
{
    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    User getUserById(String userId);

    /**
     *  根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);
}