package com.wu.general.timer;

import com.wu.general.utils.CommUtil;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by wuxun on 2017/12/20.
 */
@Component("timer_MethodInvokingJobDetailFactoryBean")
public class Timer_MethodInvokingJobDetailFactoryBean {
	public void execute() throws Exception {
		System.out.println(CommUtil.formatLongDate(new Date()) + " MethodInvokingJobDetailFactoryBean创建的定时任务");
	}
}
