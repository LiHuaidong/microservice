package hdli.sample;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:42 2018/6/19
 */
@Getter
@Setter
public class Outter {

	private String name;
	private int age;

	@Getter
	@Setter
	public static class Builder {
		private String name;
		private int age;

		public Builder(int age) {
			this.age = age;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAge(int age) {
			this.age = age;
			return this;
		}

		public Outter build() {
			return new Outter(this);
		}
	}

	public class Inner {
		private String name;
		private int age;

		public Inner(String name) {
			this.name = name;
		}
	}

	private Outter(Builder b) {
		this.age = b.age;
		this.name = b.name;
	}

	public static void main(String... args) {

	}

}
