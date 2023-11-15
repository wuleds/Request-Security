package cn.wule.requestsecurity.vo;
//汉江师范学院 数计学院 吴乐创建于2023/11/13 16:39

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wule
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpRequest<T>
{
    /**
     * 自定义响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
}
