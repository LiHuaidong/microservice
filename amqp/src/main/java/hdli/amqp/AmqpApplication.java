package hdli.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class AmqpApplication {

	public static void main(String[] args) {

		int MAXIMUM_CAPACITY = 1 << 30;
//		System.out.println("MAXIMUM_CAPACITY = [" + MAXIMUM_CAPACITY + "]");
//		System.out.println("MAX_VALUE = [" + Integer.MAX_VALUE + "]");
		System.out.println("tableSize = [" + tableSizeFor(1) + "]");
		System.out.println("tableSize = [" + tableSizeFor(2) + "]");
		System.out.println("tableSize = [" + tableSizeFor(3) + "]");
		System.out.println("tableSize = [" + tableSizeFor(4) + "]");
		System.out.println("tableSize = [" + tableSizeFor(5) + "]");
		System.out.println("tableSize = [" + tableSizeFor(9) + "]");
		System.out.println("tableSize = [" + tableSizeFor(15) + "]");
		System.out.println("tableSize = [" + tableSizeFor(16) + "]");
		System.out.println("tableSize = [" + tableSizeFor(31) + "]");
		System.out.println("tableSize = [" + tableSizeFor(32) + "]");
		System.out.println("tableSize = [" + tableSizeFor(33) + "]");
		System.out.println("tableSize = [" + tableSizeFor(33) + "]");

//		SpringApplication.run(AmqpApplication.class, args);
	}

	private static final int tableSizeFor(int c) {
		int MAXIMUM_CAPACITY = 1 << 30;
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

}
