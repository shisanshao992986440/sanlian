package com.robot.sanlian.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimaryGenerater {
	private static String SERIAL_NUMBER = "001";
	private static PrimaryGenerater primaryGenerater = null;

	private PrimaryGenerater() {

	}

	/**
	 * 取得PrimaryGenerater的单例实现
	 * 
	 * @return
	 */
	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	/**
	 * 生成 日期+随机数的流水号
	 * */
	public String getNumberForPK() {
		String id = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String temp = sf.format(new Date());
		int random = (int) (Math.random() * 10000);
		id = temp + random;
		return id;
	}

	/**
	 * 生成下一个编号
	 */
	public synchronized static String generaterNextNumber(String sno) {
		String id = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		if (sno == null) {
			id = formatter.format(date) + "001";
		} else {
			int count = SERIAL_NUMBER.length();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				sb.append("0");
			}
			DecimalFormat df = new DecimalFormat("000");
			id = formatter.format(date)
					+ df.format(1 + Integer.parseInt(sno.substring(9, 12)));
		}
		return id;
	}

	public static void main(String[] args) {
		String sno = "DDD170980001";
		System.out.println(PrimaryGenerater.generaterNextNumber(sno));
	}
}
