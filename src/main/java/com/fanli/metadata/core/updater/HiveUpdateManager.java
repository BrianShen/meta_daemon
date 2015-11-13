package com.fanli.metadata.core.updater;

import com.fanli.metadata.core.bean.EtlMetaColumn;
import com.fanli.metadata.core.bean.EtlMetaHiveTable;
import com.fanli.metadata.core.bean.EtlMetaPartition;
import com.fanli.metadata.core.bean.EtlMetaTableBase;
import com.fanli.metadata.core.dao.MetaSourceDao;
import com.fanli.metadata.core.dao.MetaTargetDao;
import com.fanli.metadata.core.util.MetaUtil;
import com.mchange.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/11/1.
 */
@Component("update")
//@PropertySource("classpath:cron.properties")
public class HiveUpdateManager {
    private static Logger logger = Logger.getLogger(HiveUpdateManager.class);
    private static final int NODESIZE = 1000;

    @Autowired
    private MetaSourceDao metaSourceDao;

    @Autowired
    private MetaTargetDao metaTargetDao;

    @Scheduled(cron = "0 05 * * * ?")
    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED, timeout=3)
    public void addNewHiveTable() {
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("start to refresh tables at " + new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        List<Integer> source = metaSourceDao.queryAllTableIds();
        List<Integer> target = metaTargetDao.queryTableIds();
        int columnCount = 0;
        int partitionCount = 0;
        int tableCount = 0;
        int tableDeleted = 0;
        for (Integer tableId : source) {
            if (!target.contains(tableId)) {
                EtlMetaTableBase newTable = metaSourceDao.queryTableByHiveTableId(tableId);
                if (newTable == null) {
                    logger.error("tableId:" + tableId + "====" + newTable.toString() );
                }
                Date date = new Date();
                newTable.setUpdateTime(date);
                newTable.setStatus((byte) 1);
                metaTargetDao.saveTableBase(newTable);
                Long globalId = newTable.getGlobalTableId();
                List<EtlMetaColumn> columns = metaSourceDao.queryColumnsByTableId(tableId);
                Date now = new Date();
                for (EtlMetaColumn column:columns) {
                    column.setAddTime(now);
                    column.setUpdateTime(now);
                    column.setGlobalId(globalId);
                    metaTargetDao.saveColumn(column);
                    columnCount ++;
                }
                EtlMetaHiveTable hiveTable = metaSourceDao.queryHiveSpecialInfo(tableId);
                hiveTable.setGlobalId(globalId);
                //hive元数据
                metaTargetDao.saveHiveSpecialMetadata(hiveTable);
                List<EtlMetaPartition> parts = metaSourceDao.querypartitionsByTableId(tableId);
                for (EtlMetaPartition partition:parts) {
                    partition.setAddTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
                    metaTargetDao.savePartition(partition);
                    partitionCount ++;
                }
                tableCount ++;
            }
        }

        for (Integer tableId : target) {
            if (!source.contains(tableId)) {
                metaTargetDao.updateStatusByTableId(tableId,0);  //table offline statu=0
                tableDeleted ++;
            }
        }

        logger.info(tableCount + " tables added!==============>>>");
        logger.info(tableDeleted + " tables are deleted!==============>>>");
        logger.info(columnCount + " columns added!===============>>>");
        logger.info(partitionCount + " partitions added!===============>>>");
        logger.info("table refresh finished at " + new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }



    @Scheduled(cron = "0 15 * * * ?")
    public void updatePartitions() {
        logger.info("update partition===========================>>>>>>>>>>>");
        List<EtlMetaPartition> parts = metaSourceDao.queryAllPartitions();
        DateTime date = new DateTime();
        String dateStr = date.toString("yyyy-MM-dd HH:mm:ss");
        for (EtlMetaPartition partition:parts) {
            partition.setAddTime(dateStr);
        }
        int blocks = parts.size() / NODESIZE + 1;
        List<EtlMetaPartition> sub = null;
        int rows = 0;
        for (int i = 0;i < blocks;i ++) {
            if (i == blocks - 1) {
                sub = parts.subList(i * NODESIZE, parts.size());
            } else {
                sub = parts.subList(i * NODESIZE, (i + 1) * NODESIZE - 1);
            }
            int num = metaTargetDao.savePartitionsIgnoreExists(sub);
            rows += num;
            logger.info("BLOCK " + (i + 1)  + ", with " + num + " rows added!------------->");
        }
        logger.info(rows + " rows is added! Partition updated successfully! ===========================>>>>>>>>>>>");
    }
}
