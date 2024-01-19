import java.util.ArrayList;

public class SimApp {

	public static void main(String[] args) {
		System.out.println("***街づくりゲーム***");
		System.out.println("総人口20万人を目指しましょう！");
		System.out.println("");
		System.out.print("あなたの名前を入力してください >> ");
		String playerName = new java.util.Scanner(System.in).nextLine();
		if (playerName.equals("")) {
			playerName = "ゆりこ";
		}
		System.out.print("街の名前を決めてください >> ");
		String cityName = new java.util.Scanner(System.in).nextLine();
		if (cityName.equals("")) {
			cityName = "とうきょう";
		}
		City myCity = new City(cityName, playerName);
		System.out.println("");

		while (true) {
			if (myCity.money <= 0) {
				System.out.println(playerName + "市長！" + cityName + "には使える資金がもうありません！");
				gameOver(myCity);
				return;
			} else if (myCity.population <= 0) {
				System.out.println(playerName + "市長！市民が全員居なくなってしまいました！");
				gameOver(myCity);
				return;
			} else if (myCity.population >= 200000) {
				gameClear(myCity);
				return;
			}

			System.out.println(playerName + "市長！何をしますか？");
			System.out.print("1.建築 / 2.イベント / 3.税率変更 /4.終了 >> ");
			int select = new java.util.Scanner(System.in).nextInt();
			System.out.println("");
			switch (select) {
			case 1:
				build(myCity);
				System.out.println("");
				break;
			case 2:
				event(myCity);
				System.out.println("");
				break;
			case 3:
				taxRate(myCity);
				System.out.println("");
				break;
			case 4:
				gameOver(myCity);
				return;
			}

		}

	}

	public static void build(City myCity) {
		System.out.println("何を建築しますか？ ");
		System.out.print("1.住宅 / 2.商業施設 / 3.工場 / 4.学校 / 5.公園 >> ");
		int select = new java.util.Scanner(System.in).nextInt();
		System.out.println();
		switch (select) {
		case 1:
			System.out.println("資金を投入して、新たな住宅地を作りました");
			myCity.spendMoney(5000);
			System.out.println("綺麗な家が完成して、新しい市民が引っ越してきました！");
			myCity.popurationGrowth(7500);
			myCity.housing = true;
			break;
		case 2:
			System.out.println("商店街を取り壊し、新たな商業施設を建設しました");
			myCity.popurationDecline(5000);
			System.out.println("商業が活発になり、臨時収入がありました！");
			myCity.incomeMoney(7500);
			myCity.commercial = true;
			break;
		case 3:
			System.out.println("工業が活発になり、臨時収入がありました！");
			myCity.incomeMoney(20000);
			System.out.println("環境汚染を気にした市民が出て行ってしまいました……");
			myCity.popurationDecline(10000);
			myCity.industry = true;
			break;
		case 4:
			System.out.println("資金を投入して、学校を建てました");
			myCity.spendMoney(10000);
			System.out.println("子育て中の家族が新しく引っ越してきました！");
			myCity.popurationGrowth(20000);
			myCity.school = true;
			break;
		case 5:
			System.out.println("自然豊かな公園を建てました！");
			System.out.println("市民の心身が健康になり、街が少し発展しました！");
			myCity.incomeMoney(500);
			myCity.popurationGrowth(500);
			myCity.park = true;
			break;
		}
		displayTown(myCity);
	}

	public static void gameOver(City mycity) {
		System.out.println("GAME OVER...");
		System.out.println("最終資金：" + mycity.money + "G");
		System.out.println("最終人口：" + mycity.population + "人");
	}

	public static void gameClear(City mycity) {
		System.out.println("GAME CLEAR!!!");
		System.out.println("最終資金：" + mycity.money + "G");
		System.out.println("最終人口：" + mycity.population + "人");
	}

	public static void event(City mycity) {
		ArrayList<Event> events = new ArrayList<Event>();

		// イベントリスト
		Event Remains = new Event("市内で歴史的な遺跡が発見！学者や観光客で賑わう", 10000, 0);
		events.add(Remains);
		Event Idols = new Event("ご当地アイドル「" + mycity.name + "娘」が全国的に大人気！", 5000, 5000);
		events.add(Idols);
		Event Anime = new Event(mycity.name + "を舞台にしたアニメが大人気！アニメファンの聖地となる", 5000, 5000);
		events.add(Anime);
		Event Ufo1 = new Event("UFO襲来！宇宙人の攻撃により街が壊滅状態になる", -100000, -50000);
		events.add(Ufo1);
		Event Dam = new Event("集落を取り壊しダムを建設", 15000, -5000);
		events.add(Dam);
		Event Tonde1 = new Event("ダサい街ランキング1位に選ばれる……", 0, -10000);
		events.add(Tonde1);
		Event Typhoon = new Event("大型の台風が街を直撃し大きな被害を受ける……", -10000, -10000);
		events.add(Typhoon);
		Event Marriage = new Event("地元の神社に縁結びのご利益があると話題に！", 0, 10000);
		events.add(Marriage);
		Event Virus1 = new Event("新型の感染症が流行し、対応に追われる", -30000, -30000);
		events.add(Virus1);
		Event Squid1 = new Event("駅前にイカのオブジェを設置するも市民から絶不評……", -10000, -5000);
		events.add(Squid1);
		// 住宅地を建設したことがある場合のみ発生
		if (mycity.housing == true) {
			Event Ufo2 = new Event("UFO襲来！街を気に入った宇宙人が故郷の仲間を連れてきた", 50000, 25000);
			events.add(Ufo2);
			Event Ranking = new Event("住みやすい街ランキング1位に選ばれる！", 0, 10000);
			events.add(Ranking);
		}
		// 商業施設を建設したことがある場合のみ発生
		if (mycity.commercial == true) {
			Event Tonde2 = new Event("ダサい街ランキング1位に選ばれるも、それを逆手にとった映画\n「飛んで " + mycity.name + "」が大ヒット！", 30000, 10000);
			events.add(Tonde2);
		}
		// 工場を建設したことがある場合のみ発生
		if (mycity.industry == true) {
			Event Virus2 = new Event("新型の感染症が流行し、対応に追われるも\n通称「" + mycity.playerName + "ノマスク」を配布し、感染拡大を最小限に食い止める",
					-30000, 0);
			events.add(Virus2);
		}
		// 学校を建設したことがある場合のみ発生
		if (mycity.school == true) {
			Event SchoolPool = new Event("学校職員がプールの水を止め忘れて大損失……", -15000, 0);
			events.add(SchoolPool);
			Event Koshien = new Event("地元の高校が甲子園に出場！名門校として全国から生徒が集まる", 0, 15000);
			events.add(Koshien);
			Event Abroad = new Event("海外の姉妹都市から留学生を受け入れる", 0, 10000);
			events.add(Abroad);
		}
		// 公園を建設したことがある場合のみ発生
		if (mycity.park == true) {
			Event Squid2 = new Event("駅前に以下のオブジェを設置するも市民から不評……\nしかしSNSでは大バズリし、観光スポットに！", 20000, -5000);
			events.add(Squid2);
			Event Marathon = new Event(mycity.name + "出身のマラソン選手が金メダルを獲得！\n凱旋パレードにたくさんの人が集まる", 20000, 20000);
			events.add(Marathon);
		}
		// 学校を建設したことがあり、かつ公園も建設をしたことがある場合のみ発生
		if (mycity.school == true && mycity.park == true) {
			Event Olympic = new Event("20XX年のオリンピック開催地に選ばれる！！！", 200000, 200000);
			events.add(Olympic);
		}

		// イベント実行
		int dice = new java.util.Random().nextInt(events.size());
		System.out.println(events.get(dice).message);
		System.out.println("資金 " + events.get(dice).moneyMessage);
		System.out.println("人口 " + events.get(dice).populationMessage);
		mycity.money += events.get(dice).money;
		mycity.population += events.get(dice).population;
		displayTown(mycity);

	}

	public static void taxRate(City mycity) {
		System.out.println("現在の税率：" + (int) (mycity.taxRate * 100) + "％");
		System.out.print("新しい税率を入力してください（％） >> ");
		double newRate = new java.util.Scanner(System.in).nextInt();
		if (newRate > 100 || newRate < 1) {
			System.out.println("税率は1～100％の間で設定してください");
			return;
		}
		System.out.println("税率を" + (int) newRate + "％に変更しました。");
		mycity.taxRate = newRate / 100;
	}

	public static void displayTown(City mycity) {
		System.out.println("現在の総資金：" + mycity.money + "G 現在の総人口：" + mycity.population + "人");
	}

}
