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
 * 该类用于实现人员调动
 * @author Yaxin Wen
 * @data 2017/11/13
 *
 */
public class NodeTwoPanelOne extends JPanel implements ActionListener, ListSelectionListener, ItemListener {

	//定义所用的面板
	JPanel upPanel = new JPanel();   	//上部面板
	JPanel centerPanel = new JPanel();  //中部面板
	JPanel bottomPanel = new JPanel();  //下部面板
	
	JLabel title = new JLabel("人员调动");  //表明该模块的作用
	JLabel pNameLabel = new JLabel();             
	JTextField pNameText = new JTextField(15);    //存放人员姓名
	JLabel oldDepartNameLabel = new JLabel();
	JTextField oldDepartNameText = new JTextField(15);  //显示原部门名称的文本框
	JLabel newDepartNameLabel = new JLabel(); 
	JComboBox newDepartNameCon = null;              
	//定义下拉列表，人员调用是将人员从原部门调用到公司已存在的另一个部门
	
	//定义两个所需要的按钮
	JButton transToNewDepartBtn = new JButton("调入新部门");
	JButton clearInfoBtn = new JButton("清空信息");
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//定义表的字段
	String[] colName = {"工号","姓名","性别","部门","薪酬","考核信息"};
	String[][] colValue;
	String pId;    	  	//人员工号
	String newDeptId; 	//新部门编号
	String newDeptName;
	
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//构造函数完成该类对象的初始化工作
	public NodeTwoPanelOne() {
		//设置当前面板的布局方式为BorderLayout
		this.setLayout(new BorderLayout()); 
		try{
			upPanelInit();    		//上部面板初始化
			centerPanelInit(); 	    //中部面板初始化
			bottomPanelInit();  	//下部面板初始化
			addActionListener();  	//为相应的控件添加事件监听器
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	//上部面板初始化函数
	public void upPanelInit() throws Exception {
		PersonBean bean = new PersonBean();
		//设置上部面板的布局方式为网格布局
		try{
			upPanel.setLayout(gridBag);
			title.setFont(new Font("Dialog",0,25));  //设置字号
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
		pNameLabel.setText("姓名:");   //姓名
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldDepartNameLabel.setText("原部门:");
		oldDepartNameLabel.setFont(new Font("Dialog",0,15));
		newDepartNameLabel.setText("新部门:");
		newDepartNameLabel.setFont(new Font("Dialog",0,15));
		try{
			DeptBean bean = new DeptBean();
			String[] allType = bean.getAllNodeInfor();
			newDepartNameCon = new JComboBox(allType);
			
			//将这些控件添加到面板上
			centerPanel.add(pNameLabel);
			centerPanel.add(pNameText);
			centerPanel.add(oldDepartNameLabel);
			centerPanel.add(oldDepartNameText);
			centerPanel.add(newDepartNameLabel);
			centerPanel.add(newDepartNameCon);
			
			//设置所有文本框都不可更改，原来的信息不允许变动
			pNameText.setEditable(false);
			oldDepartNameText.setEditable(false);
			
			this.add(centerPanel,BorderLayout.CENTER);  //将该面板添加到中部
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void bottomPanelInit() {
		transToNewDepartBtn.setFont(new Font("Dialog",0,15));
		transToNewDepartBtn.setEnabled(false);
		clearInfoBtn.setFont(new Font("Dialog",0,15));
		
		//将控件添加到下部面板上
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
		int selectedRow= table.getSelectedRow();   				//获取选中的行号
		pNameText.setText(colValue[selectedRow][1]);  			//获取姓名
		oldDepartNameText.setText(colValue[selectedRow][3]);	//原部门
		pId = colValue[selectedRow][0];   	//获取员工工号。
		transToNewDepartBtn.setEnabled(true);  //设置按钮可用
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//如果事件来源是调入新部门的按钮，就执行修改操作。
		if(obj == transToNewDepartBtn) {
			String name = pNameText.getText();  //名字
			String oldDeptName = oldDepartNameText.getText();  //旧名
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
