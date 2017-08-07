package tw2.bot.login;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import tw2.bot.army.Army;
import tw2.bot.atac.Atac;
import tw2.bot.depozit.Depozit;
import tw2.bot.webdr.WebSetup;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1902237766851875674L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JTextField txtX;
	private JTextField txtY;
	JPanel panel_1 = null;
	JTabbedPane tabbedPane = null;
	JButton btnAdauga = null;
	JList<String> comboBox = null;
	JCheckBox chckbxAtac = null;
	DefaultListModel<String> modelList;
	JScrollPane scrollPane = null;
	JButton btnRemoveAll = null;
	boolean isDepositChecked = false;

	WebSetup websetup = null;
	
	Login login = null;
	Depozit depozit = null;
	Army army = null;
	Atac atac =  null;
	private JTextField textField;
	JSlider slider = null;
	private int cicluri= 1;
	JLabel lblNumarCicluriAtac = null;
	JButton btnStartJob = null;
	private JTextField textField_1;
	JLabel lblPradaPerAtac =null;
	JComboBox<String> comboBox_1 = null;
	DefaultComboBoxModel<String> cmodel = null;
	JCheckBox chckbxTestHeadless = null;
	JCheckBox chckbxLucrareLaDepozit= null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			if (!SystemTray.isSupported()) {
				System.out.println("System tray is not supported !!! ");
				return;
			}
			SystemTray systemTray = SystemTray.getSystemTray();
			
		
			Image image = ImageIO.read(UI.class.getResource("/resources/1.png"));
		
			

			UI dialog = new UI();
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);
			
			PopupMenu trayPopupMenu = new PopupMenu();
			MenuItem action = new MenuItem("Show");
			action.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(true);
					dialog.toFront();
					dialog.repaint();
				}
			});
			trayPopupMenu.add(action);
			MenuItem close = new MenuItem("Close");
			close.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (dialog.websetup != null) {
						try {
							if(dialog.websetup.getWait()!=null)
							dialog.websetup.quitWithDelay();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					System.exit(0);
				}
			});
			trayPopupMenu.add(close);
			// setting tray icon
			TrayIcon trayIcon = new TrayIcon(image, "TW2 Bot", trayPopupMenu);
			// adjust to default size as per system recommendation
			trayIcon.setImageAutoSize(true);

			try {
				systemTray.add(trayIcon);
			} catch (AWTException awtException) {
				awtException.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UI() {
		setResizable(false);
		setBounds(100, 100, 460, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		setName("TW2-Bot");
		setTitle("TW2-Bot");
		try {
			setIconImage(ImageIO.read(UI.class.getResource("/resources/1.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPanel.add(tabbedPane, gbc_tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Login Tab", null, panel, null);
		panel.setLayout(null);

		JLabel lblTribalWars = new JLabel("Tribal Wars 2 Test Bot");
		lblTribalWars.setBounds(99, 0, 210, 15);
		panel.add(lblTribalWars);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(99, 27, 188, 15);
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setToolTipText("Username");
		txtUsername.setBounds(99, 54, 188, 19);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(99, 86, 188, 15);
		panel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(99, 113, 188, 19);
		panel.add(passwordField);

		JButton btnEnter = new JButton("Enter");
		SwingUtilities.getRootPane(panel).setDefaultButton(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnterEvent(e);

			}

		});
		btnEnter.setBounds(99, 236, 188, 25);
		panel.add(btnEnter);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setToolTipText("Lumea in care doresti sa intrii(Lumea trebuie sa fie disponibila)");
		cmodel =  new DefaultComboBoxModel<>();
		cmodel.addElement("Prima Disponibila");
		cmodel.addElement("Orava");
		comboBox_1.setModel(cmodel);
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setEditable(false);
		comboBox_1.setBounds(99, 173, 188, 24);
		panel.add(comboBox_1);
		
		JLabel lblAlegeLumea = new JLabel("Alege Lumea:");
		lblAlegeLumea.setBounds(99, 146, 188, 15);
		panel.add(lblAlegeLumea);
		
		chckbxTestHeadless = new JCheckBox("Test Headless");
		chckbxTestHeadless.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		chckbxTestHeadless.setToolTipText("Acesta este un test pentru bot fara browser");
		chckbxTestHeadless.setBounds(99, 205, 129, 23);
		panel.add(chckbxTestHeadless);

		panel_1 = new JPanel();
		//tabbedPane.addTab("Bot", null, panel_1, null);//del after.
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		JLabel lblAcestaEsteUn = new JLabel("Acesta este un test....");
		lblAcestaEsteUn.setBounds(12, 12, 410, 15);
		panel_1.add(lblAcestaEsteUn);

		JLabel lblLucrareLaDepozitul = new JLabel("Lucrare la Depozitul de resurse ?");
		lblLucrareLaDepozitul.setBounds(12, 39, 410, 15);
		panel_1.add(lblLucrareLaDepozitul);

		chckbxLucrareLaDepozit = new JCheckBox("Lucrare la Depozitul de Resurse ?");
		chckbxLucrareLaDepozit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TO DO:
				if (chckbxLucrareLaDepozit.isSelected()){
					isDepositChecked = true;
					btnStartJob.setEnabled(true);
				}
				else{
					isDepositChecked = false;
					btnStartJob.setEnabled(false);
				}
			}

		});
		chckbxLucrareLaDepozit.setBounds(12, 62, 410, 23);
		panel_1.add(chckbxLucrareLaDepozit);
		//test pentru headless
		if(chckbxTestHeadless.isSelected())
			chckbxLucrareLaDepozit.setEnabled(false);

		JLabel lblDupaTerminareaLucrarii = new JLabel("Dupa terminarea lucrarii la depozit. facem atac ?");
		lblDupaTerminareaLucrarii.setBounds(12, 94, 410, 15);
		panel_1.add(lblDupaTerminareaLucrarii);

		chckbxAtac = new JCheckBox("Atac ?");
		chckbxAtac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttackCheck(e);
			}
		});
		chckbxAtac.setBounds(12, 117, 129, 23);
		panel_1.add(chckbxAtac);

		txtX = new JTextField();
		txtX.setToolTipText("Coordonate x");

		txtX.setBounds(12, 145, 114, 19);
		panel_1.add(txtX);
		txtX.setVisible(false);
		txtX.setColumns(10);

		txtY = new JTextField();
		txtY.setToolTipText("Coordonate y");

		txtY.setBounds(138, 145, 114, 19);
		panel_1.add(txtY);
		txtY.setVisible(false);
		txtY.setColumns(10);

		btnAdauga = new JButton("Adauga");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdaugaSat(e);
			}

		});
		btnAdauga.setBounds(278, 142, 117, 25);
		panel_1.add(btnAdauga);
		btnAdauga.setVisible(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 176, 114, 121);
		comboBox = new JList<String>();
		// comboBox.setBounds(12, 176, 114, 48);
		modelList = new DefaultListModel<String>();
		comboBox.setModel(modelList);
		scrollPane.add(comboBox);
		panel_1.add(scrollPane);
		scrollPane.setViewportView(comboBox);
		scrollPane.setVisible(false);

		btnRemoveAll = new JButton("Remove ALL");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelList.removeAllElements();
			}
		});
		btnRemoveAll.setBounds(137, 272, 129, 25);
		panel_1.add(btnRemoveAll);

		btnStartJob = new JButton("Start Job");
		btnStartJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startJob(e);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnStartJob.setBounds(314, 272, 117, 25);
		btnStartJob.setEnabled(false);
		panel_1.add(btnStartJob);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numarCicluri(e);
			}

		
		});
		slider.setValue(1);
		slider.setToolTipText("Numar Cicluri");
		slider.setMaximum(10);
		slider.setMinimum(1);
		slider.setBounds(138, 200, 200, 16);
		slider.setVisible(false);
		panel_1.add(slider);
		
		lblNumarCicluriAtac = new JLabel("Numar Cicluri Atac");
		lblNumarCicluriAtac.setBounds(138, 176, 257, 15);
		lblNumarCicluriAtac.setVisible(false);
		panel_1.add(lblNumarCicluriAtac);
		
		textField = new JTextField();
		textField.setToolTipText("Cicluri");
		textField.setBounds(138, 223, 114, 19);
		textField.setEditable(false);
		textField.setText(String.valueOf(1));
		panel_1.add(textField);
		textField.setColumns(10);
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class); 
		
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		
		
		textField_1 = new JFormattedTextField(numberFormatter);
		textField_1.setToolTipText("Cantitate Prada Per Atac");
		textField_1.setText("8000");
		textField_1.setBounds(278, 242, 117, 19);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
		lblPradaPerAtac = new JLabel("Prada per atac");
		lblPradaPerAtac.setBounds(278, 225, 117, 15);
		panel_1.add(lblPradaPerAtac);
		lblPradaPerAtac.setVisible(false);
		textField.setVisible(false);
		btnRemoveAll.setVisible(false);
	}

	private void AttackCheck(ActionEvent e) {
		// TODO Auto-generated method stub
		if (chckbxAtac.isSelected()) {
			txtX.setVisible(true);
			txtY.setVisible(true);
			btnAdauga.setVisible(true);
			scrollPane.setVisible(true);
			btnRemoveAll.setVisible(true);
			slider.setVisible(true);
			textField.setVisible(true);
			lblNumarCicluriAtac.setVisible(true);
			btnStartJob.setEnabled(true);
			lblPradaPerAtac.setVisible(true);
			textField_1.setVisible(true);
			
		}
		 else {
			txtX.setVisible(false);
			txtY.setVisible(false);
			btnAdauga.setVisible(false);
			scrollPane.setVisible(false);
			btnRemoveAll.setVisible(false);
			modelList.removeAllElements();
			slider.setVisible(false);
			textField.setVisible(false);
			lblNumarCicluriAtac.setVisible(false);
			btnStartJob.setEnabled(false);
			lblPradaPerAtac.setVisible(false);
			textField_1.setVisible(false);
		}

	}

	private void AdaugaSat(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean ch = false;
		if (ch == false) {
			if (txtX.getText().isEmpty()) {
				Component dialog = null;
				JOptionPane.showMessageDialog(dialog, "Coordonatele x nu sunt setate");
			} else if (txtY.getText().isEmpty()) {
				Component dialog = null;
				JOptionPane.showMessageDialog(dialog, "Coordonatele y nu sunt setate");
			} else if (!txtX.getText().matches("[0-9]+") || !txtY.getText().matches("[0-9]+")) {
				Component dialog = null;
				JOptionPane.showMessageDialog(dialog, "Coordonatele pot fi doar numere.");
			}

			else
				ch = true;
		}
		if (ch) {
			modelList.addElement(txtX.getText() + ":" + txtY.getText());
			txtX.setText("");
			txtY.setText("");
		}
	}

	private void btnEnterEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean rdy = false;
		if (rdy == false) {
			if (txtUsername.getText().isEmpty()) {
				Component dialog = null;
				JOptionPane.showMessageDialog(dialog, "Username Not set");
			} else if (passwordField.getPassword().length < 1) {
				Component dialog = null;
				JOptionPane.showMessageDialog(dialog, "Password Not set");
			} else
				rdy = true;

		}
		if (rdy) {

			tabbedPane.addTab("Bot", null, panel_1, null);
			tabbedPane.setSelectedIndex(1);
			tabbedPane.removeTabAt(0);
		}

	}

	private void startJob(ActionEvent e) throws InterruptedException {
		// TODO Auto-generated method stub
		websetup = new WebSetup(chckbxTestHeadless.isSelected());
		this.setVisible(false);
		Thread one = new Thread() {
		    public void run() {
		        try {
		        	btnStartJob.setEnabled(false);
		        	chckbxAtac.setEnabled(false);
		        	chckbxLucrareLaDepozit.setEnabled(false);
		        	btnAdauga.setEnabled(false);
		        	btnRemoveAll.setEnabled(false);
		        	login = new Login(websetup.getChromeWebDriver(), txtUsername.getText().toString(),
		    				String.valueOf(passwordField.getPassword()),cmodel.getSelectedItem().toString());

		    		if (isDepositChecked)
		    			depozit = new Depozit(websetup.getChromeWebDriver(), websetup.getWait(),websetup.getAWTRobot());
		    		if(chckbxAtac.isSelected()){
		    			army =  new Army();
		    			army.armyInfo(websetup.getChromeWebDriver(),websetup.getWait());
		    			if(!(army.getArmy()==null)){
		    				atac =  new Atac();
		    				List<String> ar= new ArrayList<String>();
		    				for(int i =0;i<modelList.getSize();i++)
		    					ar.add(modelList.getElementAt(i));
		    				atac.atac(websetup.getChromeWebDriver(), army.getArmy(), ar,websetup.getWait(),cicluri,
		    						Integer.parseInt(textField_1.getText()),websetup.getAWTRobot());
		    			}
		    		}
		    		
		    		btnStartJob.setEnabled(true);
		    		chckbxAtac.setEnabled(true);
		    		chckbxLucrareLaDepozit.setEnabled(true);
		    		btnAdauga.setEnabled(true);
		        	btnRemoveAll.setEnabled(true);
		    		System.out.println("Job Done !(now feel bad using this bot)");
		    		websetup.quitWithDelay();
		    	//	atac.service.shutdownNow();
		    		
		        } catch(InterruptedException v) {
		            System.out.println(v);
		            try{
		            //	atac.service.shutdownNow();//test needed
		            	websetup.quitWithDelay();
		            }catch(Exception e){
		            	e.printStackTrace(System.out);
		            }
		        }
		    }  
		};

		one.start();
		

	}
	
	
	private void numarCicluri(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(slider.getValueIsAdjusting()){
			textField.setText(String.valueOf(slider.getValue()));
			cicluri = slider.getValue();
		}
	}
}
