package com.dao.impl.sys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dao.base.BaseDao;
import com.dao.sys.PersonDao;
import com.vo.Person;
@Repository("personDao")
public class PersonDaoImpl extends BaseDao implements PersonDao{
	private final static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	@Override
	public List<Person> queryPerson(Map map) {
		// TODO Auto-generated method stub
		List<Person> list = null;
		try {
			list = sqlMapClient.queryForList("person.queryPerson", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("person.queryPerson:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deletePerson(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = sqlMapClient.delete("person.deletePerson",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("person.deletePerson"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public Long addPerson(Map map) {
		// TODO Auto-generated method stub
		Long personId = 0L;
		try {
			personId = (Long) sqlMapClient.insert("person.addPerson", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("person.addPerson"+e.getMessage());
			e.printStackTrace();
		}
		
		return personId;
	}

	@Override
	public int updatePerson(Map map) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			cnt = sqlMapClient.update("person.updatePerson",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("person.deletePerson"+e.getMessage());
			e.printStackTrace();
		}
		return cnt;
	}
}
