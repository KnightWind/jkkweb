
SELECT count(*) FROM jlappointment t where t.id='893'
SELECT COUNT(*) FROM jlappointment_push t;

select t.* from jlappointment_push t 
where 1=1
and (lower(status)  =lower('0') ) 
select t.* from engineerings t;

select t.* from jlappointment_push t;

select t.* from(
select
		pp.id as p_id,
		pp.community,
		pp.user,
		pp.space,
		pp.method,
		pp.payment,
		pp.address,
		pp.su_Type as suType,
		pp.whole_House as wholeHouse,
		pp.house_Type as houseType,
		pp.zx_Time as zxTime,
		dc.cate_name as cateName,
		sp.sp_name as spName,
		mm.nickName as nickName,
		aa.* from jlappointment_push aa
		left join jlappointment pp on pp.id=aa.aid
		left join design_cate 	dc on dc.id=pp.dcate_id
		left join supplier		sp on sp.id=aa.sp_id
		left join member 		mm on mm.id=pp.uid
)t where 1=1 and t.id in(427,428,429)
		
select t.* from jlappointment t where t.id=1035

		
select t.* from jl_gcd t;


select t.* from supplier_comment t;

select t.* from jtopic t;

select t.sp_id,count(*) as cnt from(
	select 
	(case when aa.story=1 then jp.sp_id else '' end)as sp_id,
	aa.* from jtopic_reply aa
	left join jtopic_reply 	pp on pp.id=aa.pid
	left join jtopic 		jp on jp.id=aa.pid
)t where 1=1
group by t.sp_id

	--(case when aa.story=1 then pp.sp_id else '' end)as sp_id,
	--(case when aa.story=2 then jp.id else '' end)as jp_id,

select t.* from(
	select 
	jp1.sp_id as sp1_id,
	jp2.sp_id as sp2_id,
	jp3.sp_id as sp3_id,
	
	pp1.story as pp1_story,
	pp2.story as pp2_story,
	aa.* from jtopic_reply aa
	left join jtopic_reply pp1 on pp1.id=aa.pid
	left join jtopic_reply pp2 on pp2.id=pp1.pid
	left join jtopic jp1 on jp1.id=aa.pid
	left join jtopic jp2 on jp2.id=pp1.pid
	left join jtopic jp3 on jp3.id=pp2.pid
)t where t.sp_id=196


select t.sp_id,count(*) as cnt from(
	select 
	ifnull((case 
		when jp1.sp_id is not null then jp1.sp_id 
		when jp2.sp_id is not null then jp2.sp_id
		when jp3.sp_id is not null then jp3.sp_id
	end),'') as sp_id,
	pp0.* from v_jtopic_reply pp0
	left join v_jtopic_reply 	pp1 on pp1.id=pp0.pid2
	left join v_jtopic_reply 	pp2 on pp2.id=pp1.pid2
	
	left join jtopic 		jp1 on jp1.id=pp0.pid1
	left join jtopic 		jp2 on jp2.id=pp1.pid1
	left join jtopic 		jp3 on jp3.id=pp2.pid1
)t where 1=1
group by t.sp_id

select t.* from jtopic t where t.sp_id='196'

DROP FUNCTION IF EXISTS queryChildrenAreaInfo;
CREATE FUNCTION queryChildrenAreaInfo (areaId INT)
RETURNS VARCHAR(4000)
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = cast(areaId as char);

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM v_jtopic_reply where FIND_IN_SET(pid2,sTempChd)>0;
END WHILE;
return sTemp;
END;

select * from t_areainfo where FIND_IN_SET(id, queryChildrenAreaInfo(1)); 





