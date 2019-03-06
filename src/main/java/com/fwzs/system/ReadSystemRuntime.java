package com.fwzs.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 */
public class ReadSystemRuntime {
	public static void main(String args[]) throws IOException,
			InterruptedException {
		System.out.println("网卡物理地址：" + getMACAddress());
		System.out.println("CPU序列号：" + getCPUSN());
		System.out.println("主板序列号：" + getMotherboardSN());
	}

	public static String getMACAddress() {
		String address = "";
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream(),  "gb2312"));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0
							|| line.indexOf("物理地址") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					}
				}
				br.close();
				return address.trim();
			} catch (IOException e) {
			}
		}
		return address;
	}

	// 获取CPU序列号
	private static String getCPUSN() throws IOException {
		Process process = Runtime.getRuntime().exec(
				new String[] { "wmic", "cpu", "get", "ProcessorId" });
		process.getOutputStream().close();
		try (Scanner sc = new Scanner(process.getInputStream())) {
			String property = sc.next();
			String serial = sc.next();
			return property + ": " + serial;
		}
	}

	// 获取主板序列号
	public static String getMotherboardSN() {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new FileWriter(file);
			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n"
					+ "   (\"Select * from Win32_BaseBoard\") \n"
					+ "For Each objItem in colItems \n"
					+ "    Wscript.Echo objItem.SerialNumber \n"
					+ "    exit for  ' do the first cpu only! \n" + "Next \n";
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}
}
