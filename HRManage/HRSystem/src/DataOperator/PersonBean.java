package DataOperator;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * 该类用来实现员工信息的增删查改。
 * @author YaXin Wen
 * @date 2017/11/15
 */
public class PersonBean {
	ResultSet rs = null;
	String sql;         //存放sql语句
	String pId;         //员工编号
	String pName;       //员工姓名
	String pSex;        //员工性别
	String pBirth;      //出生年月
	String pNation;     //民族
	String pAddress;    //地址
	String Did;         //所属部门编号
	String pSalary;     //工资
	String pAssess;     //是否考核
	String pOther;      //其他
	
	/**
	 * @param name    员工姓名
	 * @param sex     员工性别
	 * @param birth   员工出生年月
	 * @param nation  员工民族
	 * @param address 员工地址
	 * @return 如果信息合法，返回true,否则返回false
	 */
	public boolean isLegal(String name,String sex,String birth,String nation,String address) {
		if(name==null || name.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入姓名","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(sex==null || sex.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入性别","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(birth==null || birth.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入出生年月","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(nation==null || nation.equals("")) {
			JOptionPane.showMessageDialog(null,"请输入民族","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(address==null || address.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入地址","错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param id  		员工编号
	 * @param name		员工姓名
	 * @param sex		员工性别
	 * @param birth		员工出生年月
	 * @param nation	员工民族
	 * @param address	员工家庭住址
	 * @param did		员工所属部门
	 * @param salary	员工薪资
	 * @param access	员工考核
	 * @param other		其他信息
	 */
	public void addInfo(String id,String name,String sex,String birth,String nation,String address,String did,String salary,String access,String other) {
		
		//如果信息合法
		this.pId = id;
		this.pName = name;
		this.pSex = sex;
		this.pBirth = birth;
		this.pNation = nation;
		this.pAddress = address;
		this.Did = did;
		this.pSalary = salary;
		this.pAssess = access;
		this.pOther = other;
		
		if(isLegal(name,sex,birth,nation,address)) {
			Database db = new Database();
			sql = "insert into Person(pId,pName,pSex,pBirth,pNation,pAddress,DeptId,pSalary,pAccess,pOther) ";
			String temp = "values('"+pId+"','"+pName+"','"+pSex+"','"+pBirth+"','"+pNation+"','"+pAddress+"','"+Did+"','"+pSalary+"','"+pAssess+"','"+pOther+"')";
			sql += temp;
			try {
				db.OpenConn();  //打开连接
				db.UpdateInfo(sql);  //插入数据
				//删除未使用编号表中的相关的编号
				int t = Integer.valueOf(pId);
				sql = "delete from unUsedPersonId where personId='"+t+"'";
				db.UpdateInfo(sql);
				JOptionPane.showMessageDialog(null,"成功添加一条新的纪录");
			} catch(Exception ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(null,"信息添加失败","错误",JOptionPane.ERROR_MESSAGE);
			} finally {
				db.closeStmt();
				db.closeConn();
			}
		}
	}
	
	/**
	 * 该函数用来实现信息的修改。
	 * @param id
	 * @param name
	 * @param sex
	 * @param birth
	 * @param nation
	 * @param address
	 * @param did
	 * @param salary
	 * @param access
	 * @param other
	 */
	public void modifyInfo(String id,String name,String sex,String birth,String nation,String address,String did,String salary,String access,String other) {
		
		//如果信息合法
		this.pId = id;
		this.pName = name;
		this.pSex = sex;
		this.pBirth = birth;
		this.pNation = nation;
		this.pAddress = address;
		this.Did = did;
		this.pSalary = salary;
		this.pAssess = access;
		this.pOther = other;
		
		if(isLegal(name,sex,birth,nation,address))  {
			Database db = new Database();
			try {
				int temp = Integer.valueOf(id);
				sql = "update Person set pName='"+pName+"',pSex='"+pSex+"',pBirth='"+pBirth+"',pNation='"+pNation+"',pOther='"+pOther+"' where pId = '"+temp+"'";
				db.OpenConn();  //打开连接
				db.UpdateInfo(sql);
				JOptionPane.showMessageDialog(null,"修改成功");
			} catch(Exception ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(null,"信息修改失败","错误",JOptionPane.ERROR_MESSAGE);
			} finally {
				db.closeStmt();
				db.closeConn();
			}
		}
	}
	public boolean updateDept(String id,String name,String newDeptId,String oldName,String newName) {
		Database db = new Database();
		int Did = Integer.valueOf(newDeptId);
		int Pid = Integer.valueOf(id);
		sql = "update Person set DeptId='"+Did+"' where pId='"+Pid+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);  //改变部门编号
			JOptionPane.showMessageDialog(null, "人员调动成功");
			sql = "select * from jobChange where pId='"+Pid+"'";
			int time = 0;
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//获取当前时间
			Date nowTime = new Date();
			//设置时间格式
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//日期
			String date = matter.format(nowTime);
			sql = "insert into jobChange(pId,pName,oldDept,newDept,modifyTime,modifyDate) values('"+id+"','"+name+"','"+oldName+"','"+newName+"','"+String.valueOf(time)+"','"+date+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			db.closeStmt();
			db.closeConn();
			ex.printStackTrace();
			return false;
		} 
	}
	public boolean updateAssess(String id,String name,String oldAssess,String newAssess) {
		Database db = new Database();
		int temp = Integer.valueOf(id);  //将编号转换成整数
		sql = "update Person set pAccess='"+newAssess+"' where pId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null, "考核信息修改成功");
			sql = "select * from assessChange where pId='"+temp+"'";
			rs = db.QueryInfo(sql);
			int time = 0;
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//获取当前时间
			Date nowTime = new Date();
			//设置时间格式
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//日期
			String date = matter.format(nowTime);
			sql = "insert into assessChange(pId,pName,oldAssess,newAssess,modifyTime,modifyDate) values('"+id+"','"+name+"','"+oldAssess+"','"+newAssess+"','"+time+"','"+date+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	public boolean updateSalary(String id,String name,String oldSalary,String newSalary) {
		Database db = new Database();
		int temp = Integer.valueOf(id);
		sql = "update Person set pSalary='"+newSalary+"' where pId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null, "成功修改薪资");
			sql = "select * from salaryChange where pId='"+temp+"'";
			rs = db.QueryInfo(sql);
			int time = 0;
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//获取当前时间
			Date nowTime = new Date();
			//设置时间格式
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//日期
			String date = matter.format(nowTime);
			sql = "insert into salaryChange(pId,pName,oldSalary,newSalary,modifyTime,modifyDate) values('"+id+"','"+name+"','"+oldSalary+"','"+newSalary+"','"+time+"','"+date+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	
	/**
	 * 该函数用来实现返回某个员工的所有信息
	 * @param id
	 * @return
	 */
	public String[] searchInfo(String id) {
		Database db = new Database();
		String[] s = new String[15];
		int temp = Integer.valueOf(id);
		sql = "select * from Person where pId = '"+temp+"'";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.next()) {
				s[0] = rs.getString("pId");
				s[1] = rs.getString("pName");
				s[2] = rs.getString("pSex");
				s[3] = rs.getString("pBirth");
				s[4] = rs.getString("pNation");
				s[5] = rs.getString("pAddress");
				s[6] = rs.getString("DeptId");
				s[7] = rs.getString("pSalary");
				s[8] = rs.getString("pAccess");
				s[9] = rs.getString("pOther");
			} 
			else 
				s = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.closeStmt();
			db.closeConn();
		}
		return s;
	}
	/**
	 * 该方法用来实现查询该字段的所有值。
	 * @param field   字段名
	 * @return
	 */
	public String[] selectField(String field) {
		String[] s = null;
		Database db = new Database();
		int row = 0;
		try {
			sql = "select " + field + " from Person order by pId";  //查找该字段的所有值
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
			if(row == 0) {
				s = new String[1];
				s[0] = "	";
			}
			else {
				s = new String[row];
				rs.first();
				rs.previous();
				int i = 0;
				while(rs.next()) {
					s[i++] = rs.getString(1);
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.closeStmt();
			db.closeConn();
		}
		return s;
	}
	//人员调动模块使用
	public String[][] searchAll() {
		Database db = new Database();
		String[][] sn = null;
		int row = 0;
		int i = 0;
		sql = "select pId,pName,pSex,DeptId,pSalary,pAccess from Person order by pId";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
			if(row == 0) {
				sn = new String[1][6];
				sn[0][0] = "	";
				sn[0][1] = "	";
				sn[0][2] = "	";
				sn[0][3] = "	";
				sn[0][4] = "	";
				sn[0][5] = "	";
			}
			else {
				sn = new String[row][6];
				rs.first();
				rs.previous();
				while(rs.next()) {
					sn[i][0] = rs.getString("pId");
					sn[i][1] = rs.getString("pName");
					sn[i][2] = rs.getString("pSex");
					sn[i][3] = rs.getString("DeptId");
					if(sn[i][3].equals("0")) {
						sn[i][3] = "0-未分配部门";
					} 
					else {
						int temp = Integer.valueOf(sn[i][3]);
						sql = "select FatherDept,SonDept from DeptTable where DeptId='"+temp+"'";
						ResultSet rs1 = db.QueryInfo(sql);
						if(rs1.next()) {
							sn[i][3]  += "-" + rs1.getString("FatherDept") + "-" + rs1.getString("SonDept");
						}
					}
					sn[i][4] = rs.getString("pSalary");
					sn[i][5] = rs.getString("pAccess");
					i++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally{
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
	public String[][] searchAllForNode() {
		Database db = new Database();
		String[][] sn = null;
		int row = 0;
		int i = 0;
		sql = "select pId,pName,pBirth,pNation,pAddress,DeptId from Person order by pId";
		try {
			db.OpenConn();
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
			if(row == 0) {
				sn = new String[1][6];
				sn[0][0] = "	";
				sn[0][1] = "	";
				sn[0][2] = "	";
				sn[0][3] = "	";
				sn[0][4] = "	";
				sn[0][5] = "	";
			} else {
				sn = new String[row][6];
				rs.first();
				rs.previous();
				while(rs.next()) {
					sn[i][0] = rs.getString("pId");   	 //编号
					sn[i][1] = rs.getString("pName");    //姓名
					sn[i][2] = rs.getString("pBirth");   //出生年月
					sn[i][3] = rs.getString("pNation");  //民族
					sn[i][4] = rs.getString("pAddress"); //地址
					sn[i][5] = rs.getString("DeptId");   //部门
					if(sn[i][5].equals("0")) {
						sn[i][5] = "未分配部门";
					} else {
						int temp = Integer.valueOf(sn[i][5]);
						sql = "select FatherDept,SonDept from DeptTable where DeptId = '"+temp+"'";
						ResultSet rs1 = db.QueryInfo(sql);
						if(rs1 != null) {
							if(rs1.next()) {
								sn[i][5] = "" + rs1.getString("FatherDept") +"-" + rs1.getString("SonDept");
							}
						}
					}
					i++;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
	public boolean deleteInfo(String id) {
		this.pId = id;
		int temp = Integer.valueOf(pId);
		Database db = new Database();
		sql = "delete from Person where pId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null,"成功删除一条记录");
			sql = "insert into unUsedPersonId(pId) values('"+temp+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "删除信息失败");
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	/**
	 * 该函数实现获取新编号的功能
	 * @return 返回新编号
	 */
	public int getNewId() {
		Database db = new Database();
		try{
			db.OpenConn();   //打开连接
			sql = "select * from Person";   //获取员工表的记录条数
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
				sql = "select * from unUsedPersonId";
				int Min = db.getMinId(sql);
				db.closeStmt();
				db.closeConn();
				return Min;
			}
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null,"出错","错误",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return -1;
		}		
	}
}
