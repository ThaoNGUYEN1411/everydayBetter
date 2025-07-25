select * from t_activities ta inner join t_categories tc on ta.category_id = tc.id where tc.category_name =
 'Vie quotidienne';
 

select * from t_activities;

SELECT * FROM t_users tu ;

SELECT sum(CASE WHEN done IS TRUE THEN 1 ELSE 0 END ) AS sum_done 
FROM t_tracking_logs ttl WHERE ttl.activity_id = 1 and ttl.tracked_date >= '2025-06-10'; 
SELECT * FROM t_tracking_logs ttl WHERE ttl.activity_id = 1; 
SELECT sum(CASE WHEN done IS false THEN 1 ELSE 0 END ) AS sum_done FROM t_tracking_logs ttl WHERE ttl.activity_id = 1; 
SELECT sum(CASE WHEN done IS null THEN 1 ELSE 0 END ) AS sum_done FROM t_tracking_logs ttl WHERE ttl.activity_id = 1; 
SELECT count(*)  FROM t_tracking_logs ttl WHERE ttl.activity_id = 1; 


--4
--5
--9