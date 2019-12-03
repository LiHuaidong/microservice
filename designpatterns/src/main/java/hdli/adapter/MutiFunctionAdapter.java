package hdli.adapter;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:01 2019/12/3
 */
public class MutiFunctionAdapter implements Adapter {

	Socket socket;

	@Override
	public void charge(String country) {
		if("America".equals(country)) {
			socket = new America();
			socket.chargeWithTwoCyclicFoot();
		} else if("Hongkong".equals(country)) {
			socket = new Hongkong();
			socket.chargeWithThreeFoot();
		} else if("Mainland".equals(country)) {
			socket = new Mainland();
			socket.chargeWithTwoFoot();
		}
	}
}
