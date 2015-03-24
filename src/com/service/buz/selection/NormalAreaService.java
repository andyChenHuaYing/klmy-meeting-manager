package com.service.buz.selection;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.TbBaseNormalArea;
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
@Service("normalAreaService")
public class NormalAreaService {
    @Autowired
    private SqlMapClient sqlMapClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryAreas(Map map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("normalArea.queryAreas", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("normalArea.queryAreas", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 地区查询");
        return list;
    }

    public int queryAreasCount(Map map) throws SQLException {
//        SysLog.logRecord();
        return (Integer) this.sqlMapClient.queryForObject("normalArea.queryAreasCount", map);
    }

    public int addArea(Map map) throws SQLException {
        int rtn = 0;
        map.put("id", null);
        int count = this.queryAreasCount(map);
        if (count > 0) {
            return -1;
        }
        Long id = (Long) this.sqlMapClient.insert("normalArea.addArea", map);
        if (id != null) {
            rtn = 1;
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_ADD,
                UserHolder.getUserCode() + " 增加地区分类：" + map.get("areaName"));
        return rtn;
    }

    public int modifyAreaName(Map<String, Object> map) throws SQLException {
        int rnt;
        int count = this.queryAreasCount(map);
        if (count > 0) {
            return -1;
        }
        rnt = this.sqlMapClient.update("normalArea.updateAreaName", map);

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE,
                UserHolder.getUserCode() + " 修改地区分类为：" + map.get("areaName"));
        return rnt;
    }

    public int deleteArea(Map<String, Object> map) throws SQLException {

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserCode() + " 删除地区分类");
        return this.sqlMapClient.delete("normalArea.deleteArea", map);
    }

    public List<TbBaseNormalArea> queryAllAreasForVO(Map<String, Object> map) throws SQLException {
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 地区查询");
        return this.sqlMapClient.queryForList("normalArea.queryAllAreasForVO", map);
    }
}
