public class Event {
	String message;
	int money;
	String moneyMessage;
	int population;
	String populationMessage;
	
	public Event(String message,int money,int population) {
		this.message = message;
		this.money = money;
		this.population = population;
		
		if (money > 0) {
			this.moneyMessage = "+"+this.money+"G";
		}else if (money < 0) {
			this.moneyMessage = this.money+"G";
		}else if (money == 0) {
			this.moneyMessage = "変動なし";
		}
		
		if (population > 0) {
			this.populationMessage = "+"+this.population+"人";
		}else if (population < 0) {
			this.populationMessage = this.population+"人";
		}else if (population ==0) {
			this.populationMessage = "変動なし";
		}
	}

}
