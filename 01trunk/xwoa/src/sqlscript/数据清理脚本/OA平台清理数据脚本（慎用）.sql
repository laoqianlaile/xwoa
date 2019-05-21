 ----此脚本只在初始化系统时使用，其他任何时候都不可使用
  delete from wf_action_task f;
  delete from wf_team t;
  delete from wf_organize t;
  delete from wf_manage_action t;
  delete from wf_node_instance t;
  delete from wf_flow_instance t;
  delete from opt_stuff_info t;
  delete from M_DOC_RELATIVE t;
  delete from m_dispatch_doc t;
  delete from m_signed_report t;
  delete from m_income_doc t;
  delete from m_income_project t;
  delete from oa_car_apply t;
  delete from oa_meet_apply t;
  delete from oa_supervise t;
  delete from oa_meetroom_apply t;
  delete from opt_idea_info t;
  delete from OPT_ZWH t;
  delete from opt_proc_info t;
  delete from opt_base_info t;
  delete from opt_apply_info t;
    