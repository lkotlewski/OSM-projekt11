package application;

import java.time.LocalDate;

public class FormExamData {
	String bilirubinLevel;
	boolean antiBodiesHCV;
	boolean antigenHBS;
	LocalDate examDate;
	
	public FormExamData(String bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, LocalDate examDate) {
		super();
		this.bilirubinLevel = bilirubinLevel;
		this.antiBodiesHCV = antiBodiesHCV;
		this.antigenHBS = antigenHBS;
		this.examDate = examDate;
		
	}

	public String getBilirubinLevel() {
		return bilirubinLevel;
	}

	public boolean isAntiBodiesHCV() {
		return antiBodiesHCV;
	}


	public boolean isAntigenHBS() {
		return antigenHBS;
	}

	public LocalDate getExamDate() {
		return examDate;
	}
	
}

