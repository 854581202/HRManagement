package LaborManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.PersonBean;
import HRPackage.HrMain;

/**
 * @author Yaxin Wen
 * @date 2017/11/18
 */
public class NodeFourPanelOne extends JPanel implements ActionListener, ListSelectionListener {

	//�������õ����
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//�����ĸ�����ľ�̬�ı�
	JLabel title = new JLabel("���ʷ���");
	JLabel pNameLabel = new JLabel("����:");
	JLabel oldSalaryLabel = new JLabel("����ǰ�Ĺ���:");
	JLabel newSalaryLabel = new JLabel("������Ĺ���:");
	
	//����������Ҫ���ı���
	JTextField pNameText = new JTextField(10);
	JTextField oldSalaryText = new JTextField(10);
	JTextField newSalaryText = new JTextField(10);
	
	//������Ҫ�õ�������ť
	JButton okBtn = new JButton("ȷ��");
	JButton clearBtn = new JButton("���");
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"����","����","�Ա�","����","н��","������Ϣ"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	String pId;   	//Ա�����
	String pName;   //Ա������
	String oldSalary;  //ԭ���Ĺ���
	String newSalary;  //�µ����Ĺ��ʡ�
	
	public NodeFourPanelOne() {
		//���ò��ַ�ʽΪBorderLayout
		this.setLayout(new BorderLayout()); 
		try {
			upPanelInit();   //�ϲ���岼��
			centerPanelInit();
			bottomPanelInit();
			addActionListener();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void upPanelInit() throws Exception{
		PersonBean bean = new PersonBean();
		upPanel.setLayout(gridBag);
		
		try {
			title.setFont(new Font("Dialog",0,25));
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
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//�в����Ĳ���
	public void centerPanelInit() throws Exception {
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldSalaryLabel.setFont(new Font("Dialog",0,15));
		newSalaryLabel.setFont(new Font("Dialog",0,15));
		
		//���ؼ���ӵ������
		centerPanel.add(pNameLabel);
		centerPanel.add(pNameText);
		centerPanel.add(oldSalaryLabel);
		centerPanel.add(oldSalaryText);
		centerPanel.add(newSalaryLabel);
		centerPanel.add(newSalaryText);
		
		pNameText.setEditable(false);
		oldSalaryText.setEditable(false);
		newSalaryText.setEditable(false);
		
		
		this.add(centerPanel);
	}
	
	public void bottomPanelInit() {
		
		okBtn.setFont(new Font("Dialog",0,15));
		clearBtn.setFont(new Font("Dialog",0,15));
		okBtn.setEnabled(false);
		clearBtn.setEnabled(true);
		bottomPanel.add(okBtn);
		bottomPanel.add(clearBtn);
		this.add(bottomPanel,BorderLayout.SOUTH);
	}
	
	public void addActionListener() {
		
		okBtn.addActionListener(this);
		clearBtn.addActionListener(this);
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// TODO Auto-generated method stub
		//�����ѡ�е�ʱ��ִ����Щ����
		int selectedRow = table.getSelectedRow();
		pId = colValue[selectedRow][0];   	//Ա�����
		pName = colValue[selectedRow][1];   //Ա������
		oldSalary = colValue[selectedRow][4]; //ԭ���Ĺ���
		
		pNameText.setText(pName);
		oldSalaryText.setText(oldSalary);
		newSalaryText.setEditable(true);
		okBtn.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = new Object();
		obj = event.getSource();
		if(obj == okBtn) {
			PersonBean bean = new PersonBean();
			try {
				newSalary = newSalaryText.getText();
				boolean success = bean.updateSalary(pId, pName, oldSalary, newSalary);
				if(success == true) {
					NodeFourPanelOne nodePanel41 = new NodeFourPanelOne();
					HrMain.splitPane.setRightComponent(nodePanel41);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if(obj == clearBtn){
			setNull();
			okBtn.setEnabled(false);
		}
	}
	void setNull() {
		pNameText.setText(null);
		oldSalaryText.setText(null);
		newSalaryText.setText(null);
		pNameText.setEditable(false);
		oldSalaryText.setEditable(false);
		newSalaryText.setEditable(false);
		okBtn.setEnabled(false);
	}

}
