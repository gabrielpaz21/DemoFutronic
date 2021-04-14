package com.futronic.project;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.futronic.SDKHelper.FTR_PROGRESS;
import com.futronic.SDKHelper.FutronicException;
import com.futronic.SDKHelper.FutronicSdkBase;
import com.futronic.SDKHelper.FutronicVerification;
import com.futronic.SDKHelper.IVerificationCallBack;
import com.futronic.project.dto.Register;
import com.futronic.project.utility.MyIcon;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.IOException;
import java.util.Base64;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Verificacion extends Default implements IVerificationCallBack {

	private static final long serialVersionUID = 1L;

	private MyIcon fingerPrintImage;
	private JLabel huella;
	private Register register;
	private JButton capturarHuella;
	private JButton guardar;
	private FutronicVerification futronicVerification;
	private JPanel contentPane;
	private JTextField titulo;
	private JTextField mensaje;
	private JTextField descripcionMensaje;
	private String tipoDedo;

	/**
	 * Create the frame.
	 */
	public Verificacion(Register register,String tipoDedo) {

		this.register = register;
		this.tipoDedo = tipoDedo;
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titulo = new JTextField();
		if(tipoDedo.equalsIgnoreCase("Pulgar")) {
			titulo.setText("Verificaci\u00F3n pulgar derecho");
		}else {
			titulo.setText("Verificaci\u00F3n indice derecho");
		}
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setEditable(false);
		titulo.setColumns(10);
		titulo.setBorder(null);
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(64, 87, 331, 32);
		contentPane.add(titulo);

		huella = new JLabel("");
		huella.setBackground(Color.WHITE);
		huella.setBounds(131, 132, 199, 303);
		fingerPrintImage = new MyIcon(199,303);
		fingerPrintImage.setImage(createImageIcon("/com/futronic/project/image/huella.png").getImage());
		huella.setIcon(fingerPrintImage);
		contentPane.add(huella);

		capturarHuella = new JButton("");
		capturarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnVerifyActionPerformed(arg0);
			}
		});
		capturarHuella.setIcon(new ImageIcon(
				Verificacion.class.getResource("/com/futronic/project/image/capturar-huella.png")));
		capturarHuella.setBorderPainted(false);
		capturarHuella.setBorder(null);
		capturarHuella.setBounds(55, 448, 151, 48);
		contentPane.add(capturarHuella);

		guardar = new JButton("");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeFrame(new Verificante(register,tipoDedo));
			}
		});
		guardar.setIcon(
				new ImageIcon(Verificacion.class.getResource("/com/futronic/project/image/guardar.png")));
		guardar.setEnabled(false);
		guardar.setBorderPainted(false);
		guardar.setBorder(null);
		guardar.setBounds(244, 448, 151, 48);
		guardar.setEnabled(false);
		contentPane.add(guardar);

		mensaje = new JTextField();
		mensaje.setForeground(new Color(232, 138, 90));
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		mensaje.setEditable(false);
		mensaje.setColumns(10);
		mensaje.setBorder(null);
		mensaje.setBackground(Color.WHITE);
		mensaje.setBounds(12, 509, 415, 32);
		contentPane.add(mensaje);

		descripcionMensaje = new JTextField();
		descripcionMensaje.setForeground(new Color(232, 138, 90));
		descripcionMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		descripcionMensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		descripcionMensaje.setEditable(false);
		descripcionMensaje.setColumns(10);
		descripcionMensaje.setBorder(null);
		descripcionMensaje.setBackground(Color.WHITE);
		descripcionMensaje.setBounds(12, 554, 415, 32);
		contentPane.add(descripcionMensaje);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		if (futronicVerification != null) {
			futronicVerification.Dispose();
		}
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 */
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = CapturaPulgarDerecho.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * The "Put your finger on the scanner" event.
	 *
	 * @param Progress the current progress data structure.
	 */
	public void OnPutOn(FTR_PROGRESS Progress) {
		mensaje.setText("Ponga el dedo en el dispositivo, por favor.");
	}

	/**
	 * The "Take off your finger from the scanner" event.
	 *
	 * @param Progress the current progress data structure.
	 */
	public void OnTakeOff(FTR_PROGRESS Progress) {
		mensaje.setText("Quite el dedo del dispositivo, por favor.");
	}

	/**
	 * The "Show the current fingerprint image" event.
	 *
	 * @param Bitmap the instance of Bitmap class with fingerprint image.
	 * @throws IOException
	 */
	public void UpdateScreenImage(java.awt.image.BufferedImage Bitmap) {
		fingerPrintImage.setImage(Bitmap);
		huella.repaint();
	}

	/**
	 * The "Fake finger detected" event.
	 *
	 * @param Progress the fingerprint image.
	 *
	 * @return <code>true</code> if the current indetntification operation should be
	 *         aborted, otherwise is <code>false</code>
	 */
	public boolean OnFakeSource(FTR_PROGRESS Progress) {
		int nResponse;
		nResponse = JOptionPane.showConfirmDialog(this, "Fake source detected. Do you want continue process?",
				getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (nResponse == JOptionPane.NO_OPTION);
	}

	private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {
		
		byte[] template = null;
		if(tipoDedo.equalsIgnoreCase("Pulgar")) {
			template = Base64.getDecoder().decode(register.getTemplateRightThumbFinger());
		}else {
			template = Base64.getDecoder().decode(register.getTemplateRightIndexFinger());
		}
	
		try {
			descripcionMensaje.setText("");
			capturarHuella.setEnabled(false);
			futronicVerification = new FutronicVerification(template);
			futronicVerification.Verification( this );
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FutronicException e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public void OnVerificationComplete(boolean bSuccess, int nResult, boolean bVerificationSuccess) {
		
		if (bSuccess) {
			if (bVerificationSuccess) {
				mensaje.setText("La verificación fue exitosa.");
				guardar.setEnabled(true);
			} else {
				mensaje.setText("La verificación fue fallida.");
				capturarHuella.setEnabled(true);
			}
		} else {
			mensaje.setText("La verificación fue fallida.");
			descripcionMensaje.setText(FutronicSdkBase.SdkRetCode2Message(nResult));
			capturarHuella.setEnabled(true);
		}
		
		futronicVerification = null;
	}

}
