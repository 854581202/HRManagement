package DataOperator;

import java.sql.ResultSet;

/**
 * 
 * @author YaXin Wen
 * @date 2017/11/18
 */
public class salaryChangeHistory {
	String sql;
	ResultSet rs;
	
	public String[][] searchAll() {
		Database db = new Database();
		String[][] sn = null;
		int row = 0,i = 0;
		sql = "select * from salaryChange";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			} 
			if(row == 0) {
				sn = new String[1][7];
				for(int j = 0; j < 7; j++) {
					sn[0][j] = "	";
				}
			}
			else {
				sn = new String[row][7];
				rs.first();
				rs.previous();
				while(rs.next()) {
					sn[i][0] = rs.getString("operatorId");
					sn[i][1] = rs.getString("pId");
					sn[i][2] = rs.getString("pName");
					sn[i][3] = rs.getString("oldSalary");
					sn[i][4] = rs.getString("newSalary");
					sn[i][5] = rs.getString("modifyTime");
					sn[i][6] = rs.getString("modifyDate");
					i++;
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally{
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
	
}
