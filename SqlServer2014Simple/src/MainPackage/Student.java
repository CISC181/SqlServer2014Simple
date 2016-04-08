package MainPackage;

public class Student implements iStudent {

	private int StuID;
	private String StuName;
	
	public Student()
	{
		
	}
	public Student(int StuID, String StuName)
	{
		this.StuID = StuID;
		this.StuName = StuName;
	}
	
	/* (non-Javadoc)
	 * @see MainPackage.iStudent#getStuID()
	 */
	@Override
	public int getStuID() {
		return StuID;
	}
	/* (non-Javadoc)
	 * @see MainPackage.iStudent#setStuID(int)
	 */
	@Override
	public void setStuID(int stuID) {
		StuID = stuID;
	}
	/* (non-Javadoc)
	 * @see MainPackage.iStudent#getStuName()
	 */
	@Override
	public String getStuName() {
		return StuName;
	}
	/* (non-Javadoc)
	 * @see MainPackage.iStudent#setStuName(java.lang.String)
	 */
	@Override
	public void setStuName(String stuName) {
		StuName = stuName;
	}

}
