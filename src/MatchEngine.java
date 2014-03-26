import java.util.ArrayList;


public class MatchEngine {

	Commentary commentary;
	double teamOnePoint, teamTwoPoint, teamOneGoal, teamTwoGoal, teamOneDefence, teamTwoDefence, teamOneTotalProb, teamTwoTotalProb;
	int total, teamOneProb, teamTwoProb;
	String teamOneName, teamTwoName;
	public MatchEngine(String teamOneName, String teamTwoName)
	{
		commentary = new Commentary();
		this.teamOneName = teamOneName;
		this.teamTwoName = teamTwoName;
	}
	
	public void simGame()
	{
		Team teamOne = new Team("teams\\" + teamOneName), teamTwo = new Team("teams\\" + teamTwoName);
		int games = 1;
		int pointsOne = 0, pointsTwo = 0, goalsOne = 0, goalsTwo = 0;
		int[][] scores = new int[games][4];
		String playerName = "";
		Player playerAction;
		String report = "", comment = "";
		boolean score = false;
		
		setInitialProb(teamOne, teamTwo);
		
		System.out.println(teamOne.getTotal() + " " + teamTwo.getTotal());
		for(int j = 0; j < games; j++)
		{
			pointsOne = 0;
			pointsTwo = 0;
			goalsOne = 0;
			goalsTwo = 0;

			report = "";
			for(int i = 0; i < 71; i++)
			{
				
				int teamEvent = (int)(Math.random() * total);
				if(teamEvent < teamOneProb)
				{
					double event = Math.random() * teamOneTotalProb;
					if(event < teamOnePoint)
					{
						int free = (int)(Math.random() * 4);
						
						if(free > 0)
						{
							playerAction = teamOne.getPlayers().get(playerEvent(teamOne, 37, 71, 91, 99));
							playerName = playerAction.getName();		
							playerAction.scorePoint();
							comment = commentary.getPointCom(playerName, teamOne);
						}
						else
						{
							playerAction = teamOne.getFreeTaker();
							playerName = playerAction.getName();
							playerAction.scorePoint();
							comment = commentary.getFreeCom(playerName, teamOne);
						}
							
						pointsOne++;
						score = true;
					}
					else if(event < teamOneGoal )
					{
							playerAction = teamOne.getPlayers().get(playerEvent(teamOne, 55, 90, 96, 99));
							playerName = playerAction.getName();
							playerAction.scoreGoal();
							goalsOne++;
							comment = commentary.getGoalCom(playerName, teamOne);
						score = true;
					}
					else if(event < teamOneDefence)
					{
						playerAction = teamOne.getPlayers().get(playerEvent(teamOne, 10, 20, 45, 70));
						playerName = playerAction.getName();
						
						String com = commentary.getDefCom(playerName, teamTwo);
						report += "MIN) " + i + " " +  com + "\n";
					}
					//else if(event >= 0.70)
					//{
						//	report += "MIN) " + i  + " WIDE \n";
					//}
				}
				else
				{
					double event = Math.random() * teamTwoTotalProb;
					if(event < teamTwoPoint)
					{
						int free = (int)(Math.random() * 4);
						if(free > 0)
						{
							playerAction = teamTwo.getPlayers().get(playerEvent(teamTwo, 37, 71, 91, 99));
							playerName = playerAction.getName();
							playerAction.scorePoint();
							comment = commentary.getPointCom(playerName, teamTwo);
						}
						else
						{
							playerAction = teamTwo.getFreeTaker();
							playerName = playerAction.getName();
							playerAction.scorePoint();
							comment = commentary.getFreeCom(playerName, teamTwo);
						}
						pointsTwo++;
						score = true;
					}
					else if(event < teamTwoGoal)
					{
							playerAction = teamTwo.getPlayers().get(playerEvent(teamTwo, 55, 90, 96, 99));
							playerName = playerAction.getName();
							playerAction.scoreGoal();
							goalsTwo++;
							comment = commentary.getGoalCom(playerName, teamTwo);
						    score = true;
					}
					else if(event < teamTwoDefence)
					{
							playerAction = teamTwo.getPlayers().get(playerEvent(teamTwo, 10, 20, 45, 70));
							playerName = playerAction.getName();
						
							String com = commentary.getDefCom(playerName, teamOne);
							report += "MIN) " + i + " " +  com + "\n";
					}
				}
				
				scores[j][0] = goalsOne;
				scores[j][1] = pointsOne;
				scores[j][2] = goalsTwo;
				scores[j][3] = pointsTwo;
				if(score)
				{
					report += "MIN " + i + ") " + comment + " \n" + scores[j][0] + "-" + scores[j][1] + " TO " + scores[j][2] + "-" + scores[j][3] + " \n";
					//report += "MIN) " + i + " Player: " + p + " \n" + scores[j][0] + "-" + scores[j][1] + " TO " + scores[j][2] + "-" + scores[j][3] + " \n";

				}
				if(i == 35)
				{
					report  += "HALF TIME SCORE: " + scores[j][0] + "-" + scores[j][1] + " TO " + scores[j][2] + "-" + scores[j][3] + " \n";
				}
				score = false;
			}
			report += "FINAL SCORE: " + scores[j][0] + "-" + scores[j][1] + " TO " + scores[j][2] + "-" + scores[j][3] + " \n";
			report += teamOneName +"\n";
			for(int i =0; i < teamOne.getPlayers().size(); i++)
			{
				Player player = teamOne.getPlayer(i);
				report += player.getNum() + " " + 
				player.getName() + ": " + player.getGoal() + "-" + player.getPoint() +"\n";
			}
			report += teamTwoName +"\n";
			for(int i =0; i < teamTwo.getPlayers().size(); i++)
			{
				Player player = teamTwo.getPlayer(i);
				report += player.getNum() + " " + player.getName() + ": " + player.getGoal() + "-" + player.getPoint() +"\n";
			}
			FileManipulation.writeReport(report, "report" + teamOneName + "v" + teamTwoName + ".txt");
			System.out.println("SIM " + j + " FINISHED");
		}
		
		System.out.println("SIM DONE");
	}
	
	private int playerEvent(Team team, int FFprob, int HFprob, int MFprob, int HBprob)
	{
		ArrayList<Player> row;
		double rowTotal = 0, playerOne, playerTwo;
		int ranRow = (int)(Math.random() * 100);
		int ranPlayer = (int)(Math.random() * 100);
		
		if(ranRow < FFprob)
		{
			row = team.getFF();
			rowTotal = row.get(0).getAtk() + row.get(1).getAtk() + row.get(2).getAtk();
			
			playerOne = (row.get(0).getAtk()/rowTotal) * 100;
			playerTwo = playerOne + ((row.get(1).getAtk()/rowTotal) * 100);
			if(ranPlayer < playerOne)
			{
				return 12;
			}
			else if(ranPlayer < playerTwo)
			{
				return 13;
			}
			else
			{
				return 14;
			}
		}
		else if(ranRow < HFprob)
		{
			row = team.getHF();
			rowTotal = row.get(0).getAtk() + row.get(1).getAtk() + row.get(2).getAtk();
			
			playerOne =(row.get(0).getAtk()/rowTotal) * 100;
			playerTwo = playerOne + ((row.get(1).getAtk()/rowTotal) * 100);
			if(ranPlayer < playerOne)
			{
				return 9;
			}
			else if(ranPlayer < playerTwo)
			{
				return 10;
			}
			else
			{
				return 11;
			}
		}
		else if(ranRow < MFprob)
		{
			row = team.getMF();
			rowTotal = row.get(0).getAtk() + row.get(1).getAtk();
			
			playerOne = (row.get(0).getAtk()/rowTotal) * 100;
			if(ranPlayer < playerOne)
			{
				return 7;
			}
			else 
			{
				return 8;
			}
		}
		else if(ranRow < HBprob)
		{
			row = team.getHB();
			rowTotal = row.get(0).getAtk() + row.get(1).getAtk() + row.get(2).getAtk();
			
			playerOne = (row.get(0).getAtk()/rowTotal) * 100;
			playerTwo = playerOne + ((row.get(1).getAtk()/rowTotal) * 100);
			if(ranPlayer < playerOne)
			{
				return 4;
			}
			else if(ranPlayer < playerTwo)
			{
				return 5;
			}
			else
			{
				return 6;
			}
		}
		else
		{
			row = team.getFB();
			rowTotal = row.get(0).getAtk() + row.get(1).getAtk() + row.get(2).getAtk();
			
			playerOne = (row.get(0).getAtk()/rowTotal) * 100;
			playerTwo = playerOne + ((row.get(1).getAtk()/rowTotal) * 100);
			if(ranPlayer < playerOne)
			{
				return 1;
			}
			else if(ranPlayer < playerTwo)
			{
				return 2;
			}
			else
			{
				return 3;
			}
		}
	}
	
	private void setInitialProb(Team teamOne, Team teamTwo)
	{	
		double teamOnePointMod = 0, teamOneDefMod = 0, teamOneGoalMod = 0;
		double teamTwoPointMod = 0, teamTwoDefMod = 0, teamTwoGoalMod = 0;

		int counterTactic = 5;
		double defence = 0.02, point = 0.06, goal = 0.02;
		int teamOneTotalMod, teamTwoTotalMod;
		int teamOneAtk = teamOne.getAttack();
		int teamOneDef = teamOne.getDefence();
		int teamTwoAtk = teamTwo.getAttack();
		int teamTwoDef = teamTwo.getDefence();
		
		if(teamOne.getTactic() == 'A')
		{
			if(teamTwo.getTactic() == 'A')
			{
				teamOneTotalMod = 0;
				teamTwoTotalMod = 0;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = goal;
				teamTwoDefMod = -defence;
			}
			else if(teamTwo.getTactic() == 'D')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = -goal;
				teamTwoDefMod = defence;
			}
			else if(teamTwo.getTactic() == 'S')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoTotalMod += 5;
			}
			else if(teamTwo.getTactic() == 'L')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = 0.04;
			}
			else
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = -goal;
			}
			teamOnePointMod = point;
			teamOneGoalMod = goal;
			teamOneDefMod = -defence;
		}
		else if(teamOne.getTactic() == 'D')
		{
			if(teamTwo.getTactic() == 'A')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = goal;
				teamTwoDefMod = -defence;
			}
			else if(teamTwo.getTactic() == 'D')
			{
				teamOneTotalMod = 0;
				teamTwoTotalMod = 0;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = -goal;
				teamTwoDefMod = defence;
			}
			else if(teamTwo.getTactic() == 'S')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoTotalMod += 5;
			}
			else if(teamTwo.getTactic() == 'L')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = 0.04;
			}
			else
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = -goal;
			}
			teamOnePointMod = -point;
			teamOneGoalMod = -goal;
			teamOneDefMod = defence;
		}
		else if(teamOne.getTactic() == 'S')
		{
			if(teamTwo.getTactic() == 'A')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = goal;
				teamTwoDefMod = -defence;
			}
			else if(teamTwo.getTactic() == 'D')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = -goal;
				teamTwoDefMod = defence;
			}
			else if(teamTwo.getTactic() == 'S')
			{
				teamOneTotalMod = 0;
				teamTwoTotalMod = 0;
				
				teamTwoTotalMod += 5;
			}
			else if(teamTwo.getTactic() == 'L')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = 0.04;
			}
			else
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = -goal;
			}
			teamOneTotalMod += 5;
		}
		else if(teamOne.getTactic() == 'L')
		{
			if(teamTwo.getTactic() == 'A')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = goal;
				teamTwoDefMod = -defence;
			}
			else if(teamTwo.getTactic() == 'D')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = -goal;
				teamTwoDefMod = defence;
			}
			else if(teamTwo.getTactic() == 'S')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoTotalMod += 5;
			}
			else if(teamTwo.getTactic() == 'L')
			{
				teamOneTotalMod = 0;
				teamTwoTotalMod = 0;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = 0.04;
			}
			else
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = -goal;
			}
			teamOnePointMod = -point;
			teamOneGoalMod = 0.04;
		}
		else
		{
			if(teamTwo.getTactic() == 'A')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = goal;
				teamTwoDefMod = -defence;
			}
			else if(teamTwo.getTactic() == 'D')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = -goal;
				teamTwoDefMod = defence;
			}
			else if(teamTwo.getTactic() == 'S')
			{
				teamOneTotalMod = -counterTactic;
				teamTwoTotalMod = counterTactic;
				
				teamTwoTotalMod += 5;
			}
			else if(teamTwo.getTactic() == 'L')
			{
				teamOneTotalMod = counterTactic;
				teamTwoTotalMod = -counterTactic;
				
				teamTwoPointMod = -point;
				teamTwoGoalMod = goal;
			}
			else
			{
				teamOneTotalMod = 0;
				teamTwoTotalMod = 0;
				
				teamTwoPointMod = point;
				teamTwoGoalMod = -goal;
			}
			teamOnePointMod = point;
			teamOneGoalMod = -goal;
		}
		
		
		teamOneProb = teamOne.getTotal() + teamOneTotalMod + 3; //+3 for home team
		teamTwoProb = teamTwo.getTotal() + teamTwoTotalMod - 3;
		
		total = teamOneProb + teamTwoProb;
		
		teamOneTotalProb = 1.8 + ((teamOneAtk - teamTwoDef)/100) + ((teamOneAtk - teamTwoDef - teamTwo.getGoalKeeper())/100) + (teamOneDef/1000)
							+ teamOnePointMod + teamOneGoalMod + teamOneDefMod - (2 * teamTwoDefMod);
		teamOnePoint = 0.9 + ((teamOneAtk - teamTwoDef)/100) + teamOnePointMod - teamTwoDefMod;
		teamOneGoal = teamOnePoint + 0.04 + ((teamOneAtk - teamTwoDef - teamTwo.getGoalKeeper())/100) + teamOneGoalMod - teamTwoDefMod;
		teamOneDefence = teamOneGoal + 0.2 + (teamOneDef/1000) + teamOneDefMod;
				
		teamTwoTotalProb = 1.8 + ((teamTwoAtk - teamOneDef)/100) + ((teamTwoAtk - teamOneDef - teamOne.getGoalKeeper())/100) + (teamTwoDef/1000)
							+ teamTwoPointMod + teamTwoGoalMod + teamTwoDefMod - (2 * teamOneDefMod);
		teamTwoPoint = 0.9 + ((teamTwoAtk - teamOneDef)/100) + teamTwoPointMod - teamOneDefMod;
		teamTwoGoal = teamTwoPoint + 0.04 + ((teamTwoAtk - teamOneDef - teamOne.getGoalKeeper())/100) + teamTwoGoalMod - teamOneDefMod;
		teamTwoDefence = teamTwoGoal + 0.2 + (teamTwoDef/1000) + teamTwoDefMod;	
	}
}
