import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.ObjectUtils;

import com.opencsv.CSVReader;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CVSsorting extends JFrame {

	private JPanel contentPane;
	public DefaultTableModel tableModel;
	private JTextField textField;
	public String[] strArray;
	private JButton btnSaveCvs;
	public List<String[]> result = new ArrayList<String[]>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CVSsorting frame = new CVSsorting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CVSsorting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save"); 
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		//String[] strArray;
		
		JButton btnLoadCvsButton = new JButton("Load CVS");
		btnLoadCvsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int userSelection = fileChooser.showOpenDialog(fileChooser);
            	if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToLoad = fileChooser.getSelectedFile();
                    try {
//                    	
                    	List<List<String>> records = new ArrayList<List<String>>();
	                    CSVReader reader = new CSVReader(new FileReader(fileToLoad));
	                    String[] values = null;
	                    while ((values = reader.readNext()) != null) {
	                        records.add(Arrays.asList(values));
	                    }
	                    
	                    System.out.println(Arrays.toString(records.toArray()));
	                    List<List<String>> temp = new ArrayList<List<String>>();
	                    Collections.sort(records, new CustomComparator());
	                    String hold = "";
	                    int userRemoveNum = 0;
	                    for (int counter = 1; counter < records.size(); counter++) {
	                    	
	                        //System.out.println(records.get(counter));
	                        
	                        //records.get(counter).size()
	                        for(int c = 0; c < temp.size(); c++) {
	                        	if(temp.get(c).get(4).contentEquals(records.get(counter).get(4))) {
		                        	if(Integer.parseInt(temp.get(c).get(3)) < Integer.parseInt(records.get(counter).get(3))) {
		                        		temp.remove(c);
		                        		userRemoveNum = counter;
		                        	}
		                        	else {
		                        		//records.remove(c);
		                        		//userRemoveNum = c;
		                        	}
		                        }
	                        }
	                        temp.add(records.get(counter));
	                        //records.get(counter);
	                        System.out.println(records.get(counter));
	                    } 
	                    //records.remove(userRemoveNum);
	                    temp.add(0,records.get(0));
	                    for (int counter = 0; counter < temp.size(); counter++) {
	                    	System.out.println(temp.get(counter));
	                    }
	                    Object[] dataCsv1 = temp.toArray();
	                    String[] strArray = new String[dataCsv1.length];
	                    for (int i = 0; i < dataCsv1.length; i++)
	                        strArray[i] = String.valueOf(dataCsv1[i]);
	                    result.add(strArray);
	                    for (int counter = 0; counter < result.size(); counter++) {
	                    	System.out.println(result.get(counter));
	                    }
	                    
	                    textField.setText("Finish edit");
	                    //Collections.sort(records.toArray());
	                    //List myEntries = reader.readAll();
	                    
	                    //System.out.println(myEntries.get(0));
	                    //columnnames = (String[]) myEntries.get(0);
	                    //tableModel = getModel(columnnames,myEntries);
	                    
                    }
                    catch(Exception e) {
                    	StringBuffer sb = new StringBuffer(500);
    				    StackTraceElement[] st = e.getStackTrace();
    				    sb.append(e.getClass().getName() + ": " + e.getMessage() + "\n");
    				    for (int i = 0; i < st.length; i++) {
    				      sb.append("\t at " + st[i].toString() + "\n");
    				    }
    				    //sb.toString();
    				    System.out.println(sb.toString());
                    }
                    //SaveCsv(fileToSave);
                    //System.out.println("Loaded file: " + fileToLoad.getAbsolutePath());
                    //JOptionPane.showMessageDialog(null, "Loaded file: " + fileToLoad.getAbsolutePath(), "Loaded file", JOptionPane.INFORMATION_MESSAGE);
                }
			}
		});
		panel.add(btnLoadCvsButton,BorderLayout.SOUTH);
		
		btnSaveCvs = new JButton("Save CVS");
		btnSaveCvs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int userSelection = fileChooser.showSaveDialog(fileChooser);
            	if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    SaveCsv(fileToSave);
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, "Save as file: " + fileToSave.getAbsolutePath(), "Saved File", JOptionPane.INFORMATION_MESSAGE);
                }
			}
		});
		contentPane.add(btnSaveCvs);
	}
	
	
	
	
	
	public void SaveCsv(File file) {
		
		ResultGenerator save = new ResultGenerator();
		
		//File targetFile = new File(targetPath, filename);
		File targetFile = file;
		//List<String[]> list = Arrays.asList(strArray);
		save.addDataToCSV(targetFile, result);
		//ta.append("Saved File");
		//dataCsv.clear();
	}

}
