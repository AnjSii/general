package com.wu.general.timer;

import com.wu.general.utils.CommUtil;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by wuxun on 2017/12/20.
 */
@Component("Timer_JobDetailFactoryBean")
public class Timer_JobDetailFactoryBean {
	public void execute() throws Exception {
		System.out.println(CommUtil.formatLongDate(new Date()) +  " JobDetailFactoryBean创建的定时任务");
	}
}
