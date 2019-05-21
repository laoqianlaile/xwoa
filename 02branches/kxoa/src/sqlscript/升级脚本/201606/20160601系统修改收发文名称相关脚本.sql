/* 将节点名字中收文、发文分别改成阅办文和拟发文*/
update wf_node t set t.nodename = replace(nodename,'发文','拟发文');  
update wf_node t set t.nodename = replace(nodename,'收文','阅办文');  

/* 将数据字典中所有收文、发文分别改成阅办文和拟发文*/
update f_datadictionary t set t.datavalue = replace(datavalue,'收文','阅办文') where t.datavalue like '%收文%';
update f_datadictionary t set t.datavalue = replace(datavalue,'发文','拟发文') where t.datavalue like '%发文%';