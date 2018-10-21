package com.ypika.mp.service.impl;

import com.ypika.mp.dao.RecordRepository;
import com.ypika.mp.domain.Record;
import com.ypika.mp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Record saveRecord(Record record) {
        Date now = new Date();
        record.setCreateDate(now);
        record.setModifiDate(now);
        record.setDelFlag(false);
        return recordRepository.saveAndFlush(record);
    }

    @Override
    public List<Record> findAllRecord() {
        return recordRepository.findByOrderByIdDesc();
    }

    @Override
    public Object findRecordById(Long id) {
        return recordRepository.findById(id);
    }


}
