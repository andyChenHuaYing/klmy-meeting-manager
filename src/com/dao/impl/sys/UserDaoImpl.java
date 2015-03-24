package com.dao.impl.sys;

import com.dao.base.BaseDao;
import com.dao.sys.UserDao;
import com.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	public Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserVO> queryUser(Map map){
		List<UserVO> list = null;
		try {
			list = (List<UserVO>)this.sqlMapClient.queryForList("user.queryUser", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long addUser(Map map) {
		// TODO Auto-generated method stub
		Long userId = 0L;
		try {
			userId = (Long)this.sqlMapClient.insert("user.addUser", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("user.addUser:"+e.getMessage());
			e.printStackTrace();
		}
		return userId;
	}

	@Override
	public List<Map<String, Object>> queryUserForMap(Map map) {
		List<Map<String, Object>> list = null;
		try {
			if(map.get("pageSize")!=null && map.get("currPage")!=null){
				list = (List<Map<String, Object>>)this.sqlMapClient.queryForList("user.queryUserForMap", map,(Integer)map.get("pageSize")*((Integer)map.get("currPage")-1),(Integer)map.get("pageSize"));
			}else{
				list = (List<Map<String, Object>>)this.sqlMapClient.queryForList("user.queryUserForMap", map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int queyrUserCntForMap(Map map) {
		// TODO Auto-generated method stub
		int rtnCnt = 0;
		try {
			List<Map<String,Object>> list = this.sqlMapClient.queryForList("user.queryUserCntForMap", map);
			if(list!=null && list.size()>0){
				rtnCnt=((BigDecimal)list.get(0).get("CNT")).intValue();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtnCnt;
	}

	@Override
	public int deleteUserRole(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("user.deleteUserRole", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("user.deleteUser:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteUser(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.delete("user.deleteUser", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("user.deleteUser:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateUser(Map map) {
		int cnt = 0;
		try {
			cnt = this.sqlMapClient.update("user.updateUser",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("user.updateUser:"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	};
	
	@Override
	public List<Map<String, Object>> queryUserForMapNew(Map map) {
		List<Map<String, Object>> list = null;
		try {
			if(map.get("rows")!=null && map.get("page")!=null){
				list = (List<Map<String, Object>>)this.sqlMapClient.queryForList("user.queryUserForMap", map,(Integer)map.get("rows")*((Integer)map.get("page")-1),(Integer)map.get("rows"));
			}else{
				list = (List<Map<String, Object>>)this.sqlMapClient.queryForList("user.queryUserForMap", map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer queryUserNamesNumber(Map map) {
		int count = -1;
		try {
			count = (Integer)this.sqlMapClient.queryForObject("user.queryUserNamesNumber", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

    @Override
    public Long queryUserAreaId(Long updatedUserId) throws SQLException {
        return (Long) this.sqlMapClient.queryForObject("user.queryUserAreaId", updatedUserId);
    }

}
