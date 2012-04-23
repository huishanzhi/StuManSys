package com.stu.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.stu.dao.impl.StuDaoImpl;

public class StuTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Vector<Vector<String>> rowsData = new Vector<Vector<String>>();
	private Vector<String> colsData = new Vector<String>();

	@Override
	public String getColumnName(int column) {
		// System.out.println("调用了查询列名方法");
		return colsData.get(column);
	}

	public int getColumnCount() {
		// System.out.println("调用了查询列数方法");
		return colsData.size();
	}

	public int getRowCount() {
		// System.out.println("调用了查询行数方法");
		return rowsData.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// System.out.println("调用了查询数据方法");
		return rowsData.get(rowIndex).get(columnIndex);
	}

	public StuTableModel() {
		colsData.add("学号");
		colsData.add("姓名");
		colsData.add("年龄");
		colsData.add("性别");
		colsData.add("家庭住址");
		colsData.add("系别");

		StuDaoImpl stuImpl = new StuDaoImpl();
		List<StuInfo> stuInfos = stuImpl.queryAllStuInfo();
		for (int i = 0, len = stuInfos.size(); i < len; i++) {
			StuInfo stu = stuInfos.get(i);
			Vector<String> rowsTemp = new Vector<String>();

			rowsTemp.add(stu.getStuId() + "");
			rowsTemp.add(stu.getStuName());
			rowsTemp.add(stu.getStuAge() + "");
			rowsTemp.add(stu.getStuSex());
			rowsTemp.add(stu.getStuAddress());
			rowsTemp.add(stu.getStuDept());

			rowsData.add(rowsTemp);
		}
	}

	public StuTableModel(StuInfo stu) {
		colsData.add("学号");
		colsData.add("姓名");
		colsData.add("年龄");
		colsData.add("性别");
		colsData.add("家庭住址");
		colsData.add("系别");

		Vector<String> rowsTemp = new Vector<String>();

		rowsTemp.add(stu.getStuId() + "");
		rowsTemp.add(stu.getStuName());
		rowsTemp.add(stu.getStuAge() + "");
		rowsTemp.add(stu.getStuSex());
		rowsTemp.add(stu.getStuAddress());
		rowsTemp.add(stu.getStuDept());

		rowsData.add(rowsTemp);

	}
}
