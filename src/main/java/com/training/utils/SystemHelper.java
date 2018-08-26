package com.training.utils;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 读取配置文件
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class SystemHelper {
	/**
	 * 从当前系统的配置文件中获取配置信息
	 */
	public static final int MONEY = Integer.parseInt(ResourceBundle.getBundle(
			"data/bank").getString("money"));

	/**
	 * 获取当前类所在的路径
	 *
	 * @param directory
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static String getCurrentPath(final File directory, Class clazz) throws IOException {
		return directory.getCanonicalPath() + "/src/" + clazz.getPackage().getName().replace(".", "/");
	}
}
