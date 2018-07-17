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

	//定义所用的面板
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//定义四个所需的静态文本
	JLabel title = new JLabel("劳资分配");
	JLabel pNameLabel = new JLabel("姓名:");
	JLabel oldSalaryLabel = new JLabel("调整前的工资:");
	JLabel newSalaryLabel = new JLabel("调整后的工资:");
	
	//定义三个需要的文本框
	JTextField pNameText = new JTextField(10);
	JTextField oldSalaryText = new JTextField(10);
	JTextField newSalaryText = new JTextField(10);
	
	//定义需要用的两个按钮
	JButton okBtn = new JButton("确定");
	JButton clearBtn = new JButton("清空");
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"工号","姓名","性别","部门","薪酬","考核信息"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	String pId;   	//员工编号
	String pName;   //员工姓名
	String oldSalary;  //原来的工资
	String newSalary;  //新调整的工资。
	
	public NodeFourPanelOne() {
		//设置布局方式为BorderLayout
		this.setLayout(new BorderLayout()); 
		try {
			upPanelInit();   //上部面板布局
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
	
	//中部面板的布局
	public void centerPanelInit() throws Exception {
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldSalaryLabel.setFont(new Font("Dialog",0,15));
		newSalaryLabel.setFont(new Font("Dialog",0,15));
		
		//将控件添加到面板上
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
		//当表格被选中的时候执行这些操作
		int selectedRow = table.getSelectedRow();
		pId = colValue[selectedRow][0];   	//员工编号
		pName = colValue[selectedRow][1];   //员工姓名
		oldSalary = colValue[selectedRow][4]; //原来的工资
		
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
