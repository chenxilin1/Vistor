package com.zlkj.visitor.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.PlsjglDao;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.entity.TPlbqk;
import com.zlkj.visitor.service.PlsjglService;
import com.zlkj.visitor.util.TPublicUtil;

@Service
public class PlsjglServiceImpl implements PlsjglService {
	@Autowired
	private PlsjglDao dao;

	// 格式化机动车数据
	public Map<String, Integer> cshjdcsj(Workbook workbook) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;//添加总数
		Integer gxsl = 0;//更新总数
		Integer sbsl = 0;//失败总数
		Sheet sheet = workbook.getSheetAt(0);
		int num = sheet.getLastRowNum();
		map.put("ERR", 0);
		List<TAllmsg> allmsgs = new ArrayList<TAllmsg>();
		for (int i = 1; i <= num; i++) {
			TAllmsg msg = new TAllmsg();
			Row row = sheet.getRow(i);
			//int columnNum=hssfRow.getPhysicalNumberOfCells();
			int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
			for (int j = 0; j < columnNum; j++) {
				if (null!=row.getCell(j)) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				/*if ("".equals(row.getCell(j).getStringCellValue()) || null==row.getCell(j).getStringCellValue()) {
					continue;
				}*/
				if (null==row.getCell(j)) {
					row.createCell(j).setCellValue("");
				}
			}
			msg.setBqid(!"".equals(row.getCell(0).getStringCellValue().trim()) ? row.getCell(0).getStringCellValue() : null);
			msg.setHphm(!"".equals(row.getCell(1).getStringCellValue().trim()) ? row.getCell(1).getStringCellValue() : null);
			msg.setHpzl(!"".equals(row.getCell(2).getStringCellValue().trim()) ? row.getCell(2).getStringCellValue() : null);
			msg.setClpp(!"".equals(row.getCell(3).getStringCellValue().trim()) ? row.getCell(3).getStringCellValue() : null);
			msg.setClxh(!"".equals(row.getCell(4).getStringCellValue().trim()) ? row.getCell(4).getStringCellValue() : null);
			msg.setSyr(!"".equals(row.getCell(5).getStringCellValue().trim()) ? row.getCell(5).getStringCellValue() : null);
			msg.setCsys(!"".equals(row.getCell(6).getStringCellValue().trim()) ? row.getCell(6).getStringCellValue() : null);
			msg.setFdjh(!"".equals(row.getCell(7).getStringCellValue().trim()) ? row.getCell(7).getStringCellValue() : null);
			msg.setClsbdh(!"".equals(row.getCell(8).getStringCellValue().trim()) ? row.getCell(8).getStringCellValue() : null);
			msg.setSfzmhm(!"".equals(row.getCell(9).getStringCellValue().trim()) ? row.getCell(9).getStringCellValue() : null);
			try {
				msg.setCcdjrq(!"".equals(row.getCell(10).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(10).getStringCellValue().replace("/", "-")) : null);
				msg.setFprq(!"".equals(row.getCell(11).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(11).getStringCellValue().replace("/", "-")) : null);
			} catch (Exception e) {
				map.put("ERR", 1010);
			}
			msg.setDjry(!"".equals(row.getCell(12).getStringCellValue().trim()) ? row.getCell(12).getStringCellValue() : null);
			msg.setLxdh(!"".equals(row.getCell(13).getStringCellValue().trim()) ? row.getCell(13).getStringCellValue() : null);
			msg.setCllx(!"".equals(row.getCell(14).getStringCellValue().trim()) ? row.getCell(14).getStringCellValue() : null);
			msg.setFqrq(!"".equals(row.getCell(15).getStringCellValue().trim()) ? row.getCell(15).getStringCellValue().replace("/", "-") : null);
			msg.setXqrq(!"".equals(row.getCell(16).getStringCellValue().trim()) ? row.getCell(16).getStringCellValue().replace("/", "-") : null);
			msg.setBqzt(!"".equals(row.getCell(17).getStringCellValue().trim()) ? row.getCell(17).getStringCellValue() : null);
			//msg.setId(!"".equals(row.getCell(18).getStringCellValue().trim()) ? Integer.parseInt(row.getCell(18).getStringCellValue()) : null);
			msg.setId(null);
			if (msg.getId()!=null) {
				TAllmsg bqid= dao.findByJdcId(msg.getId());
				if (null!=bqid) {
					msg.setId(null);
				}
			}
			msg.setMz(!"".equals(row.getCell(19).getStringCellValue().trim()) ? row.getCell(19).getStringCellValue() : null);
			msg.setSsdw(!"".equals(row.getCell(20).getStringCellValue().trim()) ? row.getCell(20).getStringCellValue() : null);
			msg.setJe(!"".equals(row.getCell(21).getStringCellValue().trim()) ? Integer.parseInt(row.getCell(21).getStringCellValue()) : null);
			msg.setNwb(!"".equals(row.getCell(22).getStringCellValue().trim()) ? row.getCell(22).getStringCellValue() : null);
			msg.setKtxdd(!"".equals(row.getCell(23).getStringCellValue().trim()) ? row.getCell(23).getStringCellValue() : null);
			msg.setSsks(!"".equals(row.getCell(24).getStringCellValue().trim()) ? row.getCell(24).getStringCellValue() : null);
			msg.setYxjzrq(!"".equals(row.getCell(25).getStringCellValue().trim()) ? row.getCell(25).getStringCellValue().replace("/", "-") : null);
			msg.setFcphm(!"".equals(row.getCell(26).getStringCellValue().trim()) ? row.getCell(26).getStringCellValue() : null);
			msg.setLd(!"".equals(row.getCell(27).getStringCellValue().trim()) ? row.getCell(27).getStringCellValue() : null);
			msg.setSctpdz(!"".equals(row.getCell(28).getStringCellValue().trim()) ? row.getCell(28).getStringCellValue() : null);
			allmsgs.add(msg);
		}
		for (TAllmsg tAllmsg : allmsgs) {
			try {
				TAllmsg hphm = dao.findByhphm(tAllmsg.getHphm());
				if (null == hphm) {
					dao.cshjdcsj(tAllmsg);
					++cgsl;
				} else {
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					boolean s = false;
					//判断表格中的标签id是否为空
					boolean s1=null!=tAllmsg.getBqid();
					if (s1) {
						//判断数据库中的 标签id是否为空
						boolean s2= !"".equals(hphm.getBqid()) & null!=hphm.getBqid();
						if (s2) {
							//判断数据库中的发签日期和表格中的发签日期大小
							if (null!=tAllmsg.getFqrq() & !tAllmsg.getFqrq().contains("")) {
								if (null!=hphm.getFqrq() & !hphm.getFqrq().contains("")) {
									if (sdf.parse(tAllmsg.getFqrq()).compareTo(sdf.parse(hphm.getFqrq())) > 0) {
										s = true;
									}else {
										s = false;
									}
								}else {
									s = true;
								}
							}else {
								s = false;
							}
						}else {
							s=true;
						}
					}
					if (s) {
						dao.updetejdcsj(tAllmsg);
						++gxsl;
					}else {
						++sbsl;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				++sbsl;
			}
		}
		map.put("YES", cgsl);
		map.put("GX", gxsl);
		map.put("NO", sbsl);
		return map;
	}
	// --------------------------------------------------------------------------------------
	// 采集机动车数据
	public Map<String, Integer> cjjdcxx(Workbook workbook) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;//添加总数
		Integer gxsl = 0;//更新总数
		Integer sbsl = 0;//失败总数
		Sheet sheet = workbook.getSheetAt(0);
		int num = sheet.getLastRowNum();
		map.put("ERR", 0);
		List<TAllmsg> allmsgs = new ArrayList<TAllmsg>();
		for (int i = 1; i <= num; i++) {
			TAllmsg msg = new TAllmsg();
			Row row = sheet.getRow(i);
			int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
			for (int j = 0; j < columnNum; j++) {
				if (null!=row.getCell(j)) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				if (null==row.getCell(j)) {
					row.createCell(j).setCellValue("");
				}
			}
			msg.setHphm(!"".equals(row.getCell(1).getStringCellValue().trim()) ? row.getCell(1).getStringCellValue() : null);
			msg.setHpzl(!"".equals(row.getCell(2).getStringCellValue().trim()) ? row.getCell(2).getStringCellValue() : null);
			msg.setClpp(!"".equals(row.getCell(3).getStringCellValue().trim()) ? row.getCell(3).getStringCellValue() : null);
			msg.setClxh(!"".equals(row.getCell(4).getStringCellValue().trim()) ? row.getCell(4).getStringCellValue() : null);
			msg.setSyr(!"".equals(row.getCell(5).getStringCellValue().trim()) ? row.getCell(5).getStringCellValue() : null);
			msg.setCsys(!"".equals(row.getCell(6).getStringCellValue().trim()) ? row.getCell(6).getStringCellValue() : null);
			msg.setFdjh(!"".equals(row.getCell(7).getStringCellValue().trim()) ? row.getCell(7).getStringCellValue() : null);
			msg.setClsbdh(!"".equals(row.getCell(8).getStringCellValue().trim()) ? row.getCell(8).getStringCellValue() : null);
			try {
				msg.setCcdjrq(!"".equals(row.getCell(9).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(9).getStringCellValue().replace("/", "-")) : null);
				msg.setFprq(!"".equals(row.getCell(10).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(10).getStringCellValue().replace("/", "-")) : null);
			} catch (Exception e) {
				map.put("ERR", 1010);
			}
			msg.setLxdh(!"".equals(row.getCell(11).getStringCellValue().trim()) ? row.getCell(11).getStringCellValue() : null);
			msg.setCllx(!"".equals(row.getCell(12).getStringCellValue().trim()) ? row.getCell(12).getStringCellValue() : null);
			msg.setSsdw(!"".equals(row.getCell(13).getStringCellValue().trim()) ? row.getCell(13).getStringCellValue() : null);
			msg.setKtxdd(!"".equals(row.getCell(14).getStringCellValue().trim()) ? row.getCell(14).getStringCellValue() : null);
			msg.setSsks(!"".equals(row.getCell(15).getStringCellValue().trim()) ? row.getCell(15).getStringCellValue() : null);
			msg.setYxjzrq(!"".equals(row.getCell(16).getStringCellValue().trim()) ? row.getCell(16).getStringCellValue().replace("/", "-") : null);
			msg.setFcphm(!"".equals(row.getCell(17).getStringCellValue().trim()) ? row.getCell(17).getStringCellValue() : null);
			msg.setLd(!"".equals(row.getCell(18).getStringCellValue().trim()) ? row.getCell(18).getStringCellValue() : null);
			allmsgs.add(msg);
		}
		for (TAllmsg tAllmsg : allmsgs) {
			try {
				TAllmsg hphm = dao.findByhphm(tAllmsg.getHphm());
				if (null == hphm) {
					dao.cshjdcsj(tAllmsg);
					++cgsl;
				} else {
					++sbsl;
				}
			} catch (Exception e) {
				e.printStackTrace();
				++sbsl;
			}
		}
		map.put("YES", cgsl);
		map.put("GX", gxsl);
		map.put("NO", sbsl);
		return map;
	}
	// --------------------------------------------------------------------------------------
	// 初始化驾驶人数据
	public Map<String, Integer> cshjsrsj(Workbook workbook) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;//添加总数
		Integer gxsl = 0;//更新总数
		Integer sbsl = 0;//失败总数
		Sheet sheet = workbook.getSheetAt(0);
		int num = sheet.getLastRowNum();
		map.put("ERR", 0);
		List<TJsrk> jsrks = new ArrayList<TJsrk>();
		for (int i = 1; i <= num; i++) {
			TJsrk jsr = new TJsrk();
			Row row = sheet.getRow(i);
			int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
			for (int j = 0; j < columnNum; j++) {
				if (null!=row.getCell(j)) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				/*if ("".equals(row.getCell(j).getStringCellValue()) || null==row.getCell(j).getStringCellValue()) {
					continue;
				}*/
				if (null==row.getCell(j)) {
					row.createCell(j).setCellValue("");
				}
			}
			jsr.setBqid(!"".equals(TPublicUtil.replaceBlank(row.getCell(0).getStringCellValue())) ? row.getCell(0).getStringCellValue() : null);
			//jsr.setBqid(!"".equals(row.getCell(0).getStringCellValue().trim()) ? row.getCell(0).getStringCellValue() : null);
			jsr.setSyr(!"".equals(row.getCell(1).getStringCellValue().trim()) ? row.getCell(1).getStringCellValue() : null);
			jsr.setSex(!"".equals(row.getCell(2).getStringCellValue().trim()) ? row.getCell(2).getStringCellValue() : null);
			jsr.setGj(!"".equals(row.getCell(3).getStringCellValue().trim()) ? row.getCell(3).getStringCellValue() : null);
			jsr.setSfzh(!"".equals(row.getCell(4).getStringCellValue().trim()) ? row.getCell(4).getStringCellValue() : null);
			jsr.setZjcx(!"".equals(row.getCell(5).getStringCellValue().trim()) ? row.getCell(5).getStringCellValue() : null);
			jsr.setDabh(!"".equals(row.getCell(6).getStringCellValue()) ? row.getCell(6).getStringCellValue() : null);
			//jsr.setId(!"".equals(row.getCell(7).getStringCellValue()) ? Integer.parseInt(row.getCell(7).getStringCellValue()) : null);
			jsr.setId(null);
			if (jsr.getId()!=null) {
				TJsrk id =dao.findByJsrId(jsr.getId());
				if (null!=id) {
					jsr.setId(null);
				}
			}
			jsr.setZt(!"".equals(row.getCell(8).getStringCellValue().trim()) ? row.getCell(8).getStringCellValue() : null);
			jsr.setFqsj(!"".equals(row.getCell(9).getStringCellValue().trim()) ? row.getCell(9).getStringCellValue().replace("/", "-") : null);
			jsr.setZxsj(!"".equals(row.getCell(10).getStringCellValue().trim()) ? row.getCell(10).getStringCellValue().replace("/", "-") : null);
			try {
				jsr.setFzrq(!"".equals(row.getCell(11).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(11).getStringCellValue().replace("/", "-")) : null);
				jsr.setDqsj(!"".equals(row.getCell(12).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(12).getStringCellValue().replace("/", "-")) : null);
			} catch (Exception e) {
				map.put("ERR", 1010);
			}
			jsr.setSsdw(!"".equals(row.getCell(13).getStringCellValue().trim()) ? row.getCell(13).getStringCellValue() : null);
			jsr.setJe(!"".equals(row.getCell(14).getStringCellValue().trim()) ? Integer.parseInt(row.getCell(14).getStringCellValue()) : null);
			//jsr.setJe(Integer.parseInt(row.getCell(14).getStringCellValue()));
			jsr.setNwb(!"".equals(row.getCell(15).getStringCellValue().trim()) ? row.getCell(15).getStringCellValue() : null);
			jsr.setKtxdd(!"".equals(row.getCell(16).getStringCellValue().trim()) ? row.getCell(16).getStringCellValue() : null);
			jsr.setCphm(!"".equals(row.getCell(17).getStringCellValue().trim()) ? row.getCell(17).getStringCellValue() : null);
			jsr.setLd(!"".equals(row.getCell(18).getStringCellValue().trim()) ? row.getCell(18).getStringCellValue() : null);
			jsr.setSctpdz(!"".equals(row.getCell(19).getStringCellValue().trim()) ? row.getCell(19).getStringCellValue() : null);
			jsr.setYxjzrq(!"".equals(row.getCell(20).getStringCellValue().trim()) ? row.getCell(20).getStringCellValue().replace("/", "-") : null);
			jsrks.add(jsr);
		}
		for (TJsrk tJsrk : jsrks) {
			try {
				TJsrk sfz = dao.findBysfzh(tJsrk.getSfzh());
				if (null == sfz) {
					dao.cshjsrsj(tJsrk);
					++cgsl;
				} else {
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					boolean s = false;
					//判断表格中的标签id是否为空
					boolean s1=null!=tJsrk.getBqid();
					if (s1) {
						//判断数据库中的 标签id是否为空
						boolean s2= !"".equals(sfz.getBqid()) & null!=sfz.getBqid();
						if (s2) {
							//判断数据库中的发签日期和表格中的发签日期大小
							if (null!=tJsrk.getFqsj() & !tJsrk.getFqsj().contains("")) {
								if (null!=sfz.getFqsj() & !sfz.getFqsj().contains("")) {
									if (sdf.parse(tJsrk.getFqsj()).compareTo(sdf.parse(sfz.getFqsj())) > 0) {
										s = true;
									}else {
										s = false;
									}
								}else {
									s = true;
								}
							}else {
								s = false;
							}
						}else {
							s=true;
						}
					}
					if (s) {
						dao.updetejsrsj(tJsrk);
						++gxsl;
					}else {
						++sbsl;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				++sbsl;
			}
		}
		map.put("YES", cgsl);
		map.put("GX", gxsl);
		map.put("NO", sbsl);
		return map;
	}
	// --------------------------------------------------------------------------------------
	//采集驾驶人数据
	public Map<String, Integer> cjjsrxx(Workbook workbook) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;//添加总数
		Integer gxsl = 0;//更新总数
		Integer sbsl = 0;//失败总数
		Sheet sheet = workbook.getSheetAt(0);
		int num = sheet.getLastRowNum();
		map.put("ERR", 0);
		List<TJsrk> jsrks = new ArrayList<TJsrk>();
		for (int i = 1; i <= num; i++) {
			TJsrk jsr = new TJsrk();
			Row row = sheet.getRow(i);
			int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
			for (int j = 0; j < columnNum; j++) {
				if (null!=row.getCell(j)) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				if (null==row.getCell(j)) {
					row.createCell(j).setCellValue("");
				}
			}
			jsr.setSyr(!"".equals(row.getCell(1).getStringCellValue().trim()) ? row.getCell(1).getStringCellValue() : null);
			jsr.setSex(!"".equals(row.getCell(2).getStringCellValue().trim()) ? row.getCell(2).getStringCellValue() : null);
			jsr.setSfzh(!"".equals(row.getCell(3).getStringCellValue().trim()) ? row.getCell(3).getStringCellValue() : null);
			jsr.setZjcx(!"".equals(row.getCell(4).getStringCellValue().trim()) ? row.getCell(4).getStringCellValue() : null);
			jsr.setDabh(!"".equals(row.getCell(5).getStringCellValue()) ? row.getCell(5).getStringCellValue() : null);
			try {
				jsr.setFzrq(!"".equals(row.getCell(6).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(6).getStringCellValue().replace("/", "-")) : null);
				jsr.setDqsj(!"".equals(row.getCell(7).getStringCellValue().trim()) ? Timestamp.valueOf(row.getCell(7).getStringCellValue().replace("/", "-")) : null);
			} catch (Exception e) {
				map.put("ERR", 1010);
			}
			jsr.setSsdw(!"".equals(row.getCell(8).getStringCellValue().trim()) ? row.getCell(8).getStringCellValue() : null);
			jsr.setKtxdd(!"".equals(row.getCell(9).getStringCellValue().trim()) ? row.getCell(9).getStringCellValue() : null);
			jsr.setCphm(!"".equals(row.getCell(10).getStringCellValue().trim()) ? row.getCell(10).getStringCellValue() : null);
			jsr.setLd(!"".equals(row.getCell(11).getStringCellValue().trim()) ? row.getCell(11).getStringCellValue() : null);
			jsr.setYxjzrq(!"".equals(row.getCell(12).getStringCellValue().trim()) ? row.getCell(12).getStringCellValue().replace("/", "-") : null);
			jsrks.add(jsr);	
		}
		for (TJsrk tJsrk : jsrks) {
			try {
				TJsrk sfz = dao.findBysfzh(tJsrk.getSfzh());
				if (null == sfz) {
					dao.cshjsrsj(tJsrk);
					++cgsl;
				} else {
					++sbsl;
				}
			} catch (Exception e) {
				e.printStackTrace();
				++sbsl;
			}
		}
		map.put("YES", cgsl);
		map.put("GX", gxsl);
		map.put("NO", sbsl);
		return map;
	}
	// --------------------------------------------------------------------------------------
	public Map<String, Integer> cshjdcbqk(String sj) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;
		Integer sbsl = 0;
		TPlbqk plbqk = new TPlbqk();
		TPlbqk isbqid = dao.finByjdcbqid(sj);
		if (null == isbqid) {
			plbqk.setBqid(sj);
			dao.cshjdcbqk(plbqk);
			map.put("YES", cgsl);
			++cgsl;
		} else {
			map.put("NO", sbsl);
			++sbsl;
		}
		return map;
	}
	// --------------------------------------------------------------------------------------
	public Map<String, Integer> cshjsrbqk(String sj) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer cgsl = 0;
		Integer sbsl = 0;
		TPlbqk plbqk = new TPlbqk();
		TPlbqk isbqid = dao.finByjsrbqid(sj);
		if (null == isbqid) {
			plbqk.setBqid(sj);
			dao.cshjsrbqk(plbqk);
			map.put("YES", cgsl);
			++cgsl;
		} else {
			map.put("NO", sbsl);
			++sbsl;
		}
		return map;
	}
	// --------------------------------------------------------------------------------------
	// 备份机动车数据
	public List<TAllmsg> bfjdcsj() {
		
		return dao.bfjdcsj();
	}
//原始方法 -- 暂时废弃
/*	public List<Map<String, Object>> bfjdcsj(String[] keys) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<TAllmsg> bfjdcsj = dao.bfjdcsj();
		TAllmsg plbq = new TAllmsg();
		Map<String, Object> mapValue1 = new HashMap<String, Object>();
		for (String value : keys) {
			mapValue1.put(value, "");
		}
		mapValue1.put("bqid", "");
		mapValue1.put("hphm", "");
		mapValue1.put("hpzl", "");
		mapValue1.put("clpp", "");
		mapValue1.put("clxh", "");
		mapValue1.put("syr", "");
		mapValue1.put("csys", "");
		mapValue1.put("fdjh", "");
		mapValue1.put("clsbdh", "");
		mapValue1.put("sfzmhm", "");
		mapValue1.put("ccdjrq", "");
		mapValue1.put("fprq", "");
		mapValue1.put("djry", "");
		mapValue1.put("lxdh", "");
		mapValue1.put("cllx", "");
		mapValue1.put("fqrq", "");
		mapValue1.put("xqrq", "");
		mapValue1.put("bqzt", "");
		mapValue1.put("id", "");
		mapValue1.put("mz", "");
		mapValue1.put("ssdw", "");
		mapValue1.put("je", "");
		mapValue1.put("nwb", "");
		mapValue1.put("ktxdd", "");
		mapValue1.put("ssks", "");
		mapValue1.put("yxjzrq", "");
		mapValue1.put("fcphm", "");
		mapValue1.put("ld", "");
		mapValue1.put("sctpdz", "");
		listmap.add(mapValue1);
		for (int i = 0; i < bfjdcsj.size(); i++) {
			Map<String, Object> mapValue = new HashMap<String, Object>();
			plbq = bfjdcsj.get(i);
			//mapValue.put("type", Integer.parseInt(plbq.getType()));
			mapValue.put("bqid", plbq.getBqid());
			mapValue.put("hphm", plbq.getHphm());
			mapValue.put("hpzl", plbq.getHpzl());
			mapValue.put("clpp", plbq.getClpp());
			mapValue.put("clxh", plbq.getClxh());
			mapValue.put("syr", plbq.getSyr());
			mapValue.put("csys", plbq.getCsys());
			mapValue.put("fdjh", plbq.getFdjh());
			mapValue.put("clsbdh", plbq.getClsbdh());
			mapValue.put("sfzmhm", plbq.getSfzmhm());
			if (null!=plbq.getCcdjrq()) {
				mapValue.put("ccdjrq", plbq.getCcdjrq());
			}else {
				mapValue.put("ccdjrq", "");
			}
			if (null!=plbq.getFprq()) {
				mapValue.put("fprq", plbq.getFprq());
			}else {
				mapValue.put("fprq", "");
			}
			mapValue.put("djry", plbq.getDjry());
			mapValue.put("lxdh", plbq.getLxdh());
			mapValue.put("cllx", plbq.getCllx());
			mapValue.put("fqrq", plbq.getFqrq());
			mapValue.put("xqrq", plbq.getXqrq());
			mapValue.put("bqzt", plbq.getBqzt());
			mapValue.put("id", plbq.getId());
			mapValue.put("mz", plbq.getMz());
			mapValue.put("ssdw", plbq.getSsdw());
			mapValue.put("je", plbq.getJe());
			mapValue.put("nwb", plbq.getNwb());
			mapValue.put("ktxdd", plbq.getKtxdd());
			mapValue.put("ssks", plbq.getSsks());
			mapValue.put("yxjzrq", plbq.getYxjzrq());
			mapValue.put("fcphm", plbq.getFcphm());
			mapValue.put("ld", plbq.getLd());
			mapValue.put("sctpdz", plbq.getSctpdz());
			listmap.add(mapValue);
		}
		return listmap;
	}*/
	public long bfjdcsjCount() {
		return dao.bfjdcsjCount();
	}
	//
	// 备份机动车数据采集表模板
	public List<Map<String, Object>> bfjdccjxxmb(String[] keys) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapValue1 = new HashMap<String, Object>();
		mapValue1.put("xh", "");
		mapValue1.put("hphm", "");
		mapValue1.put("hpzl", "");
		mapValue1.put("clpp", "");
		mapValue1.put("clxh", "");
		mapValue1.put("syr", "");
		mapValue1.put("csys", "");
		mapValue1.put("fdjh", "");
		mapValue1.put("clsbdh", "");
		mapValue1.put("ccdjrq", "");
		mapValue1.put("fprq", "");
		mapValue1.put("lxdh", "");
		mapValue1.put("cllx", "");
		mapValue1.put("ssdw", "");
		mapValue1.put("ktxdd", "");
		mapValue1.put("ssks", "");
		mapValue1.put("yxjzrq", "");
		mapValue1.put("fcphm", "");
		mapValue1.put("ld", "");
		listmap.add(mapValue1);
		return listmap;
	}
	//备份驾驶人数据
	public List<TJsrk> bfjsrsj() {
		List<TJsrk> bfjsrs=dao.bfjsrsj();
		return bfjsrs;
	}
	public long bfjsrsjCount() {
		return dao.bfjsrsjCount();
	}
	//
	// 备份机动车数据采集表模板
	public List<Map<String, Object>> bfjsrcjxxmb() {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapValue = new HashMap<String, Object>();
		mapValue.put("xh", "");
		mapValue.put("syr", "");
		mapValue.put("sex", "");
		mapValue.put("sfzh", "");
		mapValue.put("zjcx", "");
		mapValue.put("dabh", "");
		mapValue.put("fzrq", "");
		mapValue.put("dqsj", "");
		mapValue.put("ssdw", "");
		mapValue.put("ktxdd", "");
		mapValue.put("cphm", "");
		mapValue.put("ld", "");
		mapValue.put("yxjzrq", "");
		listmap.add(mapValue);
		return listmap;
	}
	//=====
	public List<String> bfjdcbq() {
		List<String> list = new ArrayList<String>();
		List<TPlbqk> allmsgs=dao.bfjdcbq();
		for (TPlbqk tPlbqk : allmsgs) {
			list.add(tPlbqk.getBqid());
		}
		return list;
	}
	public List<String> bfjsrbq() {
		List<String> list = new ArrayList<String>();
		List<TPlbqk> plbq=dao.bfjsrbq();
		for (TPlbqk tPlbqk : plbq) {
			list.add(tPlbqk.getBqid());
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
