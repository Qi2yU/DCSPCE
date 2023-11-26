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
| springboot | springboot：2.2.2<br />jdk：1.8<br />maven: 3.8.8<br />其余部件版本可查看仓库`\src\server\yunnan\pom.xml`文件 |
| mysql      | 8.0.35                                                       |

![系统框架图](./asset/系统框架图.png)

![系统框架图](./asset/系统框架图.png)

### 二、项目体系架构

前端 到 后端 到 数据库


![系统架构图](./asset/系统架构图.png)











### 三、详细接口设计

#### 前端设计





#### Contorller设计

##### 用户登录 `login.java`



##### 数据汇总`sum_data.java`

- 页面渲染时数据获取（函数输入值均为空）

  - /government-pro/sum/years

    描述:获取年份选项框数据

    返回类型和返回值：

    | 返回类型名称 | 返回类型                   | 说明     |
    | ------------ | -------------------------- | -------- |
    | years        | List<CompareMountedEntity> | 年份list |

  - /government-pro/sum/months

    描述:获取月份选项框数据

    返回类型和返回值：

    | 返回类型名称 | 返回类型                   | 说明     |
    | ------------ | -------------------------- | -------- |
    | months       | List<CompareMountedEntity> | 年份list |

  - /government-pro/sum/mounted

    描述:获取当前调查期就业数据

    返回类型和返回值：

    | 返回类型名称 | 返回类型               | 说明               |
    | ------------ | ---------------------- | ------------------ |
    | data_mounted | List<SumMountedEntity> | 当前调查期数据list |

- 省市数据汇总

  - /government-pro/sum

    输入类型和输入值：

    | 参数名称   | 参数类型 | 参数说明           |
    | ---------- | -------- | ------------------ |
    | sum_id     | String   | 汇总字段           |
    | start_time | String   | 开始时间           |
    | end_time   | String   | 结束时间           |
    | flag_front | String   | 前端时间选项框类型 |

    返回类型和返回值：

    | 返回类型名称 | 返回类型           | 说明         |
    | ------------ | ------------------ | ------------ |
    | sum_res      | List<SumResEntity> | 汇总数据列表 |

  - /government-city/sum

    输入类型和输入值：

    | 参数名称   | 参数类型 | 参数说明           |
    | ---------- | -------- | ------------------ |
    | sum_id     | String   | 汇总字段           |
    | start_time | String   | 开始时间           |
    | end_time   | String   | 结束时间           |
    | city       | String   | 所在市区           |
    | flag_front | String   | 前端时间选项框类型 |

    返回类型和返回值：

    | 返回类型名称 | 返回类型           | 说明         |
    | ------------ | ------------------ | ------------ |
    | sum_res      | List<SumResEntity> | 汇总数据列表 |

##### 企业备案 `companyinfo_submit.java`

+ 提交企业备案信息
路由：`/record`分配给`CompanyInfo_service`

     + 输入类型和输入值：

        | 参数名称 | 参数类型 | 参数说明               |
        | -------- | -------- | ---------------------- |
        | user_id      | String   | 用户名 |
        | company_name      | String   | 企业名称 |
        | company_id      | String   | 组织机构代码 |
        | city      | String   | 企业所在城市 |
        | district      | String   | 企业所属区 |
        | phone      | String   | 联系电话 |
        | company_character      | String   | 企业性质 |
        | company_industry      | String   | 所属行业 |
        | company_business      | String   | 主要经营业务 |
        | linksman      | String   | 联系人 |
        | linaddress      | String   | 联系地址 |
        | post_num      | String   | 邮政编码 |
        | fax_num      | String   | 传真 |
        | email      | String   | EMAIL |

    + 返回类型和返回值：

        | 参数名称            | 参数类型 | 参数说明                   |
        | ------------------- | -------- | -------------------------- |
        | result           | String   | 返回备案提交结果         |


##### 企业就业信息上报 `data_collection.java`

- 查询当期上报数据 
  路由： `/get_company_cedata`       分配给 `Get_company_cedata_service`

  - 输入类型和输入值：

    | 参数名称 | 参数类型 | 参数说明               |
    | -------- | -------- | ---------------------- |
    | cid      | String   | 待查询企业当前期的数据 |

  - 返回类型和返回值：

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

- 修改当期上报数据 
  路由：`/modify_company_cedata`     分配给 `Modify_company_cedata_service`

  - 输入类型和输入值：

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

  - 返回类型和返回值：

    | 参数名称 | 参数类型 | 参数说明 |
    | -------- | -------- | -------- |
    | result   | String   | 操作结果 |



##### 查询往期数据



##### 查看通知 `company_notice.java`

- 查看特定的一条通知 

  路由： `/get_company_notice`       分配给 `Get_company_notice_service`

  + 输入类型和输入值：

    | 参数名称  | 参数类型 | 参数说明 |
    | --------- | -------- | -------- |
    | companyID | String   | 企业ID号 |
    | noticeID  | String   | 通知ID号 |

  + 输出类型和输出值：

    | 参数名称  | 参数类型 | 参数说明         |
    | --------- | -------- | ---------------- |
    | date      | String   | 发布日期         |
    | govmentID | String   | 发布通知的省ID号 |
    | title     | String   | 通知标题         |
    | content   | String   | 通知内容         |


##### 审核备案信息



##### 审核并上报数据



##### 数据查询



##### 取样分析



##### 图表分析





##### 发布通知



##### 代填报数据



##### 系统管理



#### Service 设计

##### 将企业提交的备案信息插入数据库`CompanyInfo_service.java`

+ 方法一 cpinfo_submit
    + 输入参数表: 

        | 参数名称 | 参数类型 | 参数说明               |
        | -------- | -------- | ---------------------- |
        | user_id      | String   | 用户名 |
        | company_name      | String   | 企业名称 |
        | company_id      | String   | 组织机构代码 |
        | city      | String   | 企业所在城市 |
        | district      | String   | 企业所属区 |
        | phone      | String   | 联系电话 |
        | company_character      | String   | 企业性质 |
        | company_industry      | String   | 所属行业 |
        | company_business      | String   | 主要经营业务 |
        | linksman      | String   | 联系人 |
        | linaddress      | String   | 联系地址 |
        | post_num      | String   | 邮政编码 |
        | fax_num      | String   | 传真 |
        | email      | String   | EMAIL |

    + 返回类型和返回值

        | 参数名称 | 参数类型 | 参数说明 |
        | -------- | -------- | -------- |
        | result   | String   | 操作结果 |

    + 调用Mapper `Companyinfo_Mapper`





##### 获取某企业当期调查期的数据 `Get_company_cedata_service.java`

- 方法一 get_company_cedata_by_cid

  - 输入参数表: 

    | 参数名称 | 参数类型 | 参数说明               |
    | -------- | -------- | ---------------------- |
    | cid      | String   | 待查询企业当前期的数据 |

  - 返回类型和返回值

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

  - 调用Mapper `Cedata_Operating_Mapper`



##### 修改某企业当期调查期的数据 `Modify_company_cedata_service.java`

- 方法一 modify_company_cedata_by_cid

  - 输入参数表: 

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

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明 |
    | -------- | -------- | -------- |
    | result   | String   | 操作结果 |

    + 调用Mapper `Cedata_Operating_Mapper`

##### 数据汇总服务`sum_databyTime_service.java`

- 方法一`compute_TimewithPeriod`

  描述：实现时间段自增的效果

  - 输入参数表: 

    | 参数名称 | 参数类型 | 参数说明                                               |
    | -------- | -------- | ------------------------------------------------------ |
    | time     | String   | 进行自增的时间，数据格式20xx0x0x（年，月，第几调查期） |
    | et       | String   | 终止时间，增加的时间不能超过终止时间                   |
    | bound    | char     | 调查期界限，用于判断最后一位的增加                     |

  - 返回类型和返回值

    | 返回类型名称 | 返回类型 | 说明           |
    | ------------ | -------- | -------------- |
    | time         | String   | 时间自增的结果 |

- 方法二`get_mounted_data`

  描述：获取当前调查期的数据

  - 输入参数表: void

  - 返回类型和返回值

    | 返回类型名称 | 返回类型               | 说明           |
    | ------------ | ---------------------- | -------------- |
    | data_mounted | List<SumMountedEntity> | 调查期数据list |

- 方法三`get_datafortime`

  描述：当汇总字段与时间相关时获取数据

- 方法四`get_dataforpro`

  描述：当汇总字段与时间相关时获取数据

##### 取样分析服务

趋势分析服务

对比分析服务

​	



#### Mapper设计

##### Cedata_Operating_Mapper

- 操作当前调查期的数据库



#### Entity类设计







### 四、其它

有关功能设计、数据库数据设计、界面设计部分可以查看 [概要设计文档](./summaryDesign.md)