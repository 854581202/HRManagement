package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import DataOperator.Database;
import JobTransfer.*;
import LaborManagement.*;
import PersonAccess.*;


/**
 * 人事管理系统的主界面
 * @author YaXin Wen
 * @date 2017/10/2
 */
public class HrMain extends JFrame implements ActionListener, TreeSelectionListener {

	//框架的大小
	Dimension facesize = new Dimension(1200,900);
	
	//程序左上角图标
	ImageIcon icon;
	
	//建立界面左侧JTree菜单
	JTree tree;
	DefaultMutableTreeNode root;        //根节点，人事管理系统
	DefaultMutableTreeNode node1;       //一级节点，基本信息管理
	DefaultMutableTreeNode node2;       //一级节点，人员调动管理
	DefaultMutableTreeNode node3;       //一级节点，人员考核管理
	DefaultMutableTreeNode node4;       //一级节点，劳资管理
	DefaultMutableTreeNode leafnode;    //叶子节点
	TreePath treePath;
	
	//建立右侧操作区域，整个界面由两个分离面板构成
	public static JSplitPane splitPane;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JLabel welcome = new JLabel();   	//用来显示欢迎信息
	JScrollPane scrollPane;             //滚动面板
	
	/**
	 * 构造函数，完成对象的初始化工作
	 */
	public HrMain() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		//添加框架的关闭事件处理
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(facesize);  		//设置窗体大小
		this.setLocation(400, 50);  	//设置窗体初始位置
		this.setVisible(true);      	//设置窗体可见
		this.setTitle("人事管理系统"); 	//设置窗体标题
		this.setResizable(false);   	//设置窗体大小不可变
		
		//设置窗体左上角图标
		icon = new ImageIcon("image\\computer.png");  
		this.setIconImage(icon.getImage());
		
		try {
			Init();     //控件布局。
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 初始化函数,完成界面的初始化工作
	 * @param 无参数
	 */
	private void Init() throws Exception {
		//将各个子节点，添加到root上去。
		root = new DefaultMutableTreeNode("人事管理系统");
		//实例化root的四个子节点。
		node1 = new DefaultMutableTreeNode("基本信息管理");
		node2 = new DefaultMutableTreeNode("人员调动管理");
		node3 = new DefaultMutableTreeNode("人员考核管理");
		node4 = new DefaultMutableTreeNode("劳资管理");
		
		//基本信息管理上添加5个子节点
		leafnode = new DefaultMutableTreeNode("添加人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("修改人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("删除人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("查询人员信息");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("部门管理");
		node1.add(leafnode);	
		//基本信息管理节点添加到根节点上。
		root.add(node1);
		
		//人员调动管理上添加两个子节点。
		leafnode =  new DefaultMutableTreeNode("人员调动");
		node2.add(leafnode);
		leafnode = new DefaultMutableTreeNode("调动历史查询");
		node2.add(leafnode);
		//将人员调动管理节点添加到根节点上
		root.add(node2);
		
		//人员考核管理模块子功能
		leafnode = new DefaultMutableTreeNode("人员考核");
		node3.add(leafnode);
		leafnode = new DefaultMutableTreeNode("考核历史查询");
		node3.add(leafnode);
		//人员考核管理节点添加到根节点上。
		root.add(node3);
		
		//劳资管理模块子功能
		leafnode = new DefaultMutableTreeNode("劳资分配管理");
		node4.add(leafnode);
		leafnode = new DefaultMutableTreeNode("劳资历史查询");
		node4.add(leafnode);
		//劳资管理节点添加到根节点上。
		root.add(node4);
		
		//生成左侧的JTree，并且该树以root为根。
		tree = new JTree(root);
		//将树添加到滚动面板上
		scrollPane = new JScrollPane(tree);
		//设置滚动面板的大小。
		scrollPane.setPreferredSize(new Dimension(200,850));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		//生成JPanel,panel1盛装左侧控件，
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.add(scrollPane);   //在此，左边界面设计完毕。
		
		//开始设计右部界面。
		welcome.setText("欢迎使用人事管理系统");  //显示欢迎信息
		panel3.add(welcome);                
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(false);
		splitPane.setContinuousLayout(true);
		splitPane.setPreferredSize(new Dimension(1100,900));
		//间隔方式是水平摆放间隔
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		//功能树位于界面左侧
		splitPane.setLeftComponent(panel1);
		//相应功能对应的面板位于分离面板的右侧。
		splitPane.setRightComponent(panel3);
		splitPane.setDividerSize(2);
		splitPane.setDividerLocation(200);
		this.setContentPane(splitPane);
		this.setVisible(true);
		//为左侧的tree添加事件监听器。
		tree.addTreeSelectionListener(this);
		
	}
	@Override
	/**
	 * 点击不同的节点，右侧显示不同的面板
	 */
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
		//System.out.println("nodeText = " + currentNode);
		String nodeText = currentNode.toString();
		//如果当前选择的节点是root，人事管理系统，右侧面板是初始模样
		if(nodeText == "人事管理系统") {
			splitPane.setRightComponent(panel3);
		}
		else if(nodeText == "基本信息管理") {
			/* 当点击基本信息管理节点的时候，如果此节点处于
			 * 关闭状态，则将其自动展开，用此代码实现节点自动展开。
			 */
			treePath = new TreePath(node1.getPath());
			if(tree.isExpanded(treePath)) {
				tree.collapsePath(treePath);
			}
			else {
				tree.expandPath(treePath);
			}
		}
		else if (nodeText == "添加人员信息") {
			NodeOnePanelOne nodepanel11 = new NodeOnePanelOne();
			splitPane.setRightComponent(nodepanel11);
		}
		else if(nodeText == "修改人员信息") {
			NodeOnePanelTwo nodepanel12 = new NodeOnePanelTwo();
			splitPane.setRightComponent(nodepanel12);
		}
		else if(nodeText == "删除人员信息") {
			NodeOnePanelThree nodepanel13 = new NodeOnePanelThree();
			splitPane.setRightComponent(nodepanel13);
		}
		else if(nodeText == "查询人员信息") {
			NodeOnePanelFour nodepanel14 = new NodeOnePanelFour();
			splitPane.setRightComponent(nodepanel14);
		}
		else if(nodeText == "部门管理") {
			NodeOnePanelFive nodepanel15 = new NodeOnePanelFive();
			splitPane.setRightComponent(nodepanel15);
		}
		else if(nodeText == "人员调动") {
			NodeTwoPanelOne nodePanel21 = new NodeTwoPanelOne();
			splitPane.setRightComponent(nodePanel21);
		}
		else if(nodeText == "调动历史查询") {
			 NodeTwoPanelTwo nodePanel22 = new NodeTwoPanelTwo();
			 splitPane.setRightComponent(nodePanel22);
		}
		else if(nodeText == "人员考核") {
			NodeThreePanelOne nodePanel31 = new NodeThreePanelOne();
			splitPane.setRightComponent(nodePanel31);
		}
		else if(nodeText == "考核历史查询") {
			NodeThreePanelTwo nodePanel32 = new NodeThreePanelTwo();
			splitPane.setRightComponent(nodePanel32);
		}
		else if(nodeText == "劳资分配管理") {
			NodeFourPanelOne nodePanel41 = new NodeFourPanelOne();
			splitPane.setRightComponent(nodePanel41);
		}
		else if(nodeText == "劳资历史查询") {
			NodeFourPanelTwo nodePanel42 = new NodeFourPanelTwo();
			splitPane.setRightComponent(nodePanel42);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	public static void main(String[] args) throws Exception{
		HrMain hr = new HrMain();
		
		
		/**Database db = new Database();
		db.OpenConn();
		//String sql = "insert into DeptTable(DeptId,FatherDept,SonDept) values('3','办公室','机要科')";
		String sql = "select * from DeptTable";
		int number = db.RecordNumber(sql);
		System.out.println(number);
		db.closeStmt();
		db.closeConn();
		*/
	}
}