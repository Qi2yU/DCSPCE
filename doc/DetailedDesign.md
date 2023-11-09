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

### 二、项目体系架构















### 三、详细接口设计

#### 前端设计





#### Contorller设计

##### 用户登录 `login.java`



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



##### 审核并上报数据`committed_control.java`

+ 获取各市已上报和未上报企业数量

  路由：`/count`	分配给`getCountByState`服务

  + 输入类型和输入值：

    | 参数名称  | 参数类型 | 参数说明               |
    | --------- | -------- | ---------------------- |
    | tableName | String   | 当前调查期数据表名     |
    | province  | String   | 统计上报数量的市的名称 |

  - 返回类型和返回值：

    | 参数名称    | 参数类型 | 参数说明               |
    | ----------- | -------- | ---------------------- |
    | committed   | int      | 某市已上报企业的总数量 |
    | uncommitted | int      | 某市未上报企业的总数量 |
    | province    | String   | 市的名称               |

+ 获取已上报企业的概览信息

  路由：`/committed`	分配给`getCommittedOnCity`服务

  + 输入类型和输入值：

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | city      | String   | 市的名称           |

  + 返回类型和返回值：

    | 参数名称  | 参数类型 | 参数说明     |
    | --------- | -------- | ------------ |
    | user_id   | String   | 企业的用户名 |
    | user_name | String   | 企业的名称   |
    | state1    | String   | 审核状态     |
    | state2    | String   | 上报状态     |

+ 获取某个企业具体上报数据

  路由：`/detail`	分配给`getDetailById`服务

  + 输入类型和输入值：

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | user_id   | String   | 企业的用户名       |

  + 返回类型和返回值：

    | 参数名称        | 参数类型 | 参数说明         |
    | --------------- | -------- | ---------------- |
    | user_id         | String   | 企业的用户名     |
    | user_name       | String   | 企业的名称       |
    | last_people_num | int      | 上一调查期人数   |
    | now_people_num  | int      | 当前调查期人数   |
    | decrease_reason | String   | 就业人数减少类型 |
    | main_reason     | String   | 主要原因         |
    | second_reason   | String   | 次要原因         |
    | explain_reason  | String   | 原因说明         |

+ 获取未上报企业信息

  路由：`/uncommitted`	分配给`getUncommitted`

  + 输入类型和输入值：

    | 参数名称  | 参数类型 | 参数说明 |
    | --------- | -------- | -------- |
    | tableName | String   | 数据表名 |
    | province  | String   | 市的名称 |
  
  + 返回类型和返回值：
  
    | 参数名称        | 参数类型 | 参数说明         |
    | --------------- | -------- | ---------------- |
    | user_id         | String   | 企业的用户名     |
    | user_name       | String   | 企业的名称       |
    | last_people_num | int      | 上一调查期人数   |
    | now_people_num  | int      | 当前调查期人数   |
    | decrease_reason | String   | 就业人数减少类型 |
    | main_reason     | String   | 主要原因         |
    | second_reason   | String   | 次要原因         |
    | explain_reason  | String   | 原因说明         |
    | phone           | String   | 联系电话         |
    | state           | String   | 状态             |
  
+ 退回上报数据

  路由`/retreat` 	分配给`getRetreat`

  + 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | user_id   | String   | 企业的用户名       |


+ 审核通过数据

  路由`/passbypro`   分配给`passByPro`服务

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | user_id   | String   | 企业的用户名       |

+ 单个上报数据

  路由`/singleupbypro`   分配给`singleUpByPro`

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | user_id   | String   | 企业的用户名       |

+ 批量上报数据

  路由`/batchupbypro`  分配给`batchUpByPro`服务

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | city      | String   | 批量上报的市名     |



##### 数据查询`Data_query_controller.java`

+ 获取符合查询时间段的数据表名列表

  + 输入类型和输入值

    | 参数名称   | 参数类型 | 参数说明     |
    | ---------- | -------- | ------------ |
    | start_time | String   | 起始查询时间 |
    | end_time   | String   | 终止查询时间 |

  + 返回类型和返回值

    | 参数名称   | 参数类型 | 参数说明               |
    | ---------- | -------- | ---------------------- |
    | tableName  | String   | 符合条件调查期数据表名 |
    | start_time | String   | 调查期起始时间         |
    | end_time   | String   | 调查期结束时间         |

+ 获取符合条件的数据列表

  路由`/dataquery`    分配给`getName`服务

  + 输入类型和输入值

    | 参数名称    | 参数类型 | 参数说明       |
    | ----------- | -------- | -------------- |
    | tableName   | String   | 调查期数据表名 |
    | userId      | String   | 企业的用户名   |
    | companyName | String   | 企业名称       |
    | character   | String   | 企业性质       |
    | industry    | String   | 所属行业       |
    | city        | String   | 所在市         |
    | district    | String   | 所在区县       |

  + 返回类型和返回值

    | 参数名称    | 参数类型 | 参数说明       |
    | ----------- | -------- | -------------- |
    | companyName | String   | 企业名称       |
    | userId      | String   | 企业的用户名   |
    | start_time  | String   | 调查期起始时间 |
    | end_time    | String   | 调查期结束时间 |
    | tableName   | String   | 调查期编号     |

+ 获取详细数据信息

  路由`/querydetail` 	分配给`getQueryDetail`服务

  + 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明           |
    | --------- | -------- | ------------------ |
    | tableName | String   | 当前调查期数据表名 |
    | user_id   | String   | 企业的用户名       |

  + 返回类型和返回值

    | 参数名称        | 参数类型 | 参数说明         |
    | --------------- | -------- | ---------------- |
    | user_id         | String   | 企业的用户名     |
    | company_name    | String   | 企业的名称       |
    | last_people_num | int      | 上一调查期人数   |
    | now_people_num  | int      | 当前调查期人数   |
    | decrease_reason | String   | 就业人数减少类型 |
    | main_reason     | String   | 主要原因         |
    | second_reason   | String   | 次要原因         |
    | explain_reason  | String   | 原因说明         |
    | phone           | String   | 联系电话         |
    | state           | String   | 状态             |
    | character       | String   | 企业性质         |
    | industry        | String   | 所属行业         |
    | city            | String   | 所在市           |
    | district        | String   | 所在县区         |



##### 数据汇总`sum_data.java`

​	路由/sum

- 汇总字段含有时间信息，分配给`sum_databyTime_service`

  - 输入类型和输入值

    | 参数名称 | 参数类型 | 参数说明           |
    | -------- | -------- | ------------------ |
    | sum_id   | String   | 前端传入的汇总字段 |

    

  - 返回类型和返回值

    | 名称      | 类型   | 说明               |
    | --------- | ------ | ------------------ |
    | kind_name | String | 具体的汇总字段名称 |
    | num       | Int    | 改汇总字段的汇总值 |

    

- 汇总字段不含有时间信息（单独设置选择时间段按钮），分配给`sum_databyOthers_service`

  - 输入类型和输入值

    | 参数名称   | 参数类型 | 参数说明               |
    | ---------- | -------- | ---------------------- |
    | sum_id     | String   | 前端传入的汇总字段     |
    | start_time | String   | 前端传入的汇总开始时间 |
    | end_time   | String   | 前端传入的汇总结束时间 |

  - 返回类型和返回值

    | 名称      | 类型   | 说明               |
    | --------- | ------ | ------------------ |
    | kind_name | String | 具体的汇总字段名称 |
    | num       | Int    | 改汇总字段的汇总值 |

##### 数据分析

- 取样分析

  路由： `/analy3`       分配给 `sample_analy_service`

- 趋势分析

  路由： `/analy2`       分配给 `trend_analy_service`

- 对比分析

  路由： `/analy1`       分配给 `compare_analy_service`

##### 发布通知



##### 代填报数据



##### 系统管理



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



##### 数据汇总

- 汇总字段含有时间信息`sum_databyTime_service`

  - 方法一get_alltime_data

    参数列表

    | 参数名称   | 参数类型 | 参数说明 |
    | ---------- | -------- | -------- |
    | start_time | String   | 开始时间 |
    | end_time   | String   | 结束时间 |

    返回类型和返回值

    | 参数名称    | 参数类型        | 参数说明       |
    | ----------- | --------------- | -------------- |
    | sumfor_data | List<SumEntity> | 待汇总实体列表 |

  - 方法二sumdata

    参数列表 :无

    返回类型和返回值

    | 参数名称    | 参数类型         | 参数说明         |
    | ----------- | ---------------- | ---------------- |
    | result_data | List<sum_result> | 汇总后的实体列表 |

    

- 汇总字段不含有时间信息（单独设置选择时间段按钮）`sum_databyOthers_service`

  - 方法一 getTimeId

    参数列表

    | 参数名称   | 参数类型 | 参数说明 |
    | ---------- | -------- | -------- |
    | start_time | String   | 开始时间 |
    | end_time   | String   | 结束时间 |

    返回类型和返回值

    | 参数名称     | 参数类型     | 参数说明       |
    | ------------ | ------------ | -------------- |
    | time_id_list | List<String> | 调查期时间列表 |

  - 方法二 getdata

    参数列表

    | 参数名称     | 参数类型     | 参数说明       |
    | ------------ | ------------ | -------------- |
    | time_id_list | List<String> | 调查期时间列表 |
    | sum_id       | String       | 汇总字段       |

    返回类型和返回值

    | 参数名称    | 参数类型        | 参数说明       |
    | ----------- | --------------- | -------------- |
    | sumfor_data | List<SumEntity> | 待汇总实体列表 |

  - 方法三sumdata

    参数列表 :无

    返回类型和返回值

    | 参数名称    | 参数类型         | 参数说明         |
    | ----------- | ---------------- | ---------------- |
    | result_data | List<sum_result> | 汇总后的实体列表 |

    





#### Mapper设计

##### Cedata_Operating_Mapper

+ 操作当前调查期的数据库



#### Entity类设计







### 四、其它

有关功能设计、数据库数据设计、界面设计部分可以查看 [概要设计文档](./summaryDesign.md)