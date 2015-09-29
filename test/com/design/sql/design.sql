

select aa.* from design aa

SELECT
		b.`sp_name` AS spName,
		c.`cate_name` AS huXing,
		d.`cate_name` AS kongJian ,
		e.`cate_name` AS fengGe,
		a.*
		FROM		design a
		LEFT JOIN supplier b		ON a.`sp_id` = b.`id`
		LEFT JOIN design_cate c		ON a.`huxing` = c.`id`
		LEFT JOIN design_cate d		ON a.`kongjian` = d.`id`
		LEFT JOIN design_cate e		ON a.`fengge` = e.`id`
		WHERE a.`id`=2
		
		
		
		select
		bb.sp_name AS spName,
		cc.cate_name AS huXing,
		dd.cate_name AS kongJian,
		ee.cate_name AS fengGe,
		aa.* from design aa
		LEFT JOIN supplier 		bb ON bb.id= aa.sp_id 
		LEFT JOIN design_cate cc ON cc.id= aa.huxing
		LEFT JOIN design_cate dd ON dd.id= aa.kongjian
		LEFT JOIN design_cate ee ON ee.id= aa.fengge
		
		
		
		
		
		
		