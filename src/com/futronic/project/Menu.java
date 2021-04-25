package com.futronic.project;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends Default {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField mensaje;
	/**
	 * Create the frame.
	 */
	public Menu(String mensajeExito) {
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField titulo = new JTextField();
		titulo.setBackground(Color.WHITE);
		titulo.setEditable(false);
		titulo.setBorder(null);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setText("Seleccione una acci\u00F3n");
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setBounds(86, 100, 257, 36);
		contentPane.add(titulo);
		titulo.setColumns(10);
		
		JButton registrar = new JButton("");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Registro());
			}
		});
		registrar.setIcon(new ImageIcon(Menu.class.getResource("/com/futronic/project/image/registrar.png")));
		registrar.setBorder(null);
		registrar.setBackground(Color.WHITE);
		registrar.setBounds(106, 190, 223, 45);
		contentPane.add(registrar);
		
		JButton verificar = new JButton("");
		verificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Busqueda());
			}
		});
		verificar.setBorder(null);
		verificar.setBackground(Color.WHITE);
		verificar.setIcon(new ImageIcon(Menu.class.getResource("/com/futronic/project/image/verificar.png")));
		verificar.setBounds(106, 263, 223, 45);
		contentPane.add(verificar);
	
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Menu.class.getResource("/com/futronic/project/image/telcomsis.png")));
		logo.setBounds(92, 410, 235, 93);
		contentPane.add(logo);
		
		JButton signOut = new JButton("");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				changeFrame(new Login());
			}
		});
		signOut.setIcon(new ImageIcon(Menu.class.getResource("/com/futronic/project/image/signout-icon.png")));
		signOut.setBorder(null);
		signOut.setBackground(Color.WHITE);
		signOut.setBounds(26, 500, 59, 58);
		contentPane.add(signOut);
		
		JButton listar = new JButton("");
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Lista());
			}
		});
		listar.setBorderPainted(false);
		listar.setIcon(new ImageIcon(Menu.class.getResource("/com/futronic/project/image/listar-registro.png")));
		listar.setBorder(null);
		listar.setMargin(new Insets(0, 0, 0, 0));
		listar.setBackground(Color.WHITE);
		listar.setBounds(106, 336, 223, 45);
		contentPane.add(listar);
		
		mensaje = new JTextField();
		if(mensajeExito!=null) {
			mensaje.setText(mensajeExito);
		}
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setForeground(new Color(232, 138, 90));
		mensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		mensaje.setEditable(false);
		mensaje.setColumns(10);
		mensaje.setBorder(null);
		mensaje.setBackground(Color.WHITE);
		mensaje.setBounds(12, 570, 415, 32);
		contentPane.add(mensaje);
	
	}
}
