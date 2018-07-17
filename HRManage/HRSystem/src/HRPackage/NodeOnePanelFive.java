package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.DeptBean;

/**
 * 该模块用来实现部门管理的功能，包括部门添加，部门修改，
 * 部门删除等操作。
 * @author YaXin Wen
 * @date 2017/11/17
 */
public class NodeOnePanelFive extends JPanel implements ActionListener, ListSelectionListener {

	//定义所需要用的面板
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//定义三个需要的文本标签
	JLabel departIdLabel;   	//部门编号
	JLabel firstLevelDepart;    //一级部门
	JLabel secondLevelDepart;   //二级部门
	
	//定义三个所需的文本框
	JTextField departIdText = new JTextField(10);   	//用来显示或填写部门编号
	JTextField firstLevelText = new JTextField(15);	    //用来显示或填写一级部门
	JTextField secondLevelText = new JTextField(15); 	//用来显示或填写二级部门
	
	//定义五个按钮
	//获取新编号按钮，点击此按钮，为当前要添加的部门分配新的表闹
	JButton getNewIdBtn = new JButton("获取新编号");     
	//添加按钮，点击该按钮可以实现增加部门的功能
	JButton addInfoBtn = new JButton("增加");
	//修改按钮，点击该按钮可以实现部门的修改功能
	JButton modifyInfoBtn = new JButton("修改");
	//删除按钮，点击该按钮实现删除该部门
	JButton deleteInfoBtn = new JButton("删除");
	//清空按钮，当点击清空按钮的时候，清空文本框内的值
	JButton clearInfoBtn = new JButton("清空");
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//定义表格的字段
	String[] colName = {"部门编号","一级部门","二级部门"};
	//用于存放表格记录的二维数组
	String[][] colValue;
	
	//定义网格布局方式
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon = new GridBagConstraints();
	
	/**
	 * 构造函数完成类的初始化工作。
	 */
	public NodeOnePanelFive() {
		//设计当前面板采用BorderLayout布局
		this.setLayout(new BorderLayout());
		try {
			upPanelInit();	   //上部面板初始化
			centerPanelInit(); //中部面板初始化
			bottomPanelInit(); //下部面板的布局
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 上部面板初始化，上部面板主要用来盛放部门表
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		//DeptBean可以完成对部门信息的各种操作
		DeptBean bean = new DeptBean();
		try {
			//获取部门表中的信息并返回给colValue
			colValue = bean.searchAll();
			//将从表中获取到的值绑定到表格上
			table = new JTable(colValue,colName);
			//设置表格大小
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			//设置表格选择记录时只能选择一行
			listSelectionModel = table.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//添加事件监听器
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
	 * 中部面板的布局，将所需要的文本框和所需要的标签添加
	 * 到中部面板
	 * @throws Exception
	 */
	public void centerPanelInit() throws Exception {
		//设置标签文本，和字体大小
		departIdLabel = new JLabel("编 号:");
		departIdLabel.setFont(new Font("Dialog",0,15));
		//添加编号标签和其对应的文本框
		centerPanel.add(departIdLabel);
		centerPanel.add(departIdText);
		
		//设置标签文本，和字体大小
		firstLevelDepart = new JLabel("一级部门:") ;
		firstLevelDepart.setFont(new Font("Dialog",0,15));
		//添加一级部门标签和其对应的文本框
		centerPanel.add(firstLevelDepart);
		centerPanel.add(firstLevelText);
		
		//设置标签文本，和字体大小
		secondLevelDepart = new JLabel("二级部门:");
		secondLevelDepart.setFont(new Font("Dialog",0,15));
		//添加二级部门标签和其对应的文本框
		centerPanel.add(secondLevelDepart);
		centerPanel.add(secondLevelText);
		
		/*初始所有文本框都设置为不可用，当在表格总选中一条记录的
		 * 时候，或者点击获取编号按钮的时候（即欲添加新部门的时候）
		 * 将部分文本再设置为可用。
		 */
		departIdText.setEditable(false);
		firstLevelText.setEditable(false);
		secondLevelText.setEditable(false);
		//将中部面板添加到主面板的中部
		this.add(centerPanel);
	}
	/**
	 * 下部面板的初始化，将各个按钮添加到下部面板上
	 */
	public void bottomPanelInit(){
		//获取新编号按钮，点击按钮，分配一个编号给当前欲添加的部门
		getNewIdBtn.setFont(new Font("Dialog",0,15));
		getNewIdBtn.addActionListener(this);
		bottomPanel.add(getNewIdBtn);
		
		//添加新部门按钮，点击按钮，向部门表中添加一条新记录
		addInfoBtn.setFont(new Font("Dialog",0,15));
		addInfoBtn.addActionListener(this);
		bottomPanel.add(addInfoBtn);
		
		//修改按钮，点击按钮，可以修改部门的名称
		modifyInfoBtn.setFont(new Font("Dialog",0,15));
		modifyInfoBtn.addActionListener(this);
		bottomPanel.add(modifyInfoBtn);
		
		//删除部门按钮，点击按钮，如果员工表中没有该部门的员工，则允许删除一条记录。
		deleteInfoBtn.setFont(new Font("Dialog",0,15));
		deleteInfoBtn.addActionListener(this);
		bottomPanel.add(deleteInfoBtn);
		
		//清空按钮，当点击清空按钮，清空所有文本框中的值。
		clearInfoBtn.setFont(new Font("Dialog",0,15));
		clearInfoBtn.addActionListener(this);
		bottomPanel.add(clearInfoBtn);
		
		//初始各个按钮均不可用。
		getNewIdBtn.setEnabled(true);
		addInfoBtn.setEnabled(false);
		modifyInfoBtn.setEnabled(false);
		deleteInfoBtn.setEnabled(false);
		clearInfoBtn.setEnabled(true);
		
		//将下部面板添加到主面板的下部。
		this.add(bottomPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * 当选中表中的一条记录时，将该记录的信息显示在下部的文本框
	 */
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// TODO Auto-generated method stub
		int selectionRow = table.getSelectedRow();
		for(int i = 0; i < 3; i++) {
			//将选中的记录的信息显示在下部的文本框
			departIdText.setText(colValue[selectionRow][0]);
			firstLevelText.setText(colValue[selectionRow][1]);
			secondLevelText.setText(colValue[selectionRow][2]);
		}
		firstLevelText.setEditable(true); 	 //一级部门设置为可编辑
		secondLevelText.setEditable(true);   //二级部门设置为可编辑
		addInfoBtn.setEnabled(false);        //添加信息按钮设置为不可用
		modifyInfoBtn.setEnabled(true);      //修改信息按钮设置为可用
		deleteInfoBtn.setEnabled(true);      //删除信息按钮设置为可用
		clearInfoBtn.setEnabled(true);       //清空各项信息
	}
	/**
	 * 为某些控件编写事件响应方法
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		/**
		 * 如果当前点击的按钮是获取新编号，则为将要添加的
		 * 的部门信息分配一个新的编号
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
			//如果当前要添加该记录。则将该记录添加到表中，并更新表。
			DeptBean bean = new DeptBean();
			try {
				boolean success = bean.addInfo(departIdText.getText(), firstLevelText.getText(), secondLevelText.getText());
				//如果插入成功，重新生成界面
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
			/*如果要修改当前记录，人员信息表中由于存在的是部门的编号，
			 * 所以其相应信息已经根着改变。*/
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
			/*如果要删除该部门，则如果人员表中没有该部门的人了，则允许删除，否则不允许删除*/
			DeptBean bean = new DeptBean();
			try {
				//判断该id在员工信息表中是否存在，如果存在，则不能删除，否则可以删除，并将该编号存入unUsedDepartId
				boolean exist = bean.isExist(departIdText.getText());  
				if(exist == false) {
					bean.deleteInfo(departIdText.getText());
					NodeOnePanelFive nodePanel15 = new NodeOnePanelFive();
					HrMain.splitPane.setRightComponent(nodePanel15);
				}
				else {
					JOptionPane.showMessageDialog(null,"该部门中尚有员工，不可删除");
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
	 * 对所有文本框进行制空操作。
	 */
	void setNull() {
		departIdText.setText(null);
		firstLevelText.setText(null);
		secondLevelText.setText(null);
	}
}
