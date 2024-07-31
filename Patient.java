
public abstract class Patient implements Comparable<Patient>{
	public static int idCounter=0;
	int arrivalTimeAtEmergencyFacility;
	int doctorStartTime;
	int waitTime;
	int examDuration;
	int totalTimeAtEmergencyFacility;
	String patientType;
	
	Patient(int arrivalTime){
		setArrivalTimeAtEmergencyFacility(arrivalTime);
	}
	
	public int getArrivalTimeAtEmergencyFacility() {
		return arrivalTimeAtEmergencyFacility;
	}
	
	public int getDoctorStartTime() {
		return doctorStartTime;
	}

	public int getWaitTime() {
		return waitTime;
	}
	
	public int getExamDuration() {
		return examDuration;
	}

	public int getTotalTimeAtEmergencyFacility() {
		return totalTimeAtEmergencyFacility;
	}
	
	public String getPatientType() {
		return patientType;
	}
	
	public void setArrivalTimeAtEmergencyFacility(int arri) {
		arrivalTimeAtEmergencyFacility=arri;
	}
	
	public void setExamDuration(int dur) {
		examDuration=dur;
	}
	
	public void setDoctorStartTime(int start) {
		doctorStartTime=start;
		waitTime = doctorStartTime-arrivalTimeAtEmergencyFacility;
	}
	
	public void setTotalTimeAtEmergencyFacility(int endTime) {
		totalTimeAtEmergencyFacility=endTime-arrivalTimeAtEmergencyFacility;
	}
	
	public void setPatientType(String type) {
		patientType=type;
	}
	
	abstract public void setPatientID();
	abstract public String getPatientID();
	abstract public int compareTo(Patient p);
}
