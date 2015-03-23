package com.dao.sys;

import java.util.List;
import java.util.Map;

import com.vo.Person;

public interface PersonDao {
	public List<Person> queryPerson(Map map);
	public int deletePerson(Map map);
	public Long addPerson(Map map);
	public int updatePerson(Map map);
}
