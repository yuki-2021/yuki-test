package org.hscoder.springboot.h2.repository;

import org.hscoder.springboot.h2.domain.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {
}
