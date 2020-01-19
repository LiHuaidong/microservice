package hdli.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 9:38 2020/1/17
 */
public class CopyFileChannelToChannel {

	public static void copyByChannelToChannel(String source, String dest) throws IOException {
		File sourceFile = new File(source);
		if(!sourceFile.exists()) {
			return;
		}

		RandomAccessFile randomReadFile = new RandomAccessFile(sourceFile, "r");
		FileChannel sourceChannel = randomReadFile.getChannel();
		File destFile = new File(dest);
		if(!destFile.isFile()) {
			if(!destFile.createNewFile()) {
				sourceChannel.close();
				randomReadFile.close();
				return;
			}
		}

		RandomAccessFile randomWriteFile = new RandomAccessFile(destFile, "rw");
		FileChannel destChannel = randomWriteFile.getChannel();

		long leftSize = sourceChannel.size();
		long position = 0;
		while(leftSize > 0) {
			long writeSize = sourceChannel.transferTo(position, leftSize, destChannel);
			position += writeSize;
			leftSize -= writeSize;
		}

		sourceChannel.close();
		randomReadFile.close();
		destChannel.close();
		randomWriteFile.close();
	}

}
