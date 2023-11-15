package cn.wule.requestsecurity.dao;
//汉江师范学院 数计学院 吴乐创建于2023/11/15 15:28
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wule
 */
@Mapper
public interface PermissionDao
{
    /**
     * 根据用户id查询权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> queryPermissionCodeByUserId(@Param("userId") String userId);
}