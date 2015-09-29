UPDATE region SET level = 3,parentid=110100 WHERE parentid=110000 //北京

UPDATE region SET level = 3,parentid=120100 WHERE parentid=120000 //天津

UPDATE region SET level = 3,parentid=310100 WHERE parentid=310000 //天津

INSERT INTO region (regionid, regionname,parentid,`level`,sortby,`status`) VALUES (110100, '北京',110000,2,0,0)

INSERT INTO region (regionid, regionname,parentid,`level`,sortby,`status`) VALUES (120100, '北京',120000,2,0,0)

INSERT INTO region (regionid, regionname,parentid,`level`,sortby,`status`) VALUES (310100, '北京',310000,2,0,0)

张金华 2015/9/15 16:38:12
INSERT INTO region (regionid, regionname,parentid,`level`,sortby,`status`) VALUES (120100, '天津',120000,2,0,0)

INSERT INTO region (regionid, regionname,parentid,`level`,sortby,`status`) VALUES (310100, '上海',310000,2,0,0)