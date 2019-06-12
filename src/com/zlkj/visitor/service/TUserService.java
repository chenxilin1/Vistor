package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.dto.Combotree;
import com.zlkj.visitor.dto.MenuDto;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.dto.UserAndPwd;
import com.zlkj.visitor.entity.TUser;



public interface TUserService {
	//根据用户id查询
	public TUser findByyhid(String yhid);
	//根据用户名查询
	public TUser findByyhm(String yhm);
	
	public TUser findUserAndPwd(UserAndPwd userAndPwd);
	/**
	 * 获取左侧菜单
	 * @param qxm
	 * @return
	 */
	List<MenuDto> queryCode(String qxm);
	//
	//分页查询
	public List<TUser> finAllFy(PulicCxtj pulicCxtj);
	//分页的总数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//根据用户名返回权限
	List<Combotree> findAllUserCombotree(String name);
	/**
	 * 查询用户名去除重复
	 * @param yhm
	 * @return
	 */
	TUser findByYhmWy(String yhm);
	//根据用户的权限码查询
	List<String> findByCodeWithString(String code);
	//添加用户
	public int addUser(TUser user);
	//删除用户
	public int deleteUser(String ids);
	//修改用户
	public int updateUser(TUser user);
	/**
	 * 修改用户密码
	 * @param yhm
	 * @param yhmm
	 * @return
	 */
	int updUser_mm(String yhm,String yhmm);
	/**
	 * 获取所有用户 供下拉列表使用
	 * @return
	 */
	public List<TUser> findAllUser();
	
	
	
	
}
