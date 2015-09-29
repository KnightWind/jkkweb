
select 
dc2.cate_name as houseTypeVal,
dc1.cate_name as styleVal,
t.* from  topic t
left join design_cate dc1 on dc1.id = t.style
left join design_cate dc2 on dc2.id = t.house_type 


select t.* from  design_cate t