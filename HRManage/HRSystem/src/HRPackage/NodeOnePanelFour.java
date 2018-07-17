package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DataOperator.PersonBean;

/**
 * 该模块用来实现显示所有人员信息的功能
 * @author Yaxin Wen
 * @date 2017/11/18
 */
public class NodeOnePanelFour extends JPanel implements ActionListener {
	//定义所需要的面板
	JPanel upPanel = new JPanel();
	//定义标签，用来显示该模块的功能
	JLabel title = new JLabel("人员信息查询");
	
	//定义表格
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//定义表格字段
	String[] colName = {"编 号","姓 名","出生年月","民 族","地 址","部门"};
	//定义存放表格记录所需的二维素组
	String[][] colValue;
	
	//定义网格布局
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * 构造函数完成初始化工作
	 */
	public NodeOnePanelFour() {
		this.setLayout(new BorderLayout());
		try{
			upPanelInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 对上部面板进行初始化，在上面添加模块标题和表格
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		PersonBean bean = new PersonBean();
		//设置面板的布局方式为网格布局
		upPanel.setLayout(gridBag);
		
		//将标题添加到面板上
		title.setFont(new Font("Dialog",0,25));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		try {
			//获取员工表的所有信息并返回给colValue
			colValue = bean.searchAllForNode();
			//初始化表并将得到的数据绑定到表上
			table = new JTable(colValue,colName);
			//设置表格大小
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			//将表格添加到滚动面板上
			jScrollPane = new JScrollPane(table);
			//设置滚动面板的大小
			jScrollPane.setPreferredSize(new Dimension(900,750));
			
			//将滚动面板添加到面板上
			gridBagCon = new GridBagConstraints();
			gridBagCon.gridx = 0;
			gridBagCon.gridy = 1;
			gridBagCon.insets = new Insets(0,0,0,0);
			gridBag.setConstraints(jScrollPane, gridBagCon);
			upPanel.add(jScrollPane);
			
			this.add(upPanel,BorderLayout.NORTH);
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
