package hdli.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:37 2019/1/17
 */
public class VehicalInvocationHandler implements InvocationHandler {

	private Vehical vehical;

	public VehicalInvocationHandler(Vehical vehical) {
		this.vehical = vehical;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("starting bmw");
		String startMsg = (String) method.invoke(vehical, args);
		System.out.println(startMsg);
		System.out.println("bmw is started success");
		return null;
	}
}
