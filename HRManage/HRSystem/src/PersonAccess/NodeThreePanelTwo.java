package PersonAccess;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import DataOperator.AssessChangeHistory;

/**
 * ��ģ������ʵ����Ա������ʷ�Ĳ�ѯ��
 * @author YaXin Wen
 * @date 2017/11/18
 *
 */
public class NodeThreePanelTwo extends JPanel {
	JPanel upPanel = new JPanel();
	JLabel title = new JLabel("��Ա������ʷ��ѯ");
	
	//������
	JScrollPane jScrollPane;
	JTable table = new JTable();
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"��ˮ��","Ա�����","Ա������","�ϴο���","���ο���","�������","�������"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon = new GridBagConstraints();
	
	//���캯����ɶ���ĳ�ʼ������
	public NodeThreePanelTwo() {
		try {
			upPanelInit();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void upPanelInit() {
		AssessChangeHistory bean = new AssessChangeHistory();
		upPanel.setLayout(gridBag);
		title.setFont(new Font("Dialog",0,15));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
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
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		this.add(upPanel);
	}
}
