import java.util.ArrayList;


public class Commentary {
	
	ArrayList<String> goals, points, defence, frees;
	
	public Commentary()
	{
		goals = FileManipulation.readReportText("commentary\\goals.txt");
		points = FileManipulation.readReportText("commentary\\points.txt");
		defence = FileManipulation.readReportText("commentary\\defence.txt");
		frees = FileManipulation.readReportText("commentary\\frees.txt");
	}

	public ArrayList<String> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<String> goals) {
		this.goals = goals;
	}

	public ArrayList<String> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<String> points) {
		this.points = points;
	}
	
	public String getGoalCom(String player, Team team)
	{
		String com, name = "";
		
		int n = (int)(Math.random() * goals.size());		
		do
		{
			name = team.getPlayer((int)(Math.random() * 15)).getName();
		}while(name.equals(player));
		
		com = goals.get(n);
		com = com.replace("<name>", player);
		com = com.replace("<other>", name);
		
		return com;
	}
	
	public String getPointCom(String player, Team team)
	{
		String com, name = "";
		
		int n = (int)(Math.random() * points.size());
		do
		{
			name = team.getPlayer((int)(Math.random() * 15)).getName();
		}while(name.equals(player));
		com = points.get(n);
		com = com.replaceAll("<name>", player);
		com = com.replaceAll("<other>", name);
		return com;		
	}
	
	public String getDefCom(String player, Team team)
	{
		String com, name = "";
		
		int n = (int)(Math.random() * defence.size());
		
		name = team.getPlayer((int)(Math.random() * 15)).getName();
		
		com = defence.get(n);
		com = com.replaceAll("<name>", player);
		com = com.replaceAll("<other>", name);
		
		return com;
	}
	
	public String getFreeCom(String player, Team team)
	{
		String com, name = "";
		
		int n = (int)(Math.random() * frees.size());
		
		name = team.getPlayer((int)(Math.random() * 15)).getName();
		com = frees.get(n);
		com = com.replaceAll("<name>", player);
		com = com.replaceAll("<other>", name);
		
		return com;
	}

}
