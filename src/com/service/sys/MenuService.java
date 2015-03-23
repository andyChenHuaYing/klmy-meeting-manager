package com.service.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.buffer.MenuBuffer;
import com.dao.sys.MenuDao;
import com.util.UserHolder;
@Service("menuService")
public class MenuService{
	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDao;
	public List<Map<String,Object>> findMenu(Map map) {
		// TODO Auto-generated method stub
		return this.menuDao.findMenu(map);
	}
	public List<Map<String,Object>> findMenuByUser(String topMenu) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = null;
//		List<Map<String,Object>> list = MenuBuffer.getInstance().getMenuByUserId(UserHolder.getUserContext().getUserId());
//		if(list!=null){
//			return list;
//		}else{
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("userId", UserHolder.getUserContext().getUserId());
			map.put("topMenu", topMenu);
			list = this.menuDao.findMenu(map);
//			MenuBuffer.getInstance().set(list, UserHolder.getUserContext().getUserId());
			return list;
//		}
		
	}
}
