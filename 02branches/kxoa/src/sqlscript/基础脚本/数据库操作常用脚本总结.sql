--创建临时表空间
create temporary tablespace test_temp tempfile 'E:\oracle\product\10.2.0\oradata\testserver\test_temp01.dbf'   size 32m   autoextend on   next 32m maxsize 2048m   extent management local;  

--//创建数据表空间
create tablespace test_data logging  datafile 'E:\oracle\product\10.2.0\oradata\testserver\test_data01.dbf'   size 32m   autoextend on   next 32m maxsize 2048m   extent management local; 

--//创建用户并指定表空间
create user username identified by password default tablespace test_data   temporary tablespace test_temp;

--用户下所有对象导出
exp epower/epower@jttxq13 file='c:\epower.dmp' owner=(epower) 

 --指定表结构导出
exp epower/epower@jttxq13 file='c:\epower_tables.dmp' tables=(Q_QUERYCOLUMN,Q_QUERYCONDITON,Q_QUERYMODEL)