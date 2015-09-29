


select t.* from jtopic t

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









>>>>>>> .r4295
