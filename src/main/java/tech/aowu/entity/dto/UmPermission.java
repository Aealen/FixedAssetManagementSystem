package tech.aowu.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("um_permission")
public class UmPermission {

  private static final long serialVersionUID = -40356785423868312L;

  @TableId
  private Long id;
  private String name;
  private String perms;
  private String delFlag;

}
