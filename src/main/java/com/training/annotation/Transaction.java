package com.training.annotation;

import com.util.SystemUtils;

import java.lang.reflect.Method;

/**
 * 注解测试类
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/

//银行最大转账金额5000
public class Transaction {
	// name1向name2转账money元---使用配置文件完成
	public void transaction1(String name1, String name2, int money) {
		if (money > SystemUtils.MONEY) {
			throw new RuntimeException("最大转账金额为:" + SystemUtils.MONEY + "元");
		}
		System.out.println(name1 + "向" + name2 + "转账:" + money + "元");
	}

	// name1向name2转账money元--使用注解
	@BankInfo(maxMoney = 10000)
	public void transaction2(String name1, String name2, int money)
			throws NoSuchMethodException, SecurityException {
		// 1.获取当前方法的Method对象。
		// 1.1 获取当前类的Class对象
		Class clazz = this.getClass();
		// 1.2 获取当前方法的Method对象.
		Method method = clazz.getDeclaredMethod("transaction2", String.class,
				String.class, int.class);

		// 判断当前方法上是否有BankInfo这个注解.
		boolean flag = method.isAnnotationPresent(BankInfo.class);
		if (flag) {
			// 2.在Method类中有一个 getAnnotation(Class annotationClass)，可以获取一个注解对象.
			BankInfo bif = method.getAnnotation(BankInfo.class);

			// 3.通过注解对象来调用其属性.
			int maxMoney = bif.maxMoney();
			if (money > maxMoney) {
				throw new RuntimeException("最大转账金额为:" + maxMoney + "元");
			}
			System.out.println(name1 + "向" + name2 + "转账:" + money + "元");
		}
	}
}
