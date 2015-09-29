


select aa.* from engineerings aa where aa.zx_stage=50




select aa.* from `jiakeke` aa where aa.id=2823

select t.* from supplier t where t.id=196

select 
aa.jl_id,sp.sp_name as spName,
aa.* from engineerings aa
left join supplier sp on sp.id=aa.jl_id
where 1=1 and aa.id=1

engineerings.jl_id=supplier.id
engineerings.sp_id=supplier.id

select 
aa.jl_id,
sp.sp_name as spName,
sjs.name designerName,
aa.* from engineerings aa
left join supplier_company_staff sjs on sjs.id=aa.sjs_id
left join supplier sp on sp.id=aa.sp_id
where 1=1
and aa.id=1


select sp.* from engineerings sp

zx_stage


where 1=1
and t.start_time is not null
and t.start_time is null

----监理意见;
select t.* from jl_comment t; 	

----业主意见;		
select t.* from member_comment t;

----验收请求;
select t.* from check_request t;


----验收父节点表;
select t.* from engineering_stage t;

----验收父节点实例表;
select t.* from engineering_stage_inst t;

----验收明细表;
select t.* from engineering_stage_mx t;				

----验收明细实例表;
engineering_stage_mx_inst（验收明细实例表)								

select t.* from engineering_stage_mx t;		

----验收明细表;
select t.* from engineering_stage_mx t;			






