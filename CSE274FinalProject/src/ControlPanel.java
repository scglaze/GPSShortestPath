import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

// CSE 274 Final Project
public class ControlPanel extends JPanel {

	// Properties =================================================
	private Graph graph = new Graph("MapInformationXY.txt");
	private JButton executeButton;
	private JRadioButton displayAddressesButton, displaySymbolsButton, timeCostButton, 
					     distanceCostButton, highwaysButton, avoidHighwaysButton;
	private JTextArea displayPathAndCost;
	private JTable sources, destinations;
	private Vertex selectedSource, selectedDestination;
	
	// Constructors ===============================================
	public ControlPanel() {
		this.setLayout(new GridLayout(2, 1));
		this.setSize(400, 800);
		
		// Separating the GUI into two halves. The top half will contain JTables where the user can 
		// Choose from a list of addresses to travel between and find the shortest path based on 
		// the info that they entered. The bottom half is split into 2 halves, the top half containing
		// Radio buttons for the user to make selections, and the bottom half will contain text
		// fields to display the shortest path as well as the cost of that path.
		
		// Creating the top JPanel and giving it a border and a layout manager.
		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createTitledBorder("Select Addresses"));
		GridLayout topPanelLayout = new GridLayout(1, 2);
		topPanelLayout.setHgap(25);
		topPanel.setLayout(topPanelLayout);
		
		// Creating 2 JScrollPanes that each contain a JTable with all of the addresses from 
		// the file in them, and adding them to the top panel.
		
		sources = getAddressesFromFile("Beginning Vertices");
		// Adding a ListSelectionListener to sources to keep track of the user's selection
		ListSelectionModel sourcesSelectionModel = sources.getSelectionModel();
		sourcesSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
		        int[] selectedRow = sources.getSelectedRows();
		        int[] selectedColumns = sources.getSelectedColumns();

		        for (int i = 0; i < selectedRow.length; i++) {
					for (int j = 0; j < selectedColumns.length; j++) {
						selectedSource = graph.findVertex((String) sources.getValueAt(selectedRow[i], selectedColumns[j]));
					}
		        }
			}			
		});
		JScrollPane sourcesScrollPane = new JScrollPane(sources);
		sourcesScrollPane.setPreferredSize(sources.getPreferredSize());
		
		destinations = getAddressesFromFile("Destination Vertices");
		// Adding a ListSelectionListener to destinations to keep track of the user's selection
		ListSelectionModel destinationsSelectionModel = destinations.getSelectionModel();
		destinationsSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
		        int[] selectedRow = destinations.getSelectedRows();
		        int[] selectedColumns = destinations.getSelectedColumns();

		        for (int i = 0; i < selectedRow.length; i++) {
					for (int j = 0; j < selectedColumns.length; j++) {
						selectedDestination = graph.findVertex((String) destinations.getValueAt(selectedRow[i], selectedColumns[j]));
					}
		        }
			}			
		});
		JScrollPane destinationsScrollPane = new JScrollPane(destinations);
		destinationsScrollPane.setPreferredSize(destinations.getPreferredSize());
		
		// Adding the finished JScrollPanes with JTables inside them to the panel.
		topPanel.add(sourcesScrollPane);
		topPanel.add(destinationsScrollPane);
		
		// Creating the bottom JPanel.
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		
		// Creating the top sub panel of bottomPanel
		JPanel bottomSubPanel1 = new JPanel();
		bottomSubPanel1.setBorder(BorderFactory.createTitledBorder("Select Shortest Path Criteria"));
		BorderLayout bottomSubPanel1Layout = new BorderLayout();
		bottomSubPanel1Layout.setHgap(25);
		bottomSubPanel1Layout.setVgap(25);
		bottomSubPanel1.setLayout(bottomSubPanel1Layout);	
		
		// Creating a new JPanel and adding all RadioButtons to it.
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new GridLayout(2, 4));
		
		// Adding buttons to a ButtonGroup makes it so only one of them may be selected at a time
		ButtonGroup buttongroup1 = new ButtonGroup();
		JLabel displayAddressesLabel = new JLabel("Display Addresses");
		displayAddressesButton = new JRadioButton();
		//displayAddressesButton.addActionListener(new DisplayButtonsActionListener());
		JLabel displaySymbolsLabel = new JLabel("Display Symbols");
		displaySymbolsButton = new JRadioButton();
		//displaySymbolsButton.addActionListener(new DisplayButtonsActionListener());
		buttongroup1.add(displayAddressesButton);
		buttongroup1.add(displaySymbolsButton);
		
		ButtonGroup buttongroup2 = new ButtonGroup();
		JLabel timeCostLabel = new JLabel("Find Time Cost");
		timeCostButton = new JRadioButton();
		JLabel distanceCostLabel = new JLabel("Find Distance Cost");
		distanceCostButton = new JRadioButton();
		buttongroup2.add(timeCostButton);
		buttongroup2.add(distanceCostButton);
		
		radioButtonPanel.add(displayAddressesLabel);
		radioButtonPanel.add(displayAddressesButton);
		radioButtonPanel.add(displaySymbolsLabel);
		radioButtonPanel.add(displaySymbolsButton);
		radioButtonPanel.add(timeCostLabel);
		radioButtonPanel.add(timeCostButton);
		radioButtonPanel.add(distanceCostLabel);
		radioButtonPanel.add(distanceCostButton);
		
		// Creating the button the user can press to find the shortest path based
		// on the criteria that they entered. Selecting buttons by default so 
		// empty buttons do not need to be accounted for.
		displayAddressesButton.setSelected(true);
		timeCostButton.setSelected(true);
		executeButton = new JButton("Execute");
		executeButton.addActionListener(new ExecuteButtonListener());
		
		bottomSubPanel1.add(radioButtonPanel, BorderLayout.CENTER);
		bottomSubPanel1.add(executeButton, BorderLayout.SOUTH);
		
		// Creating the bottom sub panel of bottomPanel
		JPanel bottomSubPanel2 = new JPanel();
		bottomSubPanel2.setBorder(BorderFactory.createTitledBorder("Shortest Path and Cost"));
		
		displayPathAndCost = new JTextArea(10, 50);
		displayPathAndCost.setBackground(Color.white);
		displayPathAndCost.setEditable(false);
		displayPathAndCost.setLineWrap(true);
		JScrollPane displayScollPane = new JScrollPane(displayPathAndCost);
		bottomSubPanel2.add(displayScollPane);
		
		// Adding the finished panels to the ControlPanel
		bottomPanel.add(bottomSubPanel1);
		bottomPanel.add(bottomSubPanel2);
		add(topPanel);
		add(bottomPanel);
	}
	
	// Methods ====================================================
	private JTable getAddressesFromFile(String header) {
		
		// Getting data from the file
		Set<Vertex> vertices = graph.getVertices();
		
		// Populating a 2D array that contains the address information from the file
		// This array will be used in the constructor of the JTable
		String[][] addressData = new String[vertices.size()][1];
		int index = 0;
		for (Vertex v : vertices) {
			addressData[index++][0] = Graph.returnAddress ? v.getAddress() : v.getSymbol();
		}
		String[] columnHeaderName = {header};
		
		// Creating the JTable to return and adjusting properties
		JTable addressTable = new JTable(addressData, columnHeaderName);
		addressTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addressTable.setRowHeight(18);
		TableColumnModel columnModel = addressTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(0).setHeaderValue(header);
		
		// Making all cells in the JTable uneditable.
		DefaultTableModel addressTableModel = new DefaultTableModel(addressData, columnHeaderName) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		addressTable.setModel(addressTableModel);
		
		return addressTable;
	}

	// Internal Class to respond to the user clicking the Execute button
	private class ExecuteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Graph.returnAddress = displayAddressesButton.isSelected();
			Graph.useDistCost = distanceCostButton.isSelected();
			
			// Checking that the user has selected both a beginning address and a destination
			if (selectedSource == null || selectedDestination == null) {
				displayPathAndCost.setText("Select both a beginning address and a destination!");
			} else {	
				Path shortestPath = Dijkstra.shortestPath(selectedSource, selectedDestination);
				if (shortestPath != null) {
					displayPathAndCost.setText(String.format("Shortest Path: %s%n%nCost: %s", shortestPath.toString(), Graph.useDistCost ? shortestPath.distanceCost() + " miles": shortestPath.timeCost() + " minutes"));
				} else {
					displayPathAndCost.setText("No path found!");					
				}
			}
			
		}
		
	}
	/**
	private class DisplayButtonsActionListener implements ActionListener {
		
		// repopulating the JTables with either symbols or addresses.
		@Override
		public void actionPerformed(ActionEvent e) {
			Graph.returnAddress = displayAddressesButton.isSelected();
			System.out.println("changing");
			sources = getAddressesFromFile("Beginning Vertices");
			destinations = getAddressesFromFile("Destination Vertices");
		}
		
	}
	**/
}
