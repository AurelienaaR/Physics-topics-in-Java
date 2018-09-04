package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.Variable;

public class Main extends JFrame { // implements ActionListener, ItemListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private WindowDialInfo wInfo = new WindowDialInfo();
	public boolean sendData;
	public ArrayList<String> domains = new ArrayList<String>();
	public ArrayList<String> variables = new ArrayList<String>();
	public ArrayList<Variable> variablesObj = new ArrayList<Variable>();
	public static String[] typeTab = { "Scalaires discrets", "Scalaires continus", "Vecteurs", "Tenseurs, matrices",
			"Autre" };
	public static String[] dimTab = { "-1 - questions", "0 - état", "1 - solutions - causes directes",
			"2 - solutions - champ d'évolutions" };

	public static JPanel PanDialX() {
		JPanel panInt = new JPanel();
		panInt.setSize(500, 300);
		panInt.setVisible(true);
		JLabel icon = new JLabel(new ImageIcon("images/main.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
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
