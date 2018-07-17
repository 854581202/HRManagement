package JobTransfer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DataOperator.JobChangeHistory;

/**
 * 该模块用来显示人员调动的历史
 * @author 57215
 *
 */
public class NodeTwoPanelTwo extends JPanel implements ActionListener {

	//定义所用面板
	JPanel upPanel = new JPanel();
	
	JLabel title = new JLabel("调动历史查询");
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"流水号","员工编号","员工姓名","原部门","新部门","变更次数","变更日期"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//构造函数完成对象的初始化工作
	public NodeTwoPanelTwo() {
		this.setLayout(new BorderLayout()); 
		try {
			upPanelInit();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//上部面板初始化
	public void upPanelInit() {
		JobChangeHistory bean = new JobChangeHistory();
		upPanel.setLayout(gridBag);
		title.setFont(new Font("Dialog",0,25));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
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
		this.add(upPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
