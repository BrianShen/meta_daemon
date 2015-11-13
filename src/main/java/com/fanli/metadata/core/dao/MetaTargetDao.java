package com.fanli.metadata.core.dao;

import com.fanli.metadata.core.bean.EtlMetaColumn;
import com.fanli.metadata.core.bean.EtlMetaHiveTable;
import com.fanli.metadata.core.bean.EtlMetaPartition;
import com.fanli.metadata.core.bean.EtlMetaTableBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wei.shen on 2015/10/26.
 */
public interface MetaTargetDao {

    int saveTableBase(EtlMetaTableBase tableBase);

    int saveColumn(EtlMetaColumn column);

    int saveHiveSpecialMetadata(EtlMetaHiveTable hiveTable);

    int savePartition(EtlMetaPartition partition);

    List<Integer> queryTableIds();

    int savePartitionsIgnoreExists(List<EtlMetaPartition> list);

    int updateStatusByTableId(@Param("tableId") Integer tableId,@Param("status") Integer status);
}
