package tech.aowu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("um_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UmUser {

  @TableId
  private Long uid;
  private String nickname;
  private String username;
  private String password;
  private Long department;
  private Integer delFlag;
  private String phoneNum;
  private String email;
  private java.sql.Timestamp regTime;
  private java.sql.Timestamp loginTime;

}
