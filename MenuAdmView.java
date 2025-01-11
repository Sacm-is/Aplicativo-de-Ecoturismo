package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdmView extends JFrame {

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
	public MenuAdmView() {
		setTitle("Administrador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLugares = new JButton("Cadastro de Lugares");
		btnLugares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroLugaresView cadastroLugares = new CadastroLugaresView();
				cadastroLugares.setVisible(true);
				
				setVisible(false);
			}
		});
		btnLugares.setBounds(35, 21, 221, 36);
		contentPane.add(btnLugares);
		
		JButton btnNewButton_1_1 = new JButton("Cadastro de Eventos");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroEventoView cadastroEventos = new CadastroEventoView();
				cadastroEventos.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(35, 84, 221, 36);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Gerenciador de Perfil");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorPerfil gerenciadorP = new GerenciadorPerfil();
				gerenciadorP.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1_1_1.setBounds(35, 150, 221, 36);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Gerenciador de Lugares");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorLugares gerenciadorL = new GerenciadorLugares();
				gerenciadorL.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1_1_1_1.setBounds(35, 212, 221, 36);
		contentPane.add(btnNewButton_1_1_1_1);
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("Gerenciador de Eventos");
		btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorEventos gerenciadorE = new GerenciadorEventos();
				gerenciadorE.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton_1_1_1_1_1.setBounds(35, 274, 221, 36);
		contentPane.add(btnNewButton_1_1_1_1_1);
	}
}
