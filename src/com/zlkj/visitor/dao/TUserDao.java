package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.dto.UserAndPwd;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.entity.Tmenus;

public interface TUserDao {
	//根据用户id查询
	public TUser findByyhid(String yhid);
	//根据用户名查询
	public TUser findByyhm(String yhm);
	
	public TUser findUserAndPwd(UserAndPwd userAndPwd);
	// 根据父节点 来返回权限码
	List<Tmenus> queryByPid(String pid);
	// 根据用户的权限码 返回权限集合
	List<Tmenus> findByUserQxm(String[] qxm);
	//根据父节点 来寻找 子节点的id
	List<String> findByCodeWithString(String pid);
	//
	//分页查询
	public List<TUser> finAllFy(PulicCxtj pulicCxtj);
	//分页的总数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//查询用户名去除重复
	TUser findByYhmWy(String yhm);
	//添加用户
	public int addUser(TUser user);
	//删除用户
	public int deleteUser(String[] strs);
	//修改用户
	public int updateUser(TUser user);
	//修改密码
	int updUser_mm(String yhm,String yhmm);

	/**
	 * 获取所有用户 供下拉列表使用
	 * @return
	 */
	public List<TUser> findAllUser();
	
	
	
	
	
	
	
	
	
}
