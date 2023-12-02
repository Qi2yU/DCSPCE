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

### 前端设计

####  1.路由架构设计（wyx）

####  2.具体界面设计（所有人）

-  **汇总界面**

  - **组件设计**

    按钮组件：

    表格组件：

    选项框组件

  - **接口设计**

  ![1701061193504](./asset/sum_inter.png)

- 取样分析界面

- 图表分析界面

- ​               		

- 数据审核

  - dataexam.vue

    -  组件设计

       文字组件：使用<p></p>

       表格组件：使用<el-table></el-table>，表格包括三个属性：province、committed和uncommitted，加上事件@cell-click响应

    -  方法设计

       show()：

       - 函数功能：判断点击表格的内容进行路由跳转

       - 触发事件：cell-click

       - 输入参数：val表示所点击表格的行对象，可以取的每一行的某个属性值
       - 函数实现：调用this.$router.push()进行路由跳转

    -  接口设计

       - get('/table')：取得当前调查期时间
       - get('/count')：取得每个市企业的已上报数量和未上报数量

  - detail.vue

    - 组件设计

      内容组件：使用<el-descriptions></el-description>，包括企业名称、企业编号、建档期人数、调查期人数、就业人数减少类型、主要原因、次要原因、说明的描述栏

      按钮组件：使用<el-button></el-button>，使用v-if和v-else来实现按钮组件的条件显示，设置disabled属性来控制按钮的状态，包括审核通过、退回修改、上报的按钮，加上@click事件

      输入框组件：使用<el-input></el-input>，使用v-model来获取输入内容

    - 方法设计

      pass()

      - 函数功能：点击“审核通过”按钮后对数据库进行更新
      - 触发事件：click
      - 函数实现：调用this.$http.get('/passbypro')接口，由后端进行操作

      commit()

      - 函数功能：点击“上报”按钮后对数据库进行更新
      - 触发事件：click
      - 函数实现：调用this.$http.get('/upbypro')接口，由后端进行操作

      retreat()

      - 函数功能：点击“退回修改”按钮后对数据库进行更新
      - 触发事件：click
      - 函数实现：调用this.$http.get('/retreatbypro')接口，由后端进行操作

    - 接口设计

      - get('/passbypro')：审核通过数据库更新
      - get('/commitbypro')：上报数据库更新
      - get('/retreatbypro')：退回修改数据库更新

- 数据查询界面

  - 组件设计

    表单设计：使用了<el-input>、<el-select>、<el-cascader>和<el-date-picker>组合构成表单，使用v-model获取所选内容

    按钮组件：使用<el-button>，加上了@click事件

    表格设计：使用<el-table>实现

  - 方法设计

    onSubmit()

    - 函数功能：点击“提交”按钮后，对提交内容进行处理，并从数据库获取数据
    - 触发事件：click
    - 函数实现：通过if判断，为未选择的内容设置默认值，调用后端接口this.$http.post('query')来从数据库获取查询数据

  - 接口设计

    -  post('query')：从数据库查询符合条件的数据


​		



### Contorller设计

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



##### 审核并上报数据 committed_controller

- 获取调查期时间

  路由： '/table'， 分配给 getTable()

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                                           |
    | -------- | -------- | -------------------------------------------------- |
    | data     | res_time | 返回包括正在进行的调查期的起始时间、结束时间和表名 |

-  获取概览信息

  路由：'/count'， 分配给getCount()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |

  - 返回类型和返回值

    | 参数名称 | 参数类型       | 参数说明                                 |
    | -------- | -------------- | ---------------------------------------- |
    | data     | List<DataExam> | 包括属性省、已上报数量和未上报数量的列表 |

- 获取已上报企业列表

  路由：'/committed', 分配给getCommitted()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型        | 参数说明                                             |
    | -------- | --------------- | ---------------------------------------------------- |
    | data     | List<Committed> | 包括属性企业名称、企业账号、审核状态和上报状态的列表 |

- 获取未上报企业列表

  路由:'/uncommitted'，分配给getUncommitted()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型          | 参数说明                                           |
    | -------- | ----------------- | -------------------------------------------------- |
    | data     | List<Uncommitted> | 包括属性企业名称、企业账号、联系电话和状态位的列表 |

- 获取退回修改的企业列表

  路由：'/retreat'，分配给getRetreat()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型      | 参数说明                                                     |
    | -------- | ------------- | ------------------------------------------------------------ |
    | data     | List<Retreat> | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、退回修改理由、状态位的列表 |

- 获取详细数据

  路由：'/detail'，分配给getDetail()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 返回类型和返回值

    | 参数名称 | 参数类型   | 参数说明                                                     |
    | -------- | ---------- | ------------------------------------------------------------ |
    | data     | DetailData | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、状态位 |

- 审核通过

  路由：'/singlepassbypro'，分配给SinglePassByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 单个上报

  路由：'/singleupbypro'，分配给singleUpByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 批量上报

  路由：'/batchupbypro'，分配给batchUpByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 无返回值

- 退回修改

  路由：'/retreatbypro'，分配给retreatByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明     |
    | --------- | -------- | ------------ |
    | tableName | String   | 调查期表名   |
    | info      | String   | 退回修改理由 |
    | userId    | String   | 企业账号     |

  -  无返回值

##### 数据查询

- 获取查询企业数据

  路由：'/query'，分配给getQuery()

  - 输入类型和输入值

    | 参数名称 | 参数类型   | 参数说明     |
    | -------- | ---------- | ------------ |
    | data     | query_form | 表单提交数据 |

  - 返回类型和返回值

    | 参数名称 | 参数类型    | 参数说明     |
    | -------- | ----------- | ------------ |
    | data     | querydetail | 企业查询数据 |

##### 取样分析



##### 图表分析



##### 发布通知



##### 代填报数据



##### 系统管理



### Service 设计

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

  - 输入参数表

    | 参数名称 | 参数类型 | 参数说明           |
    | -------- | -------- | ------------------ |
    | st       | String   | 获取数据的开始时间 |
    | et       | String   | 获取数据的结束时间 |

  - 返回类型和返回值：void

- 方法四`get_dataforpro`

  描述：当汇总字段与时间相关时获取数据
  
  - 输入参数表
  
    | 参数名称 | 参数类型 | 参数说明                                       |
    | -------- | -------- | ---------------------------------------------- |
    | st       | String   | 获取数据的开始时间                             |
    | et       | String   | 获取数据的结束时间                             |
    | type     | int      | 属性字段类别：0-企业地区 1-企业性质 2-所属行业 |
  
  - 返回类型和返回值：void

- 方法五`sum_datafortime`

  描述：当汇总字段与时间相关时汇总数据，根据时间类型不同调用不同的汇总函数

  所调用的函数为：sum_dataforresearch()，sum_dataformonth()，sum_dataforquter()，               sum_dataforyear()

  - 输入参数表

    | 参数名称 | 参数类型 | 参数说明                                                |
    | -------- | -------- | ------------------------------------------------------- |
    | type     | int      | 时间字段类别：0-调查期 1-企业月度 2-企业季度 3-企业年度 |

  - 返回类型和返回值：void

- 方法六`sum_dataforpro`

  描述：当汇总字段与企业属性相关时汇总数据

  - 输入参数表：void
  - 返回类型和返回值：void

- 方法六`give_res`

  描述：将汇总结果传送给控制器

  - 输入参数表

    | 参数名称 | 参数类型 | 参数说明                             |
    | -------- | -------- | ------------------------------------ |
    | flag     | int      | 判断是按属性汇总还是按时间汇总       |
    | type     | int      | 当汇总是按时间汇总时，判断时间的类型 |

  - 返回类型和返回值

    | 返回类型名称 | 返回类型        | 说明         |
    | ------------ | --------------- | ------------ |
    | data_res     | List<SumEntity> | 汇总数据list |

##### 取样分析服务

##### 趋势分析服务

##### 对比分析服务



##### 数据审核服务

- 获取调查期时间 getTable()

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                                           |
    | -------- | -------- | -------------------------------------------------- |
    | data     | res_time | 返回包括正在进行的调查期的起始时间、结束时间和表名 |

- 获取概览信息 getCount()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                           |
    | -------- | -------- | ---------------------------------- |
    | data     | DataExam | 包括属性省、已上报数量和未上报数量 |

- 获取已上报企业 getCommitted()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型  | 参数说明                                       |
    | -------- | --------- | ---------------------------------------------- |
    | data     | Committed | 包括属性企业名称、企业账号、审核状态和上报状态 |

- 获取未上报企业 getUncommitted()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型    | 参数说明                                     |
    | -------- | ----------- | -------------------------------------------- |
    | data     | Uncommitted | 包括属性企业名称、企业账号、联系电话和状态位 |

- 获取退回修改的企业 getRetreat()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                                                     |
    | -------- | -------- | ------------------------------------------------------------ |
    | data     | Retreat  | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、退回修改理由、状态位 |

- 获取详细数据 getDetail()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 返回类型和返回值

    | 参数名称 | 参数类型   | 参数说明                                                     |
    | -------- | ---------- | ------------------------------------------------------------ |
    | data     | DetailData | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、状态位 |

- 审核通过 SinglePassByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 单个上报 singleUpByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 批量上报 batchUpByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 无返回值

- 退回修改 retreatByPro()

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明     |
    | --------- | -------- | ------------ |
    | tableName | String   | 调查期表名   |
    | info      | String   | 退回修改理由 |
    | userId    | String   | 企业账号     |

  - 无返回值

##### 数据查询服务

- 获取期间调查期表 getTableByMonth()

  - 输入类型和输入值

    | 参数名称   | 参数类型 | 参数说明 |
    | ---------- | -------- | -------- |
    | start_date | String   | 起始月份 |
    | end_date   | String   | 结束月份 |

  - 返回类型和返回值

    | 参数名称  | 参数类型       | 参数说明     |
    | --------- | -------------- | ------------ |
    | tableList | List<res_time> | 期间调查期表 |

- 获取查询企业数据 getRes()

  - 输入类型和输入值

    | 参数名称 | 参数类型   | 参数说明     |
    | -------- | ---------- | ------------ |
    | data     | query_form | 表单提交数据 |

  - 返回类型和返回值

    | 参数名称 | 参数类型          | 参数说明         |
    | -------- | ----------------- | ---------------- |
    | data     | List<querydetail> | 企业查询数据列表 |

##### 

### Mapper设计

##### Cedata_Operating_Mapper

- 操作当前调查期的数据库

##### Sum_data_Mapper

- 操作的数据表：企业信息数据表company_info, 调查期索引数据表research_schedule，

  ​							调查期数据表data_20xx_09_1

- 接口：

  - `get_data`：

    描述：获取对应调查期数据表中的就业人数数据

    输入参数表：

    | 参数名称    | 参数类型 | 参数说明                           |
    | ----------- | -------- | ---------------------------------- |
    | timeTableId | String   | 调查期的名称，格式为data_20xx_09_1 |

    返回类型和返回值：

    | 返回类型        | 说明             |
    | --------------- | ---------------- |
    | List<SumEntity> | 就业人数数据列表 |

  - `get_datawithpro_XXX`:

    描述：XXX为不同的属性汇总字段，根据调查期获取对应字段的汇总数据，该类型的接口总共有三种

    输入参数表：

    | 参数名称    | 参数类型 | 参数说明                           |
    | ----------- | -------- | ---------------------------------- |
    | timeTableId | String   | 调查期的名称，格式为data_20xx_09_1 |

    返回类型和返回值：

    | 返回类型        | 说明             |
    | --------------- | ---------------- |
    | List<SumEntity> | 就业人数数据列表 |

  - `get_timemounted`:

    描述：获取调查期索引数据表中的所有调查期名称和调查期类型，该接口返回的结果主要用于计算月份list

    输入参数表：void

    返回类型和返回值：

    | 返回类型           | 说明       |
    | ------------------ | ---------- |
    | List<TimeidEntity> | 调查期list |

  - `get_datamounted`:

    描述：获取当前调查期的企业信息和就业人数

    输入参数表：

    | 参数名称    | 参数类型 | 参数说明                           |
    | ----------- | -------- | ---------------------------------- |
    | timeTableId | String   | 调查期的名称，格式为data_20xx_09_1 |

    返回类型和返回值：

    | 返回类型               | 说明                           |
    | ---------------------- | ------------------------------ |
    | List<SumMountedEntity> | 对应调查期的企业信息和就业人数 |



##### committed_mapper

- 操作的数据表：当前调查期表data_20xx_xx_x，企业信息表company_info，调查期索引表research_schedule

- 获取调查期时间 getTable()：SELECT语句

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                                           |
    | -------- | -------- | -------------------------------------------------- |
    | data     | res_time | 返回包括正在进行的调查期的起始时间、结束时间和表名 |

- 获取概览信息 getCount()：SELECT语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                           |
    | -------- | -------- | ---------------------------------- |
    | data     | DataExam | 包括属性省、已上报数量和未上报数量 |

- 获取已上报企业 getCommitted()：SELECT语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型  | 参数说明                                       |
    | -------- | --------- | ---------------------------------------------- |
    | data     | Committed | 包括属性企业名称、企业账号、审核状态和上报状态 |

- 获取未上报企业 getUncommitted()：SELECT语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型    | 参数说明                                     |
    | -------- | ----------- | -------------------------------------------- |
    | data     | Uncommitted | 包括属性企业名称、企业账号、联系电话和状态位 |

- 获取退回修改的企业 getRetreat()：SELECT语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 返回类型和返回值

    | 参数名称 | 参数类型 | 参数说明                                                     |
    | -------- | -------- | ------------------------------------------------------------ |
    | data     | Retreat  | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、退回修改理由、状态位 |

- 获取详细数据 getDetail()：SELECT语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 返回类型和返回值

    | 参数名称 | 参数类型   | 参数说明                                                     |
    | -------- | ---------- | ------------------------------------------------------------ |
    | data     | DetailData | 包括属性企业名称、企业账号、建档期人数、调查期人数、人数减少类型、主要原因、次要原因、次要原因说明、联系电话、状态位 |

- 审核通过 SinglePassByPro()：UPDATE语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 单个上报 singleUpByPro()：UPDATE语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | userId    | String   | 企业账号   |

  - 无返回值

- 批量上报 batchUpByPro()：UPDATE语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明   |
    | --------- | -------- | ---------- |
    | tableName | String   | 调查期表名 |
    | city      | String   | 市名       |

  - 无返回值

- 退回修改 retreatByPro()：UPDATE语句

  - 输入类型和输入值

    | 参数名称  | 参数类型 | 参数说明     |
    | --------- | -------- | ------------ |
    | tableName | String   | 调查期表名   |
    | info      | String   | 退回修改理由 |
    | userId    | String   | 企业账号     |

  - 无返回值

##### query_mapper

操作的数据表：多个调查期表data_20xx_xx_x，企业信息表company_info，调查期索引表research_schedule

- 获取期间调查期表 getTableByMonth()

  - 输入类型和输入值

    | 参数名称   | 参数类型 | 参数说明 |
    | ---------- | -------- | -------- |
    | start_date | String   | 起始月份 |
    | end_date   | String   | 结束月份 |

  - 返回类型和返回值

    | 参数名称  | 参数类型       | 参数说明     |
    | --------- | -------------- | ------------ |
    | tableList | List<res_time> | 期间调查期表 |

- 获取查询企业数据 getRes()

  - 输入类型和输入值

    | 参数名称 | 参数类型   | 参数说明     |
    | -------- | ---------- | ------------ |
    | data     | query_form | 表单提交数据 |

  - 返回类型和返回值

    | 参数名称 | 参数类型          | 参数说明         |
    | -------- | ----------------- | ---------------- |
    | data     | List<querydetail> | 企业查询数据列表 |

##### 

### Entity类设计

##### 1.Time_MountedEntity

​	描述：存放从调查期索引数据表获取的调查期数据或计算后的年月季度数据，可在多个Mapper中实现复用

​	属性：

- name:调查期或年月季度的名称
- id:list的id，方便前端选项框区分每个元素

##### 2.Sum_Entity

​	描述：存放从调查期数据表中获取的就业人数数据，且可以复用于汇总后的数据

​	属性：

- kind_name:汇总字段名称
- num:就业人数

##### 3.Sum_MountedEntity

​	描述：存放从调查期数据表中获取的就业人数数据和企业信息数据表获取的企业信息数据

​	属性：

- name:企业名称
- city:所属地区
- character:企业性质
- industry:所属行业
- time:所属调查期
- num:就业人数

##### 4.Tend_Entity

##### 5.Tend_TableEntity

##### 6.Tend_LineEntity

##### 7.Analy_Entity

##### 8.Analy_TableEntity

##### 9.Analy_LineEntity

##### 10. res_time

​	描述：存储调查期的起始和结束时间以及表索引

- start_time：String, 调查期起始时间
- end_time：String, 调查期结束时间
- tableName：String, 表索引

##### 11. DataExam

​	描述：存储企业上报统计数据

- province：String, 市名
- committed：int，上报企业数量
- uncommitted：int，未上报企业数量

##### 12. committed

​	描述：存储已上报企业概览信息

- companyId：企业账号
- companyName：企业名称
- flag1：审核状态
- flag2：上报状态

##### 13. uncommitted

​	描述：存储未上报企业信息

- companyId：企业账号
- companyName：企业名称
- phone：联系电话
- state：状态

##### 14. retreat

​	描述：存储退回修改企业信息

- CompanyId: String，企业账号

- CompanyName: String，企业名称

- lastNum: String，建档期人数

- nowNum: String，调查期人数

- decReason: String，减少类型

- mainReason: String，主要原因

- secReason: String，次要原因

- explain: String，描述

- phone: String，联系电话

- info: String，退回修改信息
- state: String，状态

##### 15. detaildata

​	描述：存储企业上报详细数据

- CompanyId: String，企业账号

- CompanyName: String，企业名称

- lastNum: String，建档期人数

- nowNum: String，调查期人数

- decReason: String，减少类型

- mainReason: String，主要原因

- secReason: String，次要原因

- explain: String，描述
- state：String，状态

##### 16. querydetail

​	描述：存储查询企业数据

- CompanyId: String，企业账号

- CompanyName: String，企业名称
- character：String，企业性质
- industry：String，所属行业
- city：String，所在市
- district：String，所在县

- lastNum: String，建档期人数

- nowNum: String，调查期人数

- decReason: String，减少类型

- mainReason: String，主要原因

- secReason: String，次要原因

- explain: String，描述
- state：String，状态





### 四、其它

有关功能设计、数据库数据设计、界面设计部分可以查看 [概要设计文档](./summaryDesign.md)