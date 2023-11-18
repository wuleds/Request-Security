package cn.wule.requestsecurity.vo;
//汉江师范学院 数计学院 吴乐创建于2023/11/17 16:57

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author wule
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserInfo
{
    String useId;
    String userName;
    Map<String,String> userInfo;
    List<String> authList;
}