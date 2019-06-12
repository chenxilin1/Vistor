package com.zlkj.visitor.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TUserDao;
import com.zlkj.visitor.dto.Combotree;
import com.zlkj.visitor.dto.MenuDto;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.dto.UserAndPwd;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.entity.Tmenus;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TUserService;

@Service
public class TUserServiceImpl implements TUserService{

	@Autowired
	private TUserDao dao;
	@Autowired
	private DdwhService ddwhService;
	
	
	public TUser findByyhid(String yhid) {
		return dao.findByyhid(yhid);
	}
	
	public TUser findByyhm(String yhm) {
		return dao.findByyhm(yhm);
	}
/*	
	public int updUser_mm(TUser tuser) {
		return dao.updUser_mm(tuser);
	}*/
	
	public TUser findUserAndPwd(UserAndPwd userAndPwd) {
		return dao.findUserAndPwd(userAndPwd);
	}
	
	public List<MenuDto> queryCode(String qxm) {

		List<MenuDto> tiggerTements = null;
		// 顶级节点集合
		List<MenuDto> tiggerTopTements = new ArrayList<MenuDto>();
		String[] qxms = qxm.split(",");
		// 得到权限码
		List<Tmenus> tmenuList = dao.findByUserQxm(qxms);
		List<Tmenus> topTmenus = null;
		// 得到顶级节点
		if (tmenuList != null && tmenuList.size() > 0) {
			topTmenus = dao.queryByPid("0");
			if (null == topTmenus) {
				return null;
			}
		}
		// 判定顶级节点 并且赋值到集合中去
		if (null != topTmenus) {
			for (Iterator<Tmenus> iterator = tmenuList.iterator(); iterator.hasNext();) {
				Tmenus tmenus = iterator.next();
				for (Tmenus topTmenu : topTmenus) {
					if (topTmenu.getTid().equals(tmenus.getTid())) {
						MenuDto menuDto=new MenuDto();
						menuDto.setMenuid(Integer.parseInt(topTmenu.getTid()));
						menuDto.setMenuname(topTmenu.getText());
						menuDto.setIcon(topTmenu.getImage());
						menuDto.setUrl(topTmenu.getUrl());
						tiggerTopTements.add(menuDto);
						iterator.remove();
					}
				}
			}
			if (tiggerTopTements.size() == 0) {
				return null;
			}
			for(int i=0;i<tiggerTopTements.size();i++){
				MenuDto menuDto=tiggerTopTements.get(i);
				tiggerTements = new ArrayList<MenuDto>();
				for (Iterator<Tmenus> iterator = tmenuList.iterator(); iterator.hasNext();) {
					Tmenus tmenus = iterator.next();
					if (menuDto.getMenuid()==(Integer.parseInt(tmenus.getPid()))) {
						MenuDto m=new MenuDto();
						m.setMenuid(Integer.parseInt(tmenus.getTid()));
						m.setMenuname(tmenus.getText());
						m.setIcon(tmenus.getImage());
						m.setUrl(tmenus.getUrl());
						tiggerTements.add(m);
					}
				}
				menuDto.setMenus(tiggerTements);
			}
		}
		return tiggerTopTements;
	}
	//
	
	public List<TUser> finAllFy(PulicCxtj pulicCxtj) {
		int index = pulicCxtj.getPage();
		int size = pulicCxtj.getRows();
		int minTotal = (index - 1) * size ;
		pulicCxtj.setPage(minTotal);
		pulicCxtj.setRows(size);
		return dao.finAllFy(pulicCxtj);
	}
	
	public long findAllFyCount(PulicCxtj pulicCxtj) {
		return dao.findAllFyCount(pulicCxtj);
	}
	
	public int addUser(TUser user) {
		if (null!=user.getKtxdd()) {
			String[] dd=user.getKtxdd().split(",");
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					String dds=ddwhService.ddmc2ddbh(string);
					if (null==dds) {
						dds=string;
					}
					ddmc+=dds+",";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			user.setKtxdd(ddmc);
		}
		return dao.addUser(user);
	}
	
	public int deleteUser(String ids) {
		String[] strs=ids.split(",");
		return dao.deleteUser(strs);
	}
	
	public int updateUser(TUser user) {
		if (null!=user.getKtxdd()) {
			String[] dd=user.getKtxdd().split(",");
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					if (!"".equals(string)) {
						String dds=ddwhService.ddmc2ddbh(string);
						if (null==dds) {
							dds=string;
						}
						ddmc+=dds+",";
					}
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			user.setKtxdd(ddmc);
		}
		return dao.updateUser(user);
	}
	public int updUser_mm(String yhm, String yhmm) {
		return dao.updUser_mm(yhm, yhmm);
	}
	public TUser findByYhmWy(String yhm) {
		return dao.findByYhmWy(yhm);
	}
	//权限码
	
	public List<Combotree> findAllUserCombotree(String name) {
		List<Combotree>combotreeTop=new ArrayList<Combotree>();
		List<Combotree>combotreeElement=null;
		TUser tuser=dao.findByyhm(name);
		String code=tuser.getYhqx();
		if(code==null||code.trim().equals("")){
			return null;
		}
		String[] codes=code.split(",");
		
		List<Tmenus>tmenus=dao.findByUserQxm(codes);
		for(Iterator<Tmenus> it=tmenus.iterator();it.hasNext();){
			Tmenus tmenu=it.next();
			if(tmenu.getPid().equals("0")){
				Combotree combotree=new Combotree();
				combotree.setId(tmenu.getTid());
				combotree.setText(tmenu.getText());
				combotree.setIconCls(tmenu.getImage());
				combotreeTop.add(combotree);
			}
		}
		if(combotreeTop.size()==0){
			return null;
		}
		for(int i=0;i<combotreeTop.size();i++){
			Combotree combotree=combotreeTop.get(i);
			combotreeElement=new ArrayList<Combotree>();
			for(Iterator<Tmenus> it=tmenus.iterator();it.hasNext();){
				Tmenus tmenu=it.next();
				String tpid=tmenu.getPid();
				String tid=combotree.getId();
				if(tpid.equals(tid)){
					Combotree c=new Combotree();
					c.setId(tmenu.getTid());
					c.setText(tmenu.getText());
					c.setIconCls(tmenu.getImage());
					combotreeElement.add(c);
				}
			}
			combotree.setChildren(combotreeElement);
		}
		return combotreeTop;
	}
	
	public List<String> findByCodeWithString(String cod) {
		if(cod==null){
			return null;
		}
		String[] codes=cod.split(",");
		List<String> strList=new ArrayList<String>(Arrays.asList(codes));
		List<String> sList=dao.findByCodeWithString("0");
		strList.removeAll(sList);
		return strList;
	}
	
	public List<TUser> findAllUser() {
		return dao.findAllUser();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
