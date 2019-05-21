package com.centit.sys.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.OptFlowNoInfo;
import com.centit.sys.po.OptFlowNoPool;
/**
 * 流水号生成规则，流水号根据   编码属主    OwnerCode  ，编码类别    CodeCode ，编码依据日期  CodeDate 三个属性进行顺序编码
 * @author codefan
 * @create 2012-6-11
 * @version
 */
public interface OptFlowNoInfoManager extends BaseEntityManager<OptFlowNoInfo> {
    public final static String DefaultOwnerCode="noowner";
    public final static Date DefaultCodeDate= com.centit.support.utils.DatetimeOpt.createUtilDate(2000, 1, 1);
    /**
     * 
     * 获取下一个流水号，流水好是根据 拥有者、类别代码、编码的基准时间
     * @param ownerCode 根据 拥有者，如果设置为  DefaultOwnerCode 则这个编码则依赖于编码
     * @param codeCode 类别代码
     * @param codeBaseDate 编码的基准时间
     * @return 返回流水号
     */
    public long newNextLsh(String ownerCode,String codeCode,Date codeBaseDate);
    /**
     * 
     * 获取下一个流水号，流水好是根据 拥有者、类别代码、编码的基准时间这个时间是按照天来编制的就是同一天中顺序编号
     * @param ownerCode 根据 拥有者
     * @param codeCode 类别代码
     * @param codeBaseDate 编码的基准时间
     * @return 返回流水号
     */
    public long newNextLshBaseDay(String ownerCode,String codeCode,Date codeBaseDate);
    /**
     * 
     * 获取下一个流水号，流水好是根据 拥有者、类别代码、编码的基准时间这个时间是按照月来编制的就是同一月中顺序编号
     * @param ownerCode 根据 拥有者
     * @param codeCode 类别代码
     * @param codeBaseDate 编码的基准时间
     * @return 返回流水号
     */
    public long newNextLshBaseMonth(String ownerCode,String codeCode,Date codeBaseDate);
    
    /**
     * 
     * 获取下一个流水号，流水好是根据 拥有者、类别代码、编码的基准时间这个时间是按照年来编制的就是同一年中顺序编号
     * @param ownerCode 根据 拥有者
     * @param codeCode 类别代码
     * @param codeBaseDate 编码的基准时间
     * @return 返回流水号
     */
    public long newNextLshBaseYear(String ownerCode,String codeCode,Date codeBaseDate);
    /**
     * 这个只根据 类别代码来编号，他类似于序列，sql server中没有序列可以用这个来模拟
     * @param ownerCode 根据 拥有者
     * @param codeCode
     * @return
     */
    public long newNextLsh(String ownerCode,String codeCode);
    /**
     * 这个只根据 类别代码来编号，他类似于序列，sql server中没有序列可以用这个来模拟
     * @param codeCode
     * @return
     */
    public long newNextLsh(String codeCode);    
    /**
     * view 这一组方法和上面一样，则是这一组并没有记录当前获取的值，如果反复调用则会得到相同的值，
     * 调用这组方法后再调用下面对应的record方法会获得和上面create相对应的函数的效果
     * 这组方法的用处是为了避免编码跳号，但是带来的另一个副作用就是会获得相同的编码，使用要注意处理相应的异常。
     */

    public long viewNextLsh(String ownerCode,String codeCode,Date codeBaseDate);
    public long viewNextLshBaseDay(String ownerCode,String codeCode,Date codeBaseDate);
    public long viewNextLshBaseMonth(String ownerCode,String codeCode,Date codeBaseDate);
    public long viewNextLshBaseYear(String ownerCode,String codeCode,Date codeBaseDate);
    public long viewNextLsh(String ownerCode,String codeCode);
    public long viewNextLsh(String codeCode);
    /**
     *配合view对应的方法使用。 或者配合 池中的流水号 listLshInPool
     *作用是 记录某个流水号已经被使用
     */
    public void recordNextLsh(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void recordNextLshBaseDay(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void recordNextLshBaseMonth(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void recordNextLshBaseYear(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void recordNextLsh(String ownerCode,String codeCode,long currCode);
    public void recordNextLsh(String codeCode,long currCode);
    
    /**
     * 回收一个流水号
     * @param ownerCode
     * @param codeCode
     * @param codeBaseDate
     * @param currCode
     */
    public void releaseLsh(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void releaseLshBaseDay(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void releaseLshBaseMonth(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void releaseLshBaseYear(String ownerCode,String codeCode,Date codeBaseDate,long currCode);
    public void releaseLsh(String ownerCode,String codeCode,long currCode);
    public void releaseLsh(String codeCode,long currCode);
    
    /**
     * 从pool池中查找某个类别的流水号
     * @param ownerCode
     * @param codeCode
     * @param codeBaseDate
     * @param pageDesc
     * @return
     */
    public List<OptFlowNoPool> listLshInPool(String ownerCode,String codeCode,Date codeBaseDate,PageDesc pageDesc);
    public List<OptFlowNoPool> listLshBaseDayInPool(String ownerCode,String codeCode,Date codeBaseDate,PageDesc pageDesc);
    public List<OptFlowNoPool> listLshBaseMonthInPool(String ownerCode,String codeCode,Date codeBaseDate,PageDesc pageDesc);
    public List<OptFlowNoPool> listLshBaseYearInPool(String ownerCode,String codeCode,Date codeBaseDate,PageDesc pageDesc);
    public List<OptFlowNoPool> listLshInPool(String ownerCode,String codeCode,PageDesc pageDesc);
    public List<OptFlowNoPool> listLshInPool(String codeCode,PageDesc pageDesc);
    
    /**
     * 流水号可以回收的算法，在回收池中找一个最小的流水号，如果没有调用 newNextLsh
     * @param ownerCode
     * @param codeCode
     * @param codeBaseDate
     * @return
     */
    public long assignNextLsh(String ownerCode,String codeCode,Date codeBaseDate);
    public long assignNextLshBaseDay(String ownerCode,String codeCode,Date codeBaseDate);
    public long assignNextLshBaseMonth(String ownerCode,String codeCode,Date codeBaseDate);
    public long assignNextLshBaseYear(String ownerCode,String codeCode,Date codeBaseDate);
    public long assignNextLsh(String ownerCode,String codeCode);
    public long assignNextLsh(String codeCode);
    
  
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取最新流水号
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String zdx, String zdxVal, Date codeDate, String standard);
    
    /**
     * 根据置文号规则中的某一记录值、基准日期、流水号基准规则获取最新流水号
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String codeCode, Date codeDate, String standard);
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取最新流水号并将表中的流水号+1
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genRecordLsH(String zdx, String zdxVal, Date codeDate, String standard);
    
    /**
     * 根据置文号规则中的某一记录值、基准日期、流水号基准规则获取最新流水号并将表中的流水号+1
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genRecordLsH(String codeCode, Date codeDate, String standard);
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取文号
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @param lsh 流水号
     * @return
     */
    public String genFWH(String zdx, String zdxVal, Date codeDate, String orgCode, String lsh);
    
    public String genRecordFWH(String zdx, String zdxVal, Date codeDate, String orgCode);
    /**
     * 根据字典项，对应字典项的值、基准日期、主办部门编号获取文号并将表中的流水号+1
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @return
     */
    public String genRecordFWH(String codeCode, Date codeDate, String orgCode);
    
    /**
     * 根据字典项，对应字典项的值、基准日期、主办部门编号、流水号获取文号
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @param lsh 流水号
     * @return
     */
    public String genFWH(String codeCode, Date codeDate, String orgCode, String lsh);
}
