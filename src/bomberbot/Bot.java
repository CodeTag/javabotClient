package bomberbot;

public class Bot {
	private char letter = ' ';
	private int x;
	private int y;
	private String[][] map;

	public Bot(char letter) {
		this.letter = letter;

	}

	public void updateMap(String mapa) {
		String[] rows = mapa.split("\n");
		map = new String[rows.length][];
		for (int i = 0; i < rows.length; i++) {
			map[i] = rows[i].split(",");
		}
		int pos = mapa.indexOf(this.letter) / 2;
		this.y = pos / rows.length;
		this.x = pos % rows.length;
	}

	public String move() {
		int mov = (int) Math.round(Math.random() * 8);
		System.out.println("mov "+mov);
		switch (mov) {
		case 0:
			return "N";
		case 1:
			return "E";
		case 2:
			return "S";
		case 3:
			return "O";
		case 4:
			return "BN";
		case 5:
			return "BE";
		case 6:
			return "BS";
		case 7:
			return "BO";
		}
		return "P";
	}

}
