package cn.wule.requestsecurity.service;

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
}