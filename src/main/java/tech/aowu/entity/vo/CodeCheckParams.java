package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用于验证码码接收
 * @ClassName: CodeCheckParams
 * @Author: Aealen
 * @Date: 2023/2/20 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeCheckParams {
    private String code;
    private String email;
}
