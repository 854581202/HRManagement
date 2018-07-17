package DataOperator;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 * ��������ʵ�ֶԲ��������Ϣ�������ݿ�����Ĺ���
 * @author YaXin Wen
 * @date 2017/11/15
 */
public class DeptBean {
	ResultSet rs = null;
	String sql;         //���sql���
	String Did;    		//���ű��
	String firstLevel;  //һ������
	String secondLevel; //��������
	
	String colName;    //����
	String colValue;   //��ֵ
	String colValue2;  //��ֵ
	
	/**
	 * ��������Ϣ
	 */
	public boolean addInfo(String id,String first,String second) {
		Database db = new Database();
		
		this.Did = id;
		this.firstLevel = first;
		this.secondLevel = second;
		
		if(firstLevel == null || firstLevel == "") {
			JOptionPane.showMessageDialog(null, "������һ����������","����",JOptionPane.ERROR_MESSAGE);
			return false;  //��¼���ʧ��
		}
		else if(secondLevel == null || secondLevel == "") {
			JOptionPane.showConfirmDialog(null, "�����������������","����",JOptionPane.ERROR_MESSAGE);
			return false; //��¼���ʧ��
		}
		sql = "insert into DeptTable(DeptId,FatherDept,SonDept) values('"+Did+"','"+firstLevel+"','"+secondLevel+"')";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);
			JOptionPane.showMessageDialog(null, "�ɹ����һ����¼");
			sql = "delete from unUsedDeptId where DeptId = '"+id+"'";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "����ʧ��","����",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
	}
	//��ȡ���ű��
	public int getNewId() throws Exception{
		Database db = new Database();
		db.OpenConn();   //������
		sql = "select * from DeptTable";
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
			sql = "select * from unUsedDeptId";
			int Min = db.getMinId(sql);
			db.closeStmt();
			db.closeConn();
			return Min;
		}
	}
	/**
	 * @param id  		���ű��
	 * @param first     һ����������
	 * @param second    ������������
	 * @return          �޸ĳɹ�����true,���򷵻�false
	 * @throws Exception
	 */
	public boolean modifyInfo(String id,String first,String second) throws Exception {
		Database db = new Database();
		
		this.Did = id;
		this.firstLevel = first;
		this.secondLevel = second;
		
		if(firstLevel == null || firstLevel == "") {
			JOptionPane.showMessageDialog(null, "������һ����������","����",JOptionPane.ERROR_MESSAGE);
			return false;  //��¼���ʧ��
		}
		else if(secondLevel == null || secondLevel == "") {
			JOptionPane.showConfirmDialog(null, "�����������������","����",JOptionPane.ERROR_MESSAGE);
			return false; //��¼���ʧ��
		}
		int temp = Integer.valueOf(Did);
		sql = "update DeptTable set FatherDept='"+first+"',SonDept='"+second+"' where DeptId='"+temp+"'";
		try {
			db.OpenConn();
			db.UpdateInfo(sql);  //������Ϣ
			JOptionPane.showMessageDialog(null, "�ɹ��޸�һ����¼");
			/**
			 * ����Ӧ����Ա��ϢҲ���и��¡�
			 */
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "����ʧ��","����",JOptionPane.ERROR_MESSAGE);
			db.closeStmt();
			db.closeConn();
			return false;
		}
		
	}
	/**
	 * �жϷ���Ҫ��ļ�¼�Ƿ���ڡ�������ڷ���true,���򷵻�false;
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String id) throws Exception{
		Database db = new Database();
		int temp = Integer.valueOf(id);
		sql = "select * from Person where DeptId = '"+id+"'";
		boolean flag = false;  //���費���ڡ�
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
			JOptionPane.showMessageDialog(null, "�޸�ʧ��","����",JOptionPane.ERROR_MESSAGE);
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
			db.UpdateInfo(sql);  //ɾ���ü�¼
			JOptionPane.showMessageDialog(null, "�ɹ�ɾ��һ����¼");
			//�ѱ�Ų��뵽δʹ�ñ�ű���
			sql = "insert into unUsedDeptId(DeptId) values('"+Did+"')";
			db.UpdateInfo(sql);
			db.closeStmt();
			db.closeConn();
			return true;
		} catch(Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "ɾ��ʧ��","����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "ʧ��","����",JOptionPane.ERROR_MESSAGE);
		} finally{
			db.closeStmt();
			db.closeConn();
		}
		return sn;
	}
	/**
	 * ��ѯ���м�¼
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
