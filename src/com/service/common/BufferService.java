package com.service.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.buffer.MenuBuffer;
import com.dao.sys.MenuDao;

@Service("bufferService")
public class BufferService {
	
	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshBuffer() throws SQLException{
		
		//刷新 菜单缓存
		Map<Long,List<Map<String,Object>>> map = MenuBuffer.getInstance().getUserMenuMap();
		Iterator it = map.entrySet().iterator();
		Map<String,Long> tmpMap = new HashMap<String, Long>();
		while(it.hasNext()){
			Map.Entry<Long, List<Map<String,Object>>> entry = (Map.Entry<Long, List<Map<String,Object>>>)  it.next();
			Long userId = entry.getKey();
			tmpMap.remove("userId");
			tmpMap.put("userId", userId);
			List list = menuDao.findMenu(tmpMap);
			MenuBuffer.getInstance().update(list, userId);
		}
		//刷新页面配置缓存
		
	}
}
