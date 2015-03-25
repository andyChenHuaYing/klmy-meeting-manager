package com.service.buz.klmy.person;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by andychen on 2015/3/25.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Service("klmyPersonService")
public class KlmyPersonService {

    @Autowired
    private SqlMapClient sqlMapClient;

    public List<Map<String, Object>> queryKlmyPerson(Map<String, Object> map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("klmyPerson.queryKlmyPerson", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("klmyPerson.queryKlmyPerson", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 参会人员信息查询");
        return list;
    }

    public int queryKlmyPersonCount(Map<String, Object> map) throws SQLException {
        return (Integer) this.sqlMapClient.queryForObject("klmyPerson.queryKlmyPersonCount", map);
    }
}
