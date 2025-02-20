# -
霖子的仓库：学院部门管理系统
这是一个学院部门管理系统，基于SpringBoot+Mybatis进行开发，采用多层架构设计，实现了学生，班级和部门等功能模块。该项目使用jwt+interceptor实现登录认证功能，使用动态SQL满足查询功能，通过Redis+Spring Cache对基于id查询学生实现缓存优化，并使用索引优化和explain慢查询提升效率，并使用SpringAOP+自定义直接@Log实现操作信息存储的功能，便于审查执行操作。
