package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataOperator.PersonBean;

/**
 * ���������ʵ��ɾ��Ա����Ϣ,������Ա������Ϣ���֣�
 * ѡ��һ����¼�����ִ��ɾ��������
 * @author YaXin Wen
 * @date 2017/10/22
 */
public class NodeOnePanelThree extends JPanel implements ActionListener, ListSelectionListener {
 
	private static final Container BottomPanel = null;
	//��ģ�����������Ϊ��������
	JPanel upPanel = new JPanel();   	//�ϲ��������ʢװ��ʾ��ģ�����õı�ǩ
	JPanel centerPanel = new JPanel();  //�в��������չʾԱ���ĸ�����Ϣ
	JPanel bottomPanel = new JPanel();  //�²��������չʾ��ɾ����Ա������Ϣ
	
	JLabel title = new JLabel("��Ա��Ϣɾ��");
	JLabel pIdLabel = new JLabel();     		//��Ա��ž�̬�ı�
	JLabel pNameLabel = new JLabel();   		//��Ա������̬�ı�
	JLabel pDepartmentLabel = new JLabel();    //��ʾ��Ա���ž�̬�ı�
	
	JTextField pIdText = new JTextField(15);    //��ʾ��ɾ����Ա�ı��
	JTextField pNameText = new JTextField(20);  //��ʾ��ɾ����Ա������
	JTextField pDepartmentText = new JTextField(15); //��ʾ��ɾ����Ա���ڵĲ���
	
	JButton deleteButton = new JButton("ɾ    ��");
	
	//����һ�����Թ��������
	JScrollPane jScrollPanel;
	//����һ�����
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//���ñ���ֶ���Ϣ
	String[] colName = {"�� ��","�� ��","��������","�� ��","�� ַ","��  ��",};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * ���캯����ɶ���ĳ�ʼ������
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
	 * ����ϲ����ĳ�ʼ���������ù�����Ҫ��������Ա������Ϣ
	 * �ñ���ʾ������
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		//����PersonBean����ʵ�ֶ�Ա����Ϣ�ĸ��ֲ���
		PersonBean bean = new PersonBean();
		//�����ϲ����Ĳ��ַ�ʽΪ���񲼾�
		upPanel.setLayout(gridBag);
		//���ø�ģ�����������С
		title.setFont(new Font("Dialog",0,25));
		
		//��ģ�������ӵ��ϲ����
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		//colValue������ű�ļ�¼��ͨ������searchAllForNode��������Ա�����е�������Ϣ������
		colValue = bean.searchAllForNode();
		//�Ա��ʵ������������ѯ������Ϣ�󶨵����ϡ�
		table = new JTable(colValue,colName); 
		//���ñ�Ĵ�С
		table.setPreferredScrollableViewportSize(new Dimension(900,750));
		//���ñ�ֻ��ѡ��һ��
		listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(listSelectionModel.SINGLE_SELECTION);
		//��Ӽ���������ѡ�б��ĳ����¼ʱ����
		listSelectionModel.addListSelectionListener(this);
		//�����ŵ������������
		jScrollPanel = new JScrollPane(table);
		//���ù������Ĵ�С��
		jScrollPanel.setPreferredSize(new Dimension(900,750));
		
		//�����������ӵ��ϲ������
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0,0,0,0);
		gridBag.setConstraints(jScrollPanel,gridBagCon);
		upPanel.add(jScrollPanel);
		//���ϲ������ӵ���ǰ�����ϲ�
		this.add(upPanel,BorderLayout.NORTH);
	}
	/**
	 * �в����Ĳ���
	 */
	public void centerPanelInit() throws Exception {
		this.add(centerPanel);
	}
	/**
	 * �²����Ĳ��֣��ɼ�����ǩ�ͼ����ı����һ��ɾ����ť
	 * ���ɣ���ǩ������ʾ���ݵ��ֶ������ı�����������ѡ�м�
	 * ¼��Ӧ����Ա��Ϣ��
	 */
	public void bottomPanelInit() {
		//���ñ�ǩ���ı����ݺ������С��
		pIdLabel.setText("���:");
		pIdLabel.setFont(new Font("Dialog",0,20));
		//����ǩ�������Ӧ���ı�����ӵ������
		bottomPanel.add(pIdLabel);
		bottomPanel.add(pIdText);
		
		//���ñ�ǩ���ı����ݺ������С
		pNameLabel.setText("����:");
		pNameLabel.setFont(new Font("Dialog",0,20));
		//����ǩ�������Ӧ���ı�����ӵ������
		bottomPanel.add(pNameLabel);
		bottomPanel.add(pNameText);
		
		//���ñ�ǩ���ı����ݺ������С
		pDepartmentLabel.setText("����:");
		pDepartmentLabel.setFont(new Font("Dialog",0,20));
		//����ǩ�������Ӧ���ı�����ӵ������
		bottomPanel.add(pDepartmentLabel);
		bottomPanel.add(pDepartmentText);
		
		//���ø����ı����е������С��
		pIdText.setFont(new Font("Dialog",0,15));
		pNameText.setFont(new Font("Dialog",0,15));
		pDepartmentText.setFont(new Font("Dialog",0,15));
		//�����ı����ʼʱ�̶�����Ϊ���ɱ༭����Щ�ı���ֻ����������Ϣ��
		pIdText.setEditable(false);
		pNameText.setEditable(false);
		pDepartmentText.setEditable(false);
		
		//���ð�ť�������С�����ð�ť��ʼ�����ã���Ϊ��ť����¼�������
		deleteButton.setFont(new Font("Dialog",0,15));
		bottomPanel.add(deleteButton);
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(this);
		
		//���²������ӵ��������²�
		this.add(bottomPanel,BorderLayout.SOUTH);
		
	}
	
	/**
	 * ��ѡ�еı���е�ĳ����¼�����ü�¼��Ӧ��Ա�Ĺ��ţ�������
	 * �����ڲ�����ʾ���²����ı����У����Ұ�ɾ����ť����Ϊ����
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
	 * ��д�����ؼ����¼���Ӧ������
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object obj = event.getSource();
		//�����ɾ����ť��ʱ��ɾ����Ӧ�ļ�¼
		if(obj == deleteButton) {
			PersonBean bean = new PersonBean();
			try {
				//success ������¼��¼��ɾ���Ƿ�ɹ�
				boolean success = bean.deleteInfo(pIdText.getText());
				//ɾ���ɹ��������б��������Ҫ�����¡�
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
