package com.futronic.project;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futronic.project.dto.ErrorResponse;
import com.futronic.project.dto.Register;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Busqueda extends Default {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField titulo;
	private JTextField inputDNI;
	private JTextField textDNI;
	private JLabel message;
	private JButton signOut;
	private JTextField txtTipoDeDedo;
	private JComboBox<String> tipoDedo;

	/**
	 * Create the frame.
	 */
	public Busqueda() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Busqueda.class.getResource("/com/futronic/project/image/telcomsis.png")));
		logo.setBounds(180, 75, 235, 93);
		contentPane.add(logo);
		
		titulo = new JTextField();
		titulo.setText("Modulo de b\u00FAsqueda");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setEditable(false);
		titulo.setColumns(10);
		titulo.setBorder(null);
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(108, 198, 225, 39);
		contentPane.add(titulo);
		
		inputDNI = new JTextField();
		inputDNI.setForeground(new Color(34, 139, 34));
		inputDNI.setFont(new Font("Lucida Console", Font.BOLD, 15));
		inputDNI.setColumns(10);
		inputDNI.setBackground(SystemColor.menu);
		inputDNI.setBounds(150, 250, 183, 32);
		contentPane.add(inputDNI);
		
		textDNI = new JTextField();
		textDNI.setText("DNI:");
		textDNI.setForeground(new Color(70, 130, 180));
		textDNI.setFont(new Font("Lucida Console", Font.BOLD, 15));
		textDNI.setEditable(false);
		textDNI.setColumns(10);
		textDNI.setBorder(null);
		textDNI.setBackground(Color.WHITE);
		textDNI.setBounds(61, 250, 49, 32);
		contentPane.add(textDNI);
		
		JButton capturarHuella = new JButton("");
		capturarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean DNIEstaVacio = inputDNI.getText().isEmpty();
				
				if(DNIEstaVacio){
					message.setText("El campo dni no debe estar vacio.");
					return;
				}
				
				String dni = inputDNI.getText();
				ObjectMapper objectMapper = new ObjectMapper();
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				WebResource webResource = client.resource("http://localhost:8081/register/"+dni);
				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
				String jsonResponse = response.getEntity(String.class);
				
				int status = response.getStatus();
				
				if(status!=200) {
					try {
						ErrorResponse errorResponse = objectMapper.readValue(jsonResponse, ErrorResponse.class);
						message.setText(errorResponse.getMensajeCliente());
						return;
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}			
				
				try {
					Register register = objectMapper.readValue(jsonResponse, Register.class);
					changeFrame(new Verificacion(register,(String)tipoDedo.getSelectedItem()));
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
	
			}
		});
		capturarHuella.setIcon(new ImageIcon(Busqueda.class.getResource("/com/futronic/project/image/capturar-huella.png")));
		capturarHuella.setFont(new Font("Lucida Console", Font.BOLD, 15));
		capturarHuella.setBorderPainted(false);
		capturarHuella.setBorder(null);
		capturarHuella.setBackground(Color.WHITE);
		capturarHuella.setBounds(150, 365, 152, 49);
		contentPane.add(capturarHuella);		
		
		message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(new Color(232, 138, 90));
		message.setFont(new Font("Lucida Console", Font.BOLD, 12));
		message.setBounds(12, 565, 415, 24);
		contentPane.add(message);
		
		signOut = new JButton("");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Menu(null));
			}
		});
		signOut.setIcon(new ImageIcon(Busqueda.class.getResource("/com/futronic/project/image/signout-icon.png")));
		signOut.setBorder(null);
		signOut.setBackground(Color.WHITE);
		signOut.setBounds(26, 500, 59, 58);
		contentPane.add(signOut);
		
		txtTipoDeDedo = new JTextField();
		txtTipoDeDedo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoDeDedo.setText("Tipo de dedo:");
		txtTipoDeDedo.setForeground(new Color(70, 130, 180));
		txtTipoDeDedo.setFont(new Font("Lucida Console", Font.BOLD, 15));
		txtTipoDeDedo.setEditable(false);
		txtTipoDeDedo.setColumns(10);
		txtTipoDeDedo.setBorder(null);
		txtTipoDeDedo.setBackground(Color.WHITE);
		txtTipoDeDedo.setBounds(12, 301, 132, 32);
		contentPane.add(txtTipoDeDedo);
		
		tipoDedo = new JComboBox<String>();
		tipoDedo.setModel(new DefaultComboBoxModel<String>(new String[] {"Pulgar", "Indice"}));
		tipoDedo.setForeground(new Color(34, 139, 34));
		tipoDedo.setFont(new Font("Lucida Console", Font.BOLD, 15));
		tipoDedo.setBounds(150, 302, 183, 32);
		contentPane.add(tipoDedo);
	}
}
