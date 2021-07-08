select e.event_type, 
  	((select e2.value 
  		from events e2
 	   where e2.event_type=e.event_type
    order by e2.`time` desc
     limit 1,1)
  	-  	
  	(select e2.value 
  		from events e2
 	   where e2.event_type=e.event_type
    order by e2.`time` asc
     limit 1)
	) value
    from events e 
group by e.event_type
having count(*) > 1
