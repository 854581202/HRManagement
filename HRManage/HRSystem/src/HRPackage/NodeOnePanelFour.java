package HRPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DataOperator.PersonBean;

/**
 * ��ģ������ʵ����ʾ������Ա��Ϣ�Ĺ���
 * @author Yaxin Wen
 * @date 2017/11/18
 */
public class NodeOnePanelFour extends JPanel implements ActionListener {
	//��������Ҫ�����
	JPanel upPanel = new JPanel();
	//�����ǩ��������ʾ��ģ��Ĺ���
	JLabel title = new JLabel("��Ա��Ϣ��ѯ");
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	//�������ֶ�
	String[] colName = {"�� ��","�� ��","��������","�� ��","�� ַ","����"};
	//�����ű���¼����Ķ�ά����
	String[][] colValue;
	
	//�������񲼾�
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	/**
	 * ���캯����ɳ�ʼ������
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
	 * ���ϲ������г�ʼ�������������ģ�����ͱ��
	 * @throws Exception
	 */
	public void upPanelInit() throws Exception {
		PersonBean bean = new PersonBean();
		//�������Ĳ��ַ�ʽΪ���񲼾�
		upPanel.setLayout(gridBag);
		
		//��������ӵ������
		title.setFont(new Font("Dialog",0,25));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0,10,0,10);
		gridBag.setConstraints(title, gridBagCon);
		upPanel.add(title);
		
		try {
			//��ȡԱ�����������Ϣ�����ظ�colValue
			colValue = bean.searchAllForNode();
			//��ʼ�������õ������ݰ󶨵�����
			table = new JTable(colValue,colName);
			//���ñ���С
			table.setPreferredScrollableViewportSize(new Dimension(900,750));
			//�������ӵ����������
			jScrollPane = new JScrollPane(table);
			//���ù������Ĵ�С
			jScrollPane.setPreferredSize(new Dimension(900,750));
			
			//�����������ӵ������
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
