
  --会议    
    update  F_OPTINFO set optname='我的申请',opturl='/oa/oaMeetApply!list.do?NP_bizstate=T&&show_type=myself'
            where optid = 'HYCKHZ'
      and preoptid ='HYSGL_NEW'
 --车辆     
       update  F_OPTINFO set optname='我的申请',opturl='/oa/oaCarApply!list.do?show_type=F'
            where optid = 'CLSYCK'
      and preoptid = 'CLGL_new'
      
      
      
