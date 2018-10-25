package com.fwzs.system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;

/**
 * @Author Tyler Yin
 * @create 2018-01-02 13:56
 * @description thread generate code method
 **/
public class TestGenMethond {
	public static void main(String args[]) {
//		insertGeneratedCodes(200000000, "1");
//		System.out.println("2��21λcode�������!");
//
//		insertGeneratedCodes(500000000, "2");
//		System.out.println("5��21λcode�������!");
//
//		insertGeneratedCodes(200000000, "3");
//		System.out.println("2��20λcode�������!");
//
//		insertGeneratedCodes(500000000, "4");
//		System.out.println("5��20λcode�������!");
		
		//generateCodes1();
		countUniqueCodes();
	}

	public static void insertGeneratedCodes(int count, String type) {
		String url = "jdbc:mysql://localhost:3306/Codes?characterEncoding=utf8&useSSL=true";
		String user = "root";
		String pwd = "123456";

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			if ("1".equals(type)) {
				stmt = conn
						.prepareStatement("insert into twoBillionCodes21(id,code) values (?,?)");
			} else if ("2".equals(type)) {
				stmt = conn
						.prepareStatement("insert into fiveBillionCodes21(id,code) values (?,?)");
			} else if ("3".equals(type)) {
				stmt = conn
						.prepareStatement("insert into twoBillionCodes20(id,code) values (?,?)");
			} else {
				stmt = conn
						.prepareStatement("insert into fiveBillionCodes20(id,code) values (?,?)");
			}
			conn.setAutoCommit(false);
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

		int batchSize = 20000;
		long start = System.currentTimeMillis();

		if ("1".equals(type) || "2".equals(type)) {
			for (int i = 1; i <= count; i++) {
				try {
					stmt.setInt(1, i);
					stmt.setString(2, genRadom21());
					stmt.addBatch();
					if (i % batchSize == 0) {
						stmt.executeBatch();
						conn.commit();
					}
				} catch (Exception ex) {
					// ex.printStackTrace();
				}
			}
		} else {
			for (int i = 1; i <= count; i++) {
				try {
					stmt.setInt(1, i);
					stmt.setString(2, genRadom20());
					stmt.addBatch();
					if (i % batchSize == 0) {
						stmt.executeBatch();
						conn.commit();
					}
				} catch (Exception ex) {
					// ex.printStackTrace();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println((end - start) / 60000);
	}

	private static String genRadom20() {
		String nanoTime = String.valueOf(System.nanoTime());
		String time = String.valueOf(new Date().getTime());
		return nanoTime.substring(nanoTime.length() - 5)
				+ time.substring(time.length() - 9) + getFixLenthString(6);
	}

	private static String genRadom21() {
		String nanoTime = String.valueOf(System.nanoTime());
		String time = String.valueOf(new Date().getTime());
		return nanoTime.substring(nanoTime.length() - 6)
				+ time.substring(time.length() - 9) + getFixLenthString(6);
	}

	private static String getFixLenthString(int strLength) {
		Random rm = new Random();
		// ��������
		long pross = (long) ((1 + rm.nextDouble()) * Math.pow(10, strLength));
		// ����õĻ�������ת��Ϊ�ַ���
		String fixLenthString = String.valueOf(pross);
		// ���ع̶��ĳ��ȵ������
		return fixLenthString.substring(1, strLength + 1);
	}
	
	public static void generateCodes1() {
		FileWriter fw;
		try {
			fw = new FileWriter("E:\\TwoBillionCodes.txt");
			try (BufferedWriter bufw = new BufferedWriter(fw)) {
				for (int j = 0; j < 10000000; j++) {
					bufw.write(genRadom21());
					bufw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void generateCodes() {
		File file = new File("E:\\TwoBillionCodes.txt");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			Collection<String> ids = new ArrayList<String>();
			for (int j = 0; j < 10000; j++) {
				ids.add(genRadom21());
			}

			try {
				FileUtils.writeLines(file, ids, true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		long end = System.currentTimeMillis();
		System.out.println((end - start) / 60000);
	}

	public static void countUniqueCodes() {
//		long uniqueCodes = 0;
//		try (Stream<String> lines = Files.lines(
//				Paths.get("E:\\TwoBillionCodes10000000.txt"), Charset.defaultCharset())) {
//			uniqueCodes = lines.distinct().count();
//			System.out.println(uniqueCodes);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
	}
}
