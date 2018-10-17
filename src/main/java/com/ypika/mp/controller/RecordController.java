package com.ypika.mp.controller;

import com.ypika.mp.dao.RecordRepository;
import com.ypika.mp.domain.Record;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@Api
@RequestMapping("/record")
@RestController
@EnableAutoConfiguration
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @ApiOperation(value = "保存记录",notes = "notes保存记录")
    @PostMapping
    public Object saveRecord(@RequestBody Record record){
        return recordRepository.saveAndFlush(record);
    }

    @GetMapping
    public Object findAllRecord(){
        return recordRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object findRecord(@PathVariable Long id){
        return recordRepository.findById(id);
    }

}
