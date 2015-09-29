
 
SELECT t.* FROM appointment t
SELECT t.* FROM member t

SELECT t.* FROM jlappointment_push t WHERE t.id=378
SELECT t.* FROM jlappointment t WHERE t.id=893

SELECT t.* FROM appointment t WHERE t.id=378
SELECT t.* FROM appointment_push t WHERE t.id=378

select t.* from (
	select
	dd.su_Type AS suType,
	dd.method,
	dd.space,
	dd.budget as payment,

	ap.id AS p_id,
	ap.community,
	ap.user,
	ap.address,


	ap.whole_House AS wholeHouse,
	ap.house_Type AS houseType,
	ap.zx_Time as zxTime,
	dc.cate_name as cateName,
	sp.sp_name as spName,
	mm.nickName as nickName,
	aa.* from jlappointment_push aa
	left join jlappointment pp on pp.id=aa.aid
	left join engineerings  gc on gc.id=pp.gcd_id
	left join appointment  	ap on ap.id=gc.aid
	left join design_cate 	dc on dc.id=pp.dcate_id
	left join supplier		sp on sp.id=aa.sp_id
	left join member 		mm on mm.id=pp.uid
	left join design    	dd on dd.id=gc.design_id
)t where t.id=377
		
select aa.su_type,aa.* from design aa
where aa.su_type is not null
	
	
SELECT t.* FROM (
	SELECT
	
		pp.id AS p_id,ap.community,			pp.user,ap.method,
		pp.payment,pp.address,					pp.su_Type AS suType,
		pp.whole_House AS wholeHouse,		pp.house_Type AS houseType,
		pp.zx_Time as zxTime,						pp.space,
		
		dc.cate_name as cateName,
		sp.sp_name as spName,		mm.nickName as nickName,
		aa.* from jlappointment_push aa
		left join jlappointment pp on pp.id=aa.aid
		left join engineerings  gcd on gcd.id=pp.gcd_id
		left join appointment  	ap on ap.id=gcd.aid
		left join design_cate 	dc on dc.id=pp.dcate_id
		left join supplier			sp on sp.id=aa.sp_id
		left join member 				mm on mm.id=pp.uid
)t where t.id=377

jlappointment_push.aid=jlappointment.id


select
aa.* from jlappointment_push aa
where aa.id=377


select
aa.community,
aa.* from jlappointment aa


appointment.community
select
aa.* from appointment aa

ppointment.community



select t.pointx,t.pointy,t.* from engineerings t
where 1=1
and power(pointx-116.4454,2)+power(pointy-39.8718,2)<=power(2000/11111.1,2) 

select t.pointx,t.* from jlappointment t;
select t.* from engineerings t;
北京丰台大成里青塔小区
select t.* from jlappointment_push t
where  t.sp_id IN(
   SELECT sp_id FROM supplier_user WHERE username='jl01' 
)
jlappointment_push.aid=jlappointment.id

select sp.id,aa.* from supplier_user aa
left join supplier sp on sp.id=aa.sp_id
where 1=1
and sp.type=5


and sp.sp_name like '%监理%'

aa.username='test2'
limit 0,1
and rownum=1

select t.* from supplier t 
where 1=1
and t.

where t.sp_name like '%监理%'

select t.username,count(*) as cnt
from supplier_user t
group by t.username
