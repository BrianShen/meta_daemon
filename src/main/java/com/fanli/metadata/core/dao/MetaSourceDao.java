package com.fanli.metadata.core.dao;

import com.fanli.metadata.core.bean.EtlMetaColumn;
import com.fanli.metadata.core.bean.EtlMetaHiveTable;
import com.fanli.metadata.core.bean.EtlMetaPartition;
import com.fanli.metadata.core.bean.EtlMetaTableBase;

import java.util.List;

/**
 * Created by wei.shen on 2015/10/26.
 */
public interface MetaSourceDao {
    List<EtlMetaTableBase> queryAllTables();
    List<EtlMetaColumn> queryColumnsByTableId(Integer tableId);
    EtlMetaHiveTable queryHiveSpecialInfo(Integer tableId);
    List<EtlMetaPartition> querypartitionsByTableId(Integer tableId);
    List<Integer> queryAllTableIds();
    EtlMetaTableBase queryTableByHiveTableId(Integer tableId);
    List<EtlMetaPartition> queryAllPartitions();
}
