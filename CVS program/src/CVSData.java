
public class CVSData {
	public int UserID = 0;
	public String firstName = "";
	public String lastName = "";
	public int Version = 0;
	public String InsuranceCompany = "";
	
	public void addData(int UserID,String firstName,String lastName,int Version,String InsuranceCompany) {
		this.UserID = UserID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Version = Version;
		this.InsuranceCompany = InsuranceCompany;
	}
	
	public String[] returnData() {
		String[] dataring = new String[]{Integer.toString(UserID), firstName, lastName , Integer.toString(Version),InsuranceCompany};
		return dataring;
	}
	
	
}
