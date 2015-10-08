package com.group3.faceit.dao.regisration;
import java.sql.*;

import javax.naming.ldap.StartTlsRequest;

import org.apache.commons.codec.digest.Md5Crypt;

import common.*;
import model.*;

import model.registration.RegistrationModel;


public class RegistrationDAO{
	private String strQry = "";
	
	public Boolean registerAccount(RegistrationModel regData, Connection con) throws SQLException{
		System.out.println(regData);
		Boolean isValid = false;
		try{
			strQry = "INSERT INTO user (username, password) VALUES (?, sha1(?))";
			PreparedStatement stmt = con.prepareStatement(strQry, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, regData.getUsername());
			stmt.setString(2, regData.getPassword());
			stmt.executeUpdate();
			
			ResultSet result = stmt.getGeneratedKeys();
			int userId = 0;
			if (result.next()) {
					userId = result.getInt(1);
			}
			
			strQry = "INSERT INTO userprofile (userid, fname, lname, email, birthdate, gender)"
					+ " VALUES (?, ?, ?, ?, STR_TO_DATE(?, '%m/%d/%Y'), ?)";
			PreparedStatement stmt2 = con.prepareStatement(strQry);
			stmt2.setInt(1, userId);
			stmt2.setString(2, regData.getFname());
			stmt2.setString(3, regData.getLname());
			stmt2.setString(4, regData.getUsername());
			stmt2.setString(5, regData.getBirthdate());
			stmt2.setString(6, regData.getGender());
			stmt2.executeUpdate();
			
			isValid = true;
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}finally{
			con.close();
		}
		return isValid;
	}
	
}
