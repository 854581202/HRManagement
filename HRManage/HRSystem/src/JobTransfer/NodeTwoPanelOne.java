package JobTransfer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.DeptBean;
import DataOperator.PersonBean;
import HRPackage.HrMain;

/**
 * ��������ʵ����Ա����
 * @author Yaxin Wen
 * @data 2017/11/13
 *
 */
public class NodeTwoPanelOne extends JPanel implements ActionListener, ListSelectionListener, ItemListener {

	//�������õ����
	JPanel upPanel = new JPanel();   	//�ϲ����
	JPanel centerPanel = new JPanel();  //�в����
	JPanel bottomPanel = new JPanel();  //�²����
	
	JLabel title = new JLabel("��Ա����");  //������ģ�������
	JLabel pNameLabel = new JLabel();             
	JTextField pNameText = new JTextField(15);    //�����Ա����
	JLabel oldDepartNameLabel = new JLabel();
	JTextField oldDepartNameText = new JTextField(15);  //��ʾԭ�������Ƶ��ı���
	JLabel newDepartNameLabel = new JLabel(); 
	JComboBox newDepartNameCon = null;              
	//���������б���Ա�����ǽ���Ա��ԭ���ŵ��õ���˾�Ѵ��ڵ���һ������
	
	//������������Ҫ�İ�ť
	JButton transToNewDepartBtn = new JButton("�����²���");
	JButton clearInfoBtn = new JButton("�����Ϣ");
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//�������ֶ�
	String[] colName = {"����","����","�Ա�","����","н��","������Ϣ"};
	String[][] colValue;
	String pId;    	  	//��Ա����
	String newDeptId; 	//�²��ű��
	String newDeptName;
	
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//���캯����ɸ������ĳ�ʼ������
	public NodeTwoPanelOne() {
		//���õ�ǰ���Ĳ��ַ�ʽΪBorderLayout
		this.setLayout(new BorderLayout()); 
		try{
			upPanelInit();    		//�ϲ�����ʼ��
			centerPanelInit(); 	    //�в�����ʼ��
			bottomPanelInit();  	//�²�����ʼ��
			addActionListener();  	//Ϊ��Ӧ�Ŀؼ�����¼�������
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	//�ϲ�����ʼ������
	public void upPanelInit() throws Exception {
		PersonBean bean = new PersonBean();
		//�����ϲ����Ĳ��ַ�ʽΪ���񲼾�
		try{
			upPanel.setLayout(gridBag);
			title.setFont(new Font("Dialog",0,25));  //�����ֺ�
			gridBagCon = new GridBagConstraints();
			gridBagCon.gridx = 0;
			gridBagCon.gridy = 0;
			gridBagCon.insets = new Insets(0,10,0,10);
			gridBag.setConstraints(title,gridBagCon);
			upPanel.add(title);
			
			colValue = bean.searchAll();
			table = new JTable(colValue,colName);
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			listSelectionModel = table.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSelectionModel.addListSelectionListener(this);
			jScrollPane = new JScrollPane(table);
			jScrollPane.setPreferredSize(new Dimension(900,750));
			
			gridBagCon = new GridBagConstraints();
			gridBagCon.gridx = 0;
			gridBagCon.gridy = 1;
			gridBagCon.insets = new Insets(0,0,0,0);
			gridBag.setConstraints(jScrollPane,gridBagCon);
			upPanel.add(jScrollPane);
			this.add(upPanel,BorderLayout.NORTH);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void centerPanelInit() throws Exception{
		pNameLabel.setText("����:");   //����
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldDepartNameLabel.setText("ԭ����:");
		oldDepartNameLabel.setFont(new Font("Dialog",0,15));
		newDepartNameLabel.setText("�²���:");
		newDepartNameLabel.setFont(new Font("Dialog",0,15));
		try{
			DeptBean bean = new DeptBean();
			String[] allType = bean.getAllNodeInfor();
			newDepartNameCon = new JComboBox(allType);
			
			//����Щ�ؼ���ӵ������
			centerPanel.add(pNameLabel);
			centerPanel.add(pNameText);
			centerPanel.add(oldDepartNameLabel);
			centerPanel.add(oldDepartNameText);
			centerPanel.add(newDepartNameLabel);
			centerPanel.add(newDepartNameCon);
			
			//���������ı��򶼲��ɸ��ģ�ԭ������Ϣ������䶯
			pNameText.setEditable(false);
			oldDepartNameText.setEditable(false);
			
			this.add(centerPanel,BorderLayout.CENTER);  //���������ӵ��в�
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void bottomPanelInit() {
		transToNewDepartBtn.setFont(new Font("Dialog",0,15));
		transToNewDepartBtn.setEnabled(false);
		clearInfoBtn.setFont(new Font("Dialog",0,15));
		
		//���ؼ���ӵ��²������
		bottomPanel.add(transToNewDepartBtn);
		bottomPanel.add(clearInfoBtn);
		this.add(bottomPanel,BorderLayout.SOUTH);
	}
	public void addActionListener() {
		transToNewDepartBtn.addActionListener(this);
		clearInfoBtn.addActionListener(this);
		newDepartNameCon.addItemListener(this);
		
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			newDeptName = "" + event.getItem();
			int i = newDeptName.indexOf("-");
			newDeptId = newDeptName.substring(0,i);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		int selectedRow= table.getSelectedRow();   				//��ȡѡ�е��к�
		pNameText.setText(colValue[selectedRow][1]);  			//��ȡ����
		oldDepartNameText.setText(colValue[selectedRow][3]);	//ԭ����
		pId = colValue[selectedRow][0];   	//��ȡԱ�����š�
		transToNewDepartBtn.setEnabled(true);  //���ð�ť����
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//����¼���Դ�ǵ����²��ŵİ�ť����ִ���޸Ĳ�����
		if(obj == transToNewDepartBtn) {
			String name = pNameText.getText();  //����
			String oldDeptName = oldDepartNameText.getText();  //����
			PersonBean bean = new PersonBean();
			try {
				boolean success = bean.updateDept(pId,name, newDeptId, oldDeptName, newDeptName);
				if(success == true) {
					NodeTwoPanelOne nodePanel21 = new NodeTwoPanelOne();
					HrMain.splitPane.setRightComponent(nodePanel21);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if(obj == clearInfoBtn) {
			setNull();
			transToNewDepartBtn.setEnabled(false);
		}
	}
	void setNull() {
		pNameText.setText(null);
		oldDepartNameText.setText(null);
	}
}
