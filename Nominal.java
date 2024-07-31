public class Nominal extends Patient{
	
	String patientID;
	
	public Nominal(int arrivalTime){
		super(arrivalTime);
		setPatientType("NOMINAL");
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
