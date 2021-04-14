package com.futronic.project;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.futronic.project.dto.Register;
import com.futronic.project.utility.MyIcon;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class Verificante extends Default {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField titulo;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDni;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField dni;
	private JTextField estatusMensaje;
	private JButton signOut;
	private MyIcon fingerPrintImage;
	//private String tipoDedo;

	/**
	 * Create the frame.
	 */
	public Verificante(Register register,String tipoDedo) {
		
		//this.tipoDedo = tipoDedo;
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel huella = new JLabel("");
		byte[] template;
		if(tipoDedo.equalsIgnoreCase("Pulgar")) {
			template = Base64.getDecoder().decode(register.getBitMapRightThumbFinger());
		}else {
			template = Base64.getDecoder().decode(register.getBitMapRightIndexFinger());
		}
		
		InputStream is = new ByteArrayInputStream(template);
	    BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fingerPrintImage = new MyIcon(199,303);
		fingerPrintImage.setImage(new ImageIcon(bufferedImage).getImage());
		huella.setIcon(fingerPrintImage);
		huella.setBackground(Color.WHITE);
		huella.setBounds(124, 122, 199, 303);
		contentPane.add(huella);
		
		titulo = new JTextField();
		titulo.setText("Resuldado de la b\u00FAsqueda");
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setEditable(false);
		titulo.setColumns(10);
		titulo.setBorder(null);
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(78, 28, 294, 32);
		contentPane.add(titulo);
		
		textNombre = new JTextField();
		textNombre.setText("Nombre:");
		textNombre.setForeground(new Color(70, 130, 180));
		textNombre.setFont(new Font("Lucida Console", Font.BOLD, 15));
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		textNombre.setBorder(null);
		textNombre.setBackground(Color.WHITE);
		textNombre.setBounds(30, 459, 76, 32);
		contentPane.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setText("Apellido:");
		textApellido.setForeground(new Color(70, 130, 180));
		textApellido.setFont(new Font("Lucida Console", Font.BOLD, 15));
		textApellido.setEditable(false);
		textApellido.setColumns(10);
		textApellido.setBorder(null);
		textApellido.setBackground(Color.WHITE);
		textApellido.setBounds(30, 502, 106, 32);
		contentPane.add(textApellido);
		
		textDni = new JTextField();
		textDni.setText("DNI:");
		textDni.setForeground(new Color(70, 130, 180));
		textDni.setFont(new Font("Lucida Console", Font.BOLD, 15));
		textDni.setEditable(false);
		textDni.setColumns(10);
		textDni.setBorder(null);
		textDni.setBackground(Color.WHITE);
		textDni.setBounds(30, 547, 49, 32);
		contentPane.add(textDni);
		
		nombre = new JTextField();
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Lucida Console", Font.BOLD, 15));
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setBorder(null);
		nombre.setBackground(Color.WHITE);
		nombre.setBounds(124, 461, 210, 32);
		nombre.setText(register.getFirstName());
		contentPane.add(nombre);
		
		apellido = new JTextField();
		apellido.setHorizontalAlignment(SwingConstants.CENTER);
		apellido.setFont(new Font("Lucida Console", Font.BOLD, 15));
		apellido.setEditable(false);
		apellido.setColumns(10);
		apellido.setBorder(null);
		apellido.setBackground(Color.WHITE);
		apellido.setBounds(124, 504, 210, 32);
		apellido.setText(register.getLastName());
		contentPane.add(apellido);
		
		dni = new JTextField();
		dni.setHorizontalAlignment(SwingConstants.CENTER);
		dni.setFont(new Font("Lucida Console", Font.BOLD, 15));
		dni.setEditable(false);
		dni.setColumns(10);
		dni.setBorder(null);
		dni.setBackground(Color.WHITE);
		dni.setBounds(124, 547, 210, 32);
		dni.setText(register.getDni());
		contentPane.add(dni);
		
		estatusMensaje = new JTextField();
		estatusMensaje.setText("Huella encontrada");
		estatusMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		estatusMensaje.setFont(new Font("Lucida Console", Font.BOLD, 15));
		estatusMensaje.setEditable(false);
		estatusMensaje.setColumns(10);
		estatusMensaje.setBorder(null);
		estatusMensaje.setBackground(Color.WHITE);
		estatusMensaje.setBounds(78, 77, 282, 32);
		contentPane.add(estatusMensaje);
		
		signOut = new JButton("");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Menu(null));
			}
		});
		signOut.setIcon(new ImageIcon(Verificante.class.getResource("/com/futronic/project/image/signout-icon.png")));
		signOut.setBorderPainted(false);
		signOut.setBorder(null);
		signOut.setBackground(Color.WHITE);
		signOut.setBounds(337, 522, 74, 57);
		contentPane.add(signOut);
	}

}
