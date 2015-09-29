
select t.* from(
	select 
	d1.cate_name as cateName,
	(case when aa.whole_house=1 then '整装'
	when aa.whole_house=2 then '局部'
	else aa.whole_house end) as wholeHouseVal,
	aa.* from appointment aa
	left join design_cate d1 on d1.id=aa.dcate_id
)t where 1=1 and t.id=15
and t.whole_house is not null
and t.cateName is not null
select aa.* from appointment aa where aa.id=15
dcate_id
gcd_id

select t.* from(
	select
	truncate(aa.budget*0.2,2) as jgamt,
	gc.design_id as designId,
	gc.end_time as endTime,
	d1.cate_name as cateName,
	(case when aa.whole_house=1 then '整装'
	when aa.whole_house=2 then '局部'
	else aa.whole_house end) as wholeHouseVal,
	aa.* from appointment aa
	left join design_cate d1 on d1.id=aa.dcate_id
	left join engineerings gc on gc.id=aa.gcd_id
)t where 1=1 and id=2823
		
		