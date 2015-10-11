package MainPackage;

import java.sql.*;

public class SimpleInsert {

	public static void main(String[] args) throws SQLException {

		try {
			
			InsertRecordSproc();
			InsertRecord();
			UpdateRecord();
			DeleteRecord();

			ResultSet resultSet = SelectRecords();
			while (resultSet.next()) {
				System.out.println("Student Name: " + resultSet.getString("StuName"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL Exception thrown");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}
/**
 * UpdateRecord - Example how to execute an 'update' statement
 */
	private static void UpdateRecord() {
		int RecCount = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper();
			String sql = "Update dbo.Stu Set StuName = 'Joseph' Where StuID = 2;";
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

	private static void InsertRecord() {
		int RecID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper();
			String sql = "Insert Into Stu (StuName) values ('Bert')";

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

	private static void DeleteRecord() {
		int RecID = 0;

		try {
			SQLHelper sqlHelper = new SQLHelper();
			String sql = "Delete from dbo.Stu where StuID = 1";

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
			SQLHelper sqlHelper = new SQLHelper();
			String sql = "Select * from Stu";

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
			SQLHelper sqlHelper = new SQLHelper();
			String sql = "{? = call Stu_Insert (?, ?)}";
			
			CallableStatement proc = sqlHelper.callableStatement(sql);
			proc.registerOutParameter(1, java.sql.Types.INTEGER);
			proc.setString(2, "Tyler");
			proc.registerOutParameter(3, java.sql.Types.INTEGER);
			
			proc = sqlHelper.executeCallableStatement(proc);
			
			iStuID = proc.getInt(3);
			
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
