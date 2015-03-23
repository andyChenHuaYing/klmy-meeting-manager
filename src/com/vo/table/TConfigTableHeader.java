package com.vo.table;

/**
 * TConfigTableHeader 实体类 Fri Aug 15 08:01:37 CST 2014 system
 */

public class TConfigTableHeader {
	private Long id;
	private String gridId;
	private String field;
	private String title;
	private String width;
	private String align;
	private String valign;
	private Long rowspan;
	private Long colspan;
	private Long headerLevel;
	private Long hidden;
	private String height;
	private Long seq;
	private Long checkbox;
	private Long sortable;
	private String order;
	private Long resizable;
	private Long fixed;
	private Long isFrozen;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSortable() {
		return sortable;
	}

	public void setSortable(Long sortable) {
		this.sortable = sortable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getResizable() {
		return resizable;
	}

	public void setResizable(Long resizable) {
		this.resizable = resizable;
	}

	public Long getFixed() {
		return fixed;
	}

	public void setFixed(Long fixed) {
		this.fixed = fixed;
	}

	public Long getId() {
		return id;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public String getGridId() {
		return gridId;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getWidth() {
		return width;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getAlign() {
		return align;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	public String getValign() {
		return valign;
	}

	public void setRowspan(Long rowspan) {
		this.rowspan = rowspan;
	}

	public Long getRowspan() {
		return rowspan;
	}

	public void setColspan(Long colspan) {
		this.colspan = colspan;
	}

	public Long getColspan() {
		return colspan;
	}

	public void setHeaderLevel(Long headerLevel) {
		this.headerLevel = headerLevel;
	}

	public Long getHeaderLevel() {
		return headerLevel;
	}

	public void setHidden(Long hidden) {
		this.hidden = hidden;
	}

	public Long getHidden() {
		return hidden;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return height;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Long checkbox) {
		this.checkbox = checkbox;
	}

	public Long getIsFrozen() {
		return isFrozen;
	}

	public void setIsFrozen(Long isFrozen) {
		this.isFrozen = isFrozen;
	}
}
