package hdli.adapter;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:13 2019/12/3
 */
public class Traveller {

	private MutiFunctionAdapter myAdapter = new MutiFunctionAdapter();

	private String currentCountry;

	public Traveller() {
	}

	public Traveller(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public MutiFunctionAdapter getMyAdapter() {
		return myAdapter;
	}

	public void setMyAdapter(MutiFunctionAdapter myAdapter) {
		this.myAdapter = myAdapter;
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	private void charge() {
		myAdapter.charge(currentCountry);
	}

	public static void main(String[] args) {
		Traveller lina = new Traveller();
		lina.setCurrentCountry("America");
		lina.charge();
	}
}
