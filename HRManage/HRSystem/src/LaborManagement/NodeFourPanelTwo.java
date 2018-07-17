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
	
	JLabel title = new JLabel("���ʹ�����ʷ��ѯ");
	//������
	JScrollPane jScrollPane;
	JTable table;
	ListSelectionModel listSelectionModel = null;
	String[] colName = {"��ˮ��","Ա�����","Ա������","ԭн��","��ǰн��","�������","�������"};
	String[][] colValue;
	
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridBagCon;
	
	//���캯����ɳ�ʼ������
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
