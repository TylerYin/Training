package com.fwzs.thread.readqrcode.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 读取QrCode文件工具类
 * 
 * @author Tyler
 * 
 */
public class ReadQrCodeUtils {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		boolean isSaveInList = false;
		List<String> list;
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("data/495_500001_20180530092025.txt").getPath());
		try {
			// list = readFileBySingleThread(file, isSaveInList);
			list = readFileByMultiplyThread(file, 7, 18, isSaveInList);
			// list = readZipFileBySingleThread(file, isSaveInList);

			long end = System.currentTimeMillis();
			System.out.println("用时" + (end - start) / 1000 + "秒");
			if (isSaveInList) {
				System.out.println("List大小" + list.size());
				System.out.println(list);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 多线程读取码文件
	 * 
	 * @param file
	 * @param threadNumber
	 * @param rowSize
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<String> readFileByMultiplyThread(File file,
			int threadNumber, int rowSize, boolean isSaveInList)
			throws IOException, Exception {
		checkFile(file, "txt");
		checkThreadNumber(threadNumber);

//		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 3, 60,
//				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

		List<String> list = new ArrayList<>();
		CountDownLatch doneSignal = new CountDownLatch(threadNumber);
		RandomAccessFile[] rafArr = new RandomAccessFile[threadNumber];
		try {
			long length = file.length() / (rowSize + 2);
			long numPerThred = length / threadNumber;
			long left = length % threadNumber;
			for (int i = 0; i < threadNumber; i++) {
				rafArr[i] = new RandomAccessFile(file, "r");
				if (i == threadNumber - 1) {
					threadPool.execute(new ReadThread(i * numPerThred + 1,
							(i + 1) * numPerThred + left, rowSize, rafArr[i],
							list, isSaveInList, doneSignal));
				} else if (i == 0) {
					threadPool
							.execute(new ReadThread(0, (i + 1) * numPerThred,
									rowSize, rafArr[i], list, isSaveInList,
									doneSignal));
				} else {
					threadPool.execute(new ReadThread(i * numPerThred + 1,
							(i + 1) * numPerThred, rowSize, rafArr[i], list,
							isSaveInList, doneSignal));
				}

				// if (i == threadNumber - 1) {
				// new ReadThread(i * numPerThred + 1, (i + 1) * numPerThred
				// + left, rowSize, rafArr[i], list, isSaveInList,
				// doneSignal).start();
				// } else if (i == 0) {
				// new ReadThread(0, (i + 1) * numPerThred, rowSize,
				// rafArr[i], list, isSaveInList, doneSignal).start();
				// } else {
				// new ReadThread(i * numPerThred + 1, (i + 1) * numPerThred,
				// rowSize, rafArr[i], list, isSaveInList, doneSignal).start();
				// }
			}
			threadPool.shutdown();
		} catch (Exception e) {
			throw e;
		}

		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			throw e;
		}
		return list;
	}

	/**
	 * 单线程读取码文件
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readFileBySingleThread(File file,
			boolean isSaveInList) throws FileNotFoundException, IOException {
		checkFile(file, "txt");

		List<String> list = new ArrayList<>();
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"))) {
			String line = null;
			if (isSaveInList) {
				while ((line = bf.readLine()) != null) {
					list.add(line);
				}
			} else {
				while ((line = bf.readLine()) != null) {
					System.out.println(line);
				}
			}
		}
		return list;
	}

	/**
	 * 单线程读取zip文件
	 * 
	 * @param file
	 * @throws IOException
	 * @throws ZipException
	 */
	public static List<String> readZipFileBySingleThread(File file,
			boolean isSaveInList) throws ZipException, IOException {
		checkFile(file, "zip");

		List<String> list = new ArrayList<>();
		try (ZipFile zf = new ZipFile(file)) {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			try (ZipInputStream zin = new ZipInputStream(in)) {
				ZipEntry ze;
				if (isSaveInList) {
					while ((ze = zin.getNextEntry()) != null) {
						if (ze.getSize() > 0) {

							String line;
							BufferedReader br = new BufferedReader(
									new InputStreamReader(zf.getInputStream(ze)));
							while ((line = br.readLine()) != null) {
								list.add(line);
							}
							br.close();
						}
					}
				} else {
					while ((ze = zin.getNextEntry()) != null) {
						if (ze.getSize() > 0) {
							String line;
							BufferedReader br = new BufferedReader(
									new InputStreamReader(zf.getInputStream(ze)));
							while ((line = br.readLine()) != null) {
								System.out.println(line);
							}
							br.close();
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获取文件类型
	 * 
	 * @param file
	 * @return
	 */
	private static String getFileType(File file) {
		String fileName = file.getName();
		String fileTyle = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		return fileTyle;
	}

	/**
	 * 检查文件是否存在和文件类型是否匹配
	 * 
	 * @param file
	 * @param extension
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void checkFile(File file, String extension)
			throws FileNotFoundException, IOException {
		if (null == file || file.length() == 0) {
			throw new FileNotFoundException("文件不存在");
		} else if (!getFileType(file).equals(extension)) {
			throw new IOException("文件格式不正确");
		}
	}

	/**
	 * 检查线程参数是否合法
	 * 
	 * @param threadNumber
	 * @throws Exception
	 */
	private static void checkThreadNumber(int threadNumber) throws Exception {
		if (threadNumber < 2) {
			throw new Exception("线程参数不合法");
		}
	}
}

class ReadThread extends Thread {
	private long end;
	private long start;
	private int rowSize;

	private List<String> list;
	private boolean isSaveInList;
	private RandomAccessFile raf;
	private CountDownLatch doneSignal;

	public ReadThread(long start, long end, int rowSize, RandomAccessFile raf,
			List<String> list, boolean isSaveInList, CountDownLatch doneSignal) {
		this.start = start;
		this.end = end;
		this.rowSize = rowSize;
		this.raf = raf;
		this.list = list;
		this.isSaveInList = isSaveInList;
		this.doneSignal = doneSignal;
	}

	public void run() {
		if (isSaveInList) {
			synchronized (list) {
				runThread();
			}
		} else {
			runThread();
		}
	}

	private void runThread() {
		long startIndex = 0;
		if (start > 0) {
			startIndex = (start - 1) * 2 + (start - 1) * rowSize;
		}

		long totalCount = end - start;
		if (start > 0) {
			totalCount++;
		}

		try {
			raf.seek(startIndex);
			if (isSaveInList) {
				for (int i = 0; i < totalCount; i++) {
					list.add(raf.readLine());
				}
			} else {
				for (int i = 0; i < totalCount; i++) {
					System.out.println(this.getName() + ":" + raf.readLine());
				}
			}
			doneSignal.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
