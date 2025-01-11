package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;



import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class EventosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LugaresView frame = new LugaresView();
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
	public EventosView() {
		setResizable(false);
		setTitle("Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(231, 255, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuClienteView MClView = new MenuClienteView();
				MClView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnHome.setBounds(24, 11, 173, 36);
		contentPane.add(btnHome);
		
		JButton btnLugares = new JButton("Lugares");
		btnLugares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LugaresView lugaresView = new LugaresView();
				lugaresView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnLugares.setBounds(245, 11, 173, 36);
		contentPane.add(btnLugares);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfilView perfilView = new PerfilView();
				perfilView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnPerfil.setBounds(467, 11, 173, 36);
		contentPane.add(btnPerfil);
		
		JTextArea txtrParqueTangu = new JTextArea();
		txtrParqueTangu.setEditable(false);
		txtrParqueTangu.setLineWrap(true);
		txtrParqueTangu.setWrapStyleWord(true);
		txtrParqueTangu.setFont(new Font("Dialog", Font.BOLD, 13));
		txtrParqueTangu.setText("Against The Wind Chopper Show \r\n\r\nData: 31 de maio de 2024 \r\nLocal: Parque Barigui (Acesso BR 277) estacionamento gratuito.\r\n\r\nO evento é destinado aos apaixonados pela cultura Chopper e por tudo que envolve motocicletas customizadas. O evento oferece gincanas de moto – um grande diferencial que promete entreter os participantes.\r\nAlém das gincanas de moto, o Against the Wind contará com a presença de marcas importantes do segmento, de expositores de produtos e acessórios do mundo duas rodas, música ao vivo e gastronomia de qualidade.");
		txtrParqueTangu.setBackground(new Color(231, 255, 211));
		txtrParqueTangu.setBounds(24, 129, 383, 256);
		contentPane.add(txtrParqueTangu);
		
		textField = new JTextField();
		textField.setBounds(25, 98, 383, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pesquisa");
		lblNewLabel.setBounds(24, 78, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(EventosView.class.getResource("/view/imagens/EventoDeMoto.png")));
		lblNewLabel_1.setBounds(415, 148, 237, 233);
		contentPane.add(lblNewLabel_1);
	}
}
