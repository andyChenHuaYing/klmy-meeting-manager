package com.util;

import com.vo.buz.selection.SelectionVO;
import com.vo.buz.selection.TbBaseNormalArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Happy day, happy life.
 *
 * @author andy
 * @version 1.0-SNAPSHOT
 *          Created date: 2015-03-25 22:10
 */
public class TransferUtil {

    /**
     * 将具体下拉框选项填充值修改成统一属性名
     */
    public static List<SelectionVO> transferSelectionList(List<TbBaseNormalArea> list) {
        List<SelectionVO> selectionList = new ArrayList<SelectionVO>();
        for (TbBaseNormalArea tbBaseNormalArea : list) {
            SelectionVO selectionVO = new SelectionVO();
            selectionVO.setId(tbBaseNormalArea.getId());
            selectionVO.setName(tbBaseNormalArea.getAreaName());
            selectionList.add(selectionVO);
        }
        return selectionList;
    }
}
