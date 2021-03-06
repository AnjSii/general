package com.wu.general.timer.quartz.execute;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * Created by wuxun on 2017/12/20.
 */
public class QuartzExecute extends QuartzJobBean {
	private String targetObject;
	private String targetMethod;
	private ApplicationContext ctx;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		try {
			Object otargetObject = ctx.getBean(targetObject);
			Method m = null;

			try {
				m = otargetObject.getClass().getMethod(targetMethod);
				m.invoke(otargetObject);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}

	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}
