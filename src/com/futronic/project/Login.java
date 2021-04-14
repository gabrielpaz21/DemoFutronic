package com.futronic.project;

import java.awt.EventQueue;

import javax.swing.border.Border;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futronic.project.dto.LoginRequest;
import com.futronic.project.dto.ErrorResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Login extends Default {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField inputPassword;
	private JTextField inputUsername;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					Login frame = new Login();
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
	public Login() {

		super();

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imageFingerPrint = new JLabel("");
		imageFingerPrint
				.setIcon(new ImageIcon(Login.class.getResource("/com/futronic/project/image/huella-dactilar.jpg")));
		imageFingerPrint.setBounds(0, 0, 440, 264);
		contentPane.add(imageFingerPrint);

		JLabel logo = new JLabel("");
		logo.setBackground(new Color(255, 255, 255));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(Login.class.getResource("/com/futronic/project/image/telcomsis.png")));
		logo.setBounds(97, 289, 235, 65);
		contentPane.add(logo);

		JLabel userIcon = new JLabel("");
		userIcon.setIcon(new ImageIcon(Login.class.getResource("/com/futronic/project/image/user-icon.png")));
		userIcon.setBackground(new Color(255, 255, 255));
		userIcon.setForeground(new Color(255, 255, 255));
		userIcon.setHorizontalAlignment(SwingConstants.CENTER);
		userIcon.setBounds(50, 402, 32, 32);
		contentPane.add(userIcon);

		Label labelUsername = new Label("Usuario:");
		labelUsername.setForeground(new Color(70, 130, 180));
		labelUsername.setFont(new Font("Lucida Console", Font.BOLD, 15));
		labelUsername.setBounds(97, 372, 77, 24);
		contentPane.add(labelUsername);

		inputUsername = new JTextField();
		inputUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Border label_icons_boder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232, 138, 90));
				userIcon.setBorder(label_icons_boder);
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				userIcon.setBorder(null);
			}
		});
		inputUsername.setForeground(new Color(34, 139, 34));
		inputUsername.setFont(new Font("Lucida Console", Font.BOLD, 18));
		inputUsername.setHorizontalAlignment(SwingConstants.CENTER);
		inputUsername.setBackground(SystemColor.control);
		inputUsername.setBounds(97, 402, 273, 32);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);

		JLabel passwordIcon = new JLabel("");
		passwordIcon.setIcon(new ImageIcon(Login.class.getResource("/com/futronic/project/image/password-icon.png")));
		passwordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		passwordIcon.setForeground(Color.WHITE);
		passwordIcon.setBackground(Color.WHITE);
		passwordIcon.setBounds(50, 470, 32, 32);
		contentPane.add(passwordIcon);

		Label labelPassword = new Label("Contrase\u00F1a:");
		labelPassword.setForeground(new Color(70, 130, 180));
		labelPassword.setFont(new Font("Lucida Console", Font.BOLD, 15));
		labelPassword.setBounds(97, 440, 100, 24);
		contentPane.add(labelPassword);

		inputPassword = new JPasswordField();
		inputPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Border label_icons_boder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232, 138, 90));
				passwordIcon.setBorder(label_icons_boder);
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				passwordIcon.setBorder(null);
			}
		});
		inputPassword.setForeground(new Color(34, 139, 34));
		inputPassword.setBackground(SystemColor.control);
		inputPassword.setFont(new Font("Lucida Console", Font.BOLD, 18));
		inputPassword.setHorizontalAlignment(SwingConstants.CENTER);
		inputPassword.setToolTipText("");
		inputPassword.setBounds(97, 470, 273, 32);
		contentPane.add(inputPassword);

		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(new Color(232, 138, 90));
		message.setFont(new Font("Lucida Console", Font.BOLD, 12));
		message.setBounds(12, 578, 415, 24);
		contentPane.add(message);

		JButton SignIn = new JButton("");
		SignIn.setIcon(new ImageIcon(Login.class.getResource("/com/futronic/project/image/signin-icon.png")));
		SignIn.setBorder(null);
		SignIn.setBackground(Color.WHITE);
		SignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// TODO comprobar si el dispositivo tiene coneccion a internet

				String username = inputUsername.getText();

				String password = String.valueOf(inputPassword.getPassword());

				LoginRequest loginRequest = new LoginRequest(username, password);

				ObjectMapper objectMapper = new ObjectMapper();

				String jsonLoginRequest = null;

				try {
					jsonLoginRequest = objectMapper.writeValueAsString(loginRequest);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				WebResource webResource = client.resource(getBaseURI());
				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,
						jsonLoginRequest);
				String jsonLoginResponse = response.getEntity(String.class);

				int status = response.getStatus();

				ErrorResponse errorResponse = null;

				if (status != 200) {
					try {
						errorResponse = objectMapper.readValue(jsonLoginResponse, ErrorResponse.class);
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}

				if (status == 200 && jsonLoginResponse.contains("true")) {
					changeFrame(new Menu(null));
				} else if (status == 400) {
					message.setText(errorResponse.getMensajeCliente());
				} else if (status == 401) {
					message.setText(errorResponse.getMensajeCliente());
				}

			}
		});
		SignIn.setBounds(315, 515, 55, 55);
		contentPane.add(SignIn);

	}

	private static String getBaseURI() {

		return "http://localhost:8081/register/login";
	}
}
