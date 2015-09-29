


select t.* from supplier_company_staff t
select count(*) from supplier_company_staff t

(
SELECT  GROUP_CONCAT(CONCAT(t3.para_value,a.filepath)) 
from attachment a,sysconfig t3 
WHERE a.mainid=设计师的id and filetype=对应表的类型 
and t3.para_name='PHOTO_PREFIX_URL'
) as img

select t.* from attachment t
select t.* from sysconfig  aa


select	
concat(sf.para_value,ah.filepath) as imgPath,		
aa.* from supplier_company_staff	aa
left join attachment 	ah on ah.mainid=aa.id and ah.filetype=3
left join sysconfig 	sf on sf.para_name='PHOTO_PREFIX_URL' 
		
		select	
		concat(t4.para_value,t3.filepath) as coverurl,
		t3.filepath,t4.para_value,
		d0.cate_name as stylename,
		d1.cate_name as housestyle,
		aa.* from cases	aa
		LEFT JOIN design_cate d0 ON d0.id=aa.style
		LEFT JOIN design_cate d1 ON d1.id=aa.house_type
		LEFT JOIN design 			d2 on d2.id=aa.design_id 
		left join attachment	t3 on t3.id=d2.pid
		left join sysconfig 	t4 on t4.para_name='PHOTO_PREFIX_URL'
		
select 
concat(sf.para_value,aa.filepath) as curl,
aa.* from attachment aa 
left join sysconfig sf on sf.para_name='PHOTO_PREFIX_URL'  
where aa.filetype=3 and aa.mainid=4 limit 0,1

select aa.mainid,aa.filetype,count(*) as cnt
from attachment aa
group by aa.mainid,aa.filetype



select 

SELECT  GROUP_CONCAT(CONCAT(t3.para_value,a.filepath)) from attachment a,sysconfig t3 
WHERE a.mainid=4 and filetype=3 and t3.para_name='PHOTO_PREFIX_URL'   

attachment.mainid=supplier_company_staff.id
SUPPLIER_STAFF_TYPE  = 3
select	
aa.avatar,
aa.* from supplier_company_staff	aa
left join attachment 
