insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_OPTDEF)+1, '跟踪流程', 'FWGLFWCK', 'adminList', '管理跟踪办件流程步骤信息', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_OPTDEF)+1, '跟踪流程', 'SWGLSWCK', 'adminList', '管理跟踪办件流程步骤信息', null);