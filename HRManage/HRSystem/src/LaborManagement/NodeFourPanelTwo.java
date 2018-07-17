package LaborManagement;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import DataOperator.salaryChangeHistory;

/**
 * @author Yaxin Wen
 * @date 2017/11/18
 */
public class NodeFourPanelTwo extends JPanel {
	JPanel upPanel = new JPanel();
	
	JLabel title = new JLabel("劳资管理历史查询");
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"流水号","员工编号","员工姓名","原薪资","当前薪资","变更次数","变更日期"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//构造函数完成初始化工作
	public NodeFourPanelTwo() {
		try {
			upPanelInit();
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void upPanelInit() {
		upPanel.setLayout(gridBag);
		salaryChangeHistory bean = new salaryChangeHistory();
		title.setFont(new Font("Dialog",0,25));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx  = 0;
		gridBagCon.gridy  = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		try {
			colValue = bean.searchAll();
			table = new JTable(colValue,colName);
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			jScrollPane = new JScrollPane(table);
			jScrollPane.setPreferredSize(new Dimension(900,750));
			
			gridBagCon = new GridBagConstraints();
			gridBagCon.gridx = 0;
			gridBagCon.gridy = 1;
			gridBagCon.insets = new Insets(0,0,0,0);
			gridBag.setConstraints(jScrollPane, gridBagCon);
			upPanel.add(jScrollPane);
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
		this.add(upPanel);
	}
}
