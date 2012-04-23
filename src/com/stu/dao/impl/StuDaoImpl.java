package com.stu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stu.dao.IStuDao;
import com.stu.db.DBpool;
import com.stu.model.StuInfo;

public class StuDaoImpl implements IStuDao {

	private Connection conn;
	private PreparedStatement pst ;
	private ResultSet rs;
	
	public int getStuId(){
		conn = DBpool.getConn();
		int stuId = 0 ;
		try {
			pst=conn.prepareStatement("select seq_stu_info.nextval from dual");
			rs = pst.executeQuery();
			while(rs.next()){
				stuId = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return stuId;
	}
	
	public boolean addUser(StuInfo stu) {
		boolean s = false ;
		int stuId = this.getStuId();
		conn = DBpool.getConn();
		String sql = "insert into stuinfo values(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, stuId);
			pst.setString(2, stu.getStuName());
			pst.setInt(3, stu.getStuAge());
			pst.setString(4, stu.getStuSex());
			pst.setString(5, stu.getStuAddress());
			pst.setString(6, stu.getStuDept());
			int k = pst.executeUpdate();
			if(k!=0){
				s = true ;
				System.out.println("添加学生成功");
			}
			
		} catch (SQLException e) {
			System.out.println("添加学生信息时出错");
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return s;
	}

	public boolean delUser(Integer stuId) {
		boolean s = false ;
		conn = DBpool.getConn();
		String sql = "delete from stuinfo where stuId = ?";
		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, stuId);
		
			int k = pst.executeUpdate();
			if(k!=0){
				s = true ;
				System.out.println("删除学生成功");
			}
			
		} catch (SQLException e) {
			System.out.println("删除学生信息时出错");
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return s;
	}

	public List<StuInfo> queryAllStuInfo() {
		conn = DBpool.getConn();
		List<StuInfo> stuList = new ArrayList<StuInfo>();
		try {
			pst = conn.prepareStatement("select * from stuinfo");
			rs = pst.executeQuery();
			while(rs.next()){
				int stuId = rs.getInt("stuId");
				String stuName = rs.getString("stuName");
				int stuAge = rs.getInt("stuAge");
				String stuSex = rs.getString("stuSex");
				String stuAddress = rs.getString("stuAddress");
				String stuDept = rs.getString("stuDept");
				stuList.add(new StuInfo(stuId,stuName,stuAge,stuSex,stuAddress,stuDept));
			}
		} catch (SQLException e) {
			System.out.println("查询所有学生信息时出错");
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return stuList;
	}

	public boolean upUser(StuInfo stu) {
		boolean s = false ;
		conn = DBpool.getConn();
		String sql = "update stuinfo set stuName=?,stuAge=?,stuSex=?,stuAddress=?,stuDept=? where stuId = ?";
		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, stu.getStuName().trim());
			pst.setInt(2, stu.getStuAge());
			pst.setString(3, stu.getStuSex().trim());
			pst.setString(4, stu.getStuAddress().trim());
			pst.setString(5, stu.getStuDept().trim());
			pst.setInt(6, stu.getStuId());
		
			int k = pst.executeUpdate();
			if(k!=0){
				s = true ;
				System.out.println("修改学生成功");
			}
			
		} catch (SQLException e) {
			System.out.println("修改学生信息时出错");
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return s;
	}

	public StuInfo queryOneStuById(int stuId){
		conn = DBpool.getConn();
		StuInfo stu =null;
		try {
			pst = conn.prepareStatement("select * from stuinfo where stuId = ?");
			pst.setInt(1, stuId);
			rs = pst.executeQuery();
			while(rs.next()){
				String stuName = rs.getString("stuName");
				int stuAge = rs.getInt("stuAge");
				String stuSex = rs.getString("stuSex");
				String stuAddress = rs.getString("stuAddress");
				String stuDept = rs.getString("stuDept");
				stu = new StuInfo(stuId,stuName,stuAge,stuSex,stuAddress,stuDept);
			}
		} catch (SQLException e) {
			System.out.println("查询学生信息时出错");
			e.printStackTrace();
		} finally{
			DBpool.closeAll(conn, pst, rs);
		}
		return stu;
	}

}
