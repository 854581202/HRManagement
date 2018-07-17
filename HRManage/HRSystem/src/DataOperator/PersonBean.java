package DataOperator;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * ��������ʵ��Ա����Ϣ����ɾ��ġ�
 * @author YaXin Wen
 * @date 2017/11/15
 */
public class PersonBean {
	ResultSet rs = null;
	String sql;         //���sql���
	String pId;         //Ա�����
	String pName;       //Ա������
	String pSex;        //Ա���Ա�
	String pBirth;      //��������
	String pNation;     //����
	String pAddress;    //��ַ
	String Did;         //�������ű��
	String pSalary;     //����
	String pAssess;     //�Ƿ񿼺�
	String pOther;      //����
	
	/**
	 * @param name    Ա������
	 * @param sex     Ա���Ա�
	 * @param birth   Ա����������
	 * @param nation  Ա������
	 * @param address Ա����ַ
	 * @return �����Ϣ�Ϸ�������true,���򷵻�false
	 */
	public boolean isLegal(String name,String sex,String birth,String nation,String address) {
		if(name==null || name.equals("")) {
			JOptionPane.showMessageDialog(null, "����������","����",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(sex==null || sex.equals("")) {
			JOptionPane.showMessageDialog(null, "�������Ա�","����",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(birth==null || birth.equals("")) {
			JOptionPane.showMessageDialog(null, "�������������","����",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(nation==null || nation.equals("")) {
			JOptionPane.showMessageDialog(null,"����������","����",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(address==null || address.equals("")) {
			JOptionPane.showMessageDialog(null, "�������ַ","����",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param id  		Ա�����
	 * @param name		Ա������
	 * @param sex		Ա���Ա�
	 * @param birth		Ա����������
	 * @param nation	Ա������
	 * @param address	Ա����ͥסַ
	 * @param did		Ա����������
	 * @param salary	Ա��н��
	 * @param access	Ա������
	 * @param other		������Ϣ
	 */
	public void addInfo(String id,String name,String sex,String birth,String nation,String address,String did,String salary,String access,String other) {
		
		//�����Ϣ�Ϸ�
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
				db.OpenConn();  //������
				db.UpdateInfo(sql);  //��������
				//ɾ��δʹ�ñ�ű��е���صı��
				int t = Integer.valueOf(pId);
				sql = "delete from unUsedPersonId where personId='"+t+"'";
				db.UpdateInfo(sql);
				JOptionPane.showMessageDialog(null,"�ɹ����һ���µļ�¼");
			} catch(Exception ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(null,"��Ϣ���ʧ��","����",JOptionPane.ERROR_MESSAGE);
			} finally {
				db.closeStmt();
				db.closeConn();
			}
		}
	}
	
	/**
	 * �ú�������ʵ����Ϣ���޸ġ�
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
		
		//�����Ϣ�Ϸ�
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
				db.OpenConn();  //������
				db.UpdateInfo(sql);
				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
			} catch(Exception ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(null,"��Ϣ�޸�ʧ��","����",JOptionPane.ERROR_MESSAGE);
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
			db.UpdateInfo(sql);  //�ı䲿�ű��
			JOptionPane.showMessageDialog(null, "��Ա�����ɹ�");
			sql = "select * from jobChange where pId='"+Pid+"'";
			int time = 0;
			rs = db.QueryInfo(sql);
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//��ȡ��ǰʱ��
			Date nowTime = new Date();
			//����ʱ���ʽ
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//����
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
		int temp = Integer.valueOf(id);  //�����ת��������
		sql = "update Person set pAccess='"+newAssess+"' where pId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null, "������Ϣ�޸ĳɹ�");
			sql = "select * from assessChange where pId='"+temp+"'";
			rs = db.QueryInfo(sql);
			int time = 0;
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//��ȡ��ǰʱ��
			Date nowTime = new Date();
			//����ʱ���ʽ
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//����
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
			JOptionPane.showMessageDialog(null, "�ɹ��޸�н��");
			sql = "select * from salaryChange where pId='"+temp+"'";
			rs = db.QueryInfo(sql);
			int time = 0;
			if(rs.last()) {
				time = rs.getRow();
			}
			time++;
			//��ȡ��ǰʱ��
			Date nowTime = new Date();
			//����ʱ���ʽ
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//����
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
	 * �ú�������ʵ�ַ���ĳ��Ա����������Ϣ
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
	 * �÷�������ʵ�ֲ�ѯ���ֶε�����ֵ��
	 * @param field   �ֶ���
	 * @return
	 */
	public String[] selectField(String field) {
		String[] s = null;
		Database db = new Database();
		int row = 0;
		try {
			sql = "select " + field + " from Person order by pId";  //���Ҹ��ֶε�����ֵ
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
	//��Ա����ģ��ʹ��
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
						sn[i][3] = "0-δ���䲿��";
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
					sn[i][0] = rs.getString("pId");   	 //���
					sn[i][1] = rs.getString("pName");    //����
					sn[i][2] = rs.getString("pBirth");   //��������
					sn[i][3] = rs.getString("pNation");  //����
					sn[i][4] = rs.getString("pAddress"); //��ַ
					sn[i][5] = rs.getString("DeptId");   //����
					if(sn[i][5].equals("0")) {
						sn[i][5] = "δ���䲿��";
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
			JOptionPane.showMessageDialog(null,"�ɹ�ɾ��һ����¼");
			sql = "insert into unUsedPersonId(pId) values('"+temp+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ɾ����Ϣʧ��");
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	/**
	 * �ú���ʵ�ֻ�ȡ�±�ŵĹ���
	 * @return �����±��
	 */
	public int getNewId() {
		Database db = new Database();
		try{
			db.OpenConn();   //������
			sql = "select * from Person";   //��ȡԱ����ļ�¼����
			int number = db.RecordNumber(sql);   ///��ȡ��ļ�¼��
			if(number == 0) {
				db.closeStmt();
				db.closeConn();
				return 1;///�������û�м�¼��
			}
			int Max = db.getMaxId(sql);   ///��ȡ�����
			//��������ֺͱ��м�¼����һ�����򷵻�number+1
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
			JOptionPane.showMessageDialog(null,"����","����",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return -1;
		}		
	}
}
