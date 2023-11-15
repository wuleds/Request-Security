package cn.wule.requestsecurity.model;
//汉江师范学院 数计学院 吴乐创建于2023/11/13 20:03

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wule
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    private String userId;
    private String userName;
    private String userPassword;
    private String gid;
    private String status;
    private String delFlag;
}