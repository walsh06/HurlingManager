
public class Player implements Comparable<Player>{

	String pos, name;
	int gk, def, pas, atk, num, goal, point;
	
	public Player(String pos, String name, int gk, int def, int pas, int atk)
	{
		this.pos = pos;
		this.name = name;
		this.gk = gk;
		this.def = def;
		this.pas = pas;
		this.atk = atk;
		this.num = 0;
		this.goal = 0;
		this.point = 0;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGk() {
		return gk;
	}

	public void setGk(int gk) {
		this.gk = gk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void scorePoint()
	{
		this.point++;
	}
	
	public void scoreGoal()
	{
		this.goal++;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(Player p) {

		if(p.getNum() < this.num)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}
