use jiakeke;
select t.* from(
		select	
		concat(sf.para_value,ah.filepath) as photoUrl,
		aa.* from supplier	aa
		left join attachment 	ah on ah.mainid=aa.id and ah.filetype=4
		left join sysconfig 	sf on sf.para_name='PHOTO_PREFIX_URL'
)t where 1=1
and t.type=5

select	
aa.available,
aa.* from supplier	aa
available

			select xx.*
			from staff_collect xx
			where xx.sid is not null
			group by xx.sid
			
			select xx.sid,count(*) as cnt
			from staff_collect xx
			where xx.sid is not null
			group by xx.sid 
			
select t.* from (
		select	
		ifnull(sc.cnt,0) as scs,
		concat(sf.para_value,ah.filepath) as photoUrl,
		ifnull(et.cnt,0) as engineeringsCount,
		ifnull(case_tbl.cnt,0) as caseCount,
		ifnull(kgd1_tbl.cnt,0) as kgd1Count,
		ifnull(staff_tbl.cnt,0) as staffCount,
		aa.* from supplier	aa
		left join attachment 	ah on ah.mainid=aa.id and ah.filetype=4
		left join sysconfig 	sf on sf.para_name='PHOTO_PREFIX_URL'
		left join (select xx.jl_id,count(*) as cnt
			from engineerings xx
			where xx.jl_id is not null
			group by xx.jl_id
		)et on et.jl_id=aa.id
		left join (select xx.sp_id,count(*) as cnt
			from cases xx
			where xx.sp_id is not null
			group by xx.sp_id
		)case_tbl on case_tbl.sp_id=aa.id
		left join (select xx.sp_id,count(*) as cnt
			from cases xx
			where xx.sp_id is not null
			and xx.kgd_flag=1
			group by xx.sp_id
		)kgd1_tbl on kgd1_tbl.sp_id=aa.id
		left join (select xx.sp_id,count(*) as cnt
			from supplier_company_staff xx
			where xx.sp_id is not null
			group by xx.sp_id
		)staff_tbl on staff_tbl.sp_id=aa.id
		left join (
		  select xx.sp_id,count(*) as cnt
			from supplier_collect xx
			where xx.sp_id is not null
			group by xx.sp_id 
		)sc on sc.sp_id=aa.id
		
) t where t.type=5

select t.* from attachment t
select t.* from sysconfig t where t.para_name='PHOTO_PREFIX_URL'

		select	
		concat(sf.para_value,aa.filepath) as photoUrl,	
		aa.* from attachment	aa
		left join sysconfig 	sf on sf.para_name='PHOTO_PREFIX_URL'

select t.* from cases t
where 1=1
and t.sp_id=8

select	
		sp.sp_name as spName,
		ifnull(jd.cnt,0) as jds,
		ifnull(sc.cnt,0) as scs,
		concat(sf.para_value,ah.filepath) as photoUrl,		
		aa.* from supplier_company_staff	aa
		left join supplier		sp on sp.id=aa.sp_id
		left join attachment 	ah on ah.mainid=aa.id and ah.filetype=3
		left join sysconfig 	sf on sf.para_name='PHOTO_PREFIX_URL'
		left join (
			select xx.sid,count(*) as cnt
			from staff_collect xx
			where xx.sid is not null
			group by xx.sid 
		)sc on sc.sid=aa.id
		left join(
			select xx.sjs_id,count(*) as cnt
			from engineerings xx
			where xx.sjs_id is not null
			group by xx.sjs_id 
		)jd on jd.sjs_id=aa.id
		
select count(*) from cases t
where 1=1
and t.sp_id=8

and t.kgd_flag=1

select t.* from supplier_company_staff t
where 1=1
and t.sp_id=8



select xx.sp_id,count(*) as cnt
			from cases xx
			where xx.sp_id is not null
			group by xx.sp_id


select
aa.estimate_average, 
ifnull(et.cnt,0) as engineeringsCount,
aa.* from supplier aa
left join (
	select xx.jl_id,count(*) as cnt
	from engineerings xx
	where xx.jl_id is not null
	group by xx.jl_id
)et on et.jl_id=aa.id
where aa.id=195



select
ll.lname,
aa.* from supplier_label aa
left join label ll on ll.id=aa.lid
where aa.sp_id=195

select t.* from label t where t.sp_id=195

select t.* from supplier_appraise t 

select t.* from jtopic t 

select t.* from engineerings t 

where t.sp_id=195

jtopic.sp_id=supplier.id

select t.* from supplier_complain t 
select t.* from jl_complain t where t.tid=195;	--
select t.* from staff_complain t 




select t.* from supplier_complain t 
select t.* from jl_complain t where t.tid=195;	--
select t.* from staff_complain t 


SELECT 
	ifnull(sc_tbl.cnt,0) as '收藏数',
  a.`id`,
  a.`sp_name` AS spName,
  a.`estimate_average` AS assess,
  b.`filepath`,
  COUNT(c.`jl_id`) AS caseNum
FROM  supplier a 
LEFT JOIN attachment 	b ON a.`id` = b.`mainid` AND b.`filetype` = 4 
LEFT JOIN cases 			c ON c.`jl_id` = a.`id` 
left join (
	select xx.sp_id,count(*) as cnt 
	from supplier_collect xx
	where xx.sp_id is not null
	group by xx.sp_id
)sc_tbl on sc_tbl.sp_id=a.id
WHERE a.`type` = 5 
GROUP BY a.`id`

select xx.sp_id,count(*) as cnt 
from supplier_collect xx
where xx.sp_id is not null
group by xx.sp_id


