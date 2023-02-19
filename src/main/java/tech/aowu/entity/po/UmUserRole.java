package tech.aowu.entity.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@TableName("um_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UmUserRole {

  @TableId
  private Long uid;
  private Long rid;


  public Long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public Long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }

}
