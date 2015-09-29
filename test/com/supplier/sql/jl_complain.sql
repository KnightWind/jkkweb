use jiakeke;
select t.* from jl_complain t where t.tid=195;	
select t.* from attachment t 
and t.fileType=28
select t.* from attachment t where t.fileType=28
select	
		concat(sf.para_value,ah.filepath) as photoUrl,		
		sf.para_value,ah.filepath,
		ifnull(jd.cnt,0) as jds,
		ifnull(sc.cnt,0) as scs,
		aa.* from supplier_company_staff	aa
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

select t.* from sysconfig t where t.para_name='PHOTO_PREFIX_URL'

select t.* from attachment t 
select max(t.fileType) from attachment t 

----我收到的投诉;
select t.* from jl_complain t where t.bid=195;	

----我处理的投诉;
select t.* from jl_complain t where t.tid=195;
----我发起的投诉;
select t.* from jl_complain t where t.tid=195;	

----我收到的投诉;
select t.* from jl_complain t where t.bid=195;	

----我处理的投诉
select t.* from jl_complain t 
where t.tid=195;

----投诉明细
select t.* from jl_complain_details t 
jl_complain_details.type_id=supplier.id


----我处理的投诉;
select t.* from jl_complain t 
where t.id in(
	select xx.cid from jl_complain_details xx
	where xx.type_id=2 
)

select t.* from supplier t where t.id=195

----我处理的投诉
select t.* from jl_complain t 
where exists (
	select xx.cid from jl_complain_details xx
	where xx.cid=t.id and xx.type_id=2 and xx.user_id=195
)
