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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import listeners.DimListener;
import listeners.TypeListener;
import model.Topic;
import model.Variable;

public class WCreateElt extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	public static final String[] Elements = { "Domaine", "Thème", "Variables", "Type", "Caractéristiques",
			"Symbolisation" };
	private String domainChanged = "Tous domaines";
	private Border domainCenter = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private Border domainContents = BorderFactory.createEmptyBorder(0, 0, 10, 0);
	private Border domainList = BorderFactory.createLineBorder(Color.WHITE, 1);
	private Box boxButtons = Box.createVerticalBox();
	private Box boxListHidden = Box.createVerticalBox();
	private Box boxListVisible = Box.createVerticalBox();
	private Box boxListDomain = Box.createVerticalBox();
	private static JPanel contents;
	private static JPanel panelSouth;
	private static JPanel panelCenter;
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
	public ArrayList<Variable> variablesObj = new ArrayList<Variable>();
	private DefaultListModel<String> listModelVisible = new DefaultListModel<String>();
	private DefaultListModel<String> listModelHidden = new DefaultListModel<String>();
	private DefaultComboBoxModel<String> listModelDomain = new DefaultComboBoxModel<String>();
	public static String[] typeTab = { "Scalaires discrets", "Scalaires continus", "Vecteurs", "Tenseurs, matrices",
			"Autre" };
	public static String[] dimTab = { "-1 - questions", "0 - état", "1 - solutions - causes directes",
			"2 - solutions - champ d'évolutions" };
	int dimOK;
	Object obj;
	String domainOK = "";
	String contentOK = "";
	String titleOK = "";
	int idDomainOK = 0;
	int idTypeOK = 0;
	int idExtensivityOK = 0;
	int idContinuityOK = 0;
	int idCharacteristicsOK = 0;
	int idLevelOK = 0;
	Array arrTypeOK;
	String typeOK = "";
	Array arrVarOK;
	String arrVarStrOK = "";

	public WCreateElt(int iEltx, final int idM, int levelElt) {
		JPanel panInt = new JPanel();
		panInt.setSize(1400, 360);
		JPanel panType = new JPanel();
		panType.setPreferredSize(new Dimension(220, 60));
		panType.setBorder(BorderFactory.createTitledBorder("Type du thème"));
		JComboBox<String> type = new JComboBox<String>();
		TypeListener typeListener = new TypeListener();
		for (int iTy = 0; iTy < typeTab.length; iTy++) {
			type.addItem(typeTab[iTy]);
		}
		type.addActionListener(typeListener);
		JLabel typeLabel = new JLabel("Type : ");
		panType.add(typeLabel);
		panType.add(type);
		JPanel panDim = new JPanel();
		panDim.setBorder(BorderFactory.createTitledBorder("Dimension du thème "));
		panDim.setPreferredSize(new Dimension(440, 90));
		ButtonGroup group = new ButtonGroup();
		JRadioButton[] dimOption = new JRadioButton[dimTab.length];
		ButtonGroup bg = new ButtonGroup();
		DimListener dimListener = new DimListener();
		for (int iRb = 0; iRb < dimTab.length; iRb++) {
			dimOption[iRb] = new JRadioButton(dimTab[iRb]);
			dimOption[iRb].addActionListener(dimListener);
			group.add(dimOption[iRb]);
			bg.add(dimOption[iRb]);
			panDim.add(dimOption[iRb]);
		}
		dimOption[0].setSelected(true);
		JPanel panTitle = new JPanel();
		panTitle.setPreferredSize(new Dimension(520, 60));
		panTitle.setBorder(BorderFactory.createTitledBorder("Titre du thème "));
		JLabel titleLabel = new JLabel("Titre : ");
		JTextField titleJ = new JTextField("");
		titleJ.setPreferredSize(new Dimension(290, 25));
		panTitle.add(titleLabel);
		panTitle.add(titleJ);
		JPanel content = new JPanel();
		content.add(panType);
		content.add(panDim);
		content.add(panTitle);
		JPanel panContentElt = new JPanel();
		panContentElt.setPreferredSize(new Dimension(520, 230));
		panContentElt.setBorder(BorderFactory.createTitledBorder("Contenu du thème "));
		JLabel contentEltLabel = new JLabel("Contenu : ");
		JTextField contentEltJ = new JTextField("");
		contentEltJ.setPreferredSize(new Dimension(490, 150));
		panContentElt.add(contentEltLabel);
		panContentElt.add(contentEltJ);
		JPanel contentElt = new JPanel();
		contentElt.add(panContentElt);
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Enregistrer");
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titlex = getTitle();
				String contentx = getContent();
				WindowDialInfo wInfo = new WindowDialInfo(getDomain(), (String) type.getSelectedItem(), getDim(),
						titlex, contentx);
				wInfo.setVisible(false);
				System.out.println(wInfo.toString());
				switch (iEltx) {
				case 0:
					break;
				case 1:
					int idDomx = listDomain.getSelectedIndex();
					int idTypex = type.getSelectedIndex();
					ArrayList<Integer> arrVarx = new ArrayList<Integer>();
					int sizex = listModelVisible.getSize();
					for (int i = 0; i < sizex; i++) {
						arrVarx.add(Connect.getIdByTitleVar(listModelVisible.elementAt(i)));
					}
					int idX = idM + 1;
					System.out.println(arrVarx);
					ArrayList<Topic> listTop = new ArrayList<Topic>();
					if (titlex.matches("^[0-9.]+$") && idX > 0) {
						listTop.add(new Topic(idX, idDomx, titlex, (Array) arrVarx, contentx, idTypex));
						Connect.saveTopic(idX, idDomx, titlex, (Array) arrVarx, contentx, idTypex);
					}
					break;
				case 2:
					break;
				default:
					break;
				}
			}

			public String getDim() {
				int iRbSel = 0;
				while (iRbSel < dimTab.length) {
					if (dimOption[iRbSel].isSelected()) {
						return dimOption[iRbSel].getText();
					}
					iRbSel++;
				}
				dimOK = iRbSel - 1;
				return dimOption[0].getText();
			}

			public String getDomain() {
				idDomainOK = listDomain.getSelectedIndex();
				domainOK = listDomain.getItemAt(idDomainOK);
				return (domainOK.equals(null)) ? "Domaine" : domainOK;
			}

			public String getTitle() {
				titleOK = titleJ.getText();
				return (titleOK.equals(null)) ? "Titre " : titleOK;
			}

			public String getContent() {
				contentOK = contentEltJ.getText();
				return (contentOK.equals(null)) ? "Contenu " : contentOK;
			}
		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (levelElt % 3) {
				case 2:
					WCreation(iEltx, levelElt - 1);
					break;
				case 3:
					WCreation(iEltx, levelElt - 1);
					break;
				default:
					WCreation(iEltx, 1);
					break;
				}
			}
		});

		String labelButton = "Niveau ";
		switch (levelElt % 3) {
		case 1:
			labelButton += "Théories et formulations";
			break;
		case 2:
			labelButton += "Résultats et analyses";
			break;
		default:
			labelButton += "Objets et variables étudiés";
			break;
		}
		JButton levelSupButton = new JButton(labelButton);
		levelSupButton.setVisible(false);
		if (iEltx == 1) {
			levelSupButton.setVisible(true);
			levelSupButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					WCreation(iEltx, levelElt + 1);
				}
			});
		}
		control.add(okBouton);
		control.add(cancelBouton);
		control.add(levelSupButton);
		panInt.add(content, BorderLayout.NORTH);
		panInt.add(contentElt, BorderLayout.CENTER);
		panInt.add(control, BorderLayout.WEST);
		panInt.setVisible(true);
		domains.add("Tous domaines");
		variables.add("Toutes variables du domaine");
		variablesObj = Connect.initVariables();
		JFrame globalW = new JFrame();
		globalW.setSize(1400, 750);
		globalW.add(panInt);
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
		domainChanged = (String) selectedDomain;
		ArrayList<Topic> inTop = Connect.initTopic();
		for (Topic topX : inTop) {
			domX = topX.getTitle();
			if (domains.contains(domX)) {
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
		ArrayList<Variable> listVar = Connect.initVariables();
		for (int k = 0; k < listVar.size(); k++) {
			Array Arrx = listVar.get(k).getArrType();
			Integer[] typex = (Integer[]) Arrx.getArray();
			listType = Arrays.asList(typex);
			for (int i = 0; i < typex.length; i++) {
				iLevPlus = listType.get(i);
				if (iLevPlus < listVar.size()) {
					// eltSup = listVar.get(iLevPlus);
				}
			}
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
		Hashtable<String, ArrayList<String>> inDomTot = Connect.domTotString();
		for (String inDomX : inDomTot.get("title")) {
			domains.add(inDomX);
		}
		Collections.sort(domains);
		domains.forEach((String s) -> {
			listModelDomain.addElement(s);
		});

		return;
	}

	private void initHiddenModel() {
		listModelHidden.clear();
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
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return;
	}

	public WindowDialInfo showWindowDial() {
		this.sendData = false;
		this.setVisible(true);
		return this.wInfo;
	}

	public static void WCreation(final int iEt, int level) {
		final int idM = 1 + Connect.idMaxi(iEt);
		WCreateElt wtop = new WCreateElt(iEt, idM, level);
		wtop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
