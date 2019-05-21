package com.centit.app.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.app.po.ForumStaff;

import java.util.List;

public interface ForumStaffManager extends BaseEntityManager<ForumStaff> {
    void saveObjects(List<ForumStaff> forumStaffs);
}
