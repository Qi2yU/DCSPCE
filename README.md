# DCSPCE
Data Collecting System for Province Company Employment (a software engineering course design)

省企业就业失业数据采集系统 (软件工程课程设计项目)

### 近期事项
+ 本周四(23/10/26) 在教室进行周报
    + 使用 vue 把前端功能展示出来，同时完成数据库的基本设计

### 项目总规划
+ [ ] 10/26 页面vue代码编写 + 概要设计
+ [ ] 11/02 详细设计文档 + 登录功能 + 备案功能 + 通知功能
+ [ ] 11/09 报表功能
+ [ ] 11/16 分析功能
+ [ ] 11/23 测试 + 准备答辩 


### 项目当前代办项
+ [x] 需求分析 ( -2023/10/19)
+ [x] 项目分工
+ [x] 定框架
    + vue + springboot + mysql
+ [ ] 编码
    + [ ] 前端用户界面 
        + [ ] 计划：于 2023/10/26 基本完成(90%以上)
    + [ ] 数据库设计 
        + [ ] 计划：于 2023/10/26 基本完成数据库设计，于 2023/11/2 完成数据库建立
    + [ ] 后端业务处理
        + [ ] 计划：核心功能在 2023/11/2 前完成
+ [ ] 


### 项目分工
- 把整个需求分成十条业务线：
- 其中三条 企业和政府 需要配合的：
    - (qy) 企业信息审核备案 => (lzm) 政府批准备案
    - (lc) 企业上报相关数据 => (pwq) 政府对企业上报的数据进行审核并上报，可以驳回
    - (cyl) 政府发布通知 => (cyl) 企业查看通知

- 一条企业自己的：
    - (lc) 查询往期上报数据

- 另外六条政府自己的：
    - (lzm) 政府对企业基本信息进行查询
    - (wyx) 政府在企业始终未上报数据时进行代填报并生成日志
    - (pwq) 政府对企业往期数据进行查询
    - (wyx) 政府对企业往期数据进行汇总分析
    - 政府对企业往期数据进行 (wyx) 简单分析 + (lzm) 多维分析（比较复杂）
    - (lmk) 相关系统设置，包括新增或修改调查期、注册账户、用户权限管理、系统监控等