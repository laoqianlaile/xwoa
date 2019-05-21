package com.centit.app.service;

import java.util.List;

import com.centit.app.po.TaskListAnnex;
import com.centit.core.service.BaseEntityManager;

public interface TaskListAnnexManager extends BaseEntityManager<TaskListAnnex> {
    void saveBatch(List<TaskListAnnex> taskListAnnexs);
}
