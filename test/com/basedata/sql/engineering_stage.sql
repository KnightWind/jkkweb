use jiakeke

----验收请求阶段;
select t.* from engineering_stage t
where 1=1
and exists(
  select xx.* from check_request xx
  where xx.stage_id=t.id 
  and exists(
    select yy.* from  engineerings yy
    where yy.id=xx.gcd_id
  )
)
SELECT
sp.id,sp.sp_name,spl.level_name,spl.level_money
FROM
supplier sp
INNER JOIN supplier_level spl ON sp.level_id = spl.id

select t.* from supplier_level t
select 
spl.level_money,
aa.* from supplier aa
left join supplier_level spl on spl.id=aa.level_id
  select xx.* from check_request xx
  where 1=1
  and exists(
    select yy.* from  engineerings yy
    where yy.id=xx.gcd_id
  )

select t.* from engineerings t where t.id=28

select t.* from check_request t where 1=1


select t.* from engineerings t
where 1=1
and (t.pid is null or t.pid=0)
and t.ordr_by>=1
and exists(
	select distinct(t1.stage_id) 
	from check_request t1
	where t1.stage_id is not null
	and t.id=t1.stage_id
	and exists(
		select distinct(t2.sp_id) from engineerings t2 
		where t2.sp_id is not null
		and t2.id=t1.gcd_id
	)
)
select 
		--and t2.sp_id=29 
	and t2.sp_id=5 
engineering_stage.pid=
----验收阶段id;
select t.* from engineerings t where t.id in(8,5,52)

check_request.stage_id=engineering_stage.id

select t.* from check_request t where t.stage_id=30

check_request.gcd_id=engineerings.id

select t.* from engineerings t;
select t.* from engineering_stage_inst t;