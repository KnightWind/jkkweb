

select count(*) from cases t;
select t.* from cases t;

			--concat(t4.filepath,t3.para_value)as coverurl,
select t.* from (
			select	
			concat(t4.para_value,t3.filepath) as coverurl,
			t3.filepath,
			t4.para_value,
			d0.cate_name as stylename,
			d1.cate_name as housestyle,
			aa.* from cases	aa		
			LEFT JOIN design_cate d0 ON d0.id=aa.style
			LEFT JOIN design_cate d1 ON d1.id=aa.house_type
			LEFT JOIN design 			d2 on d2.id=aa.design_id 
			left join attachment	t3 on t3.id=d2.pid
			left join sysconfig 	t4 on t4.para_name='PHOTO_PREFIX_URL'
		) t 
		
		
select @rownum:=@rownum+1 rn,t.* from (
			select	
			@rownum:=0,
			concat(t3.para_value,t4.filepath)as coverurl,
			d0.cate_name as stylename,
			d1.cate_name as housestyle,
			aa.* from cases	aa		
			LEFT JOIN design_cate d0 ON d0.id=aa.style
			LEFT JOIN design_cate d1 ON d1.id=aa.house_type
			LEFT JOIN design 			d2 on d2.id=aa.design_id 
			left join attachment	t4 on t4.id=d2.pid
			left join sysconfig 	t3 on t3.para_name='PHOTO_PREFIX_URL'
		) t 
		LEFT JOIN design_image t2 on t2.design_id=t.design_id 
		
		----concat(t3.para_value,t4.filepath)as coverurl,
		
		LEFT JOIN attachment 	t4 on t2.pid=t4.id
		left join sysconfig 	
		where t3.para_name='PHOTO_PREFIX_URL'
		and t.sp_id=8
		
select t1.* from design t1;
select t2.* from (
	select 
	ah.id as aid,
	aa.pid as aid2,
	aa.* from design_image aa
	left join design 			dd on dd.id=aa.design_id
	left join attachment 	ah on ah.id=aa.pid
) t2

select t4.* from attachment t4;
design_image.pid=attachment.id
select 
t4.filepath,
t2.* from design_image t2
left join attachment t4 on t4.id=t2.pid

select @rownum:=@rownum+1 rownum,
concat(t3.para_value,t4.filepath) as coverurl,
t4.* from (
	select @rownum:=0,aa.* from attachment aa
) t4
left join sysconfig t3 on t3.para_name='PHOTO_PREFIX_URL'

select @rownum:=@rownum+1 rownum,t4.* from (
	select @rownum:=0,aa.* from attachment aa
) t4;
select t3.* from sysconfig t3 where t3.para_name='PHOTO_PREFIX_URL'
		
		
		
		
		
		
		
		
		
		
		
		
		