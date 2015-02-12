package net.erickpineda.lligajavaswing;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.SpringLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelNewLeague extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6920115013666487146L;
	private JTextField textFieldNameLeague;
	private JTextField textFieldNameClub;

	/**
	 * Create the panel.
	 */
	public JPanelNewLeague() {
		setBorder(new CompoundBorder(null, new TitledBorder(
				UIManager.getBorder("TitledBorder.border"),
				"Insert information", TitledBorder.CENTER, TitledBorder.TOP,
				null, new Color(0, 0, 0))));

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblLeagueName = new JLabel("League name");
		springLayout.putConstraint(SpringLayout.NORTH, lblLeagueName, 26,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblLeagueName, 65,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblLeagueName, 156,
				SpringLayout.WEST, this);
		add(lblLeagueName);

		textFieldNameLeague = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNameLeague, -3,
				SpringLayout.NORTH, lblLeagueName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNameLeague, 42,
				SpringLayout.EAST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNameLeague, -63,
				SpringLayout.EAST, this);
		textFieldNameLeague.setColumns(10);
		add(textFieldNameLeague);

		final JLabel lblClubName = new JLabel("Club name");
		springLayout.putConstraint(SpringLayout.WEST, lblClubName, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, lblClubName, 0,
				SpringLayout.EAST, lblLeagueName);
		add(lblClubName);
		//add(table);
		
		
		final JList<Object> listComponent = new JList<Object>();
		springLayout.putConstraint(SpringLayout.NORTH, listComponent, 0,
				SpringLayout.NORTH, lblClubName);
		springLayout.putConstraint(SpringLayout.WEST, listComponent, 0,
				SpringLayout.WEST, textFieldNameLeague);
		springLayout.putConstraint(SpringLayout.EAST, listComponent, 0,
				SpringLayout.EAST, textFieldNameLeague);
		add(listComponent);

		textFieldNameClub = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblClubName, -6,
				SpringLayout.NORTH, textFieldNameClub);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNameClub, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNameClub, 0,
				SpringLayout.EAST, lblLeagueName);
		textFieldNameClub.setColumns(10);
		add(textFieldNameClub);

		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addClub();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 120,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldNameClub, -6,
				SpringLayout.NORTH, btnAdd);
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, 6,
				SpringLayout.EAST, lblLeagueName);
		add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, listComponent, 0,
				SpringLayout.SOUTH, btnRemove);
		springLayout.putConstraint(SpringLayout.NORTH, btnRemove, 6,
				SpringLayout.SOUTH, btnAdd);
		springLayout.putConstraint(SpringLayout.WEST, btnRemove, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, btnRemove, 0,
				SpringLayout.EAST, btnAdd);
		add(btnRemove);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnFinish, 24,
				SpringLayout.SOUTH, listComponent);
		springLayout.putConstraint(SpringLayout.WEST, btnFinish, 252,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnFinish, 0,
				SpringLayout.EAST, textFieldNameLeague);
		add(btnFinish);
		
		

	}
	
	protected void addClub(){
		
		
		
		
	}
}
