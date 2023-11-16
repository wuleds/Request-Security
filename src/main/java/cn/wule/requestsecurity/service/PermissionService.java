package cn.wule.requestsecurity.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author wule
 */
public interface PermissionService
{
    /**
     * 根据用户id查询权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> queryPermissionCodeByUserId(String userId);

    /**
     * 查询用户权限并转换为GrantedAuthority
     * @param userId 用户id
     * @return List:GrantedAuthority
     */
    List<SimpleGrantedAuthority> queryPermissionByUserIdToGrantedAuthority(String userId);
}