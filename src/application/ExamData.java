package application;

import java.io.Serializable;
import java.time.LocalDate;

public class ExamData implements Serializable 
{
	private static final long serialVersionUID = 1L;
	float bilirubinLevel;
	boolean antiBodiesHCV;
	boolean antigenHBS;
	LocalDate examDate;
	String id;
	

	public ExamData(float bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, LocalDate examDate, String iD)
	{
		super();
		this.bilirubinLevel = bilirubinLevel;
		this.antiBodiesHCV = antiBodiesHCV;
		this.antigenHBS = antigenHBS;
		this.examDate = examDate;
		id = iD;
	}
	
	public float getBilirubinLevel()
	{
		return bilirubinLevel;
	}
	
	public void setBilirubinLevel(float bilirubinLevel)
	{
		this.bilirubinLevel = bilirubinLevel;
	}
	
	public boolean isAntiBodiesHCV()
	{
		return antiBodiesHCV;
	}
	
	public void setAntiBodiesHCV(boolean antiBodiesHCV) 
	{
		this.antiBodiesHCV = antiBodiesHCV;
	}
	
	public boolean isAntigenHBS()
	{
		return antigenHBS;
	}
	
	public void setAntigenHBS(boolean antigenHBS) 
	{
		this.antigenHBS = antigenHBS;
	}
	
	public LocalDate getExamDate() 
	{
		return examDate;
	}
	
	public void setExamDate(LocalDate examDate) 
	{
		this.examDate = examDate;
	}
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	@Override
	public String toString() 
	{
		return "ExamData [bilirubinLevel=" + bilirubinLevel + ", antiBodiesHCV=" + antiBodiesHCV + ", antigenHBS="
				+ antigenHBS + ", examDate=" + examDate.toString() + ", Id=" + id +"]";
	}
}
