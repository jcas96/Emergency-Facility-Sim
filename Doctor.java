
public class Doctor {
	
	public static int doctorCounter=0;
	boolean isFree;
	int doctorIDNumber;
	int totalPatientsSeenByDoctor=0;
	int timeRemainingForExam;
	Patient assignedPatient;
	
	Doctor(){
		setDoctorIDNumber();
		setIsFree(true);
	}
	
	public boolean getIsFree() {
		return isFree;
	}
	public int getDoctorIdNumber() {
		return doctorIDNumber;
	}
	public int getTotalPatientsSeenByDoctor() {
		return totalPatientsSeenByDoctor;
	}
	public int getTimeRemainingForExam() {
		return timeRemainingForExam;
	}
	public Patient getAssignedPatient() {
		return assignedPatient;
	}
	
	public void setDoctorIDNumber() {
		doctorCounter++;
		doctorIDNumber=doctorCounter;
	}
	
	public void setIsFree(boolean ans) {
		isFree=ans;
	}
	
	public void setAssignedPatient(Patient p) {
		assignedPatient=p;
		setIsFree(false);
	}
	
	public Patient removeAssignedPatient() {
		Patient tempPatient = assignedPatient;
		assignedPatient=null;
		setIsFree(true);
		totalPatientsSeenByDoctor++;
		return tempPatient;
	}
	
	public void setTimeRemainingForExam(int rem) {
		timeRemainingForExam=rem;
	}
	public void decrementTimeRemainingForExam() {
		timeRemainingForExam--;
	}
	
	public String toString() {
		return String.format("Doctor %d examines %d patients", doctorIDNumber,totalPatientsSeenByDoctor);
	}

}
