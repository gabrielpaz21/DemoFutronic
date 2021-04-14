package com.futronic.project;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuardadoExito extends Default {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtGuardadoConxito;

	/**
	 * Create the frame.
	 */
	public GuardadoExito() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(GuardadoExito.class.getResource("/com/futronic/project/image/guardado-correcto.png")));
		lblNewLabel.setBounds(86, 189, 280, 285);
		contentPane.add(lblNewLabel);

		txtGuardadoConxito = new JTextField();
		txtGuardadoConxito.setBorder(null);
		txtGuardadoConxito.setBackground(Color.WHITE);
		txtGuardadoConxito.setEditable(false);
		txtGuardadoConxito.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuardadoConxito.setText("Guardado con \u00E9xito");
		txtGuardadoConxito.setFont(new Font("Lucida Console", Font.BOLD, 18));
		txtGuardadoConxito.setBounds(86, 122, 280, 32);
		contentPane.add(txtGuardadoConxito);
		txtGuardadoConxito.setColumns(10);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Menu(null));
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(
				new ImageIcon(GuardadoExito.class.getResource("/com/futronic/project/image/signout-icon.png")));
		btnNewButton.setBounds(292, 509, 74, 57);
		contentPane.add(btnNewButton);

	}
}
