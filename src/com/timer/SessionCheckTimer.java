package com.timer;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.buffer.SessionBuffer;
import com.sys.SystemConfigContext;
import com.vo.SessionVO;


public class SessionCheckTimer{
	private static SessionCheckTimer sessionCheckTimer = null;
	/*
	 * 构造器里面启动任务调度
	 */
	private static long sessionCheckTime = Long.valueOf(SystemConfigContext.getInstance().getValue("sessionCheckTime"));
	private static long sessionOutTime = Long.valueOf(SystemConfigContext.getInstance().getValue("sessionOutTime"));
	
	public static SessionCheckTimer getInstance() {
		if (sessionCheckTimer == null) {
			sessionCheckTimer = new SessionCheckTimer();
		}
		return sessionCheckTimer;
	}
	
	private SessionCheckTimer(){
		System.out.println("session超时检查类启动====================");
		System.out.println("sessionCheckTime:"+sessionCheckTime);
		System.out.println("sessionOutTime:"+sessionOutTime);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/*
				 * 对应sessionMap做迭代，对应超过失效时间的，从内存移除
				 */
				Map<String,SessionVO> map = SessionBuffer.getInstance().getSession();
				
				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String sessionId = it.next();
					SessionVO session = map.get(sessionId);
					if(session.getLastUpdateTime()!=null){//说明创建之后，有更新
						//如果当前时间减去session最近一次的更新时间大于超时时间，说明session过期，从缓存中移除
						if(new Date().getTime() - session.getLastUpdateTime().getTime()>sessionOutTime){
							map.remove(sessionId);
						}
					}else if(session.getCreateTime()!=null){//只有要创建时间
						if(new Date().getTime() - session.getCreateTime().getTime()>sessionOutTime){
							map.remove(sessionId);
						}
					}
					
				}
				it = map.keySet().iterator();
				System.out.println("缓存中session输出start++++++++++++++++++++++");
				while(it.hasNext()){
					System.out.println(it.next());
				}
				System.out.println("缓存中session输出end++++++++++++++++++++++");
			}
		},0,sessionCheckTime);
	}
}
