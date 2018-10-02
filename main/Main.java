package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import listeners.TypeListener;
import model.ListBox;
import model.Variable;

public class Main extends JFrame {
	static ArrayList<String> topicL = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	private WindowDialInfo wInfo = new WindowDialInfo();
	public boolean sendData;
	public ArrayList<String> domains = new ArrayList<String>();
	public ArrayList<String> variables = new ArrayList<String>();
	public ArrayList<Variable> variablesObj = new ArrayList<Variable>();

	public static JPanel PanDialX() {
		ArrayList<ListBox> topicListing = Connect.topicITString(1);
		for (ListBox topicX : topicListing) {
			topicL.add(topicX.getTitle());
		}
		JPanel panTopics = new JPanel();
		panTopics.setPreferredSize(new Dimension(300, 200));
		panTopics.setBorder(BorderFactory.createTitledBorder("Thèmes disponibles"));
		JComboBox<String> topicx = new JComboBox<String>();
		TypeListener topicsListener = new TypeListener();
		for (int iTy = 0; iTy < topicL.size(); iTy++) {
			topicx.addItem(topicL.get(iTy));
		}
		topicx.addActionListener(topicsListener);
		JLabel topicsLabel = new JLabel("Thèmes : ");
		panTopics.add(topicsLabel);
		panTopics.add(topicx);
		panTopics.setVisible(true);
		int ixTopx = topicx.getSelectedIndex();
		int idTopx = topicListing.get(ixTopx).getId();
		Hashtable<String, ArrayList<String>> xTop = Connect.topString(idTopx);
		System.out.println("idtopx : " + idTopx + " * " + xTop.get("title") + " - " + xTop.get("content"));
		// ArrayList<String> strDomx = xTop.get("iddomain");
		// int idDomx = Integer.parseInt(strDomx.get(0));
		// Hashtable<String, ArrayList<String>> xDom = Connect.topString(idDomx);
		// ArrayList<String> strTypx = xTop.get("idtype");
		// int idTypx = Integer.parseInt(strTypx.get(0));
		// Hashtable<String, ArrayList<String>> xTyp = Connect.typString(idTypx);
		// String domain, String type, int dim, String title, String content
		// WindowDialInfo wInfoTop = new WindowDialInfo(xDom.get(0).get(1),
		// xTop.get(0).get(5), 1, xTop.get(0).get(2),
		// xTop.get(0).get(4));
		// wInfoTop.setVisible(true);
		JPanel panInt = new JPanel();
		panInt.setSize(500, 300);
		panInt.setVisible(true);
		JLabel icon = new JLabel(new ImageIcon("images/main.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		panInt.add(panTopics, BorderLayout.CENTER);
		panInt.add(panIcon, BorderLayout.WEST);
		return panInt;
	}

	public WindowDialInfo showWindowDial() {
		this.sendData = false;
		this.setVisible(true);
		return this.wInfo;
	}

	public Main(JPanel frameX, JMenuBar wMenuB) {
		JFrame globalW = new JFrame();
		globalW.setSize(600, 400);
		globalW.add(frameX);
		globalW.setJMenuBar(wMenuB);
		globalW.setLocationRelativeTo(null);
		globalW.setResizable(false);
		globalW.setVisible(true);
	}

	public static void main(String[] args) {
		JMenuBar wMenuBar = MenuW.MenuGlobal();
		JPanel paDial = PanDialX();
		Main mainx = new Main(paDial, wMenuBar);
		mainx.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
