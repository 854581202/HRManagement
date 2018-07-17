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

import DataOperator.PersonBean;

/**
 * ʵ���޸���Ա��Ϣ�Ľ���
 * @author Yaxin Wen
 * @date 2017/11/17
 *
 */
public class NodeOnePanelTwo extends JPanel implements ActionListener, ItemListener {

	//�����������Ҫ�õ����
	JPanel centerPanel = new JPanel();
	JPanel upPanel = new JPanel();
	
	//�����������Ҫ�ı�ǩ����̬�ı���
	JLabel title = new JLabel("�޸���Ա��Ϣ");
	
	JLabel pIdLabel = new JLabel();  	 	//Ա����� 
	JLabel pNameLabel = new JLabel();      	//Ա������
	JLabel pSexLabel = new JLabel();       	//Ա���Ա�
	JLabel pBirthDateLabel = new JLabel(); 	//��������
	JLabel pNationLabel = new JLabel();    	//����
	JLabel pAddressLabel = new JLabel();   	//��ַ
	JLabel pDepartmentLabel = new JLabel();	//����
	JLabel pOtherLabel = new JLabel();     	//����
	JLabel pInfoChoseLabel = new JLabel();  //ѡ����Ա��Ϣ��
	
	//�������������ı���
	JTextField pIdText = new JTextField(15);        //���
	JTextField pNameText = new JTextField(30);      //����
	JTextField pSexText = new JTextField(15);       //�Ա�
	JTextField pBirthDateText = new JTextField(30); //��������
	JTextField pNationText = new JTextField(15);    //����
	JTextField pAddressText = new JTextField(30);   //��ַ
	JTextField pOtherText = new JTextField(50);     //����
	
	JComboBox pInfoChoseCombo = null;               //��Ա��Ϣ�б�
	JScrollPane jScrollPane;
	
	//������������û��ʲôʵ�����塣
	String Did = "0";								//��Ա��������
	String Salary = "0";						    //��Ա����	
	String Assess = "δ����";                        //��Ա������Ϣ
	
	//����������ť��һ�����޸İ�ť��һ������հ�ť��
	JButton modifyInfoButton = new JButton("�� ��");
	JButton clearInfoButton = new JButton("�� ��");
	
	//�������񲼾�
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * ���캯����ɳ�ʼ������
	 */
	public NodeOnePanelTwo() {
		this.setLayout(new BorderLayout());
		try{
			jScrollPanelInit(); 	//�ϲ���岼��
			panelInit();            //�в���岼��
			addListener();          //Ϊ��Ӧ�ؼ�����¼�������
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	//jScrollPanel�Ĳ��֡�
	public void jScrollPanelInit() throws Exception {
		
		centerPanel.setLayout(gridBag);
		
		//�����Ա��ű�ǩ
		gridBagCon = new GridBagConstraints();
		pIdLabel.setText("�� Ա �� �� :");
		pIdLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,10,10,1);
		gridBag.setConstraints(pIdLabel, gridBagCon);
		centerPanel.add(pIdLabel);
		
		//�����ʾ��Ա�������Ҫ���ı���
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
		
		//�����Ա�Ա��ǩ
		gridBagCon = new GridBagConstraints();
		pSexLabel.setText("��          �� :");
		pSexLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pSexLabel, gridBagCon);
		centerPanel.add(pSexLabel);
		
		//�����ʾ��Ա�Ա���ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pSexText, gridBagCon);
		centerPanel.add(pSexText);
		
		//��ӳ������µı�ǩ
		gridBagCon = new GridBagConstraints();
		pBirthDateLabel.setText("�� �� �� �� :");
		pBirthDateLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pBirthDateLabel, gridBagCon);
		centerPanel.add(pBirthDateLabel);
		
		//��ӳ�������������ı���
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
		
		//��������ı���
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
		
		//���������ǩ
		gridBagCon = new GridBagConstraints();
		pOtherLabel.setText("��           �� :");
		pOtherLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pOtherLabel, gridBagCon);
		centerPanel.add(pOtherLabel);
		
		//��������ı���
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 4;
		gridBagCon.gridwidth = 3;
		gridBagCon.gridheight = 1;
		gridBagCon.insets = new Insets(15,1,10,115);
		gridBag.setConstraints(pOtherText, gridBagCon);
		centerPanel.add(pOtherText);
		
		
		//ѡ����Ա��Ϣ��ǩ
		gridBagCon = new GridBagConstraints();
		pInfoChoseLabel.setText("ѡ����Ա��Ϣ");
		pInfoChoseLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 5;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pInfoChoseLabel, gridBagCon);
		centerPanel.add(pInfoChoseLabel);
		
		//ѡ����Ա��Ҫ�õ������б��
	    PersonBean bean = new PersonBean();
	    String[] values=null;
	    try {
	    	String[] id = bean.selectField("pId");
	    	String[] name = bean.selectField("pName");
	    	int len = id.length;
	    	values = new String[len];
	    	for(int i = 0; i < len; i++) {
	    		values[i] = "" + id[i] + "-" + name[i];
	    	}
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    
		pInfoChoseCombo = new JComboBox(values);
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 5;
		gridBagCon.gridwidth = 1;
		gridBagCon.gridheight = 1;
		gridBagCon.insets = new Insets(15,10,10,10);
		gridBag.setConstraints(pInfoChoseCombo, gridBagCon);
		centerPanel.add(pInfoChoseCombo);
		
		//����޸İ�ť�������
		gridBagCon = new GridBagConstraints();
		modifyInfoButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 5;
		gridBagCon.insets = new Insets(10,10,10,10);
		gridBag.setConstraints(modifyInfoButton, gridBagCon);
		centerPanel.add(modifyInfoButton);
		
		//�����հ�ť�������
		gridBagCon = new GridBagConstraints();
		clearInfoButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 5;
		gridBagCon.insets = new Insets(10,10,10,10);
		gridBag.setConstraints(clearInfoButton, gridBagCon);
		centerPanel.add(clearInfoButton);	
		
		jScrollPane = new JScrollPane(centerPanel);
		jScrollPane.setPreferredSize(new Dimension(900,750));
		
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,0,0,0);
		gridBag.setConstraints(jScrollPane,gridBagCon);
		upPanel.add(jScrollPane);
	}
	
	public void panelInit() throws Exception {
		this.setLayout(new BorderLayout());
		upPanel.setLayout(gridBag);
		gridBagCon = new GridBagConstraints();
		title.setFont(new Font("Dialog",0,25));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		//�����ı����ʼ������Ϊ�����ã�ֱ��ѡ��һ���ˣ�Ȼ���ٽ��ı�������Ϊ���á�
		pIdText.setEditable(false);
	    pNameText.setEditable(false);
	    pSexText.setEditable(false);
	    pBirthDateText.setEditable(false);
	    pNationText.setEditable(false);
	    pAddressText.setEditable(false);
	    pOtherText.setEditable(false);
	    
	    modifyInfoButton.setEnabled(false);
	    
	    this.add(upPanel,BorderLayout.NORTH);
	}
	/**
	 * ΪһЩ�ؼ�����¼�������
	 * @throws Exception
	 */
	public void addListener() throws Exception {
		modifyInfoButton.addActionListener(this);
		clearInfoButton.addActionListener(this);
		pInfoChoseCombo.addItemListener(this);
	}
	
	/**
	 * ʵ�ֶ������б����¼�����Ӧ��
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			String temp = "" + event.getItem();   //��ȡ��ѡ�е���Ϣ
			int i = temp.indexOf("-");           //�ҵ�'-'���ڵ�λ�ã�Ȼ��������ִ��Ĳ���
			String pId = ""  + temp.substring(0,i);    //���Ҹñ����Ա��������Ϣ
			PersonBean bean = new PersonBean();
			//���Ҹñ�Ŷ�Ӧ��Ա��������Ϣ��������ʾ���ı�����
			String[] info = bean.searchInfo(pId); 
			pIdText.setText(info[0]);         
			pNameText.setText(info[1]);
			pSexText.setText(info[2]);
			pBirthDateText.setText(info[3]);
			pNationText.setText(info[4]);
			pAddressText.setText(info[5]);
			Did = info[6];
			Salary = info[7];
			Assess = info[8];
			pOtherText.setText(info[9]);
			
			//�����޸İ�ť����
			modifyInfoButton.setEnabled(true);
			
			//һ��һ����������������ַ��������Ϣ���ܸı䣬������ϢӦ�ò���ı�
		    pNameText.setEditable(true);
		    pAddressText.setEditable(true);
		    pOtherText.setEditable(true);
		}
	}

	/**
	 * �����ͬ�İ�ť��ִ�в�ͬ�Ĳ�����
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		//�޸İ�ť,ִ�и��²���
		if(event.getSource() == modifyInfoButton) {
			PersonBean bean = new PersonBean();
			try {
				bean.modifyInfo(pIdText.getText(), pNameText.getText(), pSexText.getText(), pBirthDateText.getText(),pNationText.getText(), pAddressText.getText(),Did, Salary, Assess, pOtherText.getText());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} 
		else if(event.getSource() == clearInfoButton) {
			//�����հ�ť����������Ϣ���
			setNull();
		}
	}
	void setNull() {
		//�����������ÿ�
		pIdText.setText(null);
	    pNameText.setText(null);
	    pSexText.setText(null);
	    pBirthDateText.setText(null);
	    pNationText.setText(null);
	    pAddressText.setText(null);
	    pOtherText.setText(null);
	    //�����а�ť��������Ϊ�����õ�״̬
		pIdText.setEditable(false);
	    pNameText.setEditable(false);
	    pSexText.setEditable(false);
	    pBirthDateText.setEditable(false);
	    pNationText.setEditable(false);
	    pAddressText.setEditable(false);
	    pOtherText.setEditable(false);
	}
}
