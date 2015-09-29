

----验收请求;
select t.* from check_request t where t.stage_id=20

select t.* from(
	select 
	es.stag_name as stagName,
	aa.* from check_request aa
	left join engineering_stage es on es.id=aa.stage_id
)t where 1=1 
and t.stagName is not null
and id=3

----验收阶段id;
select t.* from engineering_stage t

check_request.stage_id=engineering_stage.id


check_request.gcd_id=engineerings.id

select t.* from engineerings t;