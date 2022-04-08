/*
 Navicat Premium Data Transfer

 Source Server         : 180.163.101.78
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : 180.163.101.78:3306
 Source Schema         : blog_spring

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 07/04/2022 23:59:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `top` int(1) NULL DEFAULT 0 COMMENT '0 不置顶  1 置顶',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0 未删除  1  删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (6, 'test', 'test', 0, 0, '2022-04-06 19:39:09', 27, NULL, NULL);
INSERT INTO `announcement` VALUES (7, '哒咩', 'You can\'t let someone else\'s opinion tear you down. \n你不能被别人的看法打倒。', 1, 0, '2022-04-07 20:45:45', 27, NULL, NULL);
INSERT INTO `announcement` VALUES (8, '号外', 'Whatever happens tomorrow, we have had today', 0, 0, '2022-04-07 21:03:51', 27, NULL, NULL);
INSERT INTO `announcement` VALUES (9, '...', 'Human life is limited, and serving the people is infinite. ', 0, 0, '2022-04-07 21:04:07', 27, NULL, NULL);
INSERT INTO `announcement` VALUES (10, 'how are you', 'Positive thinking initiates more happiness! ', 0, 0, '2022-04-07 21:04:21', 27, NULL, NULL);
INSERT INTO `announcement` VALUES (11, 'yes ', 'The strongest person is the one who isn\'t scared to be alone. ', 0, 0, '2022-04-07 21:04:34', 27, NULL, NULL);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `category_id` int(10) NULL DEFAULT NULL COMMENT '分类id',
  `views` int(20) NULL DEFAULT 0 COMMENT '浏览量',
  `status` int(1) NULL DEFAULT NULL COMMENT '0：草稿   1：发布 2:审核中 3审核失败',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `top` int(10) NULL DEFAULT NULL COMMENT '置顶',
  `comment_enabled` int(1) NULL DEFAULT NULL COMMENT '评论 0否 1是',
  `appreciation` int(15) NULL DEFAULT 0 COMMENT '赞赏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (54, 'mysql基本语法', '1.备份数据库：\n1.1 备份数据库中的表：\n```\nmysqldump  -u  root  -p  test  a b  >d:\\bank_a.sql\n```\n\n//分别备份数据库test下a和b表\n1.2 备份一个数据库\n```\nmysqldump -u root -p test > d:\\testbk.sql\n```\n\n1.3 备份多个数据库\n```\nmysqldump -u root -p --databases test mysql > D:\\data.sql\n```\n\n1.4 备份所有的数据库\n```\nmysqldump -u -root -p  --all-databases > D:\\all.sql\n```\n\n1.5 直接复制整个数据库目录（物理备份）\n前提条件是停止mysql服务，然后复制mysql下的data目录下数据库目录。\n2.还原数据：\n2.1 使用 MySQL 命令恢复\n还原数据库文件:\n```\nmysql -u root -p  < d:\\backup.sql\n```\n\n还原数据库表文件：\n```\nmysql -u  root  -p  test <d:\\backup.sql\n```\n\n2.2 使用 source 命令恢复数据\n还原数据库文件：\n```\nmysql>source d:\\testbk.sql\n```\n\n还原数据库表文件：\n```\nmysql>use test\nmysql>source d:\\testbk.sql\n```\n\n2.3 先停止 MySQL 服务，然后拷贝备份的整个 test 数据库目录到目标目录。\n2.4 网络上远程还原数据可以用\n```\nmysqldump  -h  x.x.x.x  -u  root  -p  test >tesbk.sql\n```\n\n2.5mysql 忘记密码：\n1.停止mysql服务\n```\nnet stop mysql或者相应的进程\n```\n\n2.进入mysql下bin目录：\n```\nmysqld --skip-grant-tables  //跳过验证登录\n```\n\n3.另外窗口打开：\n```\nmysql  //直接可用进入系统\n```\n\n4.更改密码\n```\nuse mysql;\nupdate user set password=password(\'123456\') where user=\'root\' and host=\'localhost\';\n```\n\n5.注销系统，再进入，开MySQL，使用用户名root和刚才设置的新密码123456登陆\n2.6 修改密码：\n1.你的root用户现在没有密码，你希望的密码修改为123456，那么命令是：\n```\nmysql admin -u root password 123456\n```\n\n2.如果你的root现在有密码了（123456），那么修改密码为abcdef的命令是：\n```\nmysqladmin -u root -p password 123456\n```\n\n2.7 添加用户：\n```\ngrant select,insert,update,delete,create,drop on stud.* to user1@localhost identified by \"user1\";  //添加用户名user1密码为user1具有插入，更新，删除，创建，删除对于数据库所有表\nGRANT ALL PRIVILEGES ON *.* TO \'backlion\'@\'%\' IDENTIFIED BY \'backlion123\' WITH GRANT OPTION;\ngrant  all  privileges on  *.*  to  test@loclhost identified  by \"test\";\n```\n\n创建主键：\n```\nAlter table test add primary key(code,curlum);  //用code和curlum作为一组联合主键来约束\n```\n\n3.数据库操作：\n3.1 显示数据库\n```\nshow databases;\n```\n\n3.2 选择数据库\n```\nuse examples;\n```\n\n3.3 创建数据库并设置编码 utf8 多语言\n```\ncreate database bk  default character set utf8 collate utf8_general_ci;\n```\n\n3.4 修改数据库字符集为 utf8\n```\nuse  mysql;\nalter  database  character  set utf8;\n```\n\n3.5 删除数据库\n```\ndrop database  bk;\n```\n\n3.6 查看数据库状态：\n```\nstatus;\n```\n\n4.数据表的操作：\n4.1 显示数据表\n```\nshow tables;\n```\n\n4.2 查看数据表的结构属性（字段，类型）\n```\ndescribe  test;\ndesc  test;\n```\n\n4.3 复制表结构（里面没有数据，结构一样）\n```\ncreate table  newtest like  oldtest; //创建新表newtest和旧表oldtest数据表结构一样\n```\n\n4.4 复制表中的数据\n```\ninsert into  nettest select  *  from  oldtest;  //将旧表oldtest的数据复制到新表newtest里面\n```\n\n4.6 重命名表名\n```\nalter table  old_name  rename  new_name\n```\n\n4.7 显示当前 MySQL 版本和当前日期\n```\nselect version(),current_date;\n```\n\n4.8 创建表：\n```\ncreate table bk(\nid int(10) unsigned zerofill not null auto_increment,\nemail varchar(40) not null,\nip varchar(15) not null,\nstate int(10) not null default \'-1\',\nprimary key (id));/*\n```\n\n1.常用的数据类型为int,varchar,date,text这4个数据类型\n2.字段（行）的属性有：数据类型（数据长度） 是否为空 是否为主键  是否为自动增加  默认值\n```\n如：int(25)  not null  primary key  auto_increment default  12\n```\n\n3.每个字段之间用逗号分开，最后那个字段不需要用逗号\n4.以分号结束\n5.可以设置简单数据表结构如：\n字段名数据类型  数据长度  是否为空  是否主键是否自动增加  默认值\n```\nid  int  12  NOT NULL  primary key  auto_increment\nname varchar  30NOT NULL\npassword varchar30  NOT NULL\ntime  date  30  NOT NULL\njianyi text 400*/\n```\n\n4.9 删除数据表：\n```\ndrop  table  bk; //包括结构和数据都删除\n```\n\n10.数据库字段的操作：\n11.1 添加表字段\n```\nalter  table  test  add bk varchar(32) not null;  //向表test中添加bk字段（列）\n```\n\n11.2 修改表字段\n```\nalter table test change id id1 varchar(10) not null;  //将表test中字段（列）id更改为id1\n```\n\n11.3 删除字段（列）\n```\nalter  table  test drop cn;\n```\n\n11.4 插入表数据\n```\ninsert into test (id11,email,ip,state,bk)value(2,\'601462930@qq.com\',\'10.192.16.12\',1314,567); \n```\n\n//如果是字符型对应的值需要用单引号引起来，数字型不需要\n11.5 删除数据\n```\ndelete  from  test  //删除整个表test的数据，结构保留\ndelete  from  test  where id11=2; 删除数据表来自某个主键字段。就等于删除整条数据\n```\n\n11.6 修改表字段数据信息(数据）\n```\nUpdate table_name set 字段名=’新值’ [, 字段2 =’新值’ , …..][where id=id_num] [order by 字段 顺序]\nupdate test set email=\'895098355@qq.com\'  where id11=2;\n```\n\n11.7 修改字段的属性（数据类型）\n```\nalter table  test  modify class  varchar(30) not null;  //修改表test中字段class的属性为varchar(30)\n```\n\n12.查询数据：\n12.1 查询所有数据：\n```\nselect  *  form  test;\n```\n\n12.2 查询两个字段的数据\n```\nselect  id,number  from test;\n```\n\n12.3 查询前 2 行数据：\n```\nselect  *  from  test  limit 0,2;\n```\n\n12.4 按增序排列查询\n```\nselect *  from  test order by  id11  asc;\nselect *  from  test order by  id11  //默认为增序查询\n```\n\n12.5 按降序排列查询\n```\nselect *  from  test order by id11 desc;\n```\n\n12.6 模糊查询\n```\nselect  * from  test  where  email  \'%qq%\';  //查询test表中，条件是email的数据中包含qq的数据\n```\n\n12.7 查询某个字段下面的数据\n```\nselect  email  as  emaildata  from  test ;  //选择email字段作为emalidata统计显示出的数据\n```\n\n12.8.条件查询\n```\nselect  *  form  test  where  id=12;\n```\n\n13.多表查询：\n13.1 用法一：where 条件联合查询\n```\nselect 表1.字段 [as 别名],表n.字段 from 表1 [别名],表n where 条件;\nselect testA.username  as username,testB.id from testA,testB  where testA.uid=testB.uid\n```\n\n13.2 用法二：inner join on 条件联合查询\n```\nselect 表1.字段 [as 别名],表n.字段 from 表1 INNER JOIN 表n on 条件;\nselect  testA.username  as  uername ,testB.id from testA inner join testB on  testA.uid=testB.uid\n```\n\n13.3 记录联合：\n```\nselect语句1 union[all] select语句2\nselect *  from  testA  union  select  id  from testB;\n```\n\n设置createTime和updateTime能自动更新\n```\n-- alter TABLE tblInstrumentShortTarget modify createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP\n-- alter table blog MODIFY update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP;\n\n```\n\n', 34, 7, 1, 0, '2022-04-06 20:01:50', '2022-04-06 20:03:05', 27, 27, 1, 1, 1);
INSERT INTO `article` VALUES (55, 'Hello World!!!', '	欢迎来到我的世界！\n	建议，写文章之前切换全屏编辑！\n	奥利给！！！', 33, 9, 1, 0, '2022-04-06 20:28:35', '2022-04-06 20:28:48', 27, 27, 1, 1, 1);
INSERT INTO `article` VALUES (56, 'MarkDown代码', '    Markdown 是一种轻量级标记语言，创始人为约翰·格鲁伯（John Gruber）。它允许人们“使用易读易写的纯文本格式编写文档，然后转换成有效的XHTML(或者HTML)文档”。这种语言吸收了很多在电子邮件中已有的纯文本标记的特性MD CODE1、标题1）大标题\n\n\n在标题下面使用“====”\n```\n例如：\n大标题\n=====\n1\n2\n3\n42）中标题\n\n```\n\n\n在标题下面使用“-----”\n```\n例如：\n中标题\n---------\n注意“=”和“-”标题下面都有一条横线，并且=和-的个数没有数量限制\n注意 如果只想画一条横线，而不想上面的文字成为标题，可以使用空行代替，即=或-上面补一个空行\n1\n2\n3\n4\n5\n63）其他标题\n\n\n#一级标题\n##二级标题\n###三级标题\n####四级标题\n#####五级标题\n######六级标题\n1\n2\n3\n4\n5\n6\n```\n\n2、普通文字\n\n```\n在需要换行的地方输入至少两个空格，然后回车即可，注意，如果不回车，是没有效果的\n直接输入文字就显示文字，如果文字需要换行，就使用，\n1\n2\n```\n\n\n3、背景文字-文字高亮\n\n```\n使用``把需要加背景的文字包围起来，注意不是单引号，而是tab键上面的符号键\n1\n```\n\n4、文字链接\n\n```\n加链接的格式：[需要加链接的问题](文字的链接地址)\n例如：[百度](http://www.baidu.com)\n1\n2\n```\n\n\n5、圆点符号-列表标记\n\n```\n\n在git中使用*可以格式成圆点符号，注意*后面要有一个空格，否则*还是*\n可以使用1.+space，*+space， -+space\n定格为实心圆圈，空四个为空心圆圈。\n1\n2\n3\n```\n\n\n6、文字缩进\n\n```\n使用>格式化缩进，>表示一级缩进，>>表示二级缩进，以此类推\n例如：\n> 一级\n>> 二级\n>>> 三级\n1\n2\n3\n4\n5\n```\n\n\n7、代码段\n\n```\n使用```格式化代码段，注意此处的三个`也是tab键上面的符号键，而不是单引号\n第一种方式是使用反引号(esc键下面的按钮)将代码包裹起来\n第二种方式则是使用制表符或者至少4个空格进行缩进的行\n1\n2\n3\n\n```\n\n8、图片\n\n```\n\n`![Alt text](/path/to/img.jpg)`\n`![Alt text]()`\n详细叙述如下：一个惊叹号 !接着一个方括号，里面放上图片的替代文字\n接着一个普通括号，里面放上图片的网址\n1\n2\n3\n4\n```\n\n9、强调\n\n```\n\n*强调* 或者 _强调_ (示例：斜体)\n**加重强调** 或者 __加重强调__ (示例：粗体)\n***特别强调*** 或者 ___特别强调___ (示例：粗斜体)\n1\n2\n3\n```\n\n10、引用\n\n```\n\n如果我们在文章中引用了资料，那么我们可以通过一个右尖括号\">\"来表示这是一段引用内容。\n我们可以在开头加一个，也可以在每一行的前面都加一个。\n我们还可以在引用里面嵌套其他的引用\n1\n2\n3\n```\n11、分割线\n\n\n```\n想用分割线对内容进行分割，可以在单独一行里输入3个或以上的短横线、星号或者下划线实现。\n短横线和星号之间可以输入任意空格。以下每一行都产生一条水平分割线。\n1\n2\n```\n12、LaTeX 公式\n\n\n```\n可以创建行内公式，例如 $\\Gamma(n) = (n-1)!\\quad\\forall n\\in\\mathbb N$。或者块级公式：\n$$    x = \\dfrac{-b \\pm \\sqrt{b^2 - 4ac}}{2a} $$\n1\n2\n```\n13、表格\n\n```\n\n| Item | Value | Qty |\n| :-------- | --------:| :--: |\n| Computer | 1600 USD | 5 |\n| Phone | 12 USD | 12 |\n| Pipe | 1 USD | 234 |\n1\n2\n3\n4\n5\n```\n14、流程图\n\n```\n\nflowchat\nst=>start: Start\ne=>end\nop=>operation: My Operation\ncond=>condition: Yes or No?\n\nst->op->cond\ncond(yes)->e\ncond(no)->op\n1\n2\n3\n4\n5\n6\n7\n8\n9\n```\n\n15、时序图:\n\n\n```\nsequenceDiagram\nAlice->>Bob:HelloBob,howareyou?\nNoterightofBob:Bobthinks\nBob-->>Alice:Iamgoodthanks!\n1\n2\n3\n4\n\n>**提示：**想了解更多，请查看**流程图**[语法][3]以及**时序图**[语法][4]。\n1\n```\n16、复选框\n\n\n```\n使用`-[]`和`-[x]`语法可以创建复选框，实现todo-list等功能。例如：\n-[]已完成事项\n-[]待办事项1\n-[]待办事项2\n>**注意：**目前支持尚不完全，在印象笔记中勾选复选框是无效、不能同步的，\n所以必须在**马克飞象**中修改Markdown原文才可生效。下个版本将会全面支持。\n1\n2\n3\n4\n5\n6\n```\n', 29, 4, 1, 0, '2022-04-06 21:10:58', '2022-04-06 21:16:32', 8, 8, 0, 1, 1);
INSERT INTO `article` VALUES (57, '2022/4/6', '  今天是我来上海的第20多天，也是被隔离的第两个礼拜\n  今天，把剩下最后的一点菜吃完了，只剩下两个鸡蛋和一小块火腿肠，还有一大把葱。已经对生活失去了信心，真他奶奶的，天天做核酸就不解封。\n\n\n\n::: hljs-center\n\n\n![3eb6e72ff724e5847a064b0814038afc.gif](http://180.163.101.78:9000/public/9c28498492d444618401b473dc1a8fac_3eb6e72ff724e5847a064b0814038afc.gif)\n\n:::\n\n来个大佬救救我吧！！！\n求求了 \n', 30, 6, 1, 0, '2022-04-06 21:22:00', '2022-04-06 21:22:09', 8, 8, 0, 1, 1);
INSERT INTO `article` VALUES (58, '爱的一种解释', '爱是什么？\n一个精灵坐在碧绿的枝叶间沉思。\n风儿若有若无。\n一只鸟儿飞过来，停在枝上，望着远处将要成熟的稻田。\n精灵取出一束黄澄澄的稻谷问道：“你爱这稻谷吗？”\n“爱。”\n“为什么？”\n“它驱赶我的饥饿。”\n鸟儿啄完稻谷，轻轻梳理着光润的羽毛。\n“现在你爱这稻谷吗？”精灵又取出一束黄澄澄的稻谷。\n鸟儿抬头望着远处的一湾泉水回答：“现在我爱那一湾泉水，我有点渴了。”\n精灵摘下一片树叶，里面盛了一汪泉水。\n鸟儿喝完泉水，准备振翅飞去。\n“请再回答我一个问题，”精灵伸出指尖，鸟儿停在上面。\n“你要去做什么更重要的事吗？我这里又稻谷也有泉水。”\n“我要去那片开着风信子的山谷，去看那朵风信子。”\n“为什么？它能驱赶你的饥饿？”\n“不能。”\n“它能滋润你的干渴？”\n“不能。”\n“那你为什么要去看它呢？”\n“我需要它啊。”\n“为什么需要？”\n“我爱它啊。”\n“为什么爱它？”\n“我日日夜夜都在思念它。”\n“为什么思念它？”\n“我爱它。”\n精灵沉默了片刻，又提出一个问题：\n“你为什么只爱那一朵风信子呢？山谷里有无数朵风信子。”\n“因为它是唯一的一朵啊。”\n“为什么？它和其他所有的风信子有什么不同的地方吗？”\n“有的。”\n“哪里不同呢？”\n“只有它才是我爱的那一朵啊。”\n精灵忽然轻轻笑了起来，鸟儿振翅而去。', 33, 1, 1, 0, '2022-04-07 20:05:11', '2022-04-07 20:07:44', 27, 27, 0, 1, 1);
INSERT INTO `article` VALUES (59, '做一个努力的人', '有一次，在我参加的一个晚会上，主持人问一个小男孩：你长大以后要做什么样的人？孩子看看我们这些企业家，然后说：做企业家。在场的人忽地笑着鼓起了掌。我也拍了拍手，但听着并不舒服。我想，这孩子对于企业究竟知道多少呢？他是不是因为当着我们的面才说要当企业家的呢？他是不是受了大人的影响，以为企业家风光，都是有钱的人，才要当企业家的呢？\n\n　　这一切当然都是一个谜。但不管怎样，作为一个人的人生志向，我以为当什么并不重要；不管是谁，最重要的是从小要立志做一个努力的人。\n\n　　我小的时候也曾有人问过同样的问题，我的回答不外乎当教师、解放军和科学家之类。时光一晃流走了二十多年，当年的孩子，如今已是四十出头的大人。但仔细想一想，当年我在大人们跟前表白过的志向，实际一个也没有实现。我身边的其他人差不多也是如此。有的想当教师，后来却成了个体户；想当解放军的，有人竟做了囚犯。我上大学时有两个同窗好友，他们现在都是我国电子行业里才华出众的人，一个成长为“康佳”集团的老总，一个领导着TCL集团。我们三个不期而然地成为中国彩电骨干企业的经营者，可是当年大学毕业时，无论有多大的想像力，我们也不敢想十几年后会成现在的样子。一切都是我们在奋斗中见机行事，一步一步努力得来的。与其说我们是有理想的人，不如说我们是一直在努力的人。\n\n　　并非我们不重视理想，而是因为树雄心壮志易，为理想努力难，人生自古就如此。有谁会想到，十多年前的今天，我曾是一个在街头彷徨，为生存犯愁的人？当时的我，一无所有，前途渺茫，真不知路在何处。然而，我却没有灰心失望，回想起来，支撑着我走过这段坎坷岁月的正是我的意志品格。当许多人以为我已不行、该不行了的时候，我仍做着从地上爬起来的努力，我坚信人生就像马拉多纳踢球，往往是在快要倒下去的时候“进球”获得生机的。事实也正是如此，就在“山重水复疑无路”的时候，香港一家企业倒闭给了我东山再起的机会，使我能够与掌握世界最新技术的英国科技人员合作，开发技术先进的彩色电视机，从此一举走出困境。\n\n　　有人说，“努力”与“拥有”是人生一左一右的两道风景。但我以为，人生最美最不能逊色的风景应该是努力。努力是人生的一种精神状态，是对生命的一种赤子之情。努力是拥有之母，拥有是努力之子。一心努力可谓条条大路通罗马，只想获取可谓道路逼仄，天地窄小。所以，与其规定自己一定要成为一个什么样的人物，获得什么东西，不如磨练自己做一个努力的人。志向再高，没有努力，志向终难坚守；没有远大目标，因为努力，终会找到奋斗的方向。做一个努力的人，可以说是人生最切实际的目标，是人生最大的境界。\n\n　　许多人因为给自己定的目标太高太功利，因为难以成功而变得灰头土脸，最终灰心失望。究其原因，往往就是因为太关注拥有，而忽略做一个努力的人。对于今天的孩子们，如果只关注他们将来该做个什么样的人物，不把意志品质作为一个做人的目标提出来，最终我们只能培养出狭隘、自私、脆弱和境界不高的人。遗憾的是，我们在这方面做得并不尽如人意。', 33, 1, 1, 0, '2022-04-07 20:05:46', '2022-04-07 20:07:48', 27, 27, 0, 1, 1);
INSERT INTO `article` VALUES (60, '放弃，也是一种选择', '很喜欢这样一幅对联：得失失得　何必患得患失/舍得得舍/不妨不舍不得。也许人生的过程就是一个不断放弃，又不断得到的过程。关键是要学会放弃，因为放弃，也是人生的一种选择。\n　　放弃一颗树，你会得到整个森林！放弃一滴水，你就拥有整个大海！放弃一片洼地，你就会占领一座高山！况且有些事情放弃了并不等于失去，当你放弃了对梦的追求，回归现实，你会发现那美好的一天正等待着你，并为你敞开了一扇通往未来的大门。\n　　一颗善良的心，苦苦挣扎着在寻找慰藉。因为太爱，而决定离去，不能给予，也不要去索取。望着你认真的眼神，心里的不舍在蔓延，可我怎能将你匆匆的步伐为我停驻，回你一个微笑，默默的祝你旅行愉快。\n　　许多的事情，总是在经历过以后才会懂得。一如感情，痛过了，才会懂得如何保护自己；傻过了，才会懂得适时地坚持与放弃，在得到与失去中我们慢慢地认识自己。既然默默相守已失去意义，莫不如立即斩断心中那情思屡屡，放弃你所珍爱的，期待的，重新选择。其实，生活并不需要这么些无谓的执着，没有什么就真的不能割舍。学会放弃，生活会更容易。\n　　放弃是一种勇气，但放弃决不是对自己的背叛，放弃自私，放弃虚伪，你就会变得高尚，你生活的天空将是晴空万里。放弃一段飘渺的感情，你就会变得塌实，如释重负，轻轻爽爽。\n　　放弃，不是怯懦，不是自卑，也不是自暴自弃，更不是陷入绝境时渴望得到的一种解脱，而是在痛定思痛后的做出的一种选择。\n　　放弃，不是噩梦方醒，不是六月飞雪，也不是逃避，更不是偃旗息鼓，甘拜下风，而是在发现了对与错、真与伪、善与恶、美与丑之后做出的一种选择。\n　　放弃意味着什么？放弃是一种勇气，但放弃之后，我们将走向何处，值得深思。\n　　放弃，也是一种选择……', 33, 4, 1, 0, '2022-04-07 20:07:37', '2022-04-07 20:07:52', 27, 27, 0, 1, 1);
INSERT INTO `article` VALUES (61, '随手记', '“你要记得那些大雨中为你撑伞的人，帮你挡住外来之物的人，黑暗中默默抱紧你的人，逗你笑的人，陪你彻夜聊天的人，坐车来看望你的人，陪你哭过的人，在医院陪你的人，总是以你为重的人，是这些人组成你生命中一点一滴的温暖，是这些温暖使你成为善良的人 。”——村上春树', 30, 4, 1, 0, '2022-04-07 20:15:11', '2022-04-07 20:16:06', 26, 27, 0, 1, 1);
INSERT INTO `article` VALUES (62, '毒鸡汤', '\n- 很多人不断地规划自己的人生，每天压力很大。其实不管你怎么过。都会后悔的。想想你这前几十年就明白了。\n- 三十岁时，大部分人都卡在初级职位上。现有的工作升不上去，又无法承担转行的时间成本，更来不及再去读书。父母开始多病;自己收入有限，也没有存款。更大的痛苦，是看到身边没有背景但努力又聪明的人已经小有成就，有背景的人已经开始过上贵族生活了。\n- 我有一些漂亮又受过教育的姐妹，本来有机会嫁给官富二代，但很多被那些长得不错、有名校高学历、刚毕业在知名外企工作、加班到很晚还健身的男生所吸引。觉得人家上进又有潜力。五六年后，她们悔得肠子都青了\n- 年轻时总是缺乏对自己的正确认识。时而觉得自己能力超群，海阔天空，时而觉得一无是处，平凡无能。长大后，经历得多了，逐渐认清自己，才发现自己原来是一无是处，平凡无能。\n- 路上看到两个长得不好看也看起来很穷的男女热吻。我说两个物质生活质量很差的人，彼此相伴。朋友立刻反驳我：物质生活质量不行就不会幸福吗?他们如此相爱，比那些有钱又美但离婚的名人强多了!我说我根本没说他们不幸福呀，你努力反驳的，可能是你自己内心的声音吧。\n- 在这个以貌取人的时代，其实真的没什么人会在意你的口红是哪个奢侈品牌，更没什么人能看清你腰上的奢侈品是真是假，但是腿粗、肚子大，在十米开外就能看的一清二楚！\n- 找到男朋友后一定要对他好一点，不要欺负他、伤害他、有负于他，毕竟他已经瞎了。\n- 我对这个社会之中真正能够去追求自己理想的人，我就绝对特别地佩服这些人，因为这个社会有太多的人，分不清楚什么是自己想要的，什么是别人希望你得到的\n- 老一辈人完全不用担心小一辈人在他们走过的路上摔跟头，因为你们走的根本不是一条道，！，。\n- 总觉得叛逆其实就是懒惰和逃避，因为许多人所指的自我叛逆——就是不爱上学、痛恨家长、觉得社会不公平。可是他们从没想过，你想要公平就得努力奋斗一份真正的事业。\n- 在爱的一瞬间的时候，谁的责任并不重要，但是很多时候，真正每天牵肠挂肚，二十四小时没有一分钟，在不想着你，不关心你的人是谁，绝对不是你在想你妈，是你妈在想着你。\n- 有歌星，有笑星，而我们准备当斜星，就是有点偏的感觉。我觉得能给人群带来欢乐的人，才是真正的伟人。微笑才是最好的化妆品。\n- 艺人是所有行业中最软的职业，我只能当孙子。我不能说任何人不好。比如我去吃串，有人要我签名，要我唱歌，要跟我拍照，不同意，人家跟你嚷嚷，你就得跟人家道歉，拍照。虽然你已经很累了。我不敢抗衡，如果我一抗衡就会有更多人攻击我。我只能这样安慰自己，没新闻才是坏新闻\n- 一个好的人生，就是要做一个好的小丑，因为你永远都在耍那几个球，人生一共就五个球，什么事业、爱情、家庭之类的，这些球里面只有一个球是橡皮做的，就是事业，事业掉地上还能再弹起来，剩下都是玻璃做的，只要掉地上就碎了，所以说人生如果能做一个好的小丑是一非常好的事情。\n- 一个歌手，在台上哭来哭去的，说自己以前多苦多郁闷，这个事让我觉得非常怪异。我觉得这个事情不应该说，你已经成功了，就没有资格再去说以前苦的事情了，你一切的苦已经换得今天的成绩了，为什么还说自己苦？世界上谁不苦？谁没有风里来雨里去的日子啊\n- 孝敬父母是多余的话，因为没有人的父母是容易的。你觉得父母不容易，是因为你没有责任感，一个责任感低的人看一个责任感高的人就会觉得别人不容易。\n- 现实就像大楼，你时过境迁阅历暴涨总以为闭着眼都能数清多少层时，却总忘了算上还有地下室。\n- 屋漏偏逢连夜雨，儿到荒年饭量增！那是在一个风雨交加的夜晚，经历了一年半的时间，我们忍受了冰寒，忆往昔，看将来，满园春色关不住，一枝红杏出墙来，祖国跨上千里马，铁马冰河入梦来，君不见黄河之水天上来，人生就是这样，努力是春耕，勤劳才能掷有声。\n- 我有孩子我绝对不想让他成为成功人士，我只想让他做自己就行了，他想成为gay，他想去旅游，他想去抛弃一切，去整容、人体改造，只要他不犯法，我觉得什么都对，但是他有一天跟我说他要成功，我就开始着急了，一个人真的渴望成功，这个人一辈子都不会幸福的。\n- 我也没什么办法化解（质疑），我就是只能说，就是说别人骂街我就当娱乐，因为你让他们开心也是娱乐，你让他们骂你也是娱乐。鲁迅先生说过一句经典的话，就是说对敌人最大的蔑视是掉过头去不予理睬。明白吧，就是骂你跟爱你都是好事，他起码还理我呢。', 33, 2, 1, 0, '2022-04-07 20:15:37', '2022-04-07 20:16:00', 27, 27, 0, 1, 1);
INSERT INTO `article` VALUES (63, '失落', '你是否有这样的感觉？\n\n一觉醒来，发现身边空无一人，顿感失落；\n\n想到某些精彩的段子，却无人可以分享，满眼失望；\n\n完成某项成就，想明白某项事情，可身边无一人理解，只剩空虚。\n\n成年人的世界，孤独随处可见，如影随形。\n\n多少人越是用力去化解孤独，却越是坠入孤独的深渊，动弹不得。\n\n难道孤独真的很可怕，非要去消弭吗？其实不然，关键在于我们如何去看待、如何去和解。\n\n毕竟孤独是常态，最好的化解方式就是与其和平相处，并在其中获得成长。', 30, 0, 3, 0, '2022-04-07 20:18:05', '2022-04-07 20:33:41', 26, 29, 0, 1, 1);
INSERT INTO `article` VALUES (64, '孤独', '   将“孤独”二字拆解为孩童、瓜果、瓢虫，宛如一幅盛夏的景日图，然而这一切与你无关，这便是孤独。', 30, 2, 1, 0, '2022-04-07 20:19:17', '2022-04-07 20:34:14', 26, 29, 0, 1, 1);
INSERT INTO `article` VALUES (65, '2022/4/7', '  今天没去做核酸，然后被楼下阿姨大爷发现了，挨了一顿骂。晚上给我们发了一袋米和面条还有油，说明还得封很久。芭比Q了！', 30, 1, 1, 0, '2022-04-07 20:57:15', '2022-04-07 20:57:34', 27, 27, 0, 1, 1);
INSERT INTO `article` VALUES (66, 'Yesterday', 'Yesterday, all my troubles seemed so far away.\n往日，一切烦恼仿佛那么遥远\nNow it looks as though they’re here to stay.\n如今却似乎都缠绕着我不肯离去\nOh, I believe in yesterday.\n我沉迷于昨日\nSuddenly, I’m not half the man I used to be,\n突然间，我已不再是曾经的自己\nThere’s a shadow hanging over me.\n阴云笼罩着我的一切\nOh, yesterday came suddenly.\n昨日来得那么突然\nWhy she had to go I don’t know she wouldn’t say.\n她为何离去，我无从知晓，她也不曾提起\nI said something wrong, now I long for yesterday.\n或许是我说错过什么，现在的我是多么渴望往昔\nYesterday, love was such an easy game to play.\n往日，爱情触手可及\nNow I need a place to hide away.\n如今我却需要隐藏自我\nOh, I believe in yesterday.\n哦，我沉迷于昨天\nWhy she had to go I don’t know she wouldn’t say.\n她为何离去，我无从知晓，她也不曾提起\nI said something wrong, now I long for yesterday.\n或许是我说错过什么，现在的我是多么渴望往昔\nYesterday, love was such an easy game to play.\n往日，爱情触手可及\nNow I need a place to hide away.\n如今我却需要隐藏自我\nOh, I believe in yesterday.\n哦，我沉迷于昨天', 33, 0, 2, 0, '2022-04-07 21:10:15', NULL, 8, NULL, 0, 1, 1);

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0 启动  1 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (26, '后端', 0, '2022-04-06 19:13:28', '2022-04-06 19:13:39', 27, 27, 0);
INSERT INTO `article_category` VALUES (27, '前端', 0, '2022-04-06 19:13:45', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (28, '算法', 0, '2022-04-06 19:13:55', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (29, '工具', 0, '2022-04-06 19:14:36', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (30, '日常', 0, '2022-04-06 19:14:42', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (31, '资源', 0, '2022-04-06 19:15:40', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (32, '实用', 0, '2022-04-06 19:15:49', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (33, '闲聊', 0, '2022-04-06 19:16:01', NULL, 27, NULL, 0);
INSERT INTO `article_category` VALUES (34, '数据库', 0, '2022-04-06 19:16:08', NULL, 27, NULL, 0);

-- ----------------------------
-- Table structure for article_tags
-- ----------------------------
DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag_id` bigint(15) NULL DEFAULT NULL COMMENT '标签id',
  `article_id` bigint(15) NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tags
-- ----------------------------
INSERT INTO `article_tags` VALUES (92, 22, 54);
INSERT INTO `article_tags` VALUES (93, 23, 54);
INSERT INTO `article_tags` VALUES (94, 24, 55);
INSERT INTO `article_tags` VALUES (95, 25, 56);
INSERT INTO `article_tags` VALUES (96, 26, 57);
INSERT INTO `article_tags` VALUES (97, 27, 58);
INSERT INTO `article_tags` VALUES (98, 27, 59);
INSERT INTO `article_tags` VALUES (99, 27, 60);
INSERT INTO `article_tags` VALUES (100, 28, 61);
INSERT INTO `article_tags` VALUES (101, 29, 62);
INSERT INTO `article_tags` VALUES (102, 28, 63);
INSERT INTO `article_tags` VALUES (103, 28, 64);
INSERT INTO `article_tags` VALUES (104, 30, 65);
INSERT INTO `article_tags` VALUES (105, 31, 66);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(10) NULL DEFAULT NULL COMMENT '父类id',
  `topic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (53, 0, '54', '简单语法', 0, '2022-04-06 20:51:04', NULL, 27, NULL);
INSERT INTO `comment` VALUES (54, 53, '54', '嘿嘿', 0, '2022-04-06 20:51:51', NULL, 27, NULL);
INSERT INTO `comment` VALUES (55, 53, '54', '啊这', 0, '2022-04-06 20:52:21', NULL, 27, NULL);
INSERT INTO `comment` VALUES (56, 0, '57', '我也是，草了', 0, '2022-04-06 21:22:56', NULL, 27, NULL);
INSERT INTO `comment` VALUES (57, 0, '57', '加油', 0, '2022-04-07 20:22:22', NULL, 26, NULL);
INSERT INTO `comment` VALUES (58, 0, '61', '好诗！', 0, '2022-04-07 20:39:46', NULL, 27, NULL);
INSERT INTO `comment` VALUES (59, 58, '61', '真好', 1, '2022-04-07 20:39:53', '2022-04-07 21:14:56', 27, 27);
INSERT INTO `comment` VALUES (60, 58, '61', '嘻嘻', 0, '2022-04-07 20:42:53', NULL, 26, NULL);
INSERT INTO `comment` VALUES (61, 53, '54', '就这？', 0, '2022-04-07 20:44:14', NULL, 26, NULL);

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名字',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小',
  `extension` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀  .jpg',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES (73, 'IMG_1507.JPG', 'http://180.163.101.78:9000/public/42ee69997de2448184b115208bfe6d53_IMG_1507.JPG', 67116, 'JPG', 0, '2022-04-06 18:20:14', NULL, 27, NULL);
INSERT INTO `files` VALUES (74, 'd10855d12a39827dfcf18ab947e7ab7.jpg', 'http://180.163.101.78:9000/public/3ba8b24b8e2e4a0d9d5991a28251b508_d10855d12a39827dfcf18ab947e7ab7.jpg', 212965, 'jpg', 0, '2022-04-06 18:21:06', NULL, 27, NULL);
INSERT INTO `files` VALUES (75, '3eb6e72ff724e5847a064b0814038afc.gif', 'http://180.163.101.78:9000/public/9c28498492d444618401b473dc1a8fac_3eb6e72ff724e5847a064b0814038afc.gif', 6320, 'gif', 0, '2022-04-06 21:20:57', NULL, 8, NULL);
INSERT INTO `files` VALUES (76, 'WIN_20210406_09_57_04_Pro.jpg', 'http://180.163.101.78:9000/public/3dd2c5fb7abe4ed5946aa491358167e6_WIN_20210406_09_57_04_Pro.jpg', 56205, 'jpg', 0, '2022-04-07 20:29:24', NULL, 29, NULL);
INSERT INTO `files` VALUES (77, '微信图片_20220402214238.jpg', 'http://180.163.101.78:9000/public/e2bcfa64ca584f18853abd622ddec551_微信图片_20220402214238.jpg', 279975, 'jpg', 0, '2022-04-07 20:41:04', NULL, 26, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `parent_id` bigint(15) NULL DEFAULT NULL COMMENT '父类id',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0 启用  1 禁用',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '软删除 0 未删除  1  已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'Order-index', '订单管理', '/user/pageList', 0, 1, 1, '2022-01-18 09:57:44', 8, '2022-01-27 16:13:06', 8);
INSERT INTO `menu` VALUES (3, 'System', '系统设置', '/article/pageList', 0, 0, 0, '2022-01-18 09:59:24', 8, '2022-01-29 14:14:59', 8);
INSERT INTO `menu` VALUES (5, 'Login', '登录页', '/login', 0, 0, 0, '2022-01-20 20:29:48', 8, '2022-01-21 10:34:16', 8);
INSERT INTO `menu` VALUES (8, 'Permission', '权限许可', '/permission/page-use', 0, 0, 0, '2022-01-21 10:23:32', 8, '2022-01-21 10:34:58', 8);
INSERT INTO `menu` VALUES (9, 'PageUser', '用户权限', '/permission/page-user', 8, 0, 0, '2022-01-21 10:42:21', 8, '2022-01-21 10:42:21', 8);
INSERT INTO `menu` VALUES (10, 'PageAdmin', '管理员权限', '/permission/page-admin', 8, 0, 0, '2022-01-21 10:43:12', 8, '2022-01-21 10:43:12', 8);
INSERT INTO `menu` VALUES (11, 'Roles', '权限设置', '/permission/roles', 8, 0, 0, '2022-01-21 10:43:36', 8, '2022-01-21 10:43:36', 8);
INSERT INTO `menu` VALUES (12, 'System-menu', '菜单管理', '/system/menu-Setting', 3, 0, 0, '2022-01-26 10:34:43', 8, '2022-01-26 10:34:43', 8);
INSERT INTO `menu` VALUES (13, 'System-user', '用户管理', '/system/user-Setting', 3, 0, 0, '2022-01-26 10:35:21', 8, '2022-01-29 11:37:48', 8);
INSERT INTO `menu` VALUES (14, '404', '404', '/404', 0, 0, 0, '2022-01-29 11:38:57', 8, '2022-01-29 11:38:57', 8);
INSERT INTO `menu` VALUES (15, 'Home', 'Home', '/', 0, 0, 0, '2022-01-29 11:39:25', 8, '2022-01-29 13:42:59', 8);
INSERT INTO `menu` VALUES (16, 'dashbord', '首页', '/dashbord', 15, 0, 0, '2022-01-29 11:41:24', 8, '2022-01-29 13:42:56', 8);
INSERT INTO `menu` VALUES (17, 'Personal', 'Personal', '/personal', 0, 0, 0, '2022-01-29 11:42:19', 8, '2022-01-29 14:23:23', 8);
INSERT INTO `menu` VALUES (18, 'Personal-index', '个人中心', '/personal/index', 17, 0, 0, '2022-01-29 11:42:44', 8, '2022-01-29 14:23:28', 8);
INSERT INTO `menu` VALUES (19, 'Article', '博客管理', '/article', 0, 0, 0, '2022-01-29 11:43:36', 8, '2022-02-02 17:03:54', 8);
INSERT INTO `menu` VALUES (20, 'Article-index', '文章列表', '/article/article-index', 19, 0, 0, '2022-01-29 11:44:07', 8, '2022-01-29 11:44:07', 8);
INSERT INTO `menu` VALUES (21, 'Article-input', '新增文章', '/article/article-input', 19, 0, 0, '2022-01-29 11:45:08', 8, '2022-01-29 11:45:08', 8);
INSERT INTO `menu` VALUES (22, 'Category-index', '分类管理', '/article/category-index', 19, 0, 0, '2022-01-29 11:45:27', 8, '2022-01-29 11:45:27', 8);
INSERT INTO `menu` VALUES (23, 'Tags-index', '标签管理', '/article/tags-index', 19, 0, 0, '2022-01-29 11:46:09', 8, '2022-01-29 11:46:09', 8);
INSERT INTO `menu` VALUES (24, 'notFound', 'notFound', '/404', 0, 0, 0, '2022-01-29 13:41:28', 8, '2022-01-29 22:28:14', 8);
INSERT INTO `menu` VALUES (25, 'Excel', 'Excel', '/excel-operate/excel-out', 0, 0, 0, '2022-01-29 14:26:13', 8, '2022-01-29 14:26:13', 8);
INSERT INTO `menu` VALUES (26, 'Excel-out', 'Excel导出', '/excel-operate/excel-out', 25, 0, 0, '2022-01-29 14:26:35', 8, '2022-01-29 14:26:35', 8);
INSERT INTO `menu` VALUES (27, 'Excel-in', 'Excel导入', '/excel-operate/excel-in', 25, 0, 0, '2022-01-29 14:27:10', 8, '2022-01-29 14:27:10', 8);
INSERT INTO `menu` VALUES (28, 'Mutiheader-out', '多级表头导出', '/excel-operate/mutiheader-out', 25, 0, 0, '2022-01-29 14:27:28', 8, '2022-01-29 14:27:28', 8);
INSERT INTO `menu` VALUES (29, 'Table', 'Table', '/table/base-table', 0, 0, 0, '2022-01-29 14:30:18', 8, '2022-01-29 14:30:18', 8);
INSERT INTO `menu` VALUES (30, 'BaseTable', '普通表格', '/table/common-table', 29, 0, 0, '2022-01-29 14:30:42', 8, '2022-01-29 14:30:42', 8);
INSERT INTO `menu` VALUES (31, 'ComplexTable', '复杂表格', '/table/complex-table', 29, 0, 0, '2022-01-29 14:31:04', 8, '2022-01-29 14:31:04', 8);
INSERT INTO `menu` VALUES (32, 'Icons', 'Icons', '/icons', 0, 0, 0, '2022-01-29 14:31:53', 8, '2022-01-29 14:31:53', 8);
INSERT INTO `menu` VALUES (33, 'Icons-index', 'Icons图标', '/icons', 32, 0, 0, '2022-01-29 14:32:20', 8, '2022-01-29 14:32:20', 8);
INSERT INTO `menu` VALUES (34, 'Echarts', 'Echarts', '/echarts', 0, 0, 0, '2022-01-29 14:33:00', 8, '2022-01-29 14:33:00', 8);
INSERT INTO `menu` VALUES (35, 'Sldie-chart', '滑动charts', '/echarts/slide-chart', 34, 0, 0, '2022-01-29 14:33:18', 8, '2022-01-29 14:33:18', 8);
INSERT INTO `menu` VALUES (36, 'Dynamic-chart', '切换charts', '/echarts/dynamic-chart', 34, 0, 0, '2022-01-29 14:33:39', 8, '2022-01-29 14:33:39', 8);
INSERT INTO `menu` VALUES (37, 'Map-chart', 'map', '/echarts/map-chart', 34, 0, 0, '2022-01-29 14:33:59', 8, '2022-01-29 14:34:00', 8);
INSERT INTO `menu` VALUES (38, 'Components', '部分组件', '/components', 0, 0, 0, '2022-01-29 14:34:26', 8, '2022-01-29 14:34:26', 8);
INSERT INTO `menu` VALUES (39, 'Sldie-yz', '滑动验证', '/components/slide-yz', 38, 0, 0, '2022-01-29 14:34:45', 8, '2022-01-29 14:34:45', 8);
INSERT INTO `menu` VALUES (40, 'Upload', '上传图片', '/components/pushImg', 38, 0, 0, '2022-01-29 14:35:05', 8, '2022-01-29 14:35:05', 8);
INSERT INTO `menu` VALUES (41, 'Carousel', '轮播', '/components/carousel', 38, 0, 0, '2022-01-29 14:35:21', 8, '2022-01-29 14:35:21', 8);
INSERT INTO `menu` VALUES (42, 'Announcement', '公告管理', '/announcement', 0, 0, 0, '2022-03-30 20:58:35', 8, '2022-03-30 20:58:35', 8);
INSERT INTO `menu` VALUES (43, 'Announcement-index', '公告列表', '/announcement/index', 42, 0, 0, '2022-03-30 20:59:58', 8, '2022-03-30 20:59:58', 8);
INSERT INTO `menu` VALUES (44, 'Article-audit', '审核文章', '/article/article-audit', 19, 0, 0, '2022-04-04 00:51:25', 8, '2022-04-04 00:51:25', 8);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0未删除  1已删除',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (25, '努力！奋斗！', 0, 27, '2022-04-06 20:54:32', NULL, NULL);
INSERT INTO `message` VALUES (26, 'come on!', 0, 26, '2022-04-07 20:19:59', NULL, NULL);
INSERT INTO `message` VALUES (27, '好奇\n', 0, 29, '2022-04-07 20:35:33', NULL, NULL);
INSERT INTO `message` VALUES (28, '我心中有团火，他们看到的只是烟。而你们在人群中看到了我的火，于是狂奔，生怕晚一点，我就会淹没在转瞬的岁月里，你们带着热情，带着对爱毫无理由的相信，跑得上气不接下气', 0, 27, '2022-04-07 20:38:16', NULL, NULL);
INSERT INTO `message` VALUES (29, 'Before finding the right person, the only thing you need to do is to make yourself good enough. ', 0, 27, '2022-04-07 21:00:35', NULL, NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(15) NULL DEFAULT NULL COMMENT 'role_id',
  `menu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 812 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (542, NULL, 'Login');
INSERT INTO `role_menu` VALUES (543, NULL, '404');
INSERT INTO `role_menu` VALUES (544, NULL, 'Dashbord');
INSERT INTO `role_menu` VALUES (545, NULL, 'Personal-index');
INSERT INTO `role_menu` VALUES (546, NULL, 'Article');
INSERT INTO `role_menu` VALUES (547, NULL, 'Article-index');
INSERT INTO `role_menu` VALUES (548, NULL, 'Article-input');
INSERT INTO `role_menu` VALUES (549, NULL, 'Category-index');
INSERT INTO `role_menu` VALUES (550, NULL, 'Tags-index');
INSERT INTO `role_menu` VALUES (551, NULL, 'Components');
INSERT INTO `role_menu` VALUES (552, NULL, 'Sldie-yz');
INSERT INTO `role_menu` VALUES (553, NULL, 'Upload');
INSERT INTO `role_menu` VALUES (554, NULL, 'Carousel');
INSERT INTO `role_menu` VALUES (555, NULL, 'System');
INSERT INTO `role_menu` VALUES (556, NULL, 'System-menu');
INSERT INTO `role_menu` VALUES (557, NULL, 'System-user');
INSERT INTO `role_menu` VALUES (558, NULL, 'Permission');
INSERT INTO `role_menu` VALUES (559, NULL, 'PageUser');
INSERT INTO `role_menu` VALUES (560, NULL, 'PageAdmin');
INSERT INTO `role_menu` VALUES (561, NULL, 'Roles');
INSERT INTO `role_menu` VALUES (562, NULL, 'notFound');
INSERT INTO `role_menu` VALUES (718, 3, 'Login');
INSERT INTO `role_menu` VALUES (719, 3, '404');
INSERT INTO `role_menu` VALUES (720, 3, 'Dashbord');
INSERT INTO `role_menu` VALUES (721, 3, 'Personal-index');
INSERT INTO `role_menu` VALUES (722, 3, 'Article-index');
INSERT INTO `role_menu` VALUES (723, 3, 'Article-input');
INSERT INTO `role_menu` VALUES (724, 3, 'Tags-index');
INSERT INTO `role_menu` VALUES (725, 3, 'PageUser');
INSERT INTO `role_menu` VALUES (726, 3, 'notFound');
INSERT INTO `role_menu` VALUES (777, 2, 'Login');
INSERT INTO `role_menu` VALUES (778, 2, '404');
INSERT INTO `role_menu` VALUES (779, 2, 'Dashbord');
INSERT INTO `role_menu` VALUES (780, 2, 'Personal-index');
INSERT INTO `role_menu` VALUES (781, 2, 'Article');
INSERT INTO `role_menu` VALUES (782, 2, 'Article-index');
INSERT INTO `role_menu` VALUES (783, 2, 'Article-input');
INSERT INTO `role_menu` VALUES (784, 2, 'Article-audit');
INSERT INTO `role_menu` VALUES (785, 2, 'Category-index');
INSERT INTO `role_menu` VALUES (786, 2, 'Tags-index');
INSERT INTO `role_menu` VALUES (787, 2, 'Announcement');
INSERT INTO `role_menu` VALUES (788, 2, 'Announcement-index');
INSERT INTO `role_menu` VALUES (789, 2, 'System-user');
INSERT INTO `role_menu` VALUES (790, 2, 'PageAdmin');
INSERT INTO `role_menu` VALUES (791, 2, 'notFound');
INSERT INTO `role_menu` VALUES (792, 1, 'Login');
INSERT INTO `role_menu` VALUES (793, 1, '404');
INSERT INTO `role_menu` VALUES (794, 1, 'Dashbord');
INSERT INTO `role_menu` VALUES (795, 1, 'Personal-index');
INSERT INTO `role_menu` VALUES (796, 1, 'Article');
INSERT INTO `role_menu` VALUES (797, 1, 'Article-index');
INSERT INTO `role_menu` VALUES (798, 1, 'Article-input');
INSERT INTO `role_menu` VALUES (799, 1, 'Article-audit');
INSERT INTO `role_menu` VALUES (800, 1, 'Category-index');
INSERT INTO `role_menu` VALUES (801, 1, 'Tags-index');
INSERT INTO `role_menu` VALUES (802, 1, 'Announcement');
INSERT INTO `role_menu` VALUES (803, 1, 'Announcement-index');
INSERT INTO `role_menu` VALUES (804, 1, 'System');
INSERT INTO `role_menu` VALUES (805, 1, 'System-menu');
INSERT INTO `role_menu` VALUES (806, 1, 'System-user');
INSERT INTO `role_menu` VALUES (807, 1, 'Permission');
INSERT INTO `role_menu` VALUES (808, 1, 'PageUser');
INSERT INTO `role_menu` VALUES (809, 1, 'PageAdmin');
INSERT INTO `role_menu` VALUES (810, 1, 'Roles');
INSERT INTO `role_menu` VALUES (811, 1, 'notFound');

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT 'ip地址',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0 未删除  1已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES (59, '220.196.192.13', 0, '2022-02-16 17:40:36');
INSERT INTO `site` VALUES (60, '220.196.192.13', 0, '2022-02-16 17:40:36');
INSERT INTO `site` VALUES (61, '220.196.192.13', 0, '2022-02-18 17:40:36');
INSERT INTO `site` VALUES (62, '220.196.192.13', 0, '2022-04-06 20:03:55');
INSERT INTO `site` VALUES (63, '220.196.192.13', 0, '2022-04-06 20:48:29');
INSERT INTO `site` VALUES (64, '39.144.39.15', 0, '2022-04-06 20:50:23');
INSERT INTO `site` VALUES (65, '39.144.39.15', 0, '2022-04-06 20:52:31');
INSERT INTO `site` VALUES (66, '39.144.39.15', 0, '2022-04-06 20:54:43');
INSERT INTO `site` VALUES (67, '39.144.39.15', 0, '2022-04-06 21:01:03');
INSERT INTO `site` VALUES (68, '39.144.39.15', 0, '2022-04-06 21:02:47');
INSERT INTO `site` VALUES (69, '39.144.39.15', 0, '2022-04-06 21:11:02');
INSERT INTO `site` VALUES (70, '39.144.39.15', 0, '2022-04-06 21:16:21');
INSERT INTO `site` VALUES (71, '39.144.39.15', 0, '2022-04-06 21:22:15');
INSERT INTO `site` VALUES (72, '39.144.39.15', 0, '2022-04-06 21:36:58');
INSERT INTO `site` VALUES (73, '58.219.179.145', 0, '2022-04-06 21:41:31');
INSERT INTO `site` VALUES (74, '58.219.179.145', 0, '2022-04-06 21:42:38');
INSERT INTO `site` VALUES (75, '39.144.39.15', 0, '2022-04-06 21:44:32');
INSERT INTO `site` VALUES (76, '112.3.41.41', 0, '2022-04-07 07:37:37');
INSERT INTO `site` VALUES (77, '39.144.39.15', 0, '2022-04-07 10:30:41');
INSERT INTO `site` VALUES (78, '39.144.39.15', 0, '2022-04-07 10:35:15');
INSERT INTO `site` VALUES (79, '220.196.192.84', 0, '2022-04-07 20:03:08');
INSERT INTO `site` VALUES (80, '223.84.238.131', 0, '2022-04-07 20:08:09');
INSERT INTO `site` VALUES (81, '220.196.192.84', 0, '2022-04-07 20:10:54');
INSERT INTO `site` VALUES (82, '223.84.238.131', 0, '2022-04-07 20:11:12');
INSERT INTO `site` VALUES (83, '223.84.238.131', 0, '2022-04-07 20:18:38');
INSERT INTO `site` VALUES (84, '223.84.238.131', 0, '2022-04-07 20:20:02');
INSERT INTO `site` VALUES (85, '223.84.238.131', 0, '2022-04-07 20:23:54');
INSERT INTO `site` VALUES (86, '39.144.104.194', 0, '2022-04-07 20:24:47');
INSERT INTO `site` VALUES (87, '39.144.104.194', 0, '2022-04-07 20:35:07');
INSERT INTO `site` VALUES (88, '39.144.104.194', 0, '2022-04-07 20:36:13');
INSERT INTO `site` VALUES (89, '220.196.192.84', 0, '2022-04-07 20:38:23');
INSERT INTO `site` VALUES (90, '220.196.192.84', 0, '2022-04-07 20:41:35');
INSERT INTO `site` VALUES (91, '223.84.238.131', 0, '2022-04-07 20:42:36');
INSERT INTO `site` VALUES (92, '220.196.192.84', 0, '2022-04-07 20:54:05');
INSERT INTO `site` VALUES (93, '220.196.192.84', 0, '2022-04-07 20:57:24');
INSERT INTO `site` VALUES (94, '220.196.192.84', 0, '2022-04-07 21:00:42');
INSERT INTO `site` VALUES (95, '220.196.192.84', 0, '2022-04-07 21:02:18');
INSERT INTO `site` VALUES (96, '220.196.192.84', 0, '2022-04-07 21:14:24');
INSERT INTO `site` VALUES (97, '220.196.192.84', 0, '2022-04-07 21:36:28');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0 启用  1 禁用',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (22, 'mysql', 0, 0, '2022-04-06 20:01:51', NULL, 27, NULL);
INSERT INTO `tags` VALUES (23, '基础', 0, 0, '2022-04-06 20:01:51', NULL, 27, NULL);
INSERT INTO `tags` VALUES (24, '建议', 0, 0, '2022-04-06 20:28:35', NULL, 27, NULL);
INSERT INTO `tags` VALUES (25, 'MarkDown', 0, 0, '2022-04-06 21:10:58', '2022-04-06 21:16:06', 8, 8);
INSERT INTO `tags` VALUES (26, '日记', 0, 0, '2022-04-06 21:22:00', NULL, 8, NULL);
INSERT INTO `tags` VALUES (27, '美文', 0, 0, '2022-04-07 20:05:11', NULL, 27, NULL);
INSERT INTO `tags` VALUES (28, '文艺', 0, 0, '2022-04-07 20:15:11', NULL, 26, NULL);
INSERT INTO `tags` VALUES (29, '毒鸡汤', 0, 0, '2022-04-07 20:15:37', NULL, 27, NULL);
INSERT INTO `tags` VALUES (30, '日记', 0, 0, '2022-04-07 20:57:15', NULL, 27, NULL);
INSERT INTO `tags` VALUES (31, '歌词', 0, 0, '2022-04-07 21:10:15', NULL, 8, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(15) NOT NULL COMMENT '角色编号',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` int(1) NULL DEFAULT NULL COMMENT '男 0   女 1',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `qq` bigint(15) NULL DEFAULT NULL COMMENT 'QQ',
  `wechat` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'WeChat',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` bigint(11) NULL DEFAULT NULL COMMENT '电话',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0:未删除  1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0 启动  1  关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 1, 'admin', 0, 'FTnF43ZOT0tqFvcHCS2xtaPWlzmpeA==', 'http://180.163.101.78:9000/public/984db29d0bc543c491b9671095efd3f8_IMG_1500.JPG', 1574802397, 'llllllxxxxxxxxxxxxxxxx', 'l1574802397@163.com', 1231232132, '帅气 21', 0, '2021-12-28 00:00:00', '2022-04-06 17:43:46', 8, 0);
INSERT INTO `user` VALUES (26, 3, 'xxx', 0, 'YhaNmDcXXQoUdQl1kFa+n19eyqz7CQ==', 'http://180.163.101.78:9000/public/16caa19f5ef34e62ac0c24297665c648_2046185.jpg', 111111, 'xxxxx', '2518435628@qq.com', 666666, '“不是所有的鱼都会生活在同一片海里。”', 0, '2022-04-05 19:49:29', '2022-04-05 20:52:21', 26, 0);
INSERT INTO `user` VALUES (27, 2, 'lgq', 0, '03UMGZmoIi9K71PE6TRtPyfuwmm2tg==', 'http://180.163.101.78:9000/public/42ee69997de2448184b115208bfe6d53_IMG_1507.JPG', 1574802397, 'lllxxxxxxxxxx', '1574802397@qq.com', 15779434844, '嘿嘿', 0, '2022-04-06 18:13:21', '2022-04-07 20:06:24', 27, 0);
INSERT INTO `user` VALUES (28, 3, 'zq', 1, 'LNa9KnU84QrcOUm6Wau+VuBX8g+IPg==', NULL, 1254789631, 'zq0922', 'l1574802397@gmail.com', 13452149875, 'xx', 0, '2022-03-22 18:19:24', NULL, NULL, 0);
INSERT INTO `user` VALUES (29, 2, 'zdf', NULL, '53NehJdl4QrcOUm6Wau+VuBX8g+IPg==', 'http://180.163.101.78:9000/public/3dd2c5fb7abe4ed5946aa491358167e6_WIN_20210406_09_57_04_Pro.jpg', NULL, NULL, '2631193744@qq.com', NULL, '', 0, '2022-04-07 20:27:29', '2022-04-07 20:29:24', 27, 0);

-- ----------------------------
-- Table structure for user_donate
-- ----------------------------
DROP TABLE IF EXISTS `user_donate`;
CREATE TABLE `user_donate`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `donate_json` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '赞赏信息',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_donate
-- ----------------------------
INSERT INTO `user_donate` VALUES (5, 'http://180.163.101.78:9000/public/3ba8b24b8e2e4a0d9d5991a28251b508_d10855d12a39827dfcf18ab947e7ab7.jpg', 0, '2022-04-06 18:21:08', NULL, 27, NULL);
INSERT INTO `user_donate` VALUES (6, 'http://180.163.101.78:9000/public/e2bcfa64ca584f18853abd622ddec551_微信图片_20220402214238.jpg', 0, '2022-04-07 20:41:04', NULL, 26, NULL);

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `like` int(1) NULL DEFAULT NULL COMMENT '未点赞：0，点赞：1',
  `article_id` bigint(15) NULL DEFAULT NULL COMMENT '文章id',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0:未删除   1:删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_like
-- ----------------------------
INSERT INTO `user_like` VALUES (20, 1, 54, 0, '2022-04-06 20:50:51', 27, NULL, NULL);
INSERT INTO `user_like` VALUES (21, 0, 55, 0, '2022-04-06 21:03:34', 27, 27, '2022-04-06 21:23:48');
INSERT INTO `user_like` VALUES (22, 1, 56, 0, '2022-04-06 21:24:00', 27, NULL, NULL);
INSERT INTO `user_like` VALUES (23, 1, 61, 0, '2022-04-07 20:41:17', 27, 27, '2022-04-07 20:41:18');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 'superAdmin', '超级管理员', 0, NULL, '2022-04-07 21:07:50', NULL, 8);
INSERT INTO `user_role` VALUES (2, 'admin', '管理员', 0, '2022-03-28 19:42:54', '2022-04-05 14:42:05', 8, 8);
INSERT INTO `user_role` VALUES (3, 'user', '普通用户', 0, NULL, '2022-04-04 02:46:10', NULL, 8);

SET FOREIGN_KEY_CHECKS = 1;
