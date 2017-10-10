package com.demo.account.controllers;

import com.demo.account.services.LoginLogService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/log")
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @RequestMapping(method = RequestMethod.GET, value = "/{start}/{end}")
    @ResponseBody
    public Map<String, Object> getAccount(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        List<String> logList = loginLogService.findLogList(start, end);
        Map<String, Object> map = new HashMap<>();
        map.put("data", logList);
        return map;
    }
}
