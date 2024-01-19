
public class City {
	String name;
	String playerName;
	int population;
	int money;
	double taxRate;
	
	public City(String name,String playerName) {
		this.name = name;
		this.playerName = playerName;
		this.population = 10000;
		this.money = 10000;
		this.taxRate = 0.05;
		
	}
	
	public void popurationGrowth(int popuration) {
		double growth = popuration*(1-this.taxRate);
		this.population += growth;
		System.out.println("人口が"+(int)growth+"人増えました");
	}
	
	public void popurationDecline(int popuration) {
		double decline = popuration*(1+this.taxRate);
		this.population -= decline;
		System.out.println("人口が"+(int)decline+"人減りました");
	}
	
	public void incomeMoney(int money) {
		double income = money*(1+this.taxRate);
		this.money += income;
		System.out.println("資金が"+(int)income+"G増えました");
	}
	
	public void spendMoney(int money) {
		double spend = money*(1-this.taxRate);
		this.money -= spend;
		System.out.println("資金が"+(int)spend+"G減りました");
	}

}
