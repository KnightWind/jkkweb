alter table payment_record add column (`total_fee` decimal(18,2) DEFAULT NULL COMMENT '订单总价，单位：元');
alter table payment_record add column (`dicount_type` tinyint(4) DEFAULT NULL COMMENT '折扣类型');
alter table payment_record add column (`dicount_fee` decimal(18,2) DEFAULT NULL COMMENT '折扣金额');