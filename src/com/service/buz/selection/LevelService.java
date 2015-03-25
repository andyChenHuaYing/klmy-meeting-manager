package com.service.buz.selection;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.buz.selection.LevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 级别模块Service
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Service("levelService")
public class LevelService {
    @Autowired
    private SqlMapClient sqlMapClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryLevels(Map map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("level.queryLevels", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("level.queryLevels", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 级别查询");
        return list;
    }

    public int queryLevelsCount(Map map) throws SQLException {
//        SysLog.logRecord();
        return (Integer) this.sqlMapClient.queryForObject("level.queryLevelsCount", map);
    }

    public int addLevel(Map map) throws SQLException {
        int rtn = 0;
        map.put("id", null);
        int count = this.queryLevelsCount(map);
        if (count > 0) {
            return -1;
        }
        Long id = (Long) this.sqlMapClient.insert("level.addLevel", map);
        if (id != null) {
            rtn = 1;
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_ADD,
                UserHolder.getUserCode() + " 增加级别：" + map.get("levelName"));
        return rtn;
    }

    public int modifyLevelName(Map<String, Object> map) throws SQLException {
        int rnt;
        int count = this.queryLevelsCount(map);
        if (count > 0) {
            return -1;
        }
        rnt = this.sqlMapClient.update("level.updateLevelName", map);

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE,
                UserHolder.getUserCode() + " 修改级别为：" + map.get("levelName"));
        return rnt;
    }

    public int deleteLevel(Map<String, Object> map) throws SQLException {

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserCode() + " 删除级别");
        return this.sqlMapClient.delete("level.deleteLevel", map);
    }

    public List<LevelVO> queryAllLevelsForVO(Map<String, Object> map) throws SQLException {
        return this.sqlMapClient.queryForList("level.queryAllLevelsForVO", map);
    }
}
