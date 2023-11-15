package cn.wule.requestsecurity.model;
//汉江师范学院 数计学院 吴乐创建于2023/11/15 15:25

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wule
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements Serializable {
    private String permissionId;
    private String permissionDescription;
    private String permissionCode;
}