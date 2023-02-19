package tech.aowu.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@TableName("um_role_permission")
@AllArgsConstructor
@NoArgsConstructor
public class UmRolePermission {

  @TableId
  private Long rid;
  private Long permid;


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }


  public long getPermid() {
    return permid;
  }

  public void setPermid(long permid) {
    this.permid = permid;
  }

}
