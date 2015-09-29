sDROP TABLE IF EXISTS `interface_record`;
CREATE TABLE `interface_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tocken_id` varchar(100) DEFAULT NULL COMMENT '登录标识',
  `user_id` int(11) DEFAULT NULL COMMENT '登录用户',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `interface_code` varchar(30) DEFAULT NULL COMMENT '接口编码',
  `interface_name` varchar(50) DEFAULT NULL COMMENT '接口名称',
  `input_param` varchar(500) DEFAULT NULL COMMENT '入参',
  `out_param` varchar(1000) DEFAULT NULL COMMENT '出参',
  `oper_result` varchar(50) DEFAULT NULL COMMENT '操作结果',
  `oper_type` varchar(2) DEFAULT NULL COMMENT '操作类型',
  `oper_desc` varchar(300) DEFAULT NULL COMMENT '操作描述',
  `oper_time` date DEFAULT NULL COMMENT '操作时间',
  `src_ip` varchar(30) DEFAULT NULL COMMENT '调用源ip',
  `oper_result_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4285 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `interface_register`;
CREATE TABLE `interface_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `interface_code` varchar(30) DEFAULT NULL COMMENT '接口编码',
  `interface_name` varchar(50) DEFAULT NULL COMMENT '接口名称',
  `module_code` varchar(30) DEFAULT NULL COMMENT '模块编码',
  `module_name` varchar(50) DEFAULT NULL COMMENT '模块名称(比如登录模块，方案设计模块)',
  `channel_type` int(11) DEFAULT NULL COMMENT '渠道分类(?(android，ios，微信....等)根据实际需要考虑是否需要对接口分类)',
  `oper_type` int(11) DEFAULT NULL COMMENT '操作类型(?（C、R、U、D）对应增、删、改、查)',
  `oper_desc` varchar(100) DEFAULT NULL COMMENT '操作描述（该接口的作用或用途描述）',
  `status` int(11) DEFAULT NULL COMMENT '状态（是否需要记录日志 1记录,2不记录）',
  `log_type` int(11) DEFAULT NULL COMMENT '日志记录类型(1:只记录入参，2：只记录出参，3：全部记录)',
  `need_login_auth` int(11) DEFAULT NULL COMMENT '是否需要登录(0不需要，1需要)',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_tocken`;
CREATE TABLE `user_tocken` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tocken_id` varchar(100) DEFAULT NULL COMMENT '用户登录的tockenId',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id，配合用户type',
  `user_name` varchar(30) DEFAULT NULL COMMENT '登录用户名字',
  `user_type` int(11) DEFAULT NULL COMMENT '登录用户类型，0是用户，1商家（装修商家，监理，因为在同一个表）',
  `create_date` datetime DEFAULT NULL,
  `recent_visti_time` datetime DEFAULT NULL COMMENT '最近访问时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3378 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_activity`;
CREATE TABLE `zc_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '活动结束时间',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '定金',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100862 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_activity_category`;
CREATE TABLE `zc_activity_category` (
  `activity_id` int(11) NOT NULL COMMENT '活动id',
  `category_id` int(11) NOT NULL COMMENT '商品品类id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `activity_info_id` int(11) DEFAULT NULL COMMENT '与zc_itemcategory_info商品首页品类表id关联',
  PRIMARY KEY (`activity_id`,`category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_express`;
CREATE TABLE `zc_express` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expressCode` varchar(100) DEFAULT NULL COMMENT '快递号',
  `expressName` varchar(100) DEFAULT NULL COMMENT '快递公司名称',
  `createDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `address` varchar(200) DEFAULT NULL COMMENT '收件地址',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_item`;
CREATE TABLE `zc_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `top_price` decimal(9,2) NOT NULL COMMENT '市场价-最高价',
  `low_price` decimal(9,2) NOT NULL COMMENT '抄底价-最低价',
  `item_id` int(11) NOT NULL COMMENT '与item表id关联',
  `num_people` int(11) NOT NULL COMMENT '众筹人数',
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `flag` varchar(2) DEFAULT NULL COMMENT '活动状态，0结束，1进行中',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `is_refund` int(11) DEFAULT NULL COMMENT '是否可以退款',
  `refund_proportion` decimal(9,2) DEFAULT NULL COMMENT '退款比例',
  `status` int(1) NOT NULL COMMENT '审核状态：0未通过，1已通过',
  `price_grad` decimal(9,2) DEFAULT NULL COMMENT '价格梯度',
  `plat_price` decimal(9,2) DEFAULT NULL COMMENT '平台价格',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100867 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_item_member`;
CREATE TABLE `zc_item_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL COMMENT '众筹商品ID',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '参加时间',
  `activityId` int(11) DEFAULT NULL COMMENT '活动id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=80069 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_item_order`;
CREATE TABLE `zc_item_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` varchar(100) DEFAULT NULL COMMENT '产品id',
  `sale_price` decimal(9,2) DEFAULT NULL COMMENT '初始价',
  `end_price` decimal(9,2) DEFAULT NULL COMMENT '最终众筹价格',
  `create_date` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `order_code` varchar(100) DEFAULT NULL COMMENT '订单code',
  `item_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `item_type` int(1) DEFAULT NULL COMMENT '1商品 2扫码商品',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=200000259 DEFAULT CHARSET=utf8;

/*Table structure for table `qr_code` */

DROP TABLE IF EXISTS `qr_code`;

CREATE TABLE `qr_code` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sp_id` int(11) DEFAULT NULL,
  `fee` float DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `qr_code` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 定金 2全款',
  `activity_name` varchar(200) DEFAULT NULL,
  `product_info` varchar(500) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3000019 DEFAULT CHARSET=utf8;

/*Table structure for table `zc_itemcategory_info` */

DROP TABLE IF EXISTS `zc_itemcategory_info`;

CREATE TABLE `zc_itemcategory_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `url` varchar(255) DEFAULT NULL COMMENT '点击后链接',
  `seq` int(11) DEFAULT NULL COMMENT '显示顺序',
  `place` varchar(255) DEFAULT NULL COMMENT '置放位置',
  `status` int(11) DEFAULT NULL COMMENT '是否显示（1是，0否）',
  `city_domain` varchar(50) DEFAULT NULL COMMENT '对应城市',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `admin_id` int(11) DEFAULT NULL COMMENT '创建人账号',
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10000009 DEFAULT CHARSET=utf8;

/*Table structure for table `zc_js` */

DROP TABLE IF EXISTS `zc_js`;

CREATE TABLE `zc_js` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '结算金额',
  `sp_id` int(11) DEFAULT NULL COMMENT '商家id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '结算时间',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '操作人昵称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '0:未付款 1：已付款',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `zc_order` */

DROP TABLE IF EXISTS `zc_order`;

CREATE TABLE `zc_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_code` varchar(32) NOT NULL DEFAULT '0',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `order_price` decimal(9,2) DEFAULT NULL COMMENT '订单价格',
  `money_state` int(2) DEFAULT NULL COMMENT '付款状态 1 付定金 2 付尾款',
  `express_state` int(2) DEFAULT NULL COMMENT '快递状态 1 待收货 2 收货',
  `use_state` int(2) DEFAULT NULL COMMENT '定金使用状态 0未使用 1已使用',
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `express_id` int(11) DEFAULT NULL COMMENT '物流id',
  `supplier_id` int(11) DEFAULT NULL COMMENT '商家id',
  `retainage` decimal(9,2) DEFAULT NULL COMMENT '尾款',
  `deposit` decimal(9,2) DEFAULT NULL COMMENT '定金',
  `payment_model` int(11) DEFAULT NULL COMMENT '支付方式 1 支付宝 2 易宝',
  `pay_type` int(11) DEFAULT NULL COMMENT '1 普通支付 2扫码支付',
  `serial_code` varchar(32) DEFAULT NULL COMMENT '流水号',
  `remark` varchar(500) DEFAULT NULL,
  `deposit_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`,`order_code`)
) ENGINE=MyISAM AUTO_INCREMENT=5270 DEFAULT CHARSET=utf8;

/*Table structure for table `zc_qrcode_log` */

DROP TABLE IF EXISTS `zc_qrcode_log`;

CREATE TABLE `zc_qrcode_log` (
  `id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `fee` decimal(10,2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(11) DEFAULT NULL,
  `sp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `zc_refund_order` */

DROP TABLE IF EXISTS `zc_refund_order`;

CREATE TABLE `zc_refund_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(50) NOT NULL COMMENT '订单id',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '申请时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_bank_card`;
CREATE TABLE `zc_bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `card_no` varchar(50) DEFAULT NULL COMMENT '卡号',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '银行名称',
  `region_id` int(11) DEFAULT NULL COMMENT '所在地，region表',
  `pay_pwd` varchar(255) DEFAULT NULL COMMENT '支付密码  MD5加密',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `sp_id` int(11) DEFAULT NULL COMMENT '商户id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

ALTER TABLE `supplier` ADD COLUMN `id_card` VARCHAR(18) NULL;

ALTER TABLE item ADD zc_flag BIT(1) COMMENT '是否参与众筹', ADD model  VARCHAR (100);
ALTER TABLE activity_join_sign ADD `sp_id` int(11) DEFAULT '0' COMMENT '商家id';
ALTER TABLE region  ADD pointx  VARCHAR(32) NULL;
ALTER TABLE region  ADD pointy  VARCHAR(32) NULL;
ALTER TABLE zc_item  ADD privilege  INT NULL; 
ALTER TABLE supplier  ADD pointx  VARCHAR(32)  NULL;
ALTER TABLE supplier  ADD pointy  VARCHAR(32)  NULL;
insert  into `sysconfig`(`para_code`,`para_name`,`para_value`,`para_desc`) values (47,'IMAGE_URL','http://www.jiakeke.com/attachment','');
alter TABLE payment_record add `batch_no` varchar(45) DEFAULT NULL COMMENT '支付宝退款批次号', add `requestid` varchar(45) DEFAULT NULL COMMENT '易宝退款退款请求号',add `refundexternalid` varchar(45) DEFAULT NULL COMMENT '易宝退款流水号';
UPDATE supplier_user set userpwd = '5690DDDFA28AE085D23518A035707282' where 1=1;
UPDATE member set `password` = '5690DDDFA28AE085D23518A035707282' where 1=1;
ALTER TABLE appointment_push MODIFY COLUMN quote DECIMAL(20,3);
ALTER TABLE `jiakeke`.`payment_record` 
ADD COLUMN `batch_no` VARCHAR(50) NULL COMMENT '批次号' AFTER `log_type`;
ALTER TABLE `jiakeke`.`payment_record` CHANGE COLUMN `batch_no` `batch_no` VARCHAR(45) NULL DEFAULT NULL COMMENT '支付宝退款批次号' ,ADD COLUMN `requestid` VARCHAR(45) NULL COMMENT '易宝退款退款请求号' AFTER `batch_no`,ADD COLUMN `refundexternalid` VARCHAR(45) NULL COMMENT '易宝退款流水号' AFTER `requestid`;
ALTER TABLE supp_message_push ADD COLUMN user_type int NULL COMMENT '1装修公司2建材商3工长5监理20用户' ;

CREATE TABLE `jl_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `ctype` varchar(2) DEFAULT NULL COMMENT '套餐类型 A或B',
  `cprice` float DEFAULT NULL COMMENT '金额',
  `cispay` int(1) DEFAULT '0' COMMENT '是否已支付  0未支付（默认）1已支付',
  `order_code` char(20) NOT NULL COMMENT '订单号',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


ALTER TABLE attachment ADD COLUMN remark varchar(20) NULL COMMENT '图片备注信息' ;
ALTER TABLE region  ADD status int(1) NULL;

alter table zc_item alter column privilege set default '1'