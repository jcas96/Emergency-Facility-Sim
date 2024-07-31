public class UrgentPatient extends Patient{
	
	String patientID;
	
	public UrgentPatient(int arrivalTime){
		super(arrivalTime);
		setPatientType("URGENT");
		setPatientID();
	}
	
	@Override
	public String getPatientID() {
		return patientID;
	}
	
	@Override
	public void setPatientID() {
		Patient.idCounter++;
		patientID=patientType+" "+Patient.idCounter;
	}

	public int compareTo(Patient p) {
		return -(getPatientType().compareTo(p.getPatientType()));
	}
}
