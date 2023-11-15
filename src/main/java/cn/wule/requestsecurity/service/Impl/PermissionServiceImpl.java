package cn.wule.requestsecurity.service.Impl;
//汉江师范学院 数计学院 吴乐创建于2023/11/15 15:37

import cn.wule.requestsecurity.service.PermissionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wule
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService
{
    @Resource
    private PermissionService permissionService;
    @Override
    public List<String> queryPermissionCodeByUserId(String userId) {
        return permissionService.queryPermissionCodeByUserId(userId);
    }
}