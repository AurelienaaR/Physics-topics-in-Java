package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
// import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Topic;
import model.Variable;

public class Main extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;

	private String domainChanged = "Tous domaines";

	private Border domainCenter = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private Border domainContents = BorderFactory.createEmptyBorder(0, 0, 10, 0);
	private Border domainList = BorderFactory.createLineBorder(Color.WHITE, 1);

	private Box boxButtons = Box.createVerticalBox();
	private Box boxListHidden = Box.createVerticalBox();
	private Box boxListVisible = Box.createVerticalBox();
	private Box boxListDomain = Box.createVerticalBox();

	private JPanel contents;
	private JPanel panelSouth;
	private JPanel panelCenter;

	private JButton buttonAdd;
	private JButton buttonAddAll;
	private JButton buttonRemove;
	private JButton buttonRemoveAll;

	private JLabel labelListVisible;
	private JLabel labelListHidden;
	private JLabel labellistDomain;
	private JLabel labelSelectedVisible;
	private JLabel labelSelectedHidden;
	private JLabel labelSelectedVisiblelb;
	private JLabel labelSelectedHiddenlb;
	private JLabel labelSelectedDomain;
	private JLabel labelSelectedDomainlb;

	private WindowDialInfo wInfo = new WindowDialInfo();
	public boolean sendData;

	private JList<String> listVisible;
	private JList<String> listHidden;
	private JComboBox<String> listDomain;
	public ArrayList<String> domains = new ArrayList<String>();
	public ArrayList<String> variables = new ArrayList<String>();
	// private ArrayList<String> topics = new ArrayList<String>();
	// private ArrayList<String> cadres = new ArrayList<String>();
	// private ArrayList<String> types = new ArrayList<String>();
	// private ArrayList<String> extensivities = new ArrayList<String>();
	// private ArrayList<String> continuity = new ArrayList<String>();
	// private ArrayList<String> characteristics = new ArrayList<String>();
	// private ArrayList<String> levels = new ArrayList<String>();
	// private ArrayList<String> symbols = new ArrayList<String>();
	public ArrayList<Variable> variablesObj = new ArrayList<Variable>();
	private DefaultListModel<String> listModelVisible = new DefaultListModel<String>();
	private DefaultListModel<String> listModelHidden = new DefaultListModel<String>();
	private DefaultComboBoxModel<String> listModelDomain = new DefaultComboBoxModel<String>();

	public static JPanel PanDialX() {

		JPanel panInt = new JPanel();
		panInt.setSize(1400, 200);
		// panInt.setBackground(Color.yellow);

		JPanel panType = new JPanel();
		// panType.setBackground(Color.blue);
		panType.setPreferredSize(new Dimension(220, 60));
		panType.setBorder(BorderFactory.createTitledBorder("Type du thème"));
		JComboBox<String> type = new JComboBox<String>();
		type.addItem("Scalaires discrets");
		type.addItem("Scalaires continus");
		type.addItem("Vecteurs");
		type.addItem("Tenseurs, matrices");
		type.addItem("Autre");
		JLabel typeLabel = new JLabel("Type : ");
		panType.add(typeLabel);
		panType.add(type);

		JPanel panDim = new JPanel();
		panDim.setBackground(Color.white);
		panDim.setBorder(BorderFactory.createTitledBorder("Dimension du thème "));
		panDim.setPreferredSize(new Dimension(440, 60));
		JRadioButton option1 = new JRadioButton("-1 - questions");
		option1.setSelected(true);
		JRadioButton option2 = new JRadioButton("0 - état");
		JRadioButton option3 = new JRadioButton("1 - solutions - causes directes");
		JRadioButton option4 = new JRadioButton("2 - solutions - champ d'évolutions");
		ButtonGroup bg = new ButtonGroup();
		bg.add(option1);
		bg.add(option2);
		bg.add(option3);
		bg.add(option4);
		panDim.add(option1);
		panDim.add(option2);
		panDim.add(option3);
		panDim.add(option4);

		JPanel panTitle = new JPanel();
		panTitle.setBackground(Color.white);
		panTitle.setPreferredSize(new Dimension(520, 60));
		panTitle.setBorder(BorderFactory.createTitledBorder("Titre du thème "));
		JLabel titleLabel = new JLabel("Titre : ");
		JTextField titleJ = new JTextField("");
		titleJ.setPreferredSize(new Dimension(290, 25));
		panTitle.add(titleLabel);
		panTitle.add(titleJ);

		JPanel content = new JPanel();
		// content.setBackground(Color.red);
		content.add(panType);
		content.add(panDim);
		content.add(panTitle);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowDialInfo wInfo = new WindowDialInfo((String) type.getSelectedItem(), getDim(), getTitle());
				wInfo.setVisible(false);
			}

			public String getDim() {
				return (option1.isSelected()) ? option1.getText()
						: (option2.isSelected()) ? option2.getText()
								: (option3.isSelected()) ? option3.getText()
										: (option4.isSelected()) ? option4.getText() : option1.getText();
			}

			public String getTitle() {
				return (titleJ.getText().equals("")) ? "Titre " : titleJ.getText();
			}
		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		panInt.add(content, BorderLayout.NORTH);
		panInt.add(control, BorderLayout.WEST);
		panInt.setVisible(true);

		return panInt;
	}

	public WindowDialInfo showWindowDial() {
		this.sendData = false;
		this.setVisible(true);
		return this.wInfo;
	}

	public Main(JPanel frameX, JMenuBar wMenuB) {

		domains.add("Tous domaines");
		variables.add("Toutes variables du domaine");
		variablesObj = Connect.connect();
		JFrame globalW = new JFrame();
		globalW.setSize(1400, 600);
		globalW.add(frameX);

		Collections.sort(domains);

		for (Iterator<Variable> iter = variablesObj.iterator(); iter.hasNext();) {
			Variable v = iter.next();
			variables.add(v.getTitle());

		}

		contents = new JPanel();
		contents.setBorder(domainContents);
		contents.setLayout(new BorderLayout());
		setContentPane(contents);

		JLabel lblTitle = new JLabel("Variables", SwingConstants.CENTER);
		contents.add(lblTitle, BorderLayout.NORTH);

		panelCenter = new JPanel();
		panelCenter.setBorder(domainCenter);

		labelListHidden = new JLabel("Hidden");
		labelListHidden.setAlignmentX(LEFT_ALIGNMENT);

		initHiddenModel();
		listHidden = new JList<>(listModelHidden);
		listHidden.setAlignmentX(LEFT_ALIGNMENT);
		listHidden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listHidden.setBorder(domainList);

		JScrollPane scrollListHidden = new JScrollPane(listHidden);
		scrollListHidden.setAlignmentX(LEFT_ALIGNMENT);
		scrollListHidden.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setSpecificSize(scrollListHidden, new Dimension(200, 300));

		boxListHidden.add(labelListHidden);
		boxListHidden.add(scrollListHidden);
		panelCenter.add(boxListHidden);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));

		buttonAdd = new JButton("Add >");
		buttonAddAll = new JButton("Add All >>");
		buttonRemove = new JButton("< Remove");
		buttonRemoveAll = new JButton("<< Remove All");

		Dimension dimRef = buttonRemoveAll.getPreferredSize();
		setSpecificSize(buttonAdd, dimRef);
		setSpecificSize(buttonAddAll, dimRef);
		setSpecificSize(buttonRemove, dimRef);

		boxButtons.add(buttonAdd);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 5)));
		boxButtons.add(buttonAddAll);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 20)));
		boxButtons.add(buttonRemove);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 5)));
		boxButtons.add(buttonRemoveAll);
		panelCenter.add(boxButtons);

		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));

		labelListVisible = new JLabel("Visible");
		labelListVisible.setAlignmentX(LEFT_ALIGNMENT);

		listVisible = new JList<>(listModelVisible);
		listVisible.setAlignmentX(LEFT_ALIGNMENT);
		listVisible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVisible.setBorder(domainList);

		JScrollPane scrollListVisible = new JScrollPane(listVisible);
		scrollListVisible.setAlignmentX(LEFT_ALIGNMENT);
		scrollListVisible.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setSpecificSize(scrollListVisible, new Dimension(200, 300));

		boxListVisible.add(labelListVisible);
		boxListVisible.add(scrollListVisible);
		panelCenter.add(boxListVisible);

		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));

		labellistDomain = new JLabel("Domain");
		labellistDomain.setAlignmentX(LEFT_ALIGNMENT);

		initDomainModel();
		// initDomainModelTopic();

		listDomain = new JComboBox<>(listModelDomain);
		listDomain.setAlignmentX(LEFT_ALIGNMENT);

		Dimension dimListView = listDomain.getPreferredSize();
		setSpecificSize(listDomain, new Dimension(200, dimListView.height));

		boxListDomain.add(labellistDomain);
		boxListDomain.add(listDomain);
		panelCenter.add(boxListDomain);

		contents.add(panelCenter, BorderLayout.CENTER);

		panelSouth = new JPanel();

		labelSelectedVisiblelb = new JLabel("Selected Visible");
		labelSelectedVisible = new JLabel();
		labelSelectedHiddenlb = new JLabel("Selected Hidden");
		labelSelectedHidden = new JLabel();
		labelSelectedDomainlb = new JLabel("Selected Domain");
		labelSelectedDomain = new JLabel();
		labelListHidden.setAlignmentX(LEFT_ALIGNMENT);

		panelSouth.add(labelSelectedHiddenlb);
		panelSouth.add(labelSelectedHidden);
		panelSouth.add(Box.createRigidArea(new Dimension(100, 1)));
		panelSouth.add(labelSelectedVisiblelb);
		panelSouth.add(labelSelectedVisible);
		panelSouth.add(Box.createRigidArea(new Dimension(100, 1)));
		panelSouth.add(labelSelectedDomainlb);
		panelSouth.add(labelSelectedDomain);

		contents.add(panelCenter, BorderLayout.SOUTH);

		buttonAdd.addActionListener(this);
		buttonAddAll.addActionListener(this);
		buttonRemove.addActionListener(this);
		buttonRemoveAll.addActionListener(this);
		listVisible.addListSelectionListener(this);
		listHidden.addListSelectionListener(this);
		listDomain.addItemListener(this);
		globalW.setJMenuBar(wMenuB);
		globalW.setLocationRelativeTo(null);
		globalW.add(contents);
		globalW.setResizable(false);
		globalW.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == buttonAdd) {
			try {
				addItem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		if (source == buttonAddAll) {
			addAllItems();
			return;
		}
		if (source == buttonRemove) {
			try {
				removeItem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		if (source == buttonRemoveAll) {
			try {
				removeAllItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	public void addItem() throws SQLException {
		int iSelected = listHidden.getSelectedIndex();
		if (iSelected == -1) {
			return;
		}

		String addedItem = listHidden.getSelectedValue();
		listModelHidden.removeElementAt(iSelected);
		displaySelectedItems();
		int size = listModelVisible.getSize();
		if (size == 0) {
			listModelVisible.addElement(addedItem);
			return;
		}

		for (int i = 0; i < size; i++) {
			String item = listModelVisible.elementAt(i);
			int compare = addedItem.compareToIgnoreCase(item);
			if (compare < 0) {
				listModelVisible.add(i, addedItem);
				return;
			}
		}

		listModelVisible.addElement(addedItem);

	}

	public void addAllItems() {
		listModelVisible.clear();
		if (domainChanged.equals("Tous domaines")) {
			Collections.sort(variables);
			variables.forEach((String s) -> {
				listModelVisible.addElement(s);
			});
		}
		listModelHidden.clear();
	}

	private int changeDomain() {
		int iSelected = listDomain.getSelectedIndex();
		String domX;
		if (iSelected == -1) {
			return 1;
		}
		Object selectedDomain = listDomain.getSelectedItem();
		// switch(iSelected) {}
		domainChanged = (String) selectedDomain;

		ArrayList<Topic> inTop = Connect.topic();
		for (Topic topX : inTop) {
			int iddomX = topX.getIddomain();
			domX = topX.getTitle();
			if (domains.contains(domX)) {
				System.out.println("iddomX" + iddomX + " : " + domX);
				domains.add(domX);
			}
		}
		Collections.sort(domains);
		return iSelected;
	}

	private void displaySelectedItems() throws SQLException {

		String itemName;
		List<Integer> listType;
		int iLevPlus;
		Variable eltSup;
		ArrayList<Variable> listVar = Connect.connect(); // Elements.Element();
		for (int k = 0; k < listVar.size(); k++) {
			System.out.println("k : " + k);
			System.out.println("Contenu : " + listVar.get(k).getContent());
			System.out.println("Continuité : " + listVar.get(k).getContinuity());
			System.out.println("Extensivité : " + listVar.get(k).getExtensivity());
			System.out.println("Type : " + listVar.get(k).getType());

			Array Arrx = listVar.get(k).getType();
			Integer[] typex = (Integer[]) Arrx.getArray();
			System.out.println(Arrays.toString(typex));
			listType = Arrays.asList(typex);
			for (int i = 0; i < typex.length; i++) {
				iLevPlus = listType.get(i);
				System.out.println("element " + i + " = " + iLevPlus);
				if (iLevPlus < listVar.size()) {
					eltSup = listVar.get(iLevPlus);
					if (listVar.contains(eltSup)) {
						System.out.println("element sup " + iLevPlus + " = " + eltSup.getContent());
						System.out.println("Contenu sup : " + eltSup.getContent());
						System.out.println(eltSup.getContinuity());
						System.out.println(eltSup.getExtensivity());
						System.out.println("Type sup : " + eltSup.getType());
					}
				}
			}
			System.out.println("Dim : " + listVar.get(k).getDim());
			System.out.println("Domain : " + listVar.get(k).getDomain());
		}

		int iSelected = listHidden.getSelectedIndex();
		if (iSelected == -1) {
			labelSelectedHidden.setText("");
		} else {
			itemName = listVisible.getSelectedValue();
			labelSelectedVisible.setText(itemName);
		}
	}

	private void initDomainModel() {
		ArrayList<Variable> inVar = Connect.connect();
		for (Variable varX : inVar) {
			String domX = varX.getDomain();
			if (!domains.contains(domX)) {
				System.out.println("domX" + domX);
				domains.add(domX);
			}
		}
		Collections.sort(domains);
		domains.forEach((String s) -> {
			System.out.println(s);
			listModelDomain.addElement(s);
		});
		return;
	}

	private void initHiddenModel() {
		listModelHidden.clear();
		// if (!domainChanged.equals("Tous domaines")) {
		// listModelVisible.clear();
		// displaySelectedItems();
		// return;
		// }
		Collections.sort(variables);
		variables.forEach((String s) -> {
			listModelHidden.addElement(s);
		});

	}

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if (source == listDomain) {
			changeDomain();
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	private void removeItem() throws SQLException {
		int iSelected = listVisible.getSelectedIndex();
		String removedItem = listVisible.getSelectedValue();
		if (iSelected == -1) {
			return;
		}
		listModelVisible.remove(iSelected);
		displaySelectedItems();
		int size = listModelHidden.getSize();
		if (size == 0) {
			listModelHidden.addElement(removedItem);
			return;
		}

		for (int i = 0; i < size; i++) {
			String item = listModelHidden.elementAt(i);
			int compare = removedItem.compareToIgnoreCase(item);
			if (compare < 0) {
				listModelHidden.add(i, removedItem);
				return;
			}
		}
		listModelHidden.addElement(removedItem);
	}

	private void removeAllItems() throws SQLException {
		listModelHidden.clear();
		initHiddenModel();
		listModelVisible.clear();
		displaySelectedItems();

	}

	private void setSpecificSize(JComponent component, Dimension dimension) {
		component.setMinimumSize(dimension);
		component.setPreferredSize(dimension);
		component.setMaximumSize(dimension);
	}

	public void valueChanged(ListSelectionEvent e) {
		Object source = e.getSource();
		if (source == listHidden) {
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (source == listVisible) {
			// changeImage();
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return;
	}

	public void createVariable(int id, ArrayList<Variable> list, String title, String domain, Array type,
			String characteristics, int dim, String extensivity, String continuity, String content, int idLevelInt,
			LocalDate saved) {
		if (title.matches("^[0-9.]+$")) {
			list.add(new Variable(id, title, domain, type, characteristics, dim, extensivity, continuity, content,
					idLevelInt, saved));
		}
	}

	public static void main(String[] args) {
		JMenuBar wMenuBar = MenuW.MenuGlobal();
		JPanel paDial = PanDialX();
		Main mainx = new Main(paDial, wMenuBar);
		mainx.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainx.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
