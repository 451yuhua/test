/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.20-log : Database - root_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`root_manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `root_manager`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuId` varchar(32) NOT NULL COMMENT '菜单id',
  `name` varchar(32) NOT NULL COMMENT '菜单名',
  `type` varchar(32) NOT NULL COMMENT '权限类型',
  `parentId` varchar(32) DEFAULT '' COMMENT '父级权限',
  `orderBy` varchar(32) DEFAULT NULL COMMENT '排序',
  `rank` int(10) DEFAULT NULL,
  `menuUrl` varchar(200) DEFAULT '' COMMENT '跳转路径url',
  `menuStatus` varchar(32) NOT NULL COMMENT '状态',
  `preIcon` varchar(32) DEFAULT '' COMMENT '点击前',
  `sufIcon` varchar(32) DEFAULT '' COMMENT '点击后',
  `frame` varchar(32) DEFAULT '' COMMENT '跳转框架',
  `description` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `system` varchar(32) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuId`,`name`,`type`,`parentId`,`orderBy`,`rank`,`menuUrl`,`menuStatus`,`preIcon`,`sufIcon`,`frame`,`description`,`system`,`updateTime`) values ('1','系统权限管理','SYS','6','/1000001/1000001/1000001',1000001,NULL,'active',NULL,NULL,NULL,'adfads','6','2018-07-28 22:01:11'),('10','系统列表','SER',NULL,'/1000001',1000001,NULL,'active',NULL,NULL,NULL,'adsfa','','2008-03-02 12:23:22'),('2','用户管理','SYS','1','/1000001/1000001/1000001/1000007',1000007,NULL,'active',NULL,NULL,NULL,'dddd','6',NULL),('260eb92831fb4abf9c4c8e7bef2104ba','2222','SYS','c51b1e431aef48f2bec4d71ef271a07e','/1000001/1000005/1000001/1000001',1000001,'33','active','ASCII.jpg','254351449550176474.png','222','','b46c198eeeed4d028cada012c5b93dc8','2018-07-30 14:12:02'),('3','组织机构管理','SYS','1','/1000001/1000001/1000001/1000008',1000008,NULL,'active',NULL,NULL,NULL,'sdafsa','6',NULL),('4','角色管理','SYS','1','/1000001/1000001/1000001/1000009',1000009,NULL,'stop',NULL,NULL,NULL,'dafasdf','6',NULL),('5','菜单管理','SYS','1','/1000001/1000001/1000001/1000010',1000010,NULL,'active',NULL,NULL,NULL,'fsda','6',NULL),('6','权限系统开发平台','SER','10','/1000001/1000001',1000001,NULL,'active',NULL,NULL,NULL,'dfasdf','','2008-01-12 12:23:22'),('7','OA信息管理系统','SER','10','/1000001/1000004',1000004,NULL,'active',NULL,NULL,NULL,'sdaf','',NULL),('8','物质管理','BIZ','7','/1000001/1000004/1000001',1000001,NULL,'stop',NULL,NULL,NULL,'sadf','7',NULL),('9','支付管理','BIZ','7','/1000001/1000004/1000003',1000003,NULL,'stop',NULL,NULL,NULL,'df','7',NULL),('b46c198eeeed4d028cada012c5b93dc8','超级病毒库','SER','10','/1000001/1000005',1000005,'1','active',NULL,NULL,'1','1','2','2018-07-29 15:05:22'),('c24d749df4334795ae513913e4b8ecf4','发人','BIZ','10','/1000001/1000002',1000002,'','active',NULL,NULL,'','','','2018-07-29 17:29:08'),('c51b1e431aef48f2bec4d71ef271a07e','f w','BIZ','b46c198eeeed4d028cada012c5b93dc8','/1000001/1000005/1000001',1000001,'f ','active',NULL,NULL,'w','','b46c198eeeed4d028cada012c5b93dc8','2018-07-30 13:56:03');

/*Table structure for table `menu_role` */

DROP TABLE IF EXISTS `menu_role`;

CREATE TABLE `menu_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `roleId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名',
  `menuId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `menu_role` */

insert  into `menu_role`(`id`,`roleId`,`menuId`) values ('1','1','1'),('2','2','2'),('3','3','3');

/*Table structure for table `org` */

DROP TABLE IF EXISTS `org`;

CREATE TABLE `org` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '999' COMMENT 'id',
  `parentId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父级id',
  `orgName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组织名',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型',
  `description` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `org` */

insert  into `org`(`id`,`parentId`,`orgName`,`level`,`type`,`description`) values ('1','0','中国海信集团',1,'组织','最高级'),('10','fe5f62971cb348138627b55c9100408c','技术主管',5,'岗位',''),('11','1','上海海信分公司',2,'组织','地区级'),('12','6','高级工程师',5,'岗位','高级职称'),('125d62e3288743528c8c6dacf58f1908','561f8e8937f149388f342eca5517c357','财务部',3,'部门','财务'),('14cd36305acc44558f10ee6e41ad9d56','265fc3caf9fe47c29dae3ebfa9a8fe3b','销售总监',4,'岗位','管理岗'),('1a2115a691db4ec4abc14e5a880cdf1a','a03bd25a8360456a8b9ea5dd1132a175','市场经理',4,'岗位','啊啊啊'),('2','1','重庆海信分公司',2,'组织','地区级'),('265fc3caf9fe47c29dae3ebfa9a8fe3b','11','市场部',3,'部门','市场部门'),('3','2','财务部',3,'部门','从九龙坡办事处迁移回来'),('4','2','市场部',3,'部门','xxx'),('5','2','技术部',3,'部门','xxx'),('561f8e8937f149388f342eca5517c357','1','成都分公司',2,'组织','分公司'),('6','5','技术工程师',4,'岗位',NULL),('6a0532d302e7433a82120f4a439e3ba1','a03bd25a8360456a8b9ea5dd1132a175','市场总监',4,'岗位',''),('6e94a35edd8546569ce6bae55db59e7f','11','研发部',3,'部门','研发部'),('7','3','财务主管',4,'岗位',NULL),('8','4','市场调研',4,'岗位',NULL),('9','4','市场主管',4,'岗位',NULL),('a03bd25a8360456a8b9ea5dd1132a175','561f8e8937f149388f342eca5517c357','市场部',3,'部门',''),('a5c1077de7d442e09b5d45a002acdc32','14cd36305acc44558f10ee6e41ad9d56','销售专员',5,'岗位','销售专员'),('c4a4b423eedd4bf1ac093eef7c963ee6','561f8e8937f149388f342eca5517c357','研发部',3,'部门','成都分公司研发部'),('cc096094cb65475c9839fa6562c0caad','6e94a35edd8546569ce6bae55db59e7f','技术总监',4,'岗位','管理岗'),('da0b4bc44ec74739b10c80490d2c46f3','6e94a35edd8546569ce6bae55db59e7f','研发经理',4,'岗位','管理岗'),('e896265ae2cb49589b562ea98a04f40d','fe5f62971cb348138627b55c9100408c','研发主管',5,'岗位','管理岗'),('ee3cb93b6c6f49ba8c3514c6c2ea7ba3','fe5f62971cb348138627b55c9100408c','java工程师',5,'岗位',''),('fe5f62971cb348138627b55c9100408c','c4a4b423eedd4bf1ac093eef7c963ee6','开发一部',4,'部门','啊啊啊');

/*Table structure for table `org_menu` */

DROP TABLE IF EXISTS `org_menu`;

CREATE TABLE `org_menu` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `menuId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '记录号',
  `orgId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色号',
  `menuType` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `org_menu` */

insert  into `org_menu`(`id`,`menuId`,`orgId`,`menuType`) values ('1','1','1','xxx'),('2','2','2','ccc'),('3','3','3','bbb'),('4','2','4','ddd');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `roleName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名',
  `roleType` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色类型',
  `roleOrder` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色序列',
  `roleStatus` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态',
  `description` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`roleName`,`roleType`,`roleOrder`,`roleStatus`,`description`) values ('1','admin','董事','111','1','董事组'),('2','test','经理','222','2','经理组'),('3','test1','员工','333','3','员工组'),('4','李婉玉','经理','444','2','经理组'),('5','马玉','员工','555','3','员工组');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `tid` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '软件名称',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `test` */

insert  into `test`(`tid`,`tname`) values (1,'测试员'),(2,'管理员'),(3,'游客');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键id',
  `userName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `userPass` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `userEmail` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `userStatus` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态',
  `description` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`userPass`,`userEmail`,`userStatus`,`description`) values ('1','admin','123','admin@qq.com','1','admin'),('10','cc','123','123@qq.com','1','cc'),('11','dd','123','123@qq.com','1','dd'),('2','test','123','tset@qq.com','1','tset'),('3','zls','123','zls@qq.com','1','zls'),('4','yh','123','yh@qq.com','1','yh'),('5','ghb','123','ghb@qq.com','1','ghb'),('6','zfl','123','zfl@qq.com','2','zfl'),('7','ljh','123','ljh@qq.com','2','ljh'),('8','aa','123','123@qq.com','1','aa'),('9','bb','123','123@qq.com','2','bb'),('9bc623a3a1d5401dbcee7e9ae18117cf','asdf','123','123@qq.com','2','asdfa'),('aa3aa59337c94c7b9cd6655f00346320','qwer','123','123@qq.com','1','rqwqr');

/*Table structure for table `user_menu` */

DROP TABLE IF EXISTS `user_menu`;

CREATE TABLE `user_menu` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `userId` varchar(32) DEFAULT NULL COMMENT '用户名',
  `menuId` varchar(32) DEFAULT NULL COMMENT '菜单id',
  `orgid` varchar(32) DEFAULT NULL COMMENT '组织架构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_menu` */

insert  into `user_menu`(`id`,`userId`,`menuId`,`orgid`) values ('1','1','1',NULL),('2','2','2',NULL),('3','3','3',NULL);

/*Table structure for table `user_org` */

DROP TABLE IF EXISTS `user_org`;

CREATE TABLE `user_org` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `userId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  `orgId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_org` */

insert  into `user_org`(`id`,`userId`,`orgId`) values ('1','1','1'),('2','2','2'),('3','3','3'),('4','4','2');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `userId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  `roleId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色id',
  `description` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`,`description`) values ('1','1','1','1'),('2','2','2','2'),('3','3','3','3'),('4','4','4','4'),('5','5','1','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
