package PersonAccess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.PersonBean;
import HRPackage.HrMain;

/**
 * ��ģ������ʵ����Ա�Ŀ��˹��ܡ�
 * @author Yaxin Wen
 * @data 2017/11/13
 */
public class NodeThreePanelOne extends JPanel implements ActionListener, ItemListener, ListSelectionListener {
	//�������õ����
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//������������Ҫ�ľ�̬�ı�
	JLabel title = new JLabel("��Ա����");
	JLabel pNameLabel = new JLabel();
	JTextField pNameText = new JTextField(15);
	JLabel oldAccessRecordLabel = new JLabel();
	JTextField oldAccessRecordText = new JTextField(10);
	JLabel newAccessRecordLabel = new JLabel();
	JComboBox newAccessRecordCom = null;
	
	JButton okBtn = new JButton("ȷ��");
	JButton clearBtn = new JButton("���");
	
	String pId;   //��Ա���
	String pName; //��Ա����
	String oldAssess;  //�ϴο���
	String newAssess;  //���ο���
	
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"����","����","�Ա�","����","н��","������Ϣ"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon = new GridBagConstraints();
	//���캮����ɶ���ĳ�ʼ������
	public NodeThreePanelOne() {
		//���õ�ǰ��岼�ַ�ʽΪborderLayout
		this.setLayout(new BorderLayout());
		try {
			upPanelInit();
			centerPanelInit();
			bottomPanelInit();
			addActionListener();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//�ú�����������ϲ����ĳ�ʼ������
	public void upPanelInit() throws Exception{
		PersonBean bean = new PersonBean();
		//�����ϲ����Ĳ��ַ�ʽΪ���񲼾�
		upPanel.setLayout(gridBag);
		try{
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
	public void centerPanelInit() throws Exception {
		pNameLabel.setText("����:");
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldAccessRecordLabel.setText("�ϴο���:");
		oldAccessRecordLabel.setFont(new Font("Dialog",0,15));
		newAccessRecordLabel.setText("���ο���:");
		newAccessRecordLabel.setFont(new Font("Dialog",0,15));
		String[] value = {"����","�ϸ�","���ϸ�"};
		newAccessRecordCom = new JComboBox(value);
		
		pNameText.setEditable(false);
		oldAccessRecordText.setEditable(false);
		
		okBtn.setEnabled(false);
		
		centerPanel.add(pNameLabel);
		centerPanel.add(pNameText);
		centerPanel.add(oldAccessRecordLabel);
		centerPanel.add(oldAccessRecordText);
		centerPanel.add(newAccessRecordLabel);
		centerPanel.add(newAccessRecordCom);
		
		this.add(centerPanel);
	}
	public void bottomPanelInit() {
		okBtn.setFont(new Font("Dialog",0,15));
		clearBtn.setFont(new Font("Dialog",0,15));
		bottomPanel.add(okBtn);
		bottomPanel.add(clearBtn);
		this.add(bottomPanel,BorderLayout.SOUTH);
	}
	void addActionListener() {
		okBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		newAccessRecordCom.addItemListener(this);
	}
	void setNull() {
		pNameText.setText(null);
		oldAccessRecordText.setText(null);
		pNameText.setEditable(false);
		oldAccessRecordText.setEditable(false);
		okBtn.setEnabled(false);
		clearBtn.setEnabled(false);
		newAccessRecordCom.setEnabled(false);
	}
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// TODO Auto-generated method stub
		int selectedRow = table.getSelectedRow();
		pId = colValue[selectedRow][0];
		pName = colValue[selectedRow][1];
		pNameText.setText(colValue[selectedRow][1]);
		oldAccessRecordText.setText(colValue[selectedRow][5]);
		oldAssess = colValue[selectedRow][5];   //�ɵĿ�����Ϣ
		okBtn.setEnabled(true);
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			newAssess = "" + event.getItem();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//���ȷ����ť��ʱ�򣬽�����Ϣ����
		if(obj == okBtn) {
			PersonBean bean = new PersonBean();
			try {
				boolean success = bean.updateAssess(pId, pName, oldAssess, newAssess);
				if(success == true) {
					NodeThreePanelOne nodePanel31 = new NodeThreePanelOne();
					HrMain.splitPane.setRightComponent(nodePanel31);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if(obj == clearBtn) {
			setNull();
			okBtn.setEnabled(false);
		}
	}
}
