# 课题简介:

城科固定资产维修系统的设计与实现:
"系统角色：管理员、负责人、报修员、维修员
管理员：用户管理、设备类型管理、设备信息管理、设备状态管理、费用管理、维修统计等
报修员：维修申请、查看维修状态、维修确认等
维修员：维修确认、费用结算等
负责人：维修审核、查看维修记录等"

# 角色:

## 管理员：

- 用户管理

- 设备类型管理
- 设备信息管理
- 设备状态管理
- 费用管理
- 维修统计

## 报修员：

- 维修申请
- 查看维修状态
- 维修确认等；

## 维修员：

- 维修确认
- 费用结算

## 负责人：

- 维修审核
- 查看维修记录

# 错误码:

## 用户:

- 100 用户登陆成功
- 101 用户名或密码错误
- 102 用户已存在
- 103 用户不存在

## 数据库:

- 201 数据库插入异常
- 202 数据库连接失败

## 权限:

- 301 权限不足
- 402 未认证

















# 数据库

## 用户表



## 资产表

根据如下内容确定固定资产各个属性相关字段

> [学校固定资产管理制度 (pingdu.gov.cn)](http://pingdu.gov.cn/n6865/n6867/n6881/n7057/n7058/n8021/n11121/220926153312000488.html)
>
> [我校固定资产管理制度 (gdpnc.edu.cn)](https://zwc.gdpnc.edu.cn/2021/0701/c537a33765/page.htm)
>
> [学校固定资产管理制度 (yjbys.com)](https://www.yjbys.com/zhidu/3019403.html)



| 字段名    | 键   | 类型    | 备注                     |
| --------- | ---- | ------- | ------------------------ |
| fid       | PK   | int     | FixedAsset Id            |
| name      |      | varchar | FixedAsset Name          |
| type      |      | int     | FixedAsset Type          |
| model     |      | varchar | FixedAsset Model         |
| producer  |      | varchar | FixedAsset Producer      |
| price     |      | decimal | FixedAsset Per Price     |
| dep       |      | int     | FixedAsset Department    |
| custodian |      | int     | FixedAsset Sub-custodian |
| del_flag  |      | int     | 0 表示未删除  1表示删除  |



## 订单表









# Logs

- 23/02/01 鉴权完成