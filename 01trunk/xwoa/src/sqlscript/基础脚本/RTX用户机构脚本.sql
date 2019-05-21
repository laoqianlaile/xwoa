----用户名	姓名	 部门名称	RTX分机号	电子邮箱	手机号
select loginname,username,depname,null,email,officephone from (
select distinct m.loginname,m.username,case when n.parentunit <>'0' then '新疆维吾尔自治区交通运输厅\'|| n.unitname else '新疆维吾尔自治区交通运输厅' end as depname,
null,m.regemail as email,m.officephone,n.unitorder,m.userorder  from f_userunit t

left join f_userinfo m on m.usercode=t.usercode
left join f_unitinfo n on n.unitcode=t.unitcode

where t.isprimary='T' and m.isvalid='T' order by unitorder,m.userorder asc ) t;