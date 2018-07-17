package DataOperator;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 * 该类用来实现对部门相关信息进行数据库操作的功能
 * @author YaXin Wen
 * @date 2017/11/15
 */
public class DeptBean {
	ResultSet rs = null;
	String sql;         //存放sql语句
	String Did;    		//部门编号
	String firstLevel;  //一级部门
	String secondLevel; //二级部门
	
	String colName;    //列名
	String colValue;   //列值
	String colValue2;  //列值
	
	/**
	 * 添加相关信息
	 */
	public boolean addInfo(String id,String first,String second) {
		Database db = new Database();
		
		this.Did = id;
		this.firstLevel = first;
		this.secondLevel = second;
		
		if(firstLevel == null || firstLevel == "") {
			JOptionPane.showMessageDialog(null, "请输入一级部门名称","错误",JOptionPane.ERROR_MESSAGE);
			return false;  //记录添加失败
		}
		else if(secondLevel == null || secondLevel == "") {
			JOptionPane.showConfirmDialog(null, "请输入二级部门名称","错误",JOptionPane.ERROR_MESSAGE);
			return false; //记录添加失败
		}
		sql = "insert into DeptTable(DeptId,FatherDept,SonDept) values('"+Did+"','"+firstLevel+"','"+secondLevel+"')";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null, "成功添加一条记录");
			sql = "delete from unUsedDeptId where DeptId = '"+id+"'";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "保存失败","错误",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	//获取部门编号
	public int getNewId() throws Exception{
		Database db = new Database();
		db.OpenConn();   //打开连接
		sql = "select * from DeptTable";
		int number = db.RecordNumber(sql);   ///获取表的记录数
		if(number == 0) {
			db.closeStmt();
			db.closeConn();
			return 1;///如果表中没有记录。
		}
		int Max = db.getMaxId(sql);   ///获取最大编号
		//如果最大表闹和表中记录条数一样，则返回number+1
		if(Max == number) {
			db.closeStmt();
			db.closeConn();
			Max++;
			return Max;
		} 
		else {   ///
			sql = "select * from unUsedDeptId";
			int Min = db.getMinId(sql);
			db.closeStmt();
			db.closeConn();
			return Min;
		}
	}
	/**
	 * @param id  		部门编号
	 * @param first     一级部门名称
	 * @param second    二级部门名称
	 * @return          修改成功返回true,否则返回false
	 * @throws Exception
	 */
	public boolean modifyInfo(String id,String first,String second) throws Exception {
		Database db = new Database();
		
		this.Did = id;
		this.firstLevel = first;
		this.secondLevel = second;
		
		if(firstLevel == null || firstLevel == "") {
			JOptionPane.showMessageDialog(null, "请输入一级部门名称","错误",JOptionPane.ERROR_MESSAGE);
			return false;  //记录添加失败
		}
		else if(secondLevel == null || secondLevel == "") {
			JOptionPane.showConfirmDialog(null, "请输入二级部门名称","错误",JOptionPane.ERROR_MESSAGE);
			return false; //记录添加失败
		}
		int temp = Integer.valueOf(Did);
		sql = "update DeptTable set FatherDept='"+first+"',SonDept='"+second+"' where DeptId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);  //更新信息
			JOptionPane.showMessageDialog(null, "成功修改一条记录");
			/**
			 * 将相应的人员信息也进行更新。
			 */
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "保存失败","错误",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
		
	}
	/**
	 * 判断符合要求的记录是否存在。如果存在返回true,否则返回false;
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String id) throws Exception{
		Database db = new Database();
		int temp = Integer.valueOf(id);
		sql = "select * from Person where DeptId = '"+id+"'";
		boolean flag = false;  //假设不存在。
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			int num = 0;
			while(rs.next()) {
				num++;
				db.closeStmt();
				db.closeConn();
				return true;
			}
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "修改失败","错误",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
		return flag;
	}
	public boolean deleteInfo(String id) {
		Database db = new Database();
		this.Did = id;
		int temp = Integer.valueOf(Did);
		sql = "delete from DeptTable where DeptId = '"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);  //删除该记录
			JOptionPane.showMessageDialog(null, "成功删除一条记录");
			//把编号插入到未使用编号表中
			sql = "insert into unUsedDeptId(DeptId) values('"+Did+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "删除失败","错误",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	public String[] getAllNodeInfor() throws Exception{
		Database db = new Database();
		String[] sn = null;
		int row = 0;
		int i = 0;
		sql = "select * from DeptTable order by DeptId";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
			if(row == 0) {
				sn = new String[1];
				sn[0] = " ";
			}
			else {
				sn = new String[row];
				rs.first();
				rs.previous();
				while(rs.next()) {
					sn[i] = "";
					sn[i] += rs.getString("DeptId");
					sn[i] += "-";
					sn[i] += rs.getString("FatherDept");
					sn[i] += "-";
					sn[i] += rs.getString("SonDept");
					i++;
				}
			}
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "失败","错误",JOptionPane.ERROR_MESSAGE);
		} finally{
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
	/**
	 * 查询所有记录
	 */
	public String[][] searchAll() throws Exception {
		Database db = new Database();
		String[][] sn = null;
		int row = 0;
		int i = 0;
		sql = "select * from DeptTable order by DeptId";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
			if(row == 0) {
				sn = new String[1][3];
				sn[0][0] = "	";
				sn[0][1] = "	";
				sn[0][2] = "	";
			}
			else {
				sn = new String[row][3];
				rs.first();
				rs.previous();
				while(rs.next()) {
					sn[i][0] = rs.getString("DeptId");
					sn[i][1] = rs.getString("FatherDept");
					sn[i][2] = rs.getString("SonDept");
					i++;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
}
