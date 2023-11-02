# 详细设计文档



### 目录

一、框架设计

二、项目体系结构

三、详细接口设计





### 一、框架设计

采用 vue + springboot + mysql的框架

| 使用技术   | 相关版本                                                     |
| ---------- | ------------------------------------------------------------ |
| vue        | 使用vue2<br />npm：9.8.1<br />vue/cli：5.0.8<br />element-ui：2.15.14<br />其余部件版本可见 仓库`\src\ui\vue-test\`目录下`package-lock.json`文件 |
| springboot | springboot：2.7.17<br />jdk：1.8<br />maven: 3.8.8<br />mybatis: 2.2.2<br />其余部件版本可查看仓库`\src\server\yunnan\pom.xml`文件 |
| mysql      | 8.0.35                                                       |

![系统框架图](./asset/系统框架图.png)

### 二、项目体系架构

前端 到 后端 到 数据库













### 三、详细接口设计

#### 前端设计





#### Contorller设计

##### 用户登录 `login.java`

* 用户输入用户名和密码登录系统

  路由：`/login`	分配给`User_login_check_service`

  * 输入类型与输入值

    | 参数名称 | 参数类型 | 参数说明       |
    | -------- | -------- | -------------- |
    | userId   | String   | 用户名         |
    | password | String   | 用户输入的密码 |
  
  * 返回类型和返回值
  
    | 参数名称     | 参数类型 | 参数说明       |
    | ------------ | -------- | -------------- |
    | return_value | String   | 返回的路由信息 |
  
  

##### 企业备案



##### 企业就业信息上报 `data_collection.java`

+ 查询当期上报数据 
    路由： `/get_company_cedata`       分配给 `Get_company_cedata_service`

    + 输入类型和输入值：

        | 参数名称 | 参数类型 | 参数说明               |
        | -------- | -------- | ---------------------- |
        | cid      | String   | 待查询企业当前期的数据 |

    + 返回类型和返回值：

        | 参数名称            | 参数类型 | 参数说明                   |
        | ------------------- | -------- | -------------------------- |
        | companyID           | String   | 查询信息的企业ID号         |
        | docEmploymentNumber | int      | 企业建档期就业人数         |
        | curEmploymentNumber | int      | 企业调查期就业人数         |
        | numDecreasedReason  | int      | 人数减少原因               |
        | mainReason          | int      | 主要原因                   |
        | secondReason        | int      | 次要原因                   |
        | reasonDetail        | String   | 原因解释                   |
        | status              | int      | 状态码，表示当前的备案状态 |

+ 修改当期上报数据 
    路由：`/modify_company_cedata`     分配给 `Modify_company_cedata_service`

    + 输入类型和输入值：

        | 参数名称            | 参数类型 | 参数说明                   |
        | ------------------- | -------- | -------------------------- |
        | cid                 | String   | 每一个操作必带的cid号      |
        | companyID           | String   | 要修改信息的企业ID号       |
        | docEmploymentNumber | int      | 企业建档期就业人数         |
        | curEmploymentNumber | int      | 企业调查期就业人数         |
        | numDecreasedReason  | int      | 人数减少原因               |
        | mainReason          | int      | 主要原因                   |
        | secondReason        | int      | 次要原因                   |
        | reasonDetail        | String   | 原因解释                   |
        | status              | int      | 状态码，表示当前的备案状态 |

    + 返回类型和返回值：

        | 参数名称 | 参数类型 | 参数说明 |
        | -------- | -------- | -------- |
        | result   | String   | 操作结果 |



##### 查询往期数据



##### 查看通知



##### 审核备案信息



##### 审核并上报数据





##### 数据查询



##### 数据汇总



##### 数据分析



##### 发布通知



##### 代填报数据





#### Service 设计







##### 获取某企业当期调查期的数据 `Get_company_cedata_service.java`

+ 方法一 get_company_cedata_by_cid

    + 输入参数表: 

        | 参数名称 | 参数类型 | 参数说明               |
        | -------- | -------- | ---------------------- |
        | cid      | String   | 待查询企业当前期的数据 |

    + 返回类型和返回值

        | 参数名称            | 参数类型 | 参数说明                   |
        | ------------------- | -------- | -------------------------- |
        | companyID           | String   | 查询信息的企业ID号         |
        | docEmploymentNumber | int      | 企业建档期就业人数         |
        | curEmploymentNumber | int      | 企业调查期就业人数         |
        | numDecreasedReason  | int      | 人数减少原因               |
        | mainReason          | int      | 主要原因                   |
        | secondReason        | int      | 次要原因                   |
        | reasonDetail        | String   | 原因解释                   |
        | status              | int      | 状态码，表示当前的备案状态 |

    + 调用Mapper `Cedata_Operating_Mapper`



##### 修改某企业当期调查期的数据 Modify_company_cedata_service.java`

+ 方法一 modify_company_cedata_by_cid

    + 输入参数表: 

        | 参数名称            | 参数类型 | 参数说明                   |
        | ------------------- | -------- | -------------------------- |
        | cid                 | String   | 每一个操作必带的cid号      |
        | companyID           | String   | 要修改信息的企业ID号       |
        | docEmploymentNumber | int      | 企业建档期就业人数         |
        | curEmploymentNumber | int      | 企业调查期就业人数         |
        | numDecreasedReason  | int      | 人数减少原因               |
        | mainReason          | int      | 主要原因                   |
        | secondReason        | int      | 次要原因                   |
        | reasonDetail        | String   | 原因解释                   |
        | status              | int      | 状态码，表示当前的备案状态 |

    + 返回类型和返回值

        | 参数名称 | 参数类型 | 参数说明 |
        | -------- | -------- | -------- |
        | result   | String   | 操作结果 |

    + 调用Mapper `Cedata_Operating_Mapper`





#### Mapper设计

##### Cedata_Operating_Mapper

+ 操作当前调查期的数据库



#### Entity类设计







### 四、其它

有关功能设计、数据库数据设计、界面设计部分可以查看 [概要设计文档](./summaryDesign.md)