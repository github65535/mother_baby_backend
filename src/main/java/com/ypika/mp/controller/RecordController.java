package com.ypika.mp.controller;

import com.ypika.mp.domain.Record;
import com.ypika.mp.service.RecordService;
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
    private RecordService recordService;

    @ApiOperation(value = "保存记录",notes = "notes保存记录")
    @PostMapping
    public Object saveRecord(@RequestBody Record record){
        return recordService.saveRecord(record);
    }

    @GetMapping
    public Object findAllRecord(){
        return recordService.findAllRecord();
    }

    @GetMapping("/{id}")
    public Object findRecordById(@PathVariable Long id){
        return recordService.findRecordById(id);
    }

}
