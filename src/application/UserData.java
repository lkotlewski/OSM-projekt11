package application;

public class UserData {

	private String fullName;
	private char sex;
	
	/**
	 * User id 
	 */
	private String id;
	private String insurance;
	private boolean examination;
	
	private UserData()
	{
		this.fullName = "";
		this.sex = 'M';
		this.id = "0";
		this.insurance = "";
		this.examination = false;
	}
	
	public UserData(String fullName, char sex, String id, String insurance, boolean examination)
	{
		this.fullName = fullName;
		this.sex = sex;
		this.id = id;
		this.insurance = insurance;
		this.examination = examination;
	}
	
	
	

	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	public char getSex() 
	{
		return sex;
	}

	public void setSex(char sex)
	{
		this.sex = sex;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getInsurance() 
	{
		return insurance;
	}

	public void setInsurance(String insurance)
	{
		this.insurance = insurance;
	}

	public boolean getExamination()
	{
		return examination;
	}

	public void setExamination(boolean examination) 
	{
		this.examination = examination;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
}
