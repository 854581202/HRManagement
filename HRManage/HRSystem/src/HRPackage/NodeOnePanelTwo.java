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
 * 实现修改人员信息的界面
 * @author Yaxin Wen
 * @date 2017/11/17
 *
 */
public class NodeOnePanelTwo extends JPanel implements ActionListener, ItemListener {

	//定义界面所需要用的面板
	JPanel centerPanel = new JPanel();
	JPanel upPanel = new JPanel();
	
	//定义界面所需要的标签（静态文本）
	JLabel title = new JLabel("修改人员信息");
	
	JLabel pIdLabel = new JLabel();  	 	//员工编号 
	JLabel pNameLabel = new JLabel();      	//员工姓名
	JLabel pSexLabel = new JLabel();       	//员工性别
	JLabel pBirthDateLabel = new JLabel(); 	//出生年月
	JLabel pNationLabel = new JLabel();    	//民族
	JLabel pAddressLabel = new JLabel();   	//地址
	JLabel pDepartmentLabel = new JLabel();	//部门
	JLabel pOtherLabel = new JLabel();     	//其他
	JLabel pInfoChoseLabel = new JLabel();  //选择人员信息。
	
	//定义界面所需的文本框
	JTextField pIdText = new JTextField(15);        //编号
	JTextField pNameText = new JTextField(30);      //姓名
	JTextField pSexText = new JTextField(15);       //性别
	JTextField pBirthDateText = new JTextField(30); //出生年月
	JTextField pNationText = new JTextField(15);    //民族
	JTextField pAddressText = new JTextField(30);   //地址
	JTextField pOtherText = new JTextField(50);     //其他
	
	JComboBox pInfoChoseCombo = null;               //人员信息列表。
	JScrollPane jScrollPane;
	
	//这三个变量并没有什么实际意义。
	String Did = "0";								//人员所属部门
	String Salary = "0";						    //人员工资	
	String Assess = "未考核";                        //人员考核信息
	
	//定义两个按钮，一个是修改按钮，一个是清空按钮。
	JButton modifyInfoButton = new JButton("修 改");
	JButton clearInfoButton = new JButton("清 空");
	
	//定义网格布局
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * 构造函数完成初始化工作
	 */
	public NodeOnePanelTwo() {
		this.setLayout(new BorderLayout());
		try{
			jScrollPanelInit(); 	//上部面板布局
			panelInit();            //中部面板布局
			addListener();          //为相应控件添加事件监听器
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	//jScrollPanel的布局。
	public void jScrollPanelInit() throws Exception {
		
		centerPanel.setLayout(gridBag);
		
		//添加人员编号标签
		gridBagCon = new GridBagConstraints();
		pIdLabel.setText("人 员 编 号 :");
		pIdLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,10,10,1);
		gridBag.setConstraints(pIdLabel, gridBagCon);
		centerPanel.add(pIdLabel);
		
		//添加显示人员标号所需要的文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,1,10,15);
		gridBag.setConstraints(pIdText, gridBagCon);
		centerPanel.add(pIdText);
		
		//添加人员姓名标签
		gridBagCon = new GridBagConstraints();
		pNameLabel.setText("人 员 姓 名 :");
		pNameLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,15,10,1);
		gridBag.setConstraints(pNameLabel, gridBagCon);
		centerPanel.add(pNameLabel);
		
		//添加显示人员姓名的文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,1,10,10);
		gridBag.setConstraints(pNameText, gridBagCon);
		centerPanel.add(pNameText);
		
		//添加人员性别标签
		gridBagCon = new GridBagConstraints();
		pSexLabel.setText("性          别 :");
		pSexLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pSexLabel, gridBagCon);
		centerPanel.add(pSexLabel);
		
		//添加显示人员性别的文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pSexText, gridBagCon);
		centerPanel.add(pSexText);
		
		//添加出生年月的标签
		gridBagCon = new GridBagConstraints();
		pBirthDateLabel.setText("出 生 年 月 :");
		pBirthDateLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pBirthDateLabel, gridBagCon);
		centerPanel.add(pBirthDateLabel);
		
		//添加出生年月所需的文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,10);
		gridBag.setConstraints(pBirthDateText, gridBagCon);
		centerPanel.add(pBirthDateText);
		
		//添加民族标签
		gridBagCon = new GridBagConstraints();
		pNationLabel.setText("民          族 :");
		pNationLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pNationLabel, gridBagCon);
		centerPanel.add(pNationLabel);
		
		//添加民族文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pNationText, gridBagCon);
		centerPanel.add(pNationText);
		
		//添加地址标签
		gridBagCon = new GridBagConstraints();
		pAddressLabel.setText("地          址 :");
		pAddressLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pAddressLabel, gridBagCon);
		centerPanel.add(pAddressLabel);
		
	    //添加地址文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 3;
		gridBagCon.gridy = 3;
		gridBagCon.insets = new Insets(15,1,10,10);
		gridBag.setConstraints(pAddressText, gridBagCon);
		centerPanel.add(pAddressText);
		
		//添加其他标签
		gridBagCon = new GridBagConstraints();
		pOtherLabel.setText("其           他 :");
		pOtherLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pOtherLabel, gridBagCon);
		centerPanel.add(pOtherLabel);
		
		//添加其他文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 4;
		gridBagCon.gridwidth = 3;
		gridBagCon.gridheight = 1;
		gridBagCon.insets = new Insets(15,1,10,115);
		gridBag.setConstraints(pOtherText, gridBagCon);
		centerPanel.add(pOtherText);
		
		
		//选择人员信息标签
		gridBagCon = new GridBagConstraints();
		pInfoChoseLabel.setText("选择人员信息");
		pInfoChoseLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 5;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pInfoChoseLabel, gridBagCon);
		centerPanel.add(pInfoChoseLabel);
		
		//选择人员需要用的下拉列表框
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
		
		//添加修改按钮到面板上
		gridBagCon = new GridBagConstraints();
		modifyInfoButton.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 5;
		gridBagCon.insets = new Insets(10,10,10,10);
		gridBag.setConstraints(modifyInfoButton, gridBagCon);
		centerPanel.add(modifyInfoButton);
		
		//添加清空按钮到面板上
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
		
		//所有文本框初始都设置为不可用，直到选中一个人，然后再将文本框设置为可用。
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
	 * 为一些控件添加事件监听器
	 * @throws Exception
	 */
	public void addListener() throws Exception {
		modifyInfoButton.addActionListener(this);
		clearInfoButton.addActionListener(this);
		pInfoChoseCombo.addItemListener(this);
	}
	
	/**
	 * 实现对下拉列表发出事件的响应。
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			String temp = "" + event.getItem();   //获取被选中的信息
			int i = temp.indexOf("-");           //找到'-'所在的位置，然后进行求字串的操作
			String pId = ""  + temp.substring(0,i);    //查找该编号人员的所有信息
			PersonBean bean = new PersonBean();
			//查找该编号对应人员的所有信息，并且显示在文本框内
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
			
			//设置修改按钮可用
			modifyInfoButton.setEnabled(true);
			
			//一般一个人他的姓名、地址和其他信息可能改变，其他信息应该不会改变
		    pNameText.setEditable(true);
		    pAddressText.setEditable(true);
		    pOtherText.setEditable(true);
		}
	}

	/**
	 * 点击不同的按钮，执行不同的操作。
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		//修改按钮,执行更新操作
		if(event.getSource() == modifyInfoButton) {
			PersonBean bean = new PersonBean();
			try {
				bean.modifyInfo(pIdText.getText(), pNameText.getText(), pSexText.getText(), pBirthDateText.getText(),pNationText.getText(), pAddressText.getText(),Did, Salary, Assess, pOtherText.getText());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} 
		else if(event.getSource() == clearInfoButton) {
			//点击清空按钮，将所有信息清空
			setNull();
		}
	}
	void setNull() {
		//把所有内容置空
		pIdText.setText(null);
	    pNameText.setText(null);
	    pSexText.setText(null);
	    pBirthDateText.setText(null);
	    pNationText.setText(null);
	    pAddressText.setText(null);
	    pOtherText.setText(null);
	    //将所有按钮重新设置为不可用的状态
		pIdText.setEditable(false);
	    pNameText.setEditable(false);
	    pSexText.setEditable(false);
	    pBirthDateText.setEditable(false);
	    pNationText.setEditable(false);
	    pAddressText.setEditable(false);
	    pOtherText.setEditable(false);
	}
}
