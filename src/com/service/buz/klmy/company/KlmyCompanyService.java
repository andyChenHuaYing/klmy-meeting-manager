package com.service.buz.klmy.company;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Service("klmyCompanyService")
public class KlmyCompanyService {

    @Autowired
    private SqlMapClient sqlMapClient;

    public List<Map<String, Object>> queryKlmyCompany(Map<String, Object> map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("klmyCompany.queryKlmyCompany", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("klmyCompany.queryKlmyCompany", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 单位信息查询");
        return list;
    }

    public int queryKlmyCompanyCount(Map<String, Object> map) throws SQLException {
        return (Integer) this.sqlMapClient.queryForObject("klmyCompany.queryKlmyCompanyCount", map);
    }
}
