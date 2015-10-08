package services.registration;
import java.sql.Connection;
import java.sql.SQLException;

import model.registration.RegistrationModel;

import com.group3.faceit.dao.*;
import com.group3.faceit.dao.regisration.RegistrationDAO;

import common.AbstractDAO;

public class RegistrationServices extends AbstractDAO{
	
	private RegistrationDAO reg = null;
	public RegistrationServices(){
		reg = new RegistrationDAO();
	}
	
	public Boolean registerAccount(RegistrationModel regData){
		Boolean isValid = false;
		try {
			Connection con = getConnection();
			isValid = reg.registerAccount(regData,con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return isValid;
	}
	
}
