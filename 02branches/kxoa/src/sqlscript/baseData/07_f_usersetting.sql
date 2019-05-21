truncate table F_USERSETTING;
insert into F_USERSETTING (USERCODE, FRAMELAYOUT, MENUSTYLE, PAGESTYLE, MAINPAGE, LINESPERPAGE, BOARDLAYOUT, FAVORURL1, FAVORURL2, FAVORURL3, FAVORURL4, FAVORURL5, FAVORURL6, FAVORURL7, FAVORURL8, FAVORURL9)
values ('noname', 'left', 'tree', 'default', 'sys/mainFrame!dashboard.do', 15, '2X2', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/mainFrame!logincas.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do');

insert into F_USERSETTING (USERCODE, FRAMELAYOUT, MENUSTYLE, PAGESTYLE, MAINPAGE, LINESPERPAGE, BOARDLAYOUT, FAVORURL1, FAVORURL2, FAVORURL3, FAVORURL4, FAVORURL5, FAVORURL6, FAVORURL7, FAVORURL8, FAVORURL9)
values ('99999999', 'left', 'tree', 'default', 'app/dashboard!show.do', 15, '2X2', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/mainFrame!logincas.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do', 'sys/addressBook!list.do');

insert into F_USERSETTING (usercode, framelayout, menustyle, pagestyle, mainpage, linesperpage, boardlayout, favorurl1, favorurl2, favorurl3, favorurl4, favorurl5, favorurl6, favorurl7, favorurl8, favorurl9)
values ('default', 'left', 'outlook', 'default', 'sys/mainFrame!dashboard.do', 15, 'default', null, null, null, null, null, null, null, null, null);