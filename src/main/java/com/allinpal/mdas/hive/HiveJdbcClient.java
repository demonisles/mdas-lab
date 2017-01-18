package com.allinpal.mdas.hive;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import org.apache.hive.jdbc.HiveDriver;

public class HiveJdbcClient {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		Connection con = null;
		try {
			// replace "hive" here with the name of the user the queries should
			// run as
			con = DriverManager.getConnection("jdbc:hive2://10.56.201.71:10000/test;auth=noSasl", "hive", "hive");
			Statement stmt = con.createStatement();
			String tableName = "pokes";

			String sql = "show tables '" + tableName + "'";
			System.out.println("Running: " + sql);
			ResultSet res = stmt.executeQuery(sql);
			if (res.next()) {
				System.out.println(res.getString(1));
			}
			// describe table
			sql = "select count(*) from " + tableName;
			System.out.println("Running: " + sql);
			res = stmt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getString(1) + "\t" );
			}
			
			//load data
			sql = "LOAD DATA LOCAL INPATH '/home/appadm/app/apache-hive-2.1.0-bin/examples/files/kv1.txt' INTO TABLE pokes";
			System.out.println("Running: " + sql);
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}