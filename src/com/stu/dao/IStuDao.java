package com.stu.dao;

import java.util.List;
import com.stu.model.*;

/**
 * 操作dao接口
 * @author hsz
 *
 */
public interface IStuDao {
	/**
	 * 添加一个用户
	 * @param StuInfo 对象
	 * @return boolean
	 */
	public boolean addUser(StuInfo stu);
	/**
	 * 删除一个用户
	 * @param stuId (按照id)
	 * @return boolean
	 */
	public boolean delUser(Integer stuId);
	/**
	 * 修改用户
	 * @param StuInfo 对象
	 * @return boolean
	 */
	public boolean upUser(StuInfo stu);
	/**
	 * 查询所有用户
	 * @return List<StuModel> 对象集合
	 */
	public List<StuInfo> queryAllStuInfo();
	/**
	 * 查询一个用户
	 * @param stuId
	 * @return StuInfo
	 */
	public StuInfo queryOneStuById(int stuId);
}
