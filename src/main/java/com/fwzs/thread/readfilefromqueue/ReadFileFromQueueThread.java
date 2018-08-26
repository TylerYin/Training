package com.fwzs.thread.readfilefromqueue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReadFileFromQueueThread implements Runnable {
	private BufferedWriter bfw;
	private ConcurrentLinkedQueue<String> queue;

	public ReadFileFromQueueThread() {
	}

	public ReadFileFromQueueThread(BufferedWriter bfw,
			ConcurrentLinkedQueue<String> queue) {
		this.bfw = bfw;
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized (queue) {
			while (!queue.isEmpty()) {
				try {
					bfw.newLine();
					bfw.write(queue.poll());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				bfw.flush();
				bfw.close();
			} catch (IOException e) {}
		}
	}
}
