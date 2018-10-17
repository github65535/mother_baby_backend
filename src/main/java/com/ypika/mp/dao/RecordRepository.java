package com.ypika.mp.dao;

import com.ypika.mp.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Long> {

}
