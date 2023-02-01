package tech.aowu.entity.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("um_user")
public class UmUser implements Serializable {

  private static final long serialVersionUID = -54979041104113736L;

  @TableId
  private Long uid;
  private String nickname;
  private String username;
  private String password;
  private Long department;
  private Long delFlag;
  private java.sql.Timestamp regTime;
  private java.sql.Timestamp loginTime;



}
