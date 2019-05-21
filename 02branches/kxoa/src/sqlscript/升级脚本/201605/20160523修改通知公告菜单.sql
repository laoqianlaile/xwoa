update F_OPTINFO f set f.OPTURL='/oa/oaInformation!mainlist.do?infoType=news'
 where f.OPTID='NEWS' and f.PREOPTID='INFO_';
 
 update F_OPTINFO f set f.OPTURL='/oa/oaInformation!mainlist.do?infoType=info'
 where f.OPTID='TZGG_' and f.PREOPTID='INFO_';
 
  update F_OPTINFO f set f.OPTURL='/oa/oaInformation!mainlist.do?infoType=bulletin'
 where f.OPTID='BULLETIN' and f.PREOPTID='INFO_';