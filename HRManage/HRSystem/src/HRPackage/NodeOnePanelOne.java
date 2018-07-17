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
 * 每个节点对应一个面板，这些面板按照父节点、子节点进行编号。
 * 1号节点下的1号子节点，其面板命名为NodeOnePanelOne.
 * @author Yaxin Wen
 * @date 2017/10/2
 *
 */
public class NodeOnePanelOne extends JPanel implements ActionListener, ItemListener {

	//界面分为两部分，上部分用来显示该模块的作用
	JPanel upPanel = new JPanel();
	//中部用来让用户执行相应的操作，来满足自己的需求。
	JPanel centerPanel = new JPanel();
	
	//定义界面标签
	JLabel title = new JLabel("增加人员信息");     	  //说明文字，表明该模块的功能
	JLabel pIdLabel = new JLabel();  	 	 		  //员工编号
	JLabel pNameLabel = new JLabel();      			  //员工姓名
	JLabel pSexLabel = new JLabel();       			  //员工性别
	JLabel pBirthDateLabel = new JLabel(); 			  //出生年月
	JLabel pNationLabel = new JLabel();    			  //民族
	JLabel pAddressLabel = new JLabel();   			  //地址
	JLabel pDepartmentLabel = new JLabel();			  //部门
	JLabel pOtherLabel = new JLabel();     			  //其他
	
	//定义界面需要的文本框
	JTextField pIdText = new JTextField(15);         //用来显示或填写员工编号
	JTextField pNameText = new JTextField(30);       //用来显示或填写员工姓名
	JTextField pSexText = new JTextField(15);        //用来显示或填写员工性别
	JTextField pBirthDateText = new JTextField(30);  //用来显示或填写出生年月
	JTextField pNationText = new JTextField(15);     //用来显示或填写民族
	JTextField pAddressText = new JTextField(30);    //用来显示或填写地址
	JTextField pOtherText = new JTextField(30);      //用来显示或填写其他信息
	JComboBox pDepartmentCombo = null;               //用来选择部门信息
	
	//两个按钮,用来添加信息和清空信息。
	//该按钮用来为新增加的员工分配编号
	JButton getNewIdButton = new JButton("获取新编号");
	//该按钮用来执行添加员工信息的操作
	JButton addInfoButton = new JButton("添   加");
	//该按钮用来完成清空文本框的操作
	JButton clearInfoButton = new JButton("清   空");
	
	
	JScrollPane jScrollPane1 = new JScrollPane();
	String Did = "0";             //员工所属部门编号
	String Salary = "0";   		  //员工工资
	String Assess = "未考核";      //员工烤鹅
	
	//使用网格布局。
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * 构造函数完成对象的初始化工作
	 */
	public NodeOnePanelOne() {
		this.setLayout(new BorderLayout());
		try {
			jScrollPanelInit();     //中部面板
			panelInit();            //上部面板
			addListener();          //为控件添加监听器
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void jScrollPanelInit() throws Exception {
		
		//设置面板的布局为网格布局。
		centerPanel.setLayout(gridBag);
		
		//将“人员编号”这个标签添加到面板的指定位置
		gridBagCon = new GridBagConstraints();
		pIdLabel.setText("人 员 编 号 :");
		pIdLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,10,10,1);
		gridBag.setConstraints(pIdLabel,gridBagCon);   //把标题放到相应的位置
		centerPanel.add(pIdLabel);
		
		//添加用来填写人员编号的文本框到面板上
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
		
		//添加性别标签
		gridBagCon = new GridBagConstraints();
		pSexLabel.setText("性          别 :");
		pSexLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pSexLabel, gridBagCon);
		centerPanel.add(pSexLabel);
		
		//添加填写性别文本框
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 1;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,1,10,15);
		gridBag.setConstraints(pSexText, gridBagCon);
		centerPanel.add(pSexText);
		
		//添加出生年月文本标签
		gridBagCon = new GridBagConstraints();
		pBirthDateLabel.setText("出 生 年 月 :");
		pBirthDateLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 2;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pBirthDateLabel, gridBagCon);
		centerPanel.add(pBirthDateLabel);
		
		//添加出生年月文本框
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
		
		//添加填写民族的文本框
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
		
		//添加部门标签
		gridBagCon = new GridBagConstraints();
		pDepartmentLabel.setText("部           门 :");
		pDepartmentLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,10,10,1);
		gridBag.setConstraints(pDepartmentLabel, gridBagCon);
		centerPanel.add(pDepartmentLabel);
		
		//添加部门列表
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
		
		//其他标签
		gridBagCon = new GridBagConstraints();
		pOtherLabel.setText("其           他 :");
		pOtherLabel.setFont(new Font("Dialog",0,15));
		gridBagCon.gridx = 2;
		gridBagCon.gridy = 4;
		gridBagCon.insets = new Insets(15,15,10,1);
		gridBag.setConstraints(pOtherLabel, gridBagCon);
		centerPanel.add(pOtherLabel);
		
		//其他标签对应的文本框
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
		
		//添加添加按钮
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
		
		//添加清空按钮
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
	 * 面板进行初始化
	 * @throws Exception
	 */
	public void panelInit() throws Exception {
		//设置面板的布局方式为网格布局
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
	 * 为各个按钮添加事件监听器
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
		//事件源是添加信息按钮
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
			//添加人员信息，将所有参数传进去
			PersonBean bean = new PersonBean();
			Did = "0";             //员工所属部门编号
			Salary = "0";   		  //员工工资
			Assess = "未考核";      //员工烤鹅
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
	 * 点击清空按钮的时候，清空所有文本框中的值
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
