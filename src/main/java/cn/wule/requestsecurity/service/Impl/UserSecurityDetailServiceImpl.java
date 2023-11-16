package cn.wule.requestsecurity.service.Impl;

import cn.wule.requestsecurity.model.User;
import cn.wule.requestsecurity.service.PermissionService;
import cn.wule.requestsecurity.service.UserService;
import cn.wule.requestsecurity.vo.UserSecurity;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wule
 */
@Service
@Slf4j
public class UserSecurityDetailServiceImpl implements UserDetailsService
{
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    /*public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
         User user = userService.getUserById(userId);
         if(user == null)
         {
             throw new UsernameNotFoundException("用户不存在");
         }
         UserSecurity userSecurity = new UserSecurity(user);
         if(userSecurity.isEnabled() || userSecurity.isAccountNonExpired() || userSecurity.isAccountNonLocked() || userSecurity.isCredentialsNonExpired()){
             throw new UsernameNotFoundException("用户已被禁用");
         }
         return userSecurity;
    }*/

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        User user = userService.getUserById(userId);
        List<SimpleGrantedAuthority> list = permissionService.queryPermissionByUserIdToGrantedAuthority(userId);
        if(user == null)
        {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserSecurity userSecurity = new UserSecurity(user,list);
        return userSecurity;
    }
}