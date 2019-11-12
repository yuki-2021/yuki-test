package org.hscoder.springboot.h2;

import org.hscoder.springboot.h2.domain.LogRecord;
import org.hscoder.springboot.h2.repository.LogRecordRepository;
import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LogRecordInitializer {

    @Autowired
    private LogRecordRepository logRecordRepository;
    private static final Logger logger = LoggerFactory.getLogger(LogRecordInitializer.class);

    @PostConstruct
    void initData(){

        if(logRecordRepository.count() > 0){

            List<LogRecord> logRecords = logRecordRepository.findAll();

            logger.info("read records: {}", JsonUtil.toPrettyJson(logRecords));
            return;
        }
        for(int i=0; i<100; i++){

            LogRecord record = new LogRecord();
            record.setLevel("info");

            record.setMessage("Heartbeat message " + UUID.randomUUID().toString());
            record.setCreateTime(new Date());

            logRecordRepository.save(record);

            logger.info("save record - " + record.getMessage());
        }

    }
}
