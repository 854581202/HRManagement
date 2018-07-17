package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.DeptBean;

/**
 * ��ģ������ʵ�ֲ��Ź���Ĺ��ܣ�����������ӣ������޸ģ�
 * ����ɾ���Ȳ�����
 * @author YaXin Wen
 * @date 2017/11/17
 */
public class NodeOnePanelFive extends JPanel implements ActionListener, ListSelectionListener {

	//��������Ҫ�õ����
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//����������Ҫ���ı���ǩ
	JLabel departIdLabel;   	//���ű��
	JLabel firstLevelDepart;    //һ������
	JLabel secondLevelDepart;   //��������
	
	//��������������ı���
	JTextField departIdText = new JTextField(10);   	//������ʾ����д���ű��
	JTextField firstLevelText = new JTextField(15);	    //������ʾ����дһ������
	JTextField secondLevelText = new JTextField(15); 	//������ʾ����д��������
	
	//���������ť
	//��ȡ�±�Ű�ť������˰�ť��Ϊ��ǰҪ��ӵĲ��ŷ����µı���
	JButton getNewIdBtn = new JButton("��ȡ�±��");     
	//��Ӱ�ť������ð�ť����ʵ�����Ӳ��ŵĹ���
	JButton addInfoBtn = new JButton("����");
	//�޸İ�ť������ð�ť����ʵ�ֲ��ŵ��޸Ĺ���
	JButton modifyInfoBtn = new JButton("�޸�");
	//ɾ����ť������ð�ťʵ��ɾ���ò���
	JButton deleteInfoBtn = new JButton("ɾ��");
	//��հ�ť���������հ�ť��ʱ������ı����ڵ�ֵ
	JButton clearInfoBtn = new JButton("���");
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//��������ֶ�
	String[] colName = {"���ű��","һ������","��������"};
	//���ڴ�ű���¼�Ķ�ά����
	String[][] colValue;
	
	//�������񲼾ַ�ʽ
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon = new GridBagConstraints();
	
	/**
	 * ���캯�������ĳ�ʼ��������
	 */
	public NodeOnePanelFive() {
		//��Ƶ�ǰ������BorderLayout����
		this.setLayout(new BorderLayout());
		try {
			upPanelInit();	   //�ϲ�����ʼ��
			centerPanelInit(); //�в�����ʼ��
			bottomPanelInit(); //�²����Ĳ���
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * �ϲ�����ʼ�����ϲ������Ҫ����ʢ�Ų��ű�
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		//DeptBean������ɶԲ�����Ϣ�ĸ��ֲ���
		DeptBean bean = new DeptBean();
		try {
			//��ȡ���ű��е���Ϣ�����ظ�colValue
			colValue = bean.searchAll();
			//���ӱ��л�ȡ����ֵ�󶨵������
			table = new JTable(colValue,colName);
			//���ñ���С
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			//���ñ��ѡ���¼ʱֻ��ѡ��һ��
			listSelectionModel = table.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//����¼�������
			listSelectionModel.addListSelectionListener(this);
			jScrollPane = new JScrollPane(table);
			jScrollPane.setPreferredSize(new Dimension(900,750));
				
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		upPanel.add(jScrollPane);
		this.add(upPanel,BorderLayout.NORTH);
	}
	/**
	 * �в����Ĳ��֣�������Ҫ���ı��������Ҫ�ı�ǩ���
	 * ���в����
	 * @throws Exception
	 */
	public void centerPanelInit() throws Exception {
		//���ñ�ǩ�ı����������С
		departIdLabel = new JLabel("�� ��:");
		departIdLabel.setFont(new Font("Dialog",0,15));
		//��ӱ�ű�ǩ�����Ӧ���ı���
		centerPanel.add(departIdLabel);
		centerPanel.add(departIdText);
		
		//���ñ�ǩ�ı����������С
		firstLevelDepart = new JLabel("һ������:") ;
		firstLevelDepart.setFont(new Font("Dialog",0,15));
		//���һ�����ű�ǩ�����Ӧ���ı���
		centerPanel.add(firstLevelDepart);
		centerPanel.add(firstLevelText);
		
		//���ñ�ǩ�ı����������С
		secondLevelDepart = new JLabel("��������:");
		secondLevelDepart.setFont(new Font("Dialog",0,15));
		//��Ӷ������ű�ǩ�����Ӧ���ı���
		centerPanel.add(secondLevelDepart);
		centerPanel.add(secondLevelText);
		
		/*��ʼ�����ı�������Ϊ�����ã����ڱ����ѡ��һ����¼��
		 * ʱ�򣬻��ߵ����ȡ��Ű�ť��ʱ�򣨼�������²��ŵ�ʱ��
		 * �������ı�������Ϊ���á�
		 */
		departIdText.setEditable(false);
		firstLevelText.setEditable(false);
		secondLevelText.setEditable(false);
		//���в������ӵ��������в�
		this.add(centerPanel);
	}
	/**
	 * �²����ĳ�ʼ������������ť��ӵ��²������
	 */
	public void bottomPanelInit(){
		//��ȡ�±�Ű�ť�������ť������һ����Ÿ���ǰ����ӵĲ���
		getNewIdBtn.setFont(new Font("Dialog",0,15));
		getNewIdBtn.addActionListener(this);
		bottomPanel.add(getNewIdBtn);
		
		//����²��Ű�ť�������ť�����ű������һ���¼�¼
		addInfoBtn.setFont(new Font("Dialog",0,15));
		addInfoBtn.addActionListener(this);
		bottomPanel.add(addInfoBtn);
		
		//�޸İ�ť�������ť�������޸Ĳ��ŵ�����
		modifyInfoBtn.setFont(new Font("Dialog",0,15));
		modifyInfoBtn.addActionListener(this);
		bottomPanel.add(modifyInfoBtn);
		
		//ɾ�����Ű�ť�������ť�����Ա������û�иò��ŵ�Ա����������ɾ��һ����¼��
		deleteInfoBtn.setFont(new Font("Dialog",0,15));
		deleteInfoBtn.addActionListener(this);
		bottomPanel.add(deleteInfoBtn);
		
		//��հ�ť���������հ�ť����������ı����е�ֵ��
		clearInfoBtn.setFont(new Font("Dialog",0,15));
		clearInfoBtn.addActionListener(this);
		bottomPanel.add(clearInfoBtn);
		
		//��ʼ������ť�������á�
		getNewIdBtn.setEnabled(true);
		addInfoBtn.setEnabled(false);
		modifyInfoBtn.setEnabled(false);
		deleteInfoBtn.setEnabled(false);
		clearInfoBtn.setEnabled(true);
		
		//���²������ӵ��������²���
		this.add(bottomPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * ��ѡ�б��е�һ����¼ʱ�����ü�¼����Ϣ��ʾ���²����ı���
	 */
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// TODO Auto-generated method stub
		int selectionRow = table.getSelectedRow();
		for(int i = 0; i < 3; i++) {
			//��ѡ�еļ�¼����Ϣ��ʾ���²����ı���
			departIdText.setText(colValue[selectionRow][0]);
			firstLevelText.setText(colValue[selectionRow][1]);
			secondLevelText.setText(colValue[selectionRow][2]);
		}
		firstLevelText.setEditable(true); 	 //һ����������Ϊ�ɱ༭
		secondLevelText.setEditable(true);   //������������Ϊ�ɱ༭
		addInfoBtn.setEnabled(false);        //�����Ϣ��ť����Ϊ������
		modifyInfoBtn.setEnabled(true);      //�޸���Ϣ��ť����Ϊ����
		deleteInfoBtn.setEnabled(true);      //ɾ����Ϣ��ť����Ϊ����
		clearInfoBtn.setEnabled(true);       //��ո�����Ϣ
	}
	/**
	 * ΪĳЩ�ؼ���д�¼���Ӧ����
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		/**
		 * �����ǰ����İ�ť�ǻ�ȡ�±�ţ���Ϊ��Ҫ��ӵ�
		 * �Ĳ�����Ϣ����һ���µı��
		 */
		if(event.getSource() == getNewIdBtn) {
			setNull();
			DeptBean bean = new DeptBean();
			try {
				int newId = bean.getNewId();
				departIdText.setText(String.valueOf(newId));
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			firstLevelText.setEditable(true);
		    secondLevelText.setEditable(true);
		    addInfoBtn.setEnabled(true);
		    modifyInfoBtn.setEnabled(false);
		    deleteInfoBtn.setEnabled(false);
		} 
		else if(event.getSource() == addInfoBtn) { 
			//�����ǰҪ��Ӹü�¼���򽫸ü�¼��ӵ����У������±�
			DeptBean bean = new DeptBean();
			try {
				boolean success = bean.addInfo(departIdText.getText(), firstLevelText.getText(), secondLevelText.getText());
				//�������ɹ����������ɽ���
				if(success == true) {
					NodeOnePanelFive nodePanel15 = new NodeOnePanelFive();
					HrMain.splitPane.setRightComponent(nodePanel15);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		} 
		else if(event.getSource() == modifyInfoBtn) {
			/*���Ҫ�޸ĵ�ǰ��¼����Ա��Ϣ�������ڴ��ڵ��ǲ��ŵı�ţ�
			 * ��������Ӧ��Ϣ�Ѿ����Ÿı䡣*/
			DeptBean bean = new DeptBean();
			try {
				boolean success = bean.modifyInfo(departIdText.getText(), firstLevelText.getText(), secondLevelText.getText());
				if(success == true) {
					NodeOnePanelFive nodePanel15 = new NodeOnePanelFive();
					HrMain.splitPane.setRightComponent(nodePanel15);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		} 
		else if(event.getSource() == deleteInfoBtn) {
			/*���Ҫɾ���ò��ţ��������Ա����û�иò��ŵ����ˣ�������ɾ������������ɾ��*/
			DeptBean bean = new DeptBean();
			try {
				//�жϸ�id��Ա����Ϣ�����Ƿ���ڣ�������ڣ�����ɾ�����������ɾ���������ñ�Ŵ���unUsedDepartId
				boolean exist = bean.isExist(departIdText.getText());  
				if(exist == false) {
					bean.deleteInfo(departIdText.getText());
					NodeOnePanelFive nodePanel15 = new NodeOnePanelFive();
					HrMain.splitPane.setRightComponent(nodePanel15);
				}
				else {
					JOptionPane.showMessageDialog(null,"�ò���������Ա��������ɾ��");
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		} 
		else if(event.getSource() == clearInfoBtn) {
			setNull();
		}
	}
	/**
	 * �������ı�������ƿղ�����
	 */
	void setNull() {
		departIdText.setText(null);
		firstLevelText.setText(null);
		secondLevelText.setText(null);
	}
}
