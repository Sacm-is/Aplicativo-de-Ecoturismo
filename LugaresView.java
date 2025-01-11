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

public class LugaresView extends JFrame {

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
	public LugaresView() {
		setResizable(false);
		setTitle("Lugares");
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
		
		JButton btnEventos = new JButton("Eventos");
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventosView eventosView = new EventosView();
				eventosView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEventos.setBounds(245, 11, 173, 36);
		contentPane.add(btnEventos);
		
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
		txtrParqueTangu.setText("PARQUE TANGUÁ\r\n\r\nO parque fica localizado na Rua Oswaldo Maciel, 97 - Taboão, Curitiba - PR, 82130-494 e fica aberto todos os dias da semana, das 07h às 22h. Possui estacionamento aberto e gratuito.\r\n\r\nO Parque Tanguá é um dos principais parques de Curitiba. Possui uma área de 235 000 m2, localizado na região norte da cidade, nos bairros Pilarzinho e Taboão.\r\nO Parque foi construído em uma pedreira desativada e rende imagens lindas seja no mirante, nas áreas verde, trilha ou na parede rochosa, com um lindo pôr do sol.\r\n\r\n\r\n");
		txtrParqueTangu.setBackground(new Color(231, 255, 211));
		txtrParqueTangu.setBounds(24, 129, 383, 256);
		contentPane.add(txtrParqueTangu);
		
		JLabel lblNewLabel = new JLabel("Pesquisa");
		lblNewLabel.setBounds(24, 78, 75, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LugaresView.class.getResource("/view/imagens/Parque Tangua.png")));
		lblNewLabel_1.setBounds(415, 148, 237, 233);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(24, 98, 394, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
