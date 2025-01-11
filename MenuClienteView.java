package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;


import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class MenuClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuClienteView frame = new MenuClienteView();
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
	public MenuClienteView() {
		setTitle("Home");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 384);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(231, 255, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(24, 11, 120, 36);
		contentPane.add(btnHome);
		
		JButton btnLugares = new JButton("Lugares");
		btnLugares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LugaresView lugaresView = new LugaresView();
				lugaresView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnLugares.setBounds(188, 11, 120, 36);
		contentPane.add(btnLugares);
		
		JButton btnEventos = new JButton("Eventos");
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventosView eventosView = new EventosView();
				eventosView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEventos.setBounds(356, 11, 120, 36);
		contentPane.add(btnEventos);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfilView perfilView = new PerfilView();
				perfilView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnPerfil.setBounds(520, 11, 120, 36);
		contentPane.add(btnPerfil);
		
		JTextArea txtrDesenvolverUmaAplicao = new JTextArea();
		txtrDesenvolverUmaAplicao.setWrapStyleWord(true);
		txtrDesenvolverUmaAplicao.setBackground(new Color(231, 255, 211));
		txtrDesenvolverUmaAplicao.setFont(new Font("Dialog", Font.BOLD, 13));
		txtrDesenvolverUmaAplicao.setLineWrap(true);
		txtrDesenvolverUmaAplicao.setText("Curitiba e a Região Metropolitana oferecem uma riqueza de belezas naturais e experiências de ecoturismo que encantam moradores e visitantes. Aqui no DEEPA, nossa missão é conectar você com os melhores destinos ecológicos, eventos e atividades ao ar livre, proporcionando aventuras inesquecíveis e sustentáveis.\r\nPromovemos o turismo sustentável. Incentivamos práticas que preservam o meio ambiente e valorizam as comunidades locais. Cada lugar e evento em nossa plataforma é escolhido com cuidado para garantir que você possa desfrutar da natureza sem comprometer sua beleza e integridade.");
		txtrDesenvolverUmaAplicao.setBounds(24, 79, 389, 227);
		contentPane.add(txtrDesenvolverUmaAplicao);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuClienteView.class.getResource("/view/imagens/Sem título.png")));
		lblNewLabel.setBounds(445, 84, 195, 222);
		contentPane.add(lblNewLabel);
	}
}
