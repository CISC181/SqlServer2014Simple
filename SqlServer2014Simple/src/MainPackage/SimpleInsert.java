package MainPackage;

import java.sql.*;
import java.util.ArrayList;

public class SimpleInsert {

	public static void main(String[] args) throws SQLException {

		try {
			/*
			InsertRecordSproc();
			
			InsertRecord();
			UpdateRecord();
			DeleteRecord();
			*/

			
			iStudent i = null;
			
			i.setStuID(1);
			i.setStuName("Bobby");
			
			System.out.println(i.getStuName());
			
			ArrayList<Person> People = new ArrayList<Person>();
			Person p = new Person();
			
			ResultSet resultSet = SelectRecords();
			while (resultSet.next()) {
				System.out.println("Student Name: " + resultSet.getString("StuName"));
				p.setStuID(resultSet.getInt("StuID"));
				p.setStuName(resultSet.getString("StuName"));
				People.add(p);
			}
			
			System.out.println("Number of People: " + People.size());
			
		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

	private static void InsertRecord() {
		int RecID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper(eDBType.ORACLE);
			String sql = "Insert Into Bert.Stu (StuID, StuName) values (bert.seq_Stu.nextval, 'Bert')";

			RecID = sqlHelper.executeUpdate(sql);

			System.out.println("Records Inserted: " + RecID);

		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some other exception thrown");
		} finally {
			System.out.println("Insert Record completed");
		}
	}

	/**
	 * UpdateRecord - Example how to execute an 'update' statement
	 */
		private static void UpdateRecord() {
			int RecCount = 0;

			try {
				SQLHelper sqlHelper = new SQLHelper(eDBType.ORACLE);
				String sql = "Update Bert.Stu Set StuName = 'Joseph' Where StuID = 2";
				RecCount = sqlHelper.executeUpdate(sql);

				System.out.println("Records Updated: " + RecCount);

			} catch (SQLException e) {
				System.out.println("SQL Exception thrown");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Some other exception thrown");
			} finally {
				System.out.println("Update Record completed");
			}
		}
	
	private static void DeleteRecord() {
		int RecID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper(eDBType.ORACLE);
			String sql = "Delete from Bert.Stu where StuID = 1";

			RecID = sqlHelper.executeUpdate(sql);

			System.out.println("Records Deleted: " + RecID);

		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some other exception thrown");
		} finally {
			System.out.println("Delete Record completed");
		}
	}

	private static ResultSet SelectRecords() {
		int RecID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper(eDBType.ORACLE);
			String sql = "Select * from Bert.Stu";

			ResultSet resultSet = sqlHelper.executeQuery(sql);
			return resultSet;
		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some other exception thrown");
		} finally {
			System.out.println("Delete Record completed");
		}
		
		return null;
	}
	
	private static void InsertRecordSproc() {
		int iStuID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper(eDBType.ORACLE);
			String sql = "{? = call Bert.FN_INSERT_STU (?)}";
			
			CallableStatement proc = sqlHelper.callableStatement(sql);
			proc.registerOutParameter(1, java.sql.Types.INTEGER);
			proc.setString(2, "Jim");			
			proc = sqlHelper.executeCallableStatement(proc);
			
			iStuID = proc.getInt(1);
			
			System.out.println("Stu ID Created: " + iStuID);

		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some other exception thrown");
		} finally {
			System.out.println("Insert Record completed");
		}
	}
}
