package tech.aowu.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@TableName("order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  @TableId
  private Long oid;
  private String description;
  private long fa;
  private Long status;
  private long reporter;
  private Integer delFlag;
  private java.sql.Timestamp upTime;


  public long getOid() {
    return oid;
  }

  public void setOid(long oid) {
    this.oid = oid;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getFa() {
    return fa;
  }

  public void setFa(long fa) {
    this.fa = fa;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getReporter() {
    return reporter;
  }

  public void setReporter(long reporter) {
    this.reporter = reporter;
  }


  public long getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(Integer delFlag) {
    this.delFlag = delFlag;
  }


  public Timestamp getUpTime() {
    return upTime;
  }

  public void setUpTime(Timestamp upTime) {
    this.upTime = upTime;
  }

}
