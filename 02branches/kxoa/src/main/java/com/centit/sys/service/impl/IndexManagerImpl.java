package com.centit.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*import org.jsoup.Jsoup;
 import org.jsoup.safety.Whitelist;*/
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.app.dao.IndexDao;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Publicinfo;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.PublicinfoManager;
import com.centit.support.searcher.DocDesc;
import com.centit.support.searcher.Indexer;
import com.centit.support.searcher.Searcher;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.TaskLog;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.IQuartzJobBean;
import com.centit.sys.service.IndexManager;
import com.centit.sys.service.SchedulerManager;
import com.centit.sys.service.TaskLogManager;

public class IndexManagerImpl implements IndexManager, IQuartzJobBean {

	public static final Log log = LogFactory.getLog(IndexManagerImpl.class);

	/**
	 * 索引任务类型常量
	 */
	public static final String INDEX_TYPE_ADD = "add";
	public static final String INDEX_TYPE_UPDATE = "update";

	public static final String INDEX_TYPE_DEL = "del";

	// 索引类型关键字
	public static final String INDEX_TYPE = "indexType";

	// 查询数据库中大文本内容JsonObject关键字
	public static final String QUERY_CONTENT_JSON = "queryContentJson";

	private SchedulerManager schedulerManager;
	private TaskLogManager taskLogManager;
	private PublicinfoManager publicinfoManager;
	private FileinfoFsManager fileinfoFsManager;
	private IndexDao indexDao;

	public void setIndexDao(IndexDao indexDao) {
		this.indexDao = indexDao;
	}




	public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }




    public void setSchedulerManager(
            SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }

    public void setTaskLogManager(TaskLogManager taskLogManager) {
		this.taskLogManager = taskLogManager;
	}

	public void setPublicinfoManager(PublicinfoManager publicinfoManager) {
		this.publicinfoManager = publicinfoManager;
	}

	@Override
	public void indexFile(Publicinfo info, DocDesc docDesc) {
		Indexer.indexFile(info, docDesc);
	}

	@Override
	public void indexFile(FileinfoFs info, DocDesc docDesc) {
		Indexer.indexFile(info, docDesc);
	}

	@Override
	public void indexFile(DocDesc docDesc) {
		Indexer.indexFile(docDesc);
	}

	public void deleteIndex(DocDesc docDesc) {
		Searcher.deleteIndexByQuery(docDesc.getDataID());
	}

	@Override
	public void initTimerTask() {
		JobDetail jobDetail = schedulerManager.getJobDetail("Job_Index", "Job_Group_Index");
		jobDetail.getJobDataMap().put(QUARTZ_JOB_BEAN_KEY, this);

		String cronEx = "0/30 * * * * ?";

		// 更新为数据字典中的配置
		FDatadictionary indexTask = CodeRepositoryUtil.getDataPiece("TIMERTASKS", "IndexByFile");
		if (null != indexTask) {
			cronEx = indexTask.getDatavalue();
		}

		jobDetail.getJobDataMap().put("cronEx", cronEx);

		try {
		    schedulerManager.schedule(jobDetail, "Trigger_Index", "Trigger_Group_Index", cronEx);
		} catch (Exception e) {
			log.error("表达式错误 " + cronEx, e);
		}
	}


	public void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		// 比对数据字典中的配置项，是否需要更新定时触发器
		FDatadictionary indexTask = CodeRepositoryUtil.getDataPiece("TIMERTASKS", "IndexByFile");
		if (null != indexTask) {
			String cronEx = jobexecutioncontext.getJobDetail().getJobDataMap().getString("cronEx");
			if (!cronEx.equals(indexTask.getDatavalue())) {
				// 删除任务，并进行重建
			    schedulerManager.deleteJob(jobexecutioncontext.getJobDetail().getName(), jobexecutioncontext
						.getJobDetail().getGroup());

				this.initTimerTask();
			}

		}

		Map<String, Object> filterMap = new HashMap<String, Object>();
		// filterMap.put("type", "FileToIndex");
		filterMap.put("taskRun", "F");

		List<TaskLog> taskLogs = taskLogManager.listObjects(filterMap);

		if (CollectionUtils.isEmpty(taskLogs)) {
			return;
		}

		for (TaskLog taskLog : taskLogs) {
			String taskLogDesc = taskLog.getTaskDesc();
			if (!StringUtils.hasText(taskLogDesc)) {
				continue;
			}

			JSONObject jsonObject = JSONObject.fromObject(taskLogDesc);

			// 索引类型
			String indexType = jsonObject.getString(IndexManagerImpl.INDEX_TYPE);

			// 删除索引
			if (INDEX_TYPE_DEL.equals(indexType)) {

				deleteIndex(new DocDesc(jsonObject.getString("dataID")));

				taskLog.setIsRun();
				taskLogManager.saveObject(taskLog);

				continue;
			}

			if (INDEX_TYPE_ADD.equals(indexType)) {
				DocDesc docDesc = getDocDesc(jsonObject);

				// 索引文件内容类型
				String type = jsonObject.getString("type");

				// 索引文本内容，索引内容根据queryContentJson字段查询数据库获取
				String queryContentJson = jsonObject.has(IndexManagerImpl.QUERY_CONTENT_JSON) ? jsonObject
						.getString(IndexManagerImpl.QUERY_CONTENT_JSON) : null;
				if (DocDesc.OPT.equalsIgnoreCase(type) && StringUtils.hasText(queryContentJson)) {
					String content = indexDao.getContentTextToIndex(queryContentJson);
					if (!StringUtils.hasText(content)) {
						log.warn("索引内容为空");

						content = jsonObject.getString("title");
					}
					// 去除html标签
					Pattern patternHtml = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
					Matcher matcherhtml = patternHtml.matcher(content);
					content = matcherhtml.replaceAll("");

					docDesc.setContent(content);
					indexFile(docDesc);

					taskLog.setIsRun();
					taskLogManager.saveObject(taskLog);

					continue;

				}

				// 索引文件管理功能中文件
				if (DocDesc.TYPE_FILE.equalsIgnoreCase(type) && jsonObject.containsKey("infocode")) {
					String infocode = jsonObject.getString("infocode");

					Publicinfo publicinfo = publicinfoManager.getObjectById(infocode);
					if (null != publicinfo) {
						indexFile(publicinfo, docDesc);

						taskLog.setIsRun();
						taskLogManager.saveObject(taskLog);

						continue;
					}

					log.error("infocode " + infocode + " 不存在");
				}

				if (DocDesc.TYPE_FILE.equalsIgnoreCase(type) && jsonObject.containsKey("filecode")) {
					String filecode = jsonObject.getString("filecode");
					FileinfoFs fileinfo = fileinfoFsManager.getObjectById(filecode);
					if (null != fileinfo) {
						indexFile(fileinfo, docDesc);

						taskLog.setIsRun();
						taskLogManager.saveObject(taskLog);

						continue;
					}

					log.error("filecode " + filecode + " 不存在");
				}

				log.error("indexType 不匹配");
			}

		}
	}

	@SuppressWarnings("static-method")
	private DocDesc getDocDesc(JSONObject jsonObject) {
		DocDesc docDesc = new DocDesc();

		if (jsonObject.containsKey("optID")) {
			docDesc.setOptID(jsonObject.getString("optID"));
		}
		if (jsonObject.containsKey("owner")) {
			docDesc.setOwner(jsonObject.getString("owner"));
		}

		if (jsonObject.containsKey("dataID")) {
			docDesc.setDataID(jsonObject.getString("dataID"));
		}
		if (jsonObject.containsKey("title")) {
			docDesc.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("extension")) {
			docDesc.setExtension(jsonObject.getString("extension"));
		}
		if (jsonObject.containsKey("method")) {
			docDesc.setMethod(jsonObject.getString("method"));
		}
		if (jsonObject.containsKey("opttag")) {
			docDesc.setOpttag(jsonObject.getString("opttag"));
		}

		return docDesc;
	}
}
