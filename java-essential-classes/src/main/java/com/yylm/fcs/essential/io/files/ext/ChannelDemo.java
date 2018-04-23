package com.yylm.fcs.essential.io.files.ext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 如下是一个使用通道的例子，展示了两个通道之间拷贝数据的过程
 * 
 * @see https://www.cnblogs.com/chenpi/p/6481271.html
 *
 */
public class ChannelDemo {
	public static void main(String[] args) throws IOException {
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);
		channelCopy1(source, dest);
		// channelCopy2 (source, dest);
		source.close();
		dest.close();
	}

	public static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			// 切换为读状态
			buffer.flip();
			// 不能保证全部写入
			dest.write(buffer);
			// 释放已读数据的空间，等待数据写入
			buffer.compact();
		}
		// 退出循环的时候，由于调用的是compact方法，缓冲区中可能还有数据
		// 需要进一步读取
		buffer.flip();
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	public static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (src.read(buffer) != -1) {
			// 切换为读状态
			buffer.flip();
			// 保证缓冲区的数据全部写入
			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			// 清除缓冲区
			buffer.clear();
		}
		// 退出循环的时候，由于调用的是clear方法，缓冲区中已经没有数据，不需要进一步处理
	}

}
