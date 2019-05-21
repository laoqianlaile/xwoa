create or replace procedure p_data_clean(v_dj_id varchar2) is
begin
  --删除办件------------------
  delete from wf_action_task f
   where f.nodeinstid in
         (select t.nodeinstid
            from wf_node_instance t
           where t.wfinstid in
                 (select opt_base_info.flowinstid
                    from opt_base_info
                   where opt_base_info.dj_id = v_dj_id));

  delete from wf_team t
   where t.wfinstid = (select opt_base_info.flowinstid
                         from opt_base_info
                        where opt_base_info.dj_id = v_dj_id);

  delete from wf_organize t
   where t.wfinstid in (select opt_base_info.flowinstid
                          from opt_base_info
                         where opt_base_info.dj_id = v_dj_id);

  delete from wf_manage_action t
   where t.wfinstid in (select opt_base_info.flowinstid
                          from opt_base_info
                         where opt_base_info.dj_id = v_dj_id);

  delete from wf_node_instance t
   where t.wfinstid in (select opt_base_info.flowinstid
                          from opt_base_info
                         where opt_base_info.dj_id = v_dj_id);

  delete from wf_flow_instance t
   where t.wfinstid in (select opt_base_info.flowinstid
                          from opt_base_info
                         where opt_base_info.dj_id = v_dj_id);

  delete from opt_stuff_info t where t.dj_id = v_dj_id; --djid
  delete from M_DOC_RELATIVE t where t.income_no = v_dj_id; -- djid
  delete from m_dispatch_doc t where t.no = v_dj_id; --djid

  delete from m_income_doc t where t.no = v_dj_id; -- djid

  delete from m_income_project t where t.no = v_dj_id; --djid

  delete from OPT_ZWH t where t.dj_id = v_dj_id; --djid

  delete from opt_idea_info t where t.dj_id = v_dj_id; --djid

  delete from opt_proc_info t where t.dj_id = v_dj_id; --djid

  delete from opt_base_info b where b.dj_id = v_dj_id; --djid
  commit;
end p_data_clean;
