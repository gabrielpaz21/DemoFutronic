package com.futronic.project;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futronic.project.dto.RegisterRequest;
import com.futronic.project.dto.ValidateUserRequest;
import com.futronic.project.dto.ValidateUserResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends Default {

	private static final long serialVersionUID = 1L;
	
	private RegisterRequest registerRequest;
	private JPanel contentPane;
	private JTextField titulo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField inputNombre;
	private JTextField inputApellido;
	private JTextField inputDNI;
	private JTextField txtTipoCaptura;
	private JTextField mensaje;
	private JButton signOut;

	/**
	 * Create the frame.
	 */
	public Registro() {

		super();
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Registro.class.getResource("/com/futronic/project/image/telcomsis.png")));
		logo.setBounds(171, 25, 235, 93);
		contentPane.add(logo);

		titulo = new JTextField();
		titulo.setBackground(Color.WHITE);
		titulo.setEditable(false);
		titulo.setBorder(null);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setText("Modulo de registro");
		titulo.setBounds(107, 131, 225, 39);
		contentPane.add(titulo);
		titulo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setEditable(false);
		txtNombre.setBorder(null);
		txtNombre.setForeground(new Color(70, 130, 180));
		txtNombre.setFont(new Font("Lucida Console", Font.BOLD, 15));
		txtNombre.setText("Nombre:");
		txtNombre.setBounds(58, 183, 76, 32);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setEditable(false);
		txtApellido.setBorder(null);
		txtApellido.setForeground(new Color(70, 130, 180));
		txtApellido.setFont(new Font("Lucida Console", Font.BOLD, 15));
		txtApellido.setText("Apellido:");
		txtApellido.setColumns(10);
		txtApellido.setBounds(40, 228, 106, 32);
		contentPane.add(txtApellido);

		txtDni = new JTextField();
		txtDni.setBackground(Color.WHITE);
		txtDni.setEditable(false);
		txtDni.setBorder(null);
		txtDni.setForeground(new Color(70, 130, 180));
		txtDni.setFont(new Font("Lucida Console", Font.BOLD, 15));
		txtDni.setText("DNI:");
		txtDni.setColumns(10);
		txtDni.setBounds(69, 273, 49, 32);
		contentPane.add(txtDni);

		inputNombre = new JTextField();
		inputNombre.setFont(new Font("Lucida Console", Font.BOLD, 15));
		inputNombre.setForeground(new Color(34, 139, 34));
		inputNombre.setBackground(SystemColor.control);
		inputNombre.setBounds(190, 183, 183, 32);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);

		inputApellido = new JTextField();
		inputApellido.setFont(new Font("Lucida Console", Font.BOLD, 15));
		inputApellido.setForeground(new Color(34, 139, 34));
		inputApellido.setBackground(SystemColor.control);
		inputApellido.setColumns(10);
		inputApellido.setBounds(190, 228, 183, 32);
		contentPane.add(inputApellido);

		inputDNI = new JTextField();
		inputDNI.setFont(new Font("Lucida Console", Font.BOLD, 15));
		inputDNI.setForeground(new Color(34, 139, 34));
		inputDNI.setBackground(SystemColor.control);
		inputDNI.setColumns(10);
		inputDNI.setBounds(190, 273, 183, 32);
		contentPane.add(inputDNI);

		JButton capturarHuella = new JButton("");
		capturarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean nombreEstaVacio = inputNombre.getText().isEmpty();
				boolean apellidoEstaVacio = inputApellido.getText().isEmpty();
				boolean DNIEstaVacio = inputDNI.getText().isEmpty();

				if (nombreEstaVacio || apellidoEstaVacio || DNIEstaVacio) {
					mensaje.setText("Todos los campos deben de estar llenos.");
					return;
				}
				
				ValidateUserResponse validateUserResponse = dniDisponible(inputDNI.getText());
				
				if(!validateUserResponse.isValidate()) {
					mensaje.setText("El DNI ingresado ya esta en nuestra base de datos.");
					return;
				}
				
				registerRequest = new RegisterRequest(inputNombre.getText(),
													  inputApellido.getText(),
													  inputDNI.getText());

				changeFrame(new CapturaPulgarDerecho(registerRequest));
			}
		});
		capturarHuella.setBackground(Color.WHITE);
		capturarHuella.setFont(new Font("Lucida Console", Font.BOLD, 15));
		capturarHuella.setBorder(null);
		capturarHuella.setBorderPainted(false);
		capturarHuella
				.setIcon(new ImageIcon(Registro.class.getResource("/com/futronic/project/image/capturar-huella.png")));
		capturarHuella.setBounds(153, 386, 152, 49);
		contentPane.add(capturarHuella);

		JComboBox<String> tipoCaptura = new JComboBox<String>();
		tipoCaptura.setForeground(new Color(34, 139, 34));
		tipoCaptura.setFont(new Font("Lucida Console", Font.BOLD, 12));
		tipoCaptura.setModel(new DefaultComboBoxModel<String>(new String[] { "Pulgar e indice derecho" }));
		tipoCaptura.setToolTipText("");
		tipoCaptura.setBounds(190, 318, 183, 32);
		contentPane.add(tipoCaptura);

		txtTipoCaptura = new JTextField();
		txtTipoCaptura.setBackground(Color.WHITE);
		txtTipoCaptura.setEditable(false);
		txtTipoCaptura.setBorder(null);
		txtTipoCaptura.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoCaptura.setFont(new Font("Lucida Console", Font.BOLD, 15));
		txtTipoCaptura.setForeground(new Color(70, 130, 180));
		txtTipoCaptura.setText("tipo de captura:");
		txtTipoCaptura.setBounds(12, 318, 166, 32);
		contentPane.add(txtTipoCaptura);
		txtTipoCaptura.setColumns(10);

		mensaje = new JTextField();
		mensaje.setForeground(new Color(232, 138, 90));
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		mensaje.setEditable(false);
		mensaje.setColumns(10);
		mensaje.setBorder(null);
		mensaje.setBackground(Color.WHITE);
		mensaje.setBounds(12, 570, 415, 32);
		contentPane.add(mensaje);
		
		signOut = new JButton("");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Menu(null));
			}
		});
		signOut.setIcon(new ImageIcon(Registro.class.getResource("/com/futronic/project/image/signout-icon.png")));
		signOut.setBorder(null);
		signOut.setBackground(Color.WHITE);
		signOut.setBounds(26, 500, 59, 58);
		contentPane.add(signOut);

	}	
	
	public ValidateUserResponse dniDisponible(String dni) {
		
		ValidateUserRequest validateUserRequest = new ValidateUserRequest(dni);
		
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonValidateUserRequest = null;

		try {
			jsonValidateUserRequest = objectMapper.writeValueAsString(validateUserRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
			
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource( "http://localhost:8081/register/validate_user");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,jsonValidateUserRequest);
		
		String jsonValidateUserResponse = response.getEntity(String.class);
		
		ValidateUserResponse validateUserResponse = null;
		
		try {
			validateUserResponse = objectMapper.readValue(jsonValidateUserResponse, ValidateUserResponse.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return validateUserResponse;
	}
	
}
