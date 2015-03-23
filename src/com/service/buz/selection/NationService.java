package com.service.buz.selection;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Happy daily, happy life.<br>
 * =========================<br>
 * Description:<br>
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Service("nationService")
public class NationService {
    @Autowired
    private SqlMapClient sqlMapClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryNations(Map map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("nation.queryNations", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("nation.queryNations", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 族别查询");
        return list;
    }

    public int queryNationsCount(Map map) throws SQLException {
//        SysLog.logRecord();
        return (Integer) this.sqlMapClient.queryForObject("nation.queryNationsCount", map);
    }

    public int addNation(Map map) throws SQLException {
        int rtn = 0;
        map.put("id", null);
        int count = this.queryNationsCount(map);
        if (count > 0) {
            return -1;
        }
        Long id = (Long) this.sqlMapClient.insert("nation.addNation", map);
        if (id != null) {
            rtn = 1;
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_ADD,
                UserHolder.getUserCode() + " 增加民族分类：" + map.get("nationName"));
        return rtn;
    }

    public int modifyNationName(Map<String, Object> map) throws SQLException {
        int rnt;
        int count = this.queryNationsCount(map);
        if (count > 0) {
            return -1;
        }
        rnt = this.sqlMapClient.update("nation.updateNationName", map);

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE,
                UserHolder.getUserCode() + " 修改民族分类为：" + map.get("nationName"));
        return rnt;
    }

    public int deleteNation(Map<String, Object> map) throws SQLException {

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserCode() + " 删除民族分类");
        return this.sqlMapClient.delete("nation.deleteNation", map);
    }
}
