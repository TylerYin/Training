package com.fwzs.system;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IdentifierUtilsTest {
	private static Long curIndex = 0L;
	private static final String STR_FORMAT = "000000";

	public static void main(String args[]) throws Exception {
		for(int i=0;i<=11;i++){
			generate1("C:\\Users\\Administrator\\Desktop\\codes\\ds_"+i, 50000);
		}
		// generateCodes(28, 20000001);
	}

	public static void generateCodes(Integer longth, Integer counts)
			throws Exception {
		// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String dqgy = "81a48b2488494a869901da3ded325d46";
		//
		// List<String> prodIds = new ArrayList<>();
		// prodIds.add("bf8b7803923b4591bb136ac9ce289a3b");
		// prodIds.add("bf8b7803923b4591bb136ac9ce289a3b");
		// prodIds.add("bf8b7803923b4591bb136ac9ce289a3b");
		// prodIds.add("ec682bc53f554bd8804d01100d4f8447");
		// prodIds.add("ec682bc53f554bd8804d01100d4f8447");
		//
		// List<String> planIds = new ArrayList<>();
		// planIds.add("547a355a50ca430fb13c188160776373");
		// planIds.add("aa799460d0e74c9bbea63868683e0f55");
		// planIds.add("c2d3754422e2479093bb2f9fa9a952b4");
		// planIds.add("b422c2bcd972478a848e3c8dacd43304");
		// planIds.add("ccf4c97d2c414b80867cdb4f66c7bd6c");
		//
		// List<String> specs = new ArrayList<>();
		// specs.add("112345615g");
		// specs.add("112345615g");
		// specs.add("112345615g");
		// specs.add("2654321210g");
		// specs.add("2654321210g");

		long start = System.currentTimeMillis();
		
		//generate1(counts);
		
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000);
	}

	private static String generateId() {
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		DateFormat dateF = new SimpleDateFormat("yyMMddHHmmss");
		if (curIndex >= 999999) {
			curIndex = 0L;
		}
		return dateF.format(new Date()) + df.format(curIndex++);
	}

	private static void generate1(String fileName, Integer counts) throws Exception {
		final Collection<String> ids = new ArrayList<String>();
		File file = new File(fileName + ".txt");
		for (int i = 0; i < counts; i++) {
			ids.add(IdGen.genRadom13());
		}

		try {
			FileUtils.writeLines(file, ids, true);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void generate2(Integer longth, Integer counts) throws Exception {
		StringBuffer bf = new StringBuffer("");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		final String dateScript = sdf.format(new Date());
		final String separator = System.getProperty("line.separator");
		for (int i = 0; i < counts; i++) {
			bf.append(IdGen.genRadom13() + separator);
			if (i % 5000 == 0) {
				FileUtils.write(new File("E:\\" + longth + "_" + counts + "_" + dateScript + ".txt"), bf.toString(), "utf-8", true);
				bf.setLength(0);
			}
		}
		FileUtils.write(new File("E:\\" + longth + "_" + counts + "_" + dateScript + ".txt"), bf.toString(), "utf-8", true);
	}
}