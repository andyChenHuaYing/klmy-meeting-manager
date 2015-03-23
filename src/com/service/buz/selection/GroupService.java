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
@Service("groupService")
public class GroupService {
    @Autowired
    private SqlMapClient sqlMapClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryGroups(Map map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("group.queryGroups", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("group.queryGroups", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 分组查询");
        return list;
    }

    public int queryGroupsCount(Map map) throws SQLException {
//        SysLog.logRecord();
        return (Integer) this.sqlMapClient.queryForObject("group.queryGroupsCount", map);
    }

    public int addGroup(Map map) throws SQLException {
        int rtn = 0;
        map.put("id", null);
        int count = this.queryGroupsCount(map);
        if (count > 0) {
            return -1;
        }
        Long id = (Long) this.sqlMapClient.insert("group.addGroup", map);
        if (id != null) {
            rtn = 1;
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_ADD,
                UserHolder.getUserCode() + " 增加分组：" + map.get("groupName"));
        return rtn;
    }

    public int modifyGroupName(Map<String, Object> map) throws SQLException {
        int rnt;
        int count = this.queryGroupsCount(map);
        if (count > 0) {
            return -1;
        }
        rnt = this.sqlMapClient.update("group.updateGroupName", map);

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE,
                UserHolder.getUserCode() + " 修改分组分类为：" + map.get("groupName"));
        return rnt;
    }

    public int deleteGroup(Map<String, Object> map) throws SQLException {

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserCode() + " 删除分组");
        return this.sqlMapClient.delete("group.deleteGroup", map);
    }
}
