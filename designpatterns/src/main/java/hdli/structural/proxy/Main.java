package hdli.structural.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:42 2019/1/17
 */
public class Main {
	public static void main(String[] args) {
		Car car = new Car();
		VehicalInvocationHandler handler = new VehicalInvocationHandler(car);
		Vehical bmw = (Vehical) Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), handler);
		bmw.start();
	}
}
