package JobTransfer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DataOperator.JobChangeHistory;

/**
 * ��ģ��������ʾ��Ա��������ʷ
 * @author 57215
 *
 */
public class NodeTwoPanelTwo extends JPanel implements ActionListener {

	//�����������
	JPanel upPanel = new JPanel();
	
	JLabel title = new JLabel("������ʷ��ѯ");
	
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"��ˮ��","Ա�����","Ա������","ԭ����","�²���","�������","�������"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//���캯����ɶ���ĳ�ʼ������
	public NodeTwoPanelTwo() {
		this.setLayout(new BorderLayout()); 
		try {
			upPanelInit();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//�ϲ�����ʼ��
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
