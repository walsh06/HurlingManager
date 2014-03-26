import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManipulation {


		public static ArrayList<String> readTeamSheet(String fileName)
		{
			ArrayList<String> teamSheet = new ArrayList<String>();
			
			try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			String fileLines;
			String[] playerInfo;
			
			
			while(br.ready())
			{
				fileLines = br.readLine();
				teamSheet.add(fileLines);
			}
			br.close();
			} 
			catch(IOException e)
			{
				System.out.println("Couldnt Read file");
			}
			
			return teamSheet;
		}
		
		public static void writeReport(String report, String fileName)
		{
			try
			{
				FileWriter fw = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(fw);
					bw.write(report);
				bw.close();
			}
			catch(IOException e)
			{
				System.out.println("Couldnt Write file");
			}
		}
		
		public static ArrayList<String> readReportText(String fileName)
		{
			ArrayList<String> commentary = new ArrayList<String>();
			
			try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			while(br.ready())
			{
				commentary.add(br.readLine());
				
			}
			br.close();
			} 
			catch(IOException e)
			{
				System.out.println("Couldnt Read file");
			}
			
			return commentary;
		}
	}

