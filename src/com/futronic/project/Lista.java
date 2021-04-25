package com.futronic.project;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futronic.project.dto.Register;
import com.futronic.project.utility.MyIcon;
import com.futronic.project.utility.TableImageRender;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scroll;
	private JPanel contentPane;
	private JButton signOut;
	
	/**
	 * Create the frame.
	 */
	public Lista() {
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		defaultProperties();
		
		DefaultTableModel model = createModelTable();
		
		String jsonRegisters = getRegisters();
		
		List<Register> registers = JsonToListResgister(jsonRegisters);
		
		for(Register register:registers) {
			String firstName = register.getFirstName();
			String lastName = register.getLastName();
			String dni = register.getDni();
			MyIcon thumbFinger = convertBase64ToImage(register.getBitMapRightThumbFinger());
			MyIcon indexFinger = convertBase64ToImage(register.getBitMapRightIndexFinger());
			JLabel labelThumbFinger = new JLabel(thumbFinger);
			JLabel labelIndexFinger = new JLabel(indexFinger);
			Object [] row = {firstName,lastName,dni,labelThumbFinger,labelIndexFinger};
			model.addRow(row);
		}
	
		createTable(model);
		createScroll();
		
		signOut = new JButton("");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				singOutPerformed(arg0);
			}
		});
		signOut.setIcon(new ImageIcon(Lista.class.getResource("/com/futronic/project/image/signout-icon.png")));
		signOut.setBorder(null);
		signOut.setBackground(Color.WHITE);
		signOut.setBounds(10, 394, 59, 58);
		contentPane.add(signOut);
		
	}
	
	private void singOutPerformed(ActionEvent arg0) {
		Menu menu = new Menu(null);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		this.dispose();
	}
	
	private MyIcon convertBase64ToImage(String base64Image) {
		byte[] template = Base64.getDecoder().decode(base64Image);
		InputStream is = new ByteArrayInputStream(template);
	    BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MyIcon fingerPrintImage = new MyIcon(90,90);
		fingerPrintImage.setImage(new ImageIcon(bufferedImage).getImage());
		
		return fingerPrintImage;
	}
		
	private void defaultProperties() {
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 500);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/futronic/project/image/icon.png")));
	}
	
	private DefaultTableModel createModelTable() {
		Object [] titulos = {"Nombre","Apellido","Cedula","Pulgar Derecho","Indice Derecho"};
		
		DefaultTableModel model = new DefaultTableModel(null,titulos) {
		
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas,int columnas) {
				
				if(columnas==5) {
					return true;
				}else {
					return false;
				}
		
			}
		};
		
		return model;
	}
	
	private void createTable(DefaultTableModel model) {	
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setDefaultRenderer(Object.class, new TableImageRender());
        table.setBounds(0, 0, 700, 500);
        table.setBorder(new LineBorder(Color.black));
        table.setRowHeight(100);
	}
	
	private void createScroll() {	
		scroll = new JScrollPane();
		scroll.setBounds(0, 0, 690, 391);
		scroll.setViewportView(table);
		contentPane.add(scroll);
	
	}
	
	
	private String getRegisters() {	
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource("http://localhost:8081/register");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
		String jsonRegisters = response.getEntity(String.class);
		return jsonRegisters;
	}
	
	private List<Register> JsonToListResgister(String jsonRegisters){
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Register> registers = null;
		try {
			registers = objectMapper.readValue(jsonRegisters,new TypeReference<List<Register>>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
				
		return registers;
	}

}
