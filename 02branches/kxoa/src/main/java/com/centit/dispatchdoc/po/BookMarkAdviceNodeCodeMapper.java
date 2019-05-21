package com.centit.dispatchdoc.po;

/**
 * 
 * 书签与领导意见环节代码映射
 * 没法动态判断当前环节是哪一个环节，这里必须要硬编码
 * 将word里书签名与环节代码建立关联关系
 * @author lay
 * @create 2016-3-10
 * @version
 */
public class BookMarkAdviceNodeCodeMapper {
     
    /**
     * 
     * 发文书签枚举
     * 
     * @author Administrator
     * @create 2016-3-10
     * @version
     */
     public static enum FWEnum{
        ACCEPTARCHIVENO("fwh"),//发文字号 
        CSSHVIEW("FW_CSSH"),//处室负责人审核
        LBHQVIEW("hqxgcs"),//部门会签
        OFFICEAUDITVIEW("zbcsfzrsh"),//办公室阅核
        LEADERSIGNEDVIEW("fgwldqf"),//领导签发
        PROOFREADER("pb");//排版、校对
        
        private String nodeCode;
              
        private FWEnum(String nodeCode) {
            this.nodeCode = nodeCode;
        }

        public String getNodeCode() {
            return nodeCode;
        }
    }
    
   /**
    * 收文书签枚举
    * TODO Class description should be added
    * 
    * @author lq
    * @create 2016年3月11日
    * @version
    */
      public static enum SWEnum{
         ZRPF("sw_py"),//发文字号 
         LDPY("sw_tldpy"),//处室负责人审核
         BMLDSH("sw_czsh"),//部门会签
         BMCLJG("sw_bl");//办公室阅核
       
         private String nodeCode;
               
         private SWEnum(String nodeCode) {
             this.nodeCode = nodeCode;
         }

         public String getNodeCode() {
             return nodeCode;
         }
     }
     /**
      * 检查是否是枚举项值
      * @param key 书签名
      * @param value 环节代码
      * @return
      */
    public static boolean isEqualFWEnum(String key,String value){
       return FWEnum.valueOf(key.toUpperCase()).getNodeCode().equals(value);
    }
    
    /**
     * 检查是否是枚举项值
     * 收文
     * @param key 书签名
     * @param value 环节代码
     * @return
     */
   public static boolean isEqualSWEnum(String key,String value){
      return SWEnum.valueOf(key.toUpperCase()).getNodeCode().equals(value);
   }
}
