package HRPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataOperator.DeptBean;
import DataOperator.PersonBean;

/**
 * ÿ���ڵ��Ӧһ����壬��Щ��尴�ո��ڵ㡢�ӽڵ���б�š�
 * 1�Žڵ��µ�1���ӽڵ㣬���������ΪNodeOnePanelOne.
 * @author Yaxin Wen
 * @date 2017/10/2
 *
 */
public class NodeOnePanelOne extends JPanel implements ActionListener, ItemListener {

	//�����Ϊ�����֣��ϲ���������ʾ��ģ�������
	JPanel upPanel = new JPanel();
	//�в��������û�ִ����Ӧ�Ĳ������������Լ�������
	JPanel centerPanel = new JPanel();
	
	//��������ǩ
	JLabel title = new JLabel("������Ա��Ϣ");     	  //˵�����֣�������ģ��Ĺ���
	JLabel pIdLabel = new JLabel();  	 	 		  //Ա�����
	JLabel pNameLabel = new JLabel();      			  //Ա������
	JLabel pSexLabel = new JLabel();       			  //Ա���Ա�
	JLabel pBirthDateLabel = new JLabel(); 			  //��������
	JLabel pNationLabel = new JLabel();    			  //����
	JLabel pAddressLabel = new JLabel();   			  //��ַ
	JLabel pDepartmentLabel = new JLabel();			  //����
	JLabel pOtherLabel = new JLabel();     			  //����
	
	//���������Ҫ���ı���
	JTextField pIdText = new JTextField(15);         //������ʾ����дԱ�����
	JTextField pNameText = new JTextField(30);       //������ʾ����дԱ������
	JTextField pSexText = new JTextField(15);        //������ʾ����дԱ���Ա�
	JTextField pBirthDateText = new JTextField(30);  //������ʾ����д��������
	JTextField pNationText = new JTextField(15);     //������ʾ����д����
	JTextField pAddressText = new JTextField(30);    //������ʾ����д��ַ
	JTextField pOtherText = new JTextField(30);      //������ʾ����д������Ϣ
	JComboBox pDepartmentCombo = null;               //����ѡ������Ϣ
	
	//������ť,���������Ϣ�������Ϣ��
	//�ð�ť����Ϊ�����ӵ�Ա��������
	JButton getNewIdButton = new JButton("��ȡ�±��");
	//�ð�ť����ִ�����Ա����Ϣ�Ĳ���
	JButton addInfoButton = new JButton("��   ��");
	//�ð�ť�����������ı���Ĳ���
	JButton clearInfoButton = new JButton("��   ��");
	
	
	JScrollPane jScrollPane1 = new JScrollPane();
	String Did = "0";             //Ա���������ű��
	String Salary = "0";   		  //Ա������
	String Assess = "δ����";      //Ա������
	
	//ʹ�����񲼾֡�
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * ���캯����ɶ���ĳ�ʼ������
	 */
	public NodeOnePanelOne() {
		this.setLayout(new BorderLayout());
		try {
			jScrollPanelInit();     //�в����
			panelInit();            //�ϲ����
			addListener();          //Ϊ�ؼ���Ӽ�����
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void jScrollPanelInit() throws Exception {
		
		//�������Ĳ���Ϊ���񲼾֡�
		centerPanel.setLayout(gridBag);
		
		//������Ա��š������ǩ��ӵ�����ָ��λ��
		gridBagCon = new GridBagConstraints();
		pIdLabel.setText("�� Ա �� �� :");
		pIdLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,10,10,1);
		gridBag.setConstraints(pIdLabel,gridBagCon);   //�ѱ���ŵ���Ӧ��λ��
		centerPanel.add(pIdLabel);
		
		//���������д��Ա��ŵ��ı��������
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,1,10,15);
		gridBag.setConstraints(pIdText, gridBagCon);
		centerPanel.add(pIdText);
		
		//�����Ա������ǩ
		gridBagCon = new GridBagConstraints();
		pNameLabel.setText("�� Ա �� �� :");
		pNameLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,15,10,1);
		gridBag.setConstraints(pNameLabel, gridBagCon);
		centerPanel.add(pNameLabel);
		
		//�����ʾ��Ա�������ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,1,10,10);
		gridBag.setConstraints(pNameText, gridBagCon);
		centerPanel.add(pNameText);
		
		//����Ա��ǩ
		gridBagCon = new GridBagConstraints();
		pSexLabel.setText("��          �� :");
		pSexLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pSexLabel, gridBagCon);
		centerPanel.add(pSexLabel);
		
		//�����д�Ա��ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pSexText, gridBagCon);
		centerPanel.add(pSexText);
		
		//��ӳ��������ı���ǩ
		gridBagCon = new GridBagConstraints();
		pBirthDateLabel.setText("�� �� �� �� :");
		pBirthDateLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pBirthDateLabel, gridBagCon);
		centerPanel.add(pBirthDateLabel);
		
		//��ӳ��������ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,10);
		gridBag.setConstraints(pBirthDateText, gridBagCon);
		centerPanel.add(pBirthDateText);
		
		//��������ǩ
		gridBagCon = new GridBagConstraints();
		pNationLabel.setText("��          �� :");
		pNationLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pNationLabel, gridBagCon);
		centerPanel.add(pNationLabel);
		
		//�����д������ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pNationText, gridBagCon);
		centerPanel.add(pNationText);
		
		//��ӵ�ַ��ǩ
		gridBagCon = new GridBagConstraints();
		pAddressLabel.setText("��          ַ :");
		pAddressLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pAddressLabel, gridBagCon);
		centerPanel.add(pAddressLabel);
		
		//��ӵ�ַ�ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,1,10,10);
		gridBag.setConstraints(pAddressText, gridBagCon);
		centerPanel.add(pAddressText);
		
		//��Ӳ��ű�ǩ
		gridBagCon = new GridBagConstraints();
		pDepartmentLabel.setText("��           �� :");
		pDepartmentLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pDepartmentLabel, gridBagCon);
		centerPanel.add(pDepartmentLabel);
		
		//��Ӳ����б�
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,1,10,15);
		DeptBean bean = new DeptBean();
		try {
			String[] values = bean.getAllNodeInfor();
			pDepartmentCombo = new JComboBox(values);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		gridBag.setConstraints(pDepartmentCombo, gridBagCon);
		centerPanel.add(pDepartmentCombo);
		
		//������ǩ
		gridBagCon = new GridBagConstraints();
		pOtherLabel.setText("��           �� :");
		pOtherLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pOtherLabel, gridBagCon);
		centerPanel.add(pOtherLabel);
		
		//������ǩ��Ӧ���ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,1,10,10);
		gridBag.setConstraints(pOtherText, gridBagCon);
		centerPanel.add(pOtherText);
		
		gridBagCon = new GridBagConstraints();
		getNewIdButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 5;
		gridBagCon.gridheight = 1;
		gridBagCon.gridwidth = 2;
		gridBagCon.insets = new Insets(15,0,10,0);
		gridBag.setConstraints(getNewIdButton, gridBagCon);
		centerPanel.add(getNewIdButton);
		
		//�����Ӱ�ť
		gridBagCon = new GridBagConstraints();
		addInfoButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 5;
		gridBagCon.gridwidth = 1;
		gridBagCon.gridheight = 1;
		gridBagCon.insets = new Insets(15,0,10,0);
		gridBag.setConstraints(addInfoButton, gridBagCon);
		centerPanel.add(addInfoButton);
		addInfoButton.setEnabled(false);
		
		//�����հ�ť
		gridBagCon = new GridBagConstraints();
		clearInfoButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 5;
		gridBagCon.gridwidth = 1;
		gridBagCon.gridheight = 1;
		gridBagCon.insets = new Insets(15,0,10,0);
		gridBag.setConstraints(clearInfoButton, gridBagCon);
		centerPanel.add(clearInfoButton);
	}
	
	/**
	 * �����г�ʼ��
	 * @throws Exception
	 */
	public void panelInit() throws Exception {
		//�������Ĳ��ַ�ʽΪ���񲼾�
		upPanel.setLayout(gridBag);
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		title.setFont(new Font("Dialog",0,25));
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		jScrollPane1 = new JScrollPane(centerPanel);
		jScrollPane1.setPreferredSize(new Dimension(900,750));
		
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,0,0,0);
		gridBag.setConstraints(jScrollPane1, gridBagCon);
		upPanel.add(jScrollPane1);
		
		this.add(upPanel,BorderLayout.NORTH);
		pIdText.setEditable(false);
		pNameText.setEditable(true);
		pSexText.setEditable(true);
		pBirthDateText.setEditable(true);
		pNationText.setEditable(true);
		pAddressText.setEditable(true);
		pOtherText.setEditable(true);
		
		addInfoButton.setEnabled(false);
	}
	
	/**
	 * Ϊ������ť����¼�������
	 * @throws Exception
	 */
	public void addListener() throws Exception {
		addInfoButton.addActionListener(this);
		clearInfoButton.addActionListener(this);
		pDepartmentCombo.addItemListener(this);
		getNewIdButton.addActionListener(this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			String temp = "" + event.getItem();
			int i = temp.indexOf("-");
			Did = temp.substring(0,i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//�¼�Դ�������Ϣ��ť
		if(obj == getNewIdButton) {
			setNull();
			PersonBean bean = new PersonBean();
			try {
				int number = bean.getNewId();
				pIdText.setText(String.valueOf(number));
				addInfoButton.setEnabled(true);
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(obj == addInfoButton) {
			//�����Ա��Ϣ�������в�������ȥ
			PersonBean bean = new PersonBean();
			Did = "0";             //Ա���������ű��
			Salary = "0";   		  //Ա������
			Assess = "δ����";      //Ա������
			try{
				bean.addInfo(pIdText.getText(), pNameText.getText(), pSexText.getText(),pBirthDateText.getText(), pNationText.getText(), pAddressText.getText(),Did,Salary, Assess, pOtherText.getText());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(obj == clearInfoButton) {
			setNull();
		}
	}
	/**
	 * �����հ�ť��ʱ����������ı����е�ֵ
	 */
	void setNull() {
		pIdText.setText(null);
		pNameText.setText(null);
		pSexText.setText(null);
		pBirthDateText.setText(null);
		pNationText.setText(null);
		pAddressText.setText(null);
		pOtherText.setText(null);
	}
}
