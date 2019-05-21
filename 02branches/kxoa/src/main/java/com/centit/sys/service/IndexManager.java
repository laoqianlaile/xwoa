package com.centit.sys.service;

import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Publicinfo;
import com.centit.support.searcher.DocDesc;

public interface IndexManager {

    void indexFile(Publicinfo info, DocDesc docDesc);

    void indexFile(FileinfoFs info, DocDesc docDesc);

    void indexFile(DocDesc docDesc);

    void deleteIndex(DocDesc docDesc);
}
