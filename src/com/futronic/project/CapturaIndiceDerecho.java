package com.futronic.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futronic.SDKHelper.FTR_PROGRESS;
import com.futronic.SDKHelper.FutronicEnrollment;
import com.futronic.SDKHelper.FutronicException;
import com.futronic.SDKHelper.FutronicSdkBase;
import com.futronic.SDKHelper.IEnrollmentCallBack;
import com.futronic.project.dto.RegisterRequest;
import com.futronic.project.utility.MyIcon;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CapturaIndiceDerecho extends Default implements IEnrollmentCallBack {

	private static final long serialVersionUID = 1L;

	private RegisterRequest registerRequest;
	private JPanel contentPane;
	private JTextField titulo;
	private JLabel huella;
	private JTextField mensaje;
	private MyIcon m_FingerPrintImage;
	private FutronicEnrollment futronicEnrollment;
	private JButton capturarHuella;
	private JTextField descripcionMensaje;
	private JButton guardar;

	/**
	 * Create the frame.
	 */
	public CapturaIndiceDerecho(RegisterRequest registerRequest) {

		this.registerRequest = registerRequest;
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titulo = new JTextField();
		titulo.setEditable(false);
		titulo.setBackground(Color.WHITE);
		titulo.setBorder(null);
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 18));
		titulo.setText("Captura indice derecho");
		titulo.setBounds(87, 55, 273, 32);
		contentPane.add(titulo);
		titulo.setColumns(10);

		huella = new JLabel("");
		huella.setBackground(Color.WHITE);
		huella.setBounds(127, 105, 199, 303);
		m_FingerPrintImage = new MyIcon(199,303);
		m_FingerPrintImage.setImage(createImageIcon("/com/futronic/project/image/huella.png").getImage());
		huella.setIcon(createImageIcon("/com/futronic/project/image/huella.png"));
		huella.setIcon(m_FingerPrintImage);
		contentPane.add(huella);
		setVisible(true);

		capturarHuella = new JButton("");
		capturarHuella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEnrollActionPerformed(arg0);
			}
		});
		capturarHuella.setBorder(null);
		capturarHuella.setBorderPainted(false);
		capturarHuella.setIcon(new ImageIcon(
				CapturaPulgarDerecho.class.getResource("/com/futronic/project/image/capturar-huella.png")));
		capturarHuella.setBounds(72, 437, 151, 48);
		contentPane.add(capturarHuella);

		mensaje = new JTextField();
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setBackground(Color.WHITE);
		mensaje.setEditable(false);
		mensaje.setBorder(null);
		mensaje.setForeground(new Color(232, 138, 90));
		mensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		mensaje.setBounds(12, 517, 415, 32);
		contentPane.add(mensaje);
		mensaje.setColumns(10);

		descripcionMensaje = new JTextField();
		descripcionMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		descripcionMensaje.setForeground(new Color(232, 138, 90));
		descripcionMensaje.setFont(new Font("Lucida Console", Font.BOLD, 12));
		descripcionMensaje.setEditable(false);
		descripcionMensaje.setColumns(10);
		descripcionMensaje.setBorder(null);
		descripcionMensaje.setBackground(Color.WHITE);
		descripcionMensaje.setBounds(12, 562, 415, 32);
		contentPane.add(descripcionMensaje);

		guardar = new JButton("");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ObjectMapper objectMapper = new ObjectMapper();

				String jsonRegisterRequest = null;

				try {
					jsonRegisterRequest = objectMapper.writeValueAsString(registerRequest);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				WebResource webResource = client.resource("http://localhost:8081/register/save_data");
				ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,
						jsonRegisterRequest);
				String jsonRegisterResponse = response.getEntity(String.class);

				int status = response.getStatus();

				if (status == 201) {
					changeFrame(new Menu("Guardado con exito."));
				}

			}
		});
		guardar.setIcon(
				new ImageIcon(CapturaPulgarDerecho.class.getResource("/com/futronic/project/image/guardar.png")));
		guardar.setBorderPainted(false);
		guardar.setBorder(null);
		guardar.setBounds(233, 437, 151, 48);
		guardar.setEnabled(false);
		contentPane.add(guardar);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		if (futronicEnrollment != null) {
			futronicEnrollment.Dispose();
		}
	}

	private void EnableControls(boolean enable) {
		capturarHuella.setEnabled(enable);
		guardar.setEnabled(enable);

	}

	private void btnEnrollActionPerformed(java.awt.event.ActionEvent evt) {

		try {

			mensaje.setText("");
			descripcionMensaje.setText("");
			futronicEnrollment = new FutronicEnrollment();

		} catch (FutronicException e) {
			JOptionPane.showMessageDialog(null,
					"Initialization failed. Application will be close.\nError description: " + e.getMessage(),
					getTitle(), JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		EnableControls(false);
		futronicEnrollment.Enrollment(this);
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
		descripcionMensaje.setText("La huella capturada tiene pesima calidad, repita el proceso");
	}

	/**
	 * The "Show the current fingerprint image" event.
	 *
	 * @param Bitmap the instance of Bitmap class with fingerprint image.
	 * @throws IOException
	 */
	public void UpdateScreenImage(java.awt.image.BufferedImage Bitmap) {
		m_FingerPrintImage.setImage(Bitmap);
		huella.repaint();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(Bitmap, "jpg", baos);
		} catch (IOException e) {

			e.printStackTrace();
		}

		byte[] imageInByte = baos.toByteArray();

		registerRequest.setBitMapRightIndexFinger(Base64.getEncoder().encodeToString(imageInByte));

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

	/**
	 * The "Enrollment operation complete" event.
	 *
	 * @param bSuccess <code>true</code> if the operation succeeds, otherwise is
	 *                 <code>false</code>.
	 * @param The      Futronic SDK return code (see FTRAPI.h).
	 */
	public void OnEnrollmentComplete(boolean bSuccess, int nResult) {

		if (!bSuccess) {
			mensaje.setText("Falló el proceso de inscripción.");

			descripcionMensaje.setText(FutronicSdkBase.SdkRetCode2Message(nResult));

			capturarHuella.setEnabled(true);

			return;
		}

		// set status string
		mensaje.setText("El proceso de inscripción finalizó con éxito.");

		descripcionMensaje.setText("Calidad: " + futronicEnrollment.getQuality());

		// Set template into passport and save it
		registerRequest.setTemplateRightIndexFinger(

				Base64.getEncoder().encodeToString(futronicEnrollment.getTemplate())

		);

		EnableControls(true);

		futronicEnrollment = null;
	}

}
