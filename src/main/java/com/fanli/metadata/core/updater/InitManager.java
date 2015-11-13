package com.fanli.metadata.core.updater;

import com.fanli.metadata.core.bean.EtlMetaColumn;
import com.fanli.metadata.core.bean.EtlMetaHiveTable;
import com.fanli.metadata.core.bean.EtlMetaPartition;
import com.fanli.metadata.core.bean.EtlMetaTableBase;
import com.fanli.metadata.core.dao.MetaSourceDao;
import com.fanli.metadata.core.dao.MetaTargetDao;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/10/27.
 */

@Component("init")
public class InitManager {

    private static Logger logger = Logger.getLogger(InitManager.class);

    @Autowired
    private MetaSourceDao metaSourceDao;

    @Autowired
    private MetaTargetDao metaTargetDao;


//    @Scheduled(cron = "30 4 12 * * ?")
    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED, timeout=3)
    public void init() {
        System.out.println(new DateTime().toLocalDateTime());
        //初始化 获取所有表信息
        List<EtlMetaTableBase> list = metaSourceDao.queryAllTables();
        logger.info(list.size());

        Date date = new Date();

        //插入etl_meta_table_base中

        for (EtlMetaTableBase tableBase:list) {
            tableBase.setUpdateTime(date);
            tableBase.setStatus((byte) 1);
            int ret = metaTargetDao.saveTableBase(tableBase);
            logger.info("ret is " + ret);
            Long globalId = tableBase.getGlobalTableId();
            logger.info("globalid is " + globalId);
            Integer hiveTableId =  tableBase.getHiveTableId();
            List<EtlMetaColumn> columns = metaSourceDao.queryColumnsByTableId(hiveTableId);
            Date now = new Date();
            for (EtlMetaColumn column:columns) {
                column.setAddTime(now);
                column.setUpdateTime(now);
                column.setGlobalId(globalId);
                metaTargetDao.saveColumn(column);
            }

            EtlMetaHiveTable hiveTable = metaSourceDao.queryHiveSpecialInfo(hiveTableId);
            hiveTable.setGlobalId(globalId);
            //hive元数据
            metaTargetDao.saveHiveSpecialMetadata(hiveTable);

            List<EtlMetaPartition> parts = metaSourceDao.querypartitionsByTableId(hiveTableId);
            for (EtlMetaPartition partition:parts) {
                partition.setAddTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
                metaTargetDao.savePartition(partition);
            }

        }


    }

}
