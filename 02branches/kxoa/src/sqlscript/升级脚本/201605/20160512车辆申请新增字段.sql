--增加字段cpbegtime,cpendtime;
alter table OA_CAR_APPLY  add (cpbegtime date,cpendtime date);

--为新增字段填值
update OA_CAR_APPLY t set t.cpbegtime=(
       case 
          when t.do_beg_time is not null
            then t.do_beg_time
          when t.do_beg_time is  null
           then t.plan_beg_time
               end      ),
             t.cpendtime=(
             case
                   when t.do_end_time is not null
                     then t.do_end_time
                       when t.do_end_time is null
                         then t.plan_end_time
                           end
                   )