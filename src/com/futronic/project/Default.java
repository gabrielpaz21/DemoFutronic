package com.futronic.project;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Default extends JFrame {

	private static final long serialVersionUID = 1L;

	public Default() {

		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 650);
		setBackground(new Color(255, 255, 255));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/futronic/project/image/icon.png")));
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public void changeFrame(JFrame frame) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		this.dispose();
	}

}
