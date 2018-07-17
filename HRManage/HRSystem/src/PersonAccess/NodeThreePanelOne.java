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
 * 该模块用来实现人员的考核功能。
 * @author Yaxin Wen
 * @data 2017/11/13
 */
public class NodeThreePanelOne extends JPanel implements ActionListener, ItemListener, ListSelectionListener {
	//定义所用的面板
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	//当以三个所需要的静态文本
	JLabel title = new JLabel("人员考核");
	JLabel pNameLabel = new JLabel();
	JTextField pNameText = new JTextField(15);
	JLabel oldAccessRecordLabel = new JLabel();
	JTextField oldAccessRecordText = new JTextField(10);
	JLabel newAccessRecordLabel = new JLabel();
	JComboBox newAccessRecordCom = null;
	
	JButton okBtn = new JButton("确定");
	JButton clearBtn = new JButton("清空");
	
	String pId;   //人员编号
	String pName; //人员姓名
	String oldAssess;  //上次考核
	String newAssess;  //本次考核
	
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"工号","姓名","性别","部门","薪酬","考核信息"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon = new GridBagConstraints();
	//构造寒素完成对象的初始化工作
	public NodeThreePanelOne() {
		//设置当前面板布局方式为borderLayout
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
	//该函数用来完成上部面板的初始化工作
	public void upPanelInit() throws Exception{
		PersonBean bean = new PersonBean();
		//设置上部面板的布局方式为网格布局
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
		pNameLabel.setText("姓名:");
		pNameLabel.setFont(new Font("Dialog",0,15));
		oldAccessRecordLabel.setText("上次考核:");
		oldAccessRecordLabel.setFont(new Font("Dialog",0,15));
		newAccessRecordLabel.setText("本次考核:");
		newAccessRecordLabel.setFont(new Font("Dialog",0,15));
		String[] value = {"优秀","合格","不合格"};
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
		oldAssess = colValue[selectedRow][5];   //旧的考核信息
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
		//点击确定按钮的时候，进行信息更改
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
