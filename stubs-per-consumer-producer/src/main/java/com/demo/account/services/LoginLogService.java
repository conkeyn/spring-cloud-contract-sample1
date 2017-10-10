package com.demo.account.services;

import java.util.Date;
import java.util.List;

public interface LoginLogService {

    List<String> findLogList(Date start,Date end);
}
