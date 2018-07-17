package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.PersonBean;

/**
 * 该面板用来实现删除员工信息,将所有员工的信息呈现，
 * 选中一条记录后可以执行删除操作。
 * @author YaXin Wen
 * @date 2017/10/22
 */
public class NodeOnePanelThree extends JPanel implements ActionListener, ListSelectionListener {
 
	private static final Container BottomPanel = null;
	//该模块整个界面分为三个部分
	JPanel upPanel = new JPanel();   	//上部面板用来盛装显示该模块作用的标签
	JPanel centerPanel = new JPanel();  //中部面板用来展示员工的各项信息
	JPanel bottomPanel = new JPanel();  //下部面板用来展示欲删除的员工的信息
	
	JLabel title = new JLabel("人员信息删除");
	JLabel pIdLabel = new JLabel();     		//人员编号静态文本
	JLabel pNameLabel = new JLabel();   		//人员姓名静态文本
	JLabel pDepartmentLabel = new JLabel();    //显示人员部门静态文本
	
	JTextField pIdText = new JTextField(15);    //显示欲删除人员的编号
	JTextField pNameText = new JTextField(20);  //显示欲删除人员的姓名
	JTextField pDepartmentText = new JTextField(15); //显示欲删除人员所在的部门
	
	JButton deleteButton = new JButton("删    除");
	
	//定义一个可以滚动的面板
	JScrollPane jScrollPanel;
	//定义一个表格
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//设置表的字段信息
	String[] colName = {"编 号","姓 名","出生年月","民 族","地 址","部  门",};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * 构造函数完成对象的初始化工作
	 */
	public NodeOnePanelThree() {
		this.setLayout(new BorderLayout());
		try{
			upPanelInit();
			centerPanelInit();
			bottomPanelInit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	/**
	 * 完成上部面板的初始化工作，该工作主要是让所有员工的信息
	 * 用表显示出来。
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		//定义PersonBean用来实现对员工信息的各种操作
		PersonBean bean = new PersonBean();
		//设置上部面板的布局方式为网格布局
		upPanel.setLayout(gridBag);
		//设置该模块标题的字体大小
		title.setFont(new Font("Dialog",0,25));
		
		//将模块标题添加到上部面板
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		//colValue用来存放表的记录，通过调用searchAllForNode方法查找员工表中的所有信息并返回
		colValue = bean.searchAllForNode();
		//对表格实例化，并将查询到的信息绑定到表上。
		table = new JTable(colValue,colName); 
		//设置表的大小
		table.setPreferredScrollableViewportSize(new Dimension(900,750));
		//设置表只能选中一行
		listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(listSelectionModel.SINGLE_SELECTION);
		//添加监听器，当选中表的某条记录时触发
		listSelectionModel.addListSelectionListener(this);
		//将表格放到滚动面板里面
		jScrollPanel = new JScrollPane(table);
		//设置滚动面板的大小。
		jScrollPanel.setPreferredSize(new Dimension(900,750));
		
		//将滚动面板添加到上部面板上
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,0,0,0);
		gridBag.setConstraints(jScrollPanel,gridBagCon);
		upPanel.add(jScrollPanel);
		//将上部面板添加到当前面板的上部
		this.add(upPanel,BorderLayout.NORTH);
	}
	/**
	 * 中部面板的布局
	 */
	public void centerPanelInit() throws Exception {
		this.add(centerPanel);
	}
	/**
	 * 下部面板的布局，由几个标签和几个文本框和一个删除按钮
	 * 构成，标签用来显示数据的字段名，文本框用来呈现选中记
	 * 录对应的人员信息，
	 */
	public void bottomPanelInit() {
		//设置标签的文本内容和字体大小。
		pIdLabel.setText("编号:");
		pIdLabel.setFont(new Font("Dialog",0,20));
		//将标签和其相对应的文本框添加到面板上
		bottomPanel.add(pIdLabel);
		bottomPanel.add(pIdText);
		
		//设置标签的文本内容和字体大小
		pNameLabel.setText("姓名:");
		pNameLabel.setFont(new Font("Dialog",0,20));
		//将标签和其相对应的文本框添加到面板上
		bottomPanel.add(pNameLabel);
		bottomPanel.add(pNameText);
		
		//设置标签的文本内容和字体大小
		pDepartmentLabel.setText("部门:");
		pDepartmentLabel.setFont(new Font("Dialog",0,20));
		//将标签和其相对应的文本框添加到面板上
		bottomPanel.add(pDepartmentLabel);
		bottomPanel.add(pDepartmentText);
		
		//设置各个文本框中的字体大小。
		pIdText.setFont(new Font("Dialog",0,15));
		pNameText.setFont(new Font("Dialog",0,15));
		pDepartmentText.setFont(new Font("Dialog",0,15));
		//所有文本框初始时刻都设置为不可编辑，这些文本框只用来呈现信息。
		pIdText.setEditable(false);
		pNameText.setEditable(false);
		pDepartmentText.setEditable(false);
		
		//设置按钮的字体大小，设置按钮初始不可用，并为按钮添加事件监听器
		deleteButton.setFont(new Font("Dialog",0,15));
		bottomPanel.add(deleteButton);
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(this);
		
		//将下部面板添加到主面板的下部
		this.add(bottomPanel,BorderLayout.SOUTH);
		
	}
	
	/**
	 * 当选中的表格中的某条记录，将该记录对应人员的工号，姓名，
	 * 和所在部门显示在下部的文本框中，并且把删除按钮设置为可用
	 */
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		// TODO Auto-generated method stub
		int selectedRow = table.getSelectedRow();
		pIdText.setText(colValue[selectedRow][0]);
		pNameText.setText(colValue[selectedRow][1]);
		pDepartmentText.setText(colValue[selectedRow][5]);
		deleteButton.setEnabled(true);
	}

	/**
	 * 编写各个控件的事件响应方法。
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//当点击删除按钮的时候，删除对应的记录
		if(obj == deleteButton) {
			PersonBean bean = new PersonBean();
			try {
				//success 用来记录记录的删除是否成功
				boolean success = bean.deleteInfo(pIdText.getText());
				//删除成功，界面中表的内容需要被更新。
				if(success == true) {
					NodeOnePanelThree nodePanel13 = new NodeOnePanelThree();
					HrMain.splitPane.setRightComponent(nodePanel13);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
