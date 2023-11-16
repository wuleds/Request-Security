package cn.wule.requestsecurity.service.Impl;
//汉江师范学院 数计学院 吴乐创建于2023/11/15 15:37

import cn.wule.requestsecurity.dao.PermissionDao;
import cn.wule.requestsecurity.service.PermissionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wule
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService
{
    @Resource
    private PermissionDao permissionDao;
    @Override
    public List<String> queryPermissionCodeByUserId(String userId) {
        return permissionDao.queryPermissionCodeByUserId(userId);
    }

    /**
     * 查询用户权限并转换为GrantedAuthority
     * @param userId 用户id
     * @return List:GrantedAuthority
     */
    public List<SimpleGrantedAuthority> queryPermissionByUserIdToGrantedAuthority(String userId) {
        List<String> list = permissionDao.queryPermissionCodeByUserId(userId);
        List<SimpleGrantedAuthority> collect;
        if(list.isEmpty()) {
            collect = null;
        }else {
            collect = list.stream().map(SimpleGrantedAuthority::new).toList();
        }
        return collect;
    }
}