
select t.* from (
select	
		sp.sp_name as spName,
		ifnull(jd.cnt,0) as jds,
		ifnull(sc.cnt,0) as scs,jiakeke
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
) t 

select t.* from engineerings t where t.engineerings.sjs_id=supplier_company_staff.id

select t.* from supplier t

staff_collect.sid=supplier_company_staff.id




select t.* from supplier_collect t