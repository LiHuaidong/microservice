package hdli.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:18 2018/10/30
 */
public class CopyFile {

	public static void main(String... args) {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputStream = new FileInputStream("E:\\study-cloud\\microservice\\io\\io.iml");
			outputStream = new FileOutputStream("E:\\study-cloud\\microservice\\io\\io_copy.iml");

			inputChannel = inputStream.getChannel();
			outputChannel = outputStream.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) {
				int eof = inputChannel.read(buffer);
				if (eof == -1) {
					break;
				}

				buffer.flip();
				outputChannel.write(buffer);
				buffer.clear();
				Thread.sleep(1000*1000);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputChannel != null) {
					outputChannel.close();
				}
				if (inputChannel != null) {
					inputChannel.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {

					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
