public class SeverePatient extends Patient {

	String patientID;
	
	public SeverePatient(int arrivalTime){
		super(arrivalTime);
		setPatientType("SEVERE");
		setPatientID();
	}
	
	@Override
	public String getPatientID(){
		return patientID;
	}
	
	@Override
	public void setPatientID() {
		Patient.idCounter++;
		patientID = patientType+" "+Patient.idCounter;
	}
	
	
	public int compareTo(Patient p) {
		return -(getPatientType().compareTo(p.getPatientType()));
	}
}
