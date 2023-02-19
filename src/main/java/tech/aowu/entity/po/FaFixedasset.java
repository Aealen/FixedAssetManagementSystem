package tech.aowu.entity.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("fa_fixedasset")
public class FaFixedasset {

  @TableId
  private long fid;
  private String name;
  private long type;
  private String model;
  private String producer;
  private double price;
  private long dep;
  private long custodian;
  private long delFlag;


  public long getFid() {
    return fid;
  }

  public void setFid(long fid) {
    this.fid = fid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getDep() {
    return dep;
  }

  public void setDep(long dep) {
    this.dep = dep;
  }


  public long getCustodian() {
    return custodian;
  }

  public void setCustodian(long custodian) {
    this.custodian = custodian;
  }


  public long getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(long delFlag) {
    this.delFlag = delFlag;
  }

}
