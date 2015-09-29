create view v_jtopic_reply as(
	select 
	(case when pp0.story=1 then pp0.pid else '' end) as pid1,
	(case when pp0.story=2 then pp0.pid else '' end) as pid2,
	pp0.* from jtopic_reply pp0
);

select t.* from v_jtopic_reply t