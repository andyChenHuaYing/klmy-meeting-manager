package com.service.sys;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.sys.PersonDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vo.Person;
@Service("personService")
public class PersonService {
	@Autowired
	@Qualifier("personDao")
	private PersonDao personDao;
	@Autowired
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public List<Person> queryPerson(Map map) {
		// TODO Auto-generated method stub
		return personDao.queryPerson(map);
	}

	public int deletePerson(Map map) {
		// TODO Auto-generated method stub
		return personDao.deletePerson(map);
	}

	public Long addPerson(Map map) {
		// TODO Auto-generated method stub
		return personDao.addPerson(map);
	}

	public int updatePerson(Map map) {
		// TODO Auto-generated method stub		
		return this.personDao.updatePerson(map);
	}
	@SuppressWarnings("rawtypes")
	public int queryPersonCntForMap(Map map) throws SQLException{
		return ((Integer)this.sqlMapClient.queryForObject("person.queryPersonCntForMap", map)).intValue();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,Object>> queryPersonForMap(Map map) throws SQLException{
		List<Map<String, Object>> list = null;
		if(map.get("pageSize")!=null && map.get("currPage")!=null){
			list = (List<Map<String, Object>>)this.sqlMapClient.queryForList("person.queryPersonForMap", map,(Integer)map.get("pageSize")*((Integer)map.get("currPage")-1),(Integer)map.get("pageSize"));
		}else{
			list = (List<Map<String,Object>>)this.sqlMapClient.queryForList("person.queryPersonForMap", map);
		}
		return list;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map queryPersonIdByRecord(String recordId) throws SQLException{
		Map person = null;
		Map paramMap = new HashMap<String, String>();
		paramMap.put("recordId", recordId);
		List<Map<String, Object>> resultList = this.sqlMapClient
				.queryForList("person.queryPersonForMap", paramMap);
		if(resultList!=null && resultList.size()>0){
			person = (Map<String,Object>)resultList.get(0);
		}
		return person;
	}
}
