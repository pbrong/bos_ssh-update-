/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.49 : Database - bos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bos`;

/*Table structure for table `auth_function` */

DROP TABLE IF EXISTS `auth_function`;

CREATE TABLE `auth_function` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `page` varchar(255) DEFAULT NULL,
  `generatemenu` varchar(255) DEFAULT NULL,
  `zindex` int(11) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33r6np87v1p6gge7t6rpcao5h` (`pid`),
  CONSTRAINT `FK33r6np87v1p6gge7t6rpcao5h` FOREIGN KEY (`pid`) REFERENCES `auth_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_function` */

insert  into `auth_function`(`id`,`name`,`code`,`description`,`page`,`generatemenu`,`zindex`,`pid`) values 
('11','基础档案','jichudangan',NULL,NULL,'1',0,NULL),
('112','收派标准','standard',NULL,'page_base_standard.action','1',1,'11'),
('113','取派员设置','staff',NULL,'page_base_staff.action','1',2,'11'),
('114','区域设置','region',NULL,'page_base_region.action','1',3,'11'),
('115','管理分区','subarea',NULL,'page_base_subarea.action','1',4,'11'),
('116','管理定区/调度排班','decidedzone',NULL,'page_base_decidedzone.action','1',5,'11'),
('12','受理','shouli',NULL,NULL,'1',1,NULL),
('121','业务受理','noticebill',NULL,'page_qupai_noticebill_add.action','1',0,'12'),
('122','工作单快速录入','quickworkordermanage',NULL,'page_qupai_quickworkorder.action','1',1,'12'),
('124','工作单导入','workordermanageimport',NULL,'page_qupai_workorderimport.action','1',3,'12'),
('13','调度','diaodu',NULL,NULL,'1',2,NULL),
('131','查台转单','changestaff',NULL,NULL,'1',0,'13'),
('132','人工调度','personalassign',NULL,'page_qupai_diaodu.action','1',1,'13'),
('14','物流配送流程管理','zhongzhuan',NULL,NULL,'1',3,NULL),
('141','启动配送流程','start',NULL,'workOrderManageAction_list.action','1',0,'14'),
('142','查看个人任务','personaltask',NULL,'taskAction_findPersonalTask.action','1',1,'14'),
('143','查看我的组任务','grouptask',NULL,'taskAction_findGroupTask.action','1',2,'14'),
('8a7e843355a4392d0155a43aa7150000','删除取派员','staff.delete','xxx','staffAction_delete.action','0',1,'113'),
('8a7e843355a442460155a442bcfc0000','传智播客','itcast','','http://www.itcast.cn','1',1,NULL);

/*Table structure for table `auth_role` */

DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_role` */

insert  into `auth_role`(`id`,`name`,`code`,`description`) values 
('2','2',NULL,'2');

/*Table structure for table `bc_decidedzone` */

DROP TABLE IF EXISTS `bc_decidedzone`;

CREATE TABLE `bc_decidedzone` (
  `id` varchar(32) NOT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0xplk12o52a6eryw4hcqnfwt` (`staff_id`),
  CONSTRAINT `FKh0xplk12o52a6eryw4hcqnfwt` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bc_decidedzone` */

insert  into `bc_decidedzone`(`id`,`staff_id`,`name`) values 
('1','402881346484e5c3016484e8680f0001',NULL);

/*Table structure for table `bc_region` */

DROP TABLE IF EXISTS `bc_region`;

CREATE TABLE `bc_region` (
  `id` varchar(32) NOT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `postcode` varchar(50) DEFAULT NULL,
  `shortcode` varchar(30) DEFAULT NULL,
  `citycode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bc_region` */

insert  into `bc_region`(`id`,`province`,`city`,`district`,`postcode`,`shortcode`,`citycode`) values 
('QY001','北京市','北京市','东城区','110101','BJBJDC','beijing'),
('QY002','北京市','北京市','西城区','110102','BJBJXC','beijing'),
('QY003','北京市','北京市','朝阳区','110105','BJBJCY','beijing'),
('QY004','北京市','北京市','丰台区','110106','BJBJFT','beijing'),
('QY005','北京市','北京市','石景山区','110107','BJBJSJS','beijing'),
('QY006','北京市','北京市','海淀区','110108','BJBJHD','beijing'),
('QY007','北京市','北京市','门头沟区','110109','BJBJMTG','beijing'),
('QY008','北京市','北京市','房山区','110111','BJBJFS','beijing'),
('QY009','北京市','北京市','通州区','110112','BJBJTZ','beijing'),
('QY010','北京市','北京市','顺义区','110113','BJBJSY','beijing'),
('QY011','北京市','北京市','昌平区','110114','BJBJCP','beijing'),
('QY012','北京市','北京市','大兴区','110115','BJBJDX','beijing'),
('QY013','北京市','北京市','怀柔区','110116','BJBJHR','beijing'),
('QY014','北京市','北京市','平谷区','110117','BJBJPG','beijing'),
('QY015','北京市','北京市','密云县','110228','BJBJMY','beijing'),
('QY016','北京市','北京市','延庆县','110229','BJBJYQ','beijing'),
('QY017','河北省','石家庄市','长安区','130102','HBSJZZA','shijiazhuang'),
('QY018','河北省','石家庄市','桥东区','130103','HBSJZQD','shijiazhuang'),
('QY019','河北省','石家庄市','桥西区','130104','HBSJZQX','shijiazhuang'),
('QY020','河北省','石家庄市','新华区','130105','HBSJZXH','shijiazhuang'),
('QY021','河北省','石家庄市','井陉矿区','130107','HBSJZJXK','shijiazhuang'),
('QY022','河北省','石家庄市','裕华区','130108','HBSJZYH','shijiazhuang'),
('QY023','河北省','石家庄市','井陉县','130121','HBSJZJX','shijiazhuang'),
('QY024','河北省','石家庄市','正定县','130123','HBSJZZD','shijiazhuang'),
('QY025','河北省','石家庄市','栾城县','130124','HBSJZLC','shijiazhuang'),
('QY026','河北省','石家庄市','行唐县','130125','HBSJZXT','shijiazhuang'),
('QY027','河北省','石家庄市','灵寿县','130126','HBSJZLS','shijiazhuang'),
('QY028','河北省','石家庄市','高邑县','130127','HBSJZGY','shijiazhuang'),
('QY029','河北省','石家庄市','深泽县','130128','HBSJZSZ','shijiazhuang'),
('QY030','河北省','石家庄市','赞皇县','130129','HBSJZZH','shijiazhuang'),
('QY031','河北省','石家庄市','无极县','130130','HBSJZWJ','shijiazhuang'),
('QY032','河北省','石家庄市','平山县','130131','HBSJZPS','shijiazhuang'),
('QY033','河北省','石家庄市','元氏县','130132','HBSJZYS','shijiazhuang'),
('QY034','河北省','石家庄市','赵县','130133','HBSJZZ','shijiazhuang'),
('QY035','河北省','石家庄市','辛集市','130181','HBSJZXJ','shijiazhuang'),
('QY036','河北省','石家庄市','藁城市','130182','HBSJZGC','shijiazhuang'),
('QY037','河北省','石家庄市','晋州市','130183','HBSJZJZ','shijiazhuang'),
('QY038','河北省','石家庄市','新乐市','130184','HBSJZXL','shijiazhuang'),
('QY039','河北省','石家庄市','鹿泉市','130185','HBSJZLQ','shijiazhuang'),
('QY040','天津市','天津市','和平区','120101','TJTJHP','tianjin'),
('QY041','天津市','天津市','河东区','120102','TJTJHD','tianjin'),
('QY042','天津市','天津市','河西区','120103','TJTJHX','tianjin'),
('QY043','天津市','天津市','南开区','120104','TJTJNK','tianjin'),
('QY044','天津市','天津市','河北区','120105','TJTJHB','tianjin'),
('QY045','天津市','天津市','红桥区','120106','TJTJHQ','tianjin'),
('QY046','天津市','天津市','滨海新区','120116','TJTJBHX','tianjin'),
('QY047','天津市','天津市','东丽区','120110','TJTJDL','tianjin'),
('QY048','天津市','天津市','西青区','120111','TJTJXQ','tianjin'),
('QY049','天津市','天津市','津南区','120112','TJTJJN','tianjin'),
('QY050','天津市','天津市','北辰区','120113','TJTJBC','tianjin'),
('QY051','天津市','天津市','武清区','120114','TJTJWQ','tianjin'),
('QY052','天津市','天津市','宝坻区','120115','TJTJBC','tianjin'),
('QY053','天津市','天津市','宁河县','120221','TJTJNH','tianjin'),
('QY054','天津市','天津市','静海县','120223','TJTJJH','tianjin'),
('QY055','天津市','天津市','蓟县','120225','TJTJJ','tianjin'),
('QY056','山西省','太原市','小店区','140105','SXTYXD','taiyuan'),
('QY057','山西省','太原市','迎泽区','140106','SXTYYZ','taiyuan'),
('QY058','山西省','太原市','杏花岭区','140107','SXTYXHL','taiyuan'),
('QY059','山西省','太原市','尖草坪区','140108','SXTYJCP','taiyuan'),
('QY060','山西省','太原市','万柏林区','140109','SXTYWBL','taiyuan'),
('QY061','山西省','太原市','晋源区','140110','SXTYJY','taiyuan'),
('QY062','山西省','太原市','清徐县','140121','SXTYQX','taiyuan'),
('QY063','山西省','太原市','阳曲县','140122','SXTYYQ','taiyuan'),
('QY064','山西省','太原市','娄烦县','140123','SXTYLF','taiyuan'),
('QY065','山西省','太原市','古交市','140181','SXTYGJ','taiyuan');

/*Table structure for table `bc_staff` */

DROP TABLE IF EXISTS `bc_staff`;

CREATE TABLE `bc_staff` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `haspda` varchar(1) DEFAULT NULL,
  `deltag` varchar(1) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `standard` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bc_staff` */

insert  into `bc_staff`(`id`,`name`,`telephone`,`haspda`,`deltag`,`station`,`standard`) values 
('402808816497d21101649848a4eb0000','华师小妹','15521333343','0','0','华师东区','两毛一斤'),
('402881346484e5c3016484e8680f0001','广工一哥','15213066541','0','1','广工','一般价格'),
('40288134648e1d7c01648e21c0330000','华工一哥','15213066541','0','1','华工','很贵'),
('4028813464b6e3080164b6e5c0e30000','广美小妹','15213066541','0','1','广美','便宜');

/*Table structure for table `bc_subarea` */

DROP TABLE IF EXISTS `bc_subarea`;

CREATE TABLE `bc_subarea` (
  `id` varchar(32) NOT NULL,
  `startnum` varchar(30) DEFAULT NULL,
  `endnum` varchar(30) DEFAULT NULL,
  `single` varchar(1) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `decidedzone_id` varchar(32) DEFAULT NULL,
  `region_id` varchar(32) DEFAULT NULL,
  `addresskey` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlndbc8oldgbg3k1x63n3n6t7n` (`decidedzone_id`),
  KEY `FKcjwxm19mx5njn3xyqgqp431mb` (`region_id`),
  CONSTRAINT `FKcjwxm19mx5njn3xyqgqp431mb` FOREIGN KEY (`region_id`) REFERENCES `bc_region` (`id`),
  CONSTRAINT `FKlndbc8oldgbg3k1x63n3n6t7n` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bc_subarea` */

insert  into `bc_subarea`(`id`,`startnum`,`endnum`,`single`,`position`,`decidedzone_id`,`region_id`,`addresskey`) values 
('402808816497d2110164985123c10001','11','12','0','东城区',NULL,'QY023','东城区'),
('402881346491a4fb016491a68d670000','111','1','0','1','1','QY002','11'),
('40288134649237af0164925ba0b70000','11','12','0','东城区',NULL,'QY001','东城区');

/*Table structure for table `qp_noticebill` */

DROP TABLE IF EXISTS `qp_noticebill`;

CREATE TABLE `qp_noticebill` (
  `id` varchar(32) NOT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  `customer_id` varchar(32) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `delegater` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `pickaddress` varchar(200) DEFAULT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `pickdate` date DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `volume` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `ordertype` varchar(20) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhmbqr6qlg0uets978w5xshler` (`staff_id`),
  KEY `FKl5j3pm969oy1qdc1318jrmbgt` (`user_id`),
  CONSTRAINT `FKl5j3pm969oy1qdc1318jrmbgt` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKhmbqr6qlg0uets978w5xshler` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qp_noticebill` */

/*Table structure for table `qp_workbill` */

DROP TABLE IF EXISTS `qp_workbill`;

CREATE TABLE `qp_workbill` (
  `id` varchar(32) NOT NULL,
  `noticebill_id` varchar(32) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `pickstate` varchar(20) DEFAULT NULL,
  `buildtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `attachbilltimes` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKggojlcovnpimuukxcueb18apt` (`noticebill_id`),
  KEY `FK55ckcnjyvwinnnniu5jpelg7y` (`staff_id`),
  CONSTRAINT `FK55ckcnjyvwinnnniu5jpelg7y` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FKggojlcovnpimuukxcueb18apt` FOREIGN KEY (`noticebill_id`) REFERENCES `qp_noticebill` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FK_workbill_noticebill_fk` FOREIGN KEY (`noticebill_id`) REFERENCES `qp_noticebill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qp_workbill` */

/*Table structure for table `qp_workordermanage` */

DROP TABLE IF EXISTS `qp_workordermanage`;

CREATE TABLE `qp_workordermanage` (
  `id` varchar(32) NOT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `floadreqr` varchar(255) DEFAULT NULL,
  `prodtimelimit` varchar(40) DEFAULT NULL,
  `prodtype` varchar(40) DEFAULT NULL,
  `sendername` varchar(20) DEFAULT NULL,
  `senderphone` varchar(20) DEFAULT NULL,
  `senderaddr` varchar(200) DEFAULT NULL,
  `receivername` varchar(20) DEFAULT NULL,
  `receiverphone` varchar(20) DEFAULT NULL,
  `receiveraddr` varchar(200) DEFAULT NULL,
  `feeitemnum` int(11) DEFAULT NULL,
  `actlweit` double DEFAULT NULL,
  `vol` varchar(20) DEFAULT NULL,
  `managerCheck` varchar(1) DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qp_workordermanage` */

/*Table structure for table `role_function` */

DROP TABLE IF EXISTS `role_function`;

CREATE TABLE `role_function` (
  `function_id` varchar(32) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FK5f8riddotqjpm9vly0b8c5nmf` (`function_id`),
  CONSTRAINT `FK5f8riddotqjpm9vly0b8c5nmf` FOREIGN KEY (`function_id`) REFERENCES `auth_function` (`id`),
  CONSTRAINT `FK10qo908yd9evkyb40vf88og85` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_function` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`salary`,`birthday`,`gender`,`station`,`telephone`,`remark`) values 
('1','admin','1234',NULL,NULL,NULL,NULL,NULL,NULL),
('2','user','1234',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKqqlqhas35obkljn18mrh6mmms` (`role_id`),
  CONSTRAINT `FKqqlqhas35obkljn18mrh6mmms` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
  CONSTRAINT `FKeqon9sx5vssprq67dxm3s7ump` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
