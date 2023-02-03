# 课题简介:

城科固定资产维修系统的设计与实现:
"系统角色：管理员、负责人、报修员、维修员
管理员：用户管理、设备类型管理、设备信息管理、设备状态管理、费用管理、维修统计等
报修员：维修申请、查看维修状态、维修确认等
维修员：维修确认、费用结算等
负责人：维修审核、查看维修记录等"

# 角色:

## 管理员：

> 管理系统所有信息,但不参与报修维修业务

- 用户管理

- 设备类型管理
- 设备信息管理
- 设备状态管理
- 费用管理
- 维修统计

## 报修员：

> 也就是普通用户  
>
> 可以在发现问题的时候在系统进行报修操作,
>
> 可以查看自己提交的维修任务的状态等信息,
>
> 订单中应有报修人以及维修员的联系方式
>
> 对自己提交的报修申请进行确认 (非为审核的维修订单)    
>
> 订单状态分为: 
>
> - 0 已提交 未审核
> - 1 审核通过 并分配工人
> - 2  未付款
> - 3 订单完成
> - 3 订单取消 

- 维修申请
- 查看维修状态
- 维修确认等；

## 维修员：

> 维修师傅
>
> 可以在系统上 **被分配** 任务  
>
> 对涉及本人的订单进行查看 确认 费用结算等操作

- 维修确认
- 费用结算

## 负责人：

> 当前订单涉及资产的负责人
>
> 可以指派资产涉及部门的维修员处理订单

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
- 401 未认证

















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