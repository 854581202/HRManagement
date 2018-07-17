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
 * ���¹���ϵͳ��������
 * @author YaXin Wen
 * @date 2017/10/2
 */
public class HrMain extends JFrame implements ActionListener, TreeSelectionListener {

	//��ܵĴ�С
	Dimension facesize = new Dimension(1200,900);
	
	//�������Ͻ�ͼ��
	ImageIcon icon;
	
	//�����������JTree�˵�
	JTree tree;
	DefaultMutableTreeNode root;        //���ڵ㣬���¹���ϵͳ
	DefaultMutableTreeNode node1;       //һ���ڵ㣬������Ϣ����
	DefaultMutableTreeNode node2;       //һ���ڵ㣬��Ա��������
	DefaultMutableTreeNode node3;       //һ���ڵ㣬��Ա���˹���
	DefaultMutableTreeNode node4;       //һ���ڵ㣬���ʹ���
	DefaultMutableTreeNode leafnode;    //Ҷ�ӽڵ�
	TreePath treePath;
	
	//�����Ҳ����������������������������幹��
	public static JSplitPane splitPane;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JLabel welcome = new JLabel();   	//������ʾ��ӭ��Ϣ
	JScrollPane scrollPane;             //�������
	
	/**
	 * ���캯������ɶ���ĳ�ʼ������
	 */
	public HrMain() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		//��ӿ�ܵĹر��¼�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(facesize);  		//���ô����С
		this.setLocation(400, 50);  	//���ô����ʼλ��
		this.setVisible(true);      	//���ô���ɼ�
		this.setTitle("���¹���ϵͳ"); 	//���ô������
		this.setResizable(false);   	//���ô����С���ɱ�
		
		//���ô������Ͻ�ͼ��
		icon = new ImageIcon("image\\computer.png");  
		this.setIconImage(icon.getImage());
		
		try {
			Init();     //�ؼ����֡�
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * ��ʼ������,��ɽ���ĳ�ʼ������
	 * @param �޲���
	 */
	private void Init() throws Exception {
		//�������ӽڵ㣬��ӵ�root��ȥ��
		root = new DefaultMutableTreeNode("���¹���ϵͳ");
		//ʵ����root���ĸ��ӽڵ㡣
		node1 = new DefaultMutableTreeNode("������Ϣ����");
		node2 = new DefaultMutableTreeNode("��Ա��������");
		node3 = new DefaultMutableTreeNode("��Ա���˹���");
		node4 = new DefaultMutableTreeNode("���ʹ���");
		
		//������Ϣ���������5���ӽڵ�
		leafnode = new DefaultMutableTreeNode("�����Ա��Ϣ");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("�޸���Ա��Ϣ");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("ɾ����Ա��Ϣ");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("��ѯ��Ա��Ϣ");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("���Ź���");
		node1.add(leafnode);	
		//������Ϣ����ڵ���ӵ����ڵ��ϡ�
		root.add(node1);
		
		//��Ա������������������ӽڵ㡣
		leafnode =  new DefaultMutableTreeNode("��Ա����");
		node2.add(leafnode);
		leafnode = new DefaultMutableTreeNode("������ʷ��ѯ");
		node2.add(leafnode);
		//����Ա��������ڵ���ӵ����ڵ���
		root.add(node2);
		
		//��Ա���˹���ģ���ӹ���
		leafnode = new DefaultMutableTreeNode("��Ա����");
		node3.add(leafnode);
		leafnode = new DefaultMutableTreeNode("������ʷ��ѯ");
		node3.add(leafnode);
		//��Ա���˹���ڵ���ӵ����ڵ��ϡ�
		root.add(node3);
		
		//���ʹ���ģ���ӹ���
		leafnode = new DefaultMutableTreeNode("���ʷ������");
		node4.add(leafnode);
		leafnode = new DefaultMutableTreeNode("������ʷ��ѯ");
		node4.add(leafnode);
		//���ʹ���ڵ���ӵ����ڵ��ϡ�
		root.add(node4);
		
		//��������JTree�����Ҹ�����rootΪ����
		tree = new JTree(root);
		//������ӵ����������
		scrollPane = new JScrollPane(tree);
		//���ù������Ĵ�С��
		scrollPane.setPreferredSize(new Dimension(200,850));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		//����JPanel,panel1ʢװ���ؼ���
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.add(scrollPane);   //�ڴˣ���߽��������ϡ�
		
		//��ʼ����Ҳ����档
		welcome.setText("��ӭʹ�����¹���ϵͳ");  //��ʾ��ӭ��Ϣ
		panel3.add(welcome);                
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(false);
		splitPane.setContinuousLayout(true);
		splitPane.setPreferredSize(new Dimension(1100,900));
		//�����ʽ��ˮƽ�ڷż��
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		//������λ�ڽ������
		splitPane.setLeftComponent(panel1);
		//��Ӧ���ܶ�Ӧ�����λ�ڷ��������Ҳࡣ
		splitPane.setRightComponent(panel3);
		splitPane.setDividerSize(2);
		splitPane.setDividerLocation(200);
		this.setContentPane(splitPane);
		this.setVisible(true);
		//Ϊ����tree����¼���������
		tree.addTreeSelectionListener(this);
		
	}
	@Override
	/**
	 * �����ͬ�Ľڵ㣬�Ҳ���ʾ��ͬ�����
	 */
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
		//System.out.println("nodeText = " + currentNode);
		String nodeText = currentNode.toString();
		//�����ǰѡ��Ľڵ���root�����¹���ϵͳ���Ҳ�����ǳ�ʼģ��
		if(nodeText == "���¹���ϵͳ") {
			splitPane.setRightComponent(panel3);
		}
		else if(nodeText == "������Ϣ����") {
			/* �����������Ϣ����ڵ��ʱ������˽ڵ㴦��
			 * �ر�״̬�������Զ�չ�����ô˴���ʵ�ֽڵ��Զ�չ����
			 */
			treePath = new TreePath(node1.getPath());
			if(tree.isExpanded(treePath)) {
				tree.collapsePath(treePath);
			}
			else {
				tree.expandPath(treePath);
			}
		}
		else if (nodeText == "�����Ա��Ϣ") {
			NodeOnePanelOne nodepanel11 = new NodeOnePanelOne();
			splitPane.setRightComponent(nodepanel11);
		}
		else if(nodeText == "�޸���Ա��Ϣ") {
			NodeOnePanelTwo nodepanel12 = new NodeOnePanelTwo();
			splitPane.setRightComponent(nodepanel12);
		}
		else if(nodeText == "ɾ����Ա��Ϣ") {
			NodeOnePanelThree nodepanel13 = new NodeOnePanelThree();
			splitPane.setRightComponent(nodepanel13);
		}
		else if(nodeText == "��ѯ��Ա��Ϣ") {
			NodeOnePanelFour nodepanel14 = new NodeOnePanelFour();
			splitPane.setRightComponent(nodepanel14);
		}
		else if(nodeText == "���Ź���") {
			NodeOnePanelFive nodepanel15 = new NodeOnePanelFive();
			splitPane.setRightComponent(nodepanel15);
		}
		else if(nodeText == "��Ա����") {
			NodeTwoPanelOne nodePanel21 = new NodeTwoPanelOne();
			splitPane.setRightComponent(nodePanel21);
		}
		else if(nodeText == "������ʷ��ѯ") {
			 NodeTwoPanelTwo nodePanel22 = new NodeTwoPanelTwo();
			 splitPane.setRightComponent(nodePanel22);
		}
		else if(nodeText == "��Ա����") {
			NodeThreePanelOne nodePanel31 = new NodeThreePanelOne();
			splitPane.setRightComponent(nodePanel31);
		}
		else if(nodeText == "������ʷ��ѯ") {
			NodeThreePanelTwo nodePanel32 = new NodeThreePanelTwo();
			splitPane.setRightComponent(nodePanel32);
		}
		else if(nodeText == "���ʷ������") {
			NodeFourPanelOne nodePanel41 = new NodeFourPanelOne();
			splitPane.setRightComponent(nodePanel41);
		}
		else if(nodeText == "������ʷ��ѯ") {
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
		//String sql = "insert into DeptTable(DeptId,FatherDept,SonDept) values('3','�칫��','��Ҫ��')";
		String sql = "select * from DeptTable";
		int number = db.RecordNumber(sql);
		System.out.println(number);
		db.closeStmt();
		db.closeConn();
		*/
	}
}