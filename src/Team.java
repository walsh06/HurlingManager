import java.util.ArrayList;
import java.util.Collections;


public class Team {

	ArrayList<Player> players;
	
	int attack, defence, midfield, total, goalKeeper;
	int atkFB, atkHB, atkMF, atkHF, atkFF;
	int freeTaker;
	char tactic;
	
	public char getTactic() {
		return tactic;
	}

	public void setTactic(char tactic) {
		this.tactic = tactic;
	}

	public Team(String teamSheetName)
	{
		ArrayList<String> teamSheet = FileManipulation.readTeamSheet(teamSheetName +"roster.txt");
		players = new ArrayList<Player>();
		String playerInfo[];
		
		
		
		for(int i = 0; i < teamSheet.size(); i++)
		{
			playerInfo = teamSheet.get(i).split(","); //split the file by commas			
			
			players.add(new Player("SUB",playerInfo[0], 
					Integer.parseInt(playerInfo[1]), 
					Integer.parseInt(playerInfo[2]),
					Integer.parseInt(playerInfo[3]),
					Integer.parseInt(playerInfo[4])));
		}
		
		teamSheet = FileManipulation.readTeamSheet(teamSheetName +".txt");
		
		tactic = teamSheet.get(0).toUpperCase().charAt(0);
		freeTaker = Integer.parseInt(teamSheet.get(1));
		
		for(int i = 2; i < teamSheet.size(); i++)
		{
			String[] currentPlayer = teamSheet.get(i).split(",");
			for(int j = 0; j < players.size(); j++)
			{
				Player p = players.get(j);
				if(p.getName().equals(currentPlayer[2]))
				{
					p.setPos(currentPlayer[1]);
					p.setNum(Integer.parseInt(currentPlayer[0]));
				}
			}
		}
		
		Collections.sort(players);
		calcTotals();
		
	}
	
	public int getGoalKeeper() {
		return goalKeeper;
	}

	public void setGoalKeeper(int goalKeeper) {
		this.goalKeeper = goalKeeper;
	}

	public int getAtkFB() {
		return atkFB;
	}

	public void setAtkFB(int atkFB) {
		this.atkFB = atkFB;
	}

	public int getAtkHB() {
		return atkHB;
	}

	public void setAtkHB(int atkHB) {
		this.atkHB = atkHB;
	}

	public int getAtkMF() {
		return atkMF;
	}

	public void setAtkMF(int atkMF) {
		this.atkMF = atkMF;
	}

	public int getAtkHF() {
		return atkHF;
	}

	public void setAtkHF(int atkHF) {
		this.atkHF = atkHF;
	}

	public int getAtkFF() {
		return atkFF;
	}

	public void setAtkFF(int atkFF) {
		this.atkFF = atkFF;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getMidfield() {
		return midfield;
	}
	public void setMidfield(int midfield) {
		this.midfield = midfield;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public Player getPlayer(int i)
	{
		return players.get(i);
	}
	
	public Player getFreeTaker()
	{
		return players.get(freeTaker - 1);
	}
	
	public ArrayList<Player> getFF()
	{
		ArrayList<Player> row = new ArrayList<Player>();
		
		row.add(players.get(12));
		row.add(players.get(13));
		row.add(players.get(14));
		
		return row;
	}
	
	public ArrayList<Player> getHF()
	{
		ArrayList<Player> row = new ArrayList<Player>();
		
		row.add(players.get(9));
		row.add(players.get(10));
		row.add(players.get(11));
		
		return row;
	}
	
	public ArrayList<Player> getMF()
	{
		ArrayList<Player> row = new ArrayList<Player>();
		
		row.add(players.get(7));
		row.add(players.get(8));
		
		return row;
	}
	
	public ArrayList<Player> getHB()
	{
		ArrayList<Player> row = new ArrayList<Player>();
		
		row.add(players.get(4));
		row.add(players.get(5));
		row.add(players.get(6));
		
		return row;
	}
	public ArrayList<Player> getFB()
	{
		ArrayList<Player> row = new ArrayList<Player>();
		
		row.add(players.get(1));
		row.add(players.get(2));
		row.add(players.get(3));
		
		return row;
	}
	
	public void calcTotals()
	{
		Player currentPlayer;
		for(int i = 0; i < 15; i++)
		{
			
			currentPlayer= players.get(i);
			switch(currentPlayer.getPos())
			{
			case "GK":
				goalKeeper = currentPlayer.getGk();
				break;
			case "FB":
				defence += currentPlayer.getDef();
				atkFB += currentPlayer.getAtk();
				break;
			case "HB":
				defence += currentPlayer.getDef();
				midfield += currentPlayer.getPas()/2;
				atkHB += currentPlayer.getAtk();
				break;
			case "MF":
				midfield += currentPlayer.getPas();
				defence += currentPlayer.getDef()/3;
				attack += currentPlayer.getAtk()/3;
				atkMF += currentPlayer.getAtk();
				break;
			case "HF":
				midfield += currentPlayer.getPas()/2;
				attack += currentPlayer.getAtk();
				atkHF += currentPlayer.getAtk();
				break;
			case "FF":
				attack += currentPlayer.getAtk();
				atkFF += currentPlayer.getAtk();
				break;
			}
				
		}
		total = attack + (midfield * 2) + defence + goalKeeper;
	}
}
