CREATE TABLE `supplier_branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家分店表ID',
  `sp_id` int(11) NOT NULL COMMENT '所属商家ID',
  `name` varchar(40) NOT NULL COMMENT '分店名称',
  `tel` varchar(15) DEFAULT NULL COMMENT '分店客服电话',
  `address` varchar(50) NOT NULL,
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_sp_id` (`sp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `red_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '红包表ID',
  `name` varchar(24) NOT NULL COMMENT '红包名称',
  `price` float unsigned NOT NULL COMMENT '金额 单位：元',
  `platform` smallint(6) NOT NULL COMMENT '适用平台：0家可可全平台；1家装；2建材',
  `start_money` float unsigned NOT NULL DEFAULT '0' COMMENT '满多少可使用',
  `limit_num` int(11) NOT NULL COMMENT '每人限领个数',
  `total_num` int(11) DEFAULT NULL COMMENT '红包总个数',
  `get_num` int(11) DEFAULT NULL COMMENT '已经领取的个数',
  `is_split` tinyint(1) NOT NULL COMMENT '红包是否支持拆分使用',
  `use_condition` tinyint(2) DEFAULT NULL COMMENT '使用条件：0全款，1定金',
  `begin_time` datetime NOT NULL COMMENT '红包可开始使用时间',
  `end_time` datetime NOT NULL COMMENT '红包结束时间',
  `udpate_time` datetime NOT NULL COMMENT '更新时间',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员用户名',
  `remark` varchar(40) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `member_red_package` (
  `id` int(11) NOT NULL COMMENT '会员红包记录表',
  `mobile` char(11) NULL COMMENT '关联会员表member的mobile',
  `red_package_id` int(11) NOT NULL COMMENT '红包表red_packageID',
  `price` float NOT NULL COMMENT '红包金额：单位元',
  `used_money` float DEFAULT NULL COMMENT '已使用金额',
  `create_time` datetime NOT NULL COMMENT '领取红包的时间',
  `memberId` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `settlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提成结算记录表',
  `role` tinyint(2) NOT NULL COMMENT '身份：0 业主，1 店员',
  `mobile` char(11) NOT NULL COMMENT '手机号码',
  `current_balance` float NOT NULL COMMENT '本期获得的提成金额，单位：元',
  `sttle_balance` float DEFAULT NULL COMMENT '本期已经结算的提成金额，单位：元',
  `status` tinyint(1) NOT NULL COMMENT '结算状态：0未结算；1已结算',
  `stat_begin_time` datetime NOT NULL COMMENT '本期统计提成开始时间',
  `stat_end_time` datetime NOT NULL COMMENT '本期统计提成结束时间',
  `settle_time` datetime DEFAULT NULL COMMENT '结算时间',
  `serial_code` varchar(30) DEFAULT NULL COMMENT '结算流水号',
  `admin_name` varchar(20) DEFAULT NULL COMMENT '财务出纳名称',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`stat_begin_time`),
  KEY `idx_role_mobile` (`role`,`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table member add column (`money_balance` float DEFAULT NULL COMMENT '账户余额，单位：元');
alter table member add column (`total_sttle_money` float DEFAULT NULL COMMENT '总共获得的提成,单位：元');
alter table member change mobile mobile char(11);

CREATE TABLE `bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '银行名称',
  `abbreviation` varchar(10) DEFAULT NULL COMMENT '简称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `red_package_category` (
  `id` int(11) NOT NULL COMMENT '红包适用的商品类目ID表',
  `red_package_id` int(11) NOT NULL COMMENT '红包ID',
  `item_category_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '适用的商品类目：0全部类目，非0为具体类目ID',
  `category_name` varchar(24) NOT NULL COMMENT '类目名称，方便冗余查询',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table supplier_company_staff add column (`mobile` varchar(11) DEFAULT NULL COMMENT '手机号码');
alter table supplier_company_staff add column (`branch_id` int(11) DEFAULT NULL COMMENT '所属分店ID，关联分店表supplier_branch表');
alter table supplier_company_staff add column (`leader_id` int(11) DEFAULT NULL COMMENT '上级领导ID,关联本表');
alter table supplier_company_staff add column (`bank_account` varchar(20) DEFAULT NULL COMMENT '银行卡号');
alter table supplier_company_staff add column (`account_name` varchar(20) DEFAULT NULL COMMENT '开户人姓名');
alter table supplier_company_staff add column (`bank_id` int(11) DEFAULT NULL COMMENT '关联银行branch表');
alter table supplier_company_staff add column (`gain_rate` float DEFAULT NULL COMMENT '分成比例');
alter table supplier_company_staff add column (`total_sttle_money` float DEFAULT NULL COMMENT '获得的总提成金额，单位：元');
alter table supplier_company_staff add column (`status` tinyint(1) DEFAULT NULL COMMENT '审核状态：0未审核；1 审核通过；2审核不通过');

alter table supplier_company_staff add index idx_mobile (mobile);
alter table supplier_company_staff add index idx_name (name);

alter table payment_record add column (`recommend_name` varchar(20) DEFAULT NULL COMMENT '推荐人姓名');
alter table payment_record add column (`recommend_mobile` varchar(11) DEFAULT NULL COMMENT '推荐人手机号码');
alter table payment_record add column total_fee decimal(18,2) DEFAULT NULL COMMENT '订单总价，单位：元';
alter table payment_record add column dicount_type tinyint(1) DEFAULT NULL COMMENT '折扣类型';
alter table payment_record add column dicount_fee decimal(18,2) DEFAULT NULL COMMENT '折扣金额';

alter table payment_record add index idx_recommend_mobile (recommend_mobile);
alter table payment_record add index idx_bussid_type (business_id,type);

alter table supplier add column (`level` tinyint(1) DEFAULT NULL COMMENT '商家身份：1普通商家；2Vip商家');

CREATE TABLE `ecshop_homepage_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(16) DEFAULT NULL,
  `member_mobile` char(11) DEFAULT NULL,
  `apply_type` tinyint(1) DEFAULT NULL COMMENT '(10.监理预约，20.估算报价，30.装修)',
  `supervisor_package_type` tinyint(1) DEFAULT NULL COMMENT '(10.质量套餐，20.管家套餐，30.尊享套餐)',
  `house_acreage` float DEFAULT NULL COMMENT '房屋面积，单位（平米）',
  `house_decorate_status` tinyint(1) DEFAULT NULL COMMENT '(10.准备装修，20.正在装修，30.已经装修 )',
  `house_addr_full` varchar(100) DEFAULT NULL COMMENT '房屋完整地址',
  `house_addr_province` varchar(16) DEFAULT NULL,
  `house_addr_city` varchar(16) DEFAULT NULL,
  `house_addr_area` varchar(16) DEFAULT NULL,
  `house_model` tinyint(1) DEFAULT NULL COMMENT '(10.普通住宅，20.商住两用...)',
  `house_model_room` tinyint(1) DEFAULT NULL,
  `house_model_hall` tinyint(1) DEFAULT NULL,
  `house_model_kitchen` tinyint(1) DEFAULT NULL,
  `house_model_toilet` tinyint(1) DEFAULT NULL,
  `house_model_balcony` tinyint(1) DEFAULT NULL,
  `house_decorate_grade` tinyint(1) DEFAULT NULL COMMENT '(10.简装，20.精装，30.豪华)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约发起时间',
  `ser_reply_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '客服响应时间',
  `ser_reply_content` varchar(100) DEFAULT NULL COMMENT '客服答复内容',
  `remark` varchar(100) DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL COMMENT '手机号是否已经通过验证码',
  `ser_has_reply` tinyint(1) DEFAULT NULL COMMENT '客服是否答复',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商城首页预约表';