import java.util.Comparator;
public class PatientPriority implements Comparator<Patient>{

	public int compare(Patient p1, Patient p2) {
		if(p1.compareTo(p2)==0) {
			return (p1.getArrivalTimeAtEmergencyFacility()-p2.getArrivalTimeAtEmergencyFacility());
		}
		else {
			return p1.compareTo(p2);
		}
	}

}
