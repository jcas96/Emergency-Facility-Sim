import java.util.Scanner;
import java.io.IOException;
public class ModelEmergencyFacility {

	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.printf("Please enter the name of the Emergency Facility: ");
		String name = input.nextLine();
		
		System.out.printf("Please enter a seed value as an int:");
		int seed = input.nextInt();
		
		System.out.printf("PLeae enter the number of doctors as an int:");
		int number = input.nextInt();
		
		EmergencyFacility emFac = new EmergencyFacility(name,seed,number);
		emFac.openEmergencyFacility();
		
		System.out.printf("Please enter the number of minutes to keep the Emergency Facility open: ");
		int durationForArriving = input.nextInt();
		emFac.operateEmergencyFacility(durationForArriving);
		
		System.out.printf("Please enter the name of the output file for Emergency Facility results: ");
		String fileName = input.next();
		emFac.generateEmergencyFacilityResults(fileName);
		input.close();
		
	}

}
