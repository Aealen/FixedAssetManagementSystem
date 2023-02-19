package tech.aowu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("fa_department")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaDepartment {

  @TableId
  private Long did;
  private String name;
  private int delFlag;


  public long getDid() {
    return did;
  }

  public void setDid(long did) {
    this.did = did;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
