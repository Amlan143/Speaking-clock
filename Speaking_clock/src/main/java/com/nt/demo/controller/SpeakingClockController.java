package com.nt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.demo.exception.InvalidTimeException;
//import com.nt.demo.exception.SpeakingClockApplication;
import com.nt.demo.service.SpeakingClockService;
import io.swagger.annotations.*;
//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/speaking-clock")
@Api(value = "Speaking Clock API")
public class SpeakingClockController {

    @Autowired
    private SpeakingClockService speakingClockService;

    @GetMapping("/{time}")
    @ApiOperation(value = "Convert time to words", response = String.class)
    public ResponseEntity<String> convertTimeToWords(@PathVariable String time) {
        try {
            String result = speakingClockService.convertTimeToWords(time);
            return ResponseEntity.ok(result);
        } catch (InvalidTimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}