import java.lang.instrument.Instrumentation;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:02 2019/1/27
 */
public class ObjectShallowSize {

	public static Instrumentation inst;

	public void premain(String agentArgs, Instrumentation instP) {
		inst = instP;
	}

	public static long sizeOf(Object object) {
		return inst.getObjectSize(object);
	}

}
