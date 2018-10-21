package com.ypika.mp.service;

import com.ypika.mp.domain.Record;

import java.util.List;

public interface RecordService {
    Record saveRecord(Record record);

    List<Record> findAllRecord();

    Object findRecordById(Long id);
}
