import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class EmergencyFacility {
	Random randy;
	PriorityQueue<Patient> waitingQ;
	ArrayList<Patient>processedPatients = new ArrayList<Patient>();
	Doctor[]doctors;
	String facilityName;
	int currentTime=0;
	
	public EmergencyFacility(String name, int seed, int numDoctors){
		facilityName = name;
		waitingQ = new PriorityQueue<Patient>(new PatientPriority());
		randy = new Random(seed);
		createDoctors(numDoctors);
	}
	
	
	public void createDoctors(int numDoctors) {
		doctors = new Doctor[numDoctors];
		for(int i =0;i<numDoctors;i++) {
			doctors[i]=new Doctor();
		}
	}
	
	public void openEmergencyFacility() {
		int num;
		for(currentTime=0;currentTime<10;currentTime++) {
			for(int a=0;a<10;a++) {
				num =randy.nextInt(1,21);
				if(num<8) {
					waitingQ.add(new UrgentPatient(currentTime));
				}else if(num<16) {
					waitingQ.add(new SeverePatient(currentTime));
				}
				else {
					waitingQ.add(new Nominal(currentTime));
				}
			}		
		}
	}
	
	public void operateEmergencyFacility(int durationForArriving) {
		int endArrivalsTime =currentTime+durationForArriving;
		Patient tempPatient;
		int num;
		while((processedPatients.size()!=Patient.idCounter)||(currentTime<endArrivalsTime)) {
			if(currentTime<endArrivalsTime) {
				for(int i =0;i<6;i++) {
					num = randy.nextInt(1,21);
					if(num<7) {
						waitingQ.add(new UrgentPatient(currentTime));
					}else if(num<14) {
						waitingQ.add(new SeverePatient(currentTime));
					}
					else {
						waitingQ.add(new Nominal(currentTime));
						}
					}
				}
				for(Doctor doc:doctors) {
					if(doc.getIsFree()==false) {
						doc.decrementTimeRemainingForExam();
						if(doc.getTimeRemainingForExam()==0) {
							tempPatient = doc.getAssignedPatient();
							processedPatients.add(tempPatient);
							tempPatient.setTotalTimeAtEmergencyFacility(currentTime);
							doc.removeAssignedPatient();
						}
					}
				}
				for(Doctor doc:doctors) {
					if((doc.getIsFree()==true)&&(!waitingQ.isEmpty())) {
						tempPatient = waitingQ.remove();
						tempPatient.setDoctorStartTime(currentTime);
						doc.setAssignedPatient(tempPatient);
						int examDuration = randy.nextInt(10,26);
						tempPatient.setExamDuration(examDuration);
						doc.setTimeRemainingForExam(examDuration);
					}
				}
			currentTime++;
		}
	}
	
	public void generateEmergencyFacilityResults(String fileName) throws IOException{
		File outFile = new File(fileName);
		PrintWriter outputWriter = new PrintWriter(outFile);
		outputWriter.printf("Data for Emergency Facility %S\n\nSummary Data\n",facilityName);
		for(Doctor doc:doctors) {
			outputWriter.printf("Doctor %d examines %d patients\n", doc.doctorIDNumber,doc.getTotalPatientsSeenByDoctor());
		}
		double uAvg = 0;
		int urgentAmount=0;
		double sAvg = 0;
		int severeAmount=0;
		double nAvg = 0;
		int nominalAmount=0;
		double totalAvg=0;
		Patient tempPatient;
		
		for(Patient p:processedPatients) {
			if(p instanceof UrgentPatient) {
				uAvg +=p.getTotalTimeAtEmergencyFacility();
				totalAvg+=p.getTotalTimeAtEmergencyFacility();
				urgentAmount++;
			}
			else if(p instanceof SeverePatient) {
				sAvg +=p.getTotalTimeAtEmergencyFacility();
				totalAvg+=p.getTotalTimeAtEmergencyFacility();
				severeAmount++;
			}
			else{
				nAvg+=p.getTotalTimeAtEmergencyFacility();
				totalAvg+=p.getTotalTimeAtEmergencyFacility();
				nominalAmount++;
			}
		}
		int totalPatients = urgentAmount+severeAmount+nominalAmount;
		outputWriter.printf("\nThe average total time at Emergency Facility per patient for %d Urgent Patients is %.2f minutes\n", urgentAmount,uAvg/urgentAmount);
		outputWriter.printf("The average total time at Emergency Facility per patient for %d Severe Patients is %.2f minutes\n", severeAmount, sAvg/severeAmount);
		outputWriter.printf("The average total time at Emergency Facility per patient for %d Nominal Patients is %.2f minutes\n", nominalAmount, nAvg/nominalAmount);
		outputWriter.printf("The average total time in office per patient for %d Patients is %.2f minutes\n", totalPatients,totalAvg/totalPatients);
		outputWriter.printf("\n%12S%19S%16S%14S%19S%13S\n", "Patient ID","Patient TYPE","ARRIVAL TIME","WAIT TIME","EXAM TIME","TOTAL TIME");
		Iterator<Patient> patientIter= processedPatients.iterator();
		while(patientIter.hasNext()) {
			tempPatient = patientIter.next();
			outputWriter.printf("%12S%19S%16d%14d%19d%13d\n",tempPatient.getPatientID(),tempPatient.getPatientType(),tempPatient.getArrivalTimeAtEmergencyFacility(),tempPatient.getWaitTime(),tempPatient.getExamDuration(),tempPatient.getTotalTimeAtEmergencyFacility());
		}
		
		outputWriter.close();
	}

}
