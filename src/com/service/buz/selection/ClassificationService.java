package com.service.buz.selection;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.SysLog;
import com.util.UserHolder;
import com.vo.buz.selection.ClassificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 分类模块Service
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Service("classificationService")
public class ClassificationService {
    @Autowired
    private SqlMapClient sqlMapClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryClassifications(Map map) throws SQLException {
        List<Map<String, Object>> list;
        if (map != null && map.get("rows") != null && map.get("page") != null) {
            list = this.sqlMapClient.queryForList("classification.queryClassifications", map,
                    (Integer) map.get("rows") * ((Integer) map.get("page") - 1), (Integer) map.get("rows"));
        } else {
            list = this.sqlMapClient.queryForList("classification.queryClassifications", map);
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_QUERY, UserHolder.getUserContext() == null ? ""
                : UserHolder.getUserContext().getUserCode() + " 分类查询");
        return list;
    }

    public int queryClassificationsCount(Map map) throws SQLException {
//        SysLog.logRecord();
        return (Integer) this.sqlMapClient.queryForObject("classification.queryClassificationsCount", map);
    }

    public int addClassification(Map map) throws SQLException {
        int rtn = 0;
        map.put("id", null);
        int count = this.queryClassificationsCount(map);
        if (count > 0) {
            return -1;
        }
        Long id = (Long) this.sqlMapClient.insert("classification.addClassification", map);
        if (id != null) {
            rtn = 1;
        }
        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_ADD,
                UserHolder.getUserCode() + " 增加分类：" + map.get("classificationName"));
        return rtn;
    }

    public int modifyClassificationName(Map<String, Object> map) throws SQLException {
        int rnt;
        int count = this.queryClassificationsCount(map);
        if (count > 0) {
            return -1;
        }
        rnt = this.sqlMapClient.update("classification.updateClassificationName", map);

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_UPDATE,
                UserHolder.getUserCode() + " 修改分类为：" + map.get("classificationName"));
        return rnt;
    }

    public int deleteClassification(Map<String, Object> map) throws SQLException {

        SysLog.logRecordSimple(SysLog.OPERATION_TYPE_DELETE, UserHolder.getUserCode() + " 删除分类");
        return this.sqlMapClient.delete("classification.deleteClassification", map);
    }

    public List<ClassificationVO> queryAllClassificationsForVO(Map<String, Object> map) throws SQLException {
        return this.sqlMapClient.queryForList("classification.queryAllClassificationsForVO", map);
    }
}
