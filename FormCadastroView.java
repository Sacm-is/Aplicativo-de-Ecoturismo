package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Conexao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FormCadastroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfEmail;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroView frame = new FormCadastroView();
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
	public FormCadastroView() {
		setTitle("Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(25, 79, 197, 34);
		contentPane.add(tfUsuario);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(25, 149, 197, 34);
		contentPane.add(tfEmail);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(25, 54, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(25, 124, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(25, 194, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Criar Conta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conexao = new Conexao().getConnection();
					
					String usuario = tfUsuario.getText();
				    String email = tfEmail.getText();
				    String senha = new String(pfSenha.getPassword());

				    if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Usuario/Email/Senha não podem estar em branco.");
				        return;
				    }
					
					String sql = "INSERT INTO dados_curso (usuario, email, senha, tipo_usuario) VALUES (?,?,?,'Cliente');";
					
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfEmail.getText());
					stmt.setString(3, new String(pfSenha.getPassword()));
					
					int rs = stmt.executeUpdate();
					
					if(rs > 0) {
						
						LoginView exibir = new LoginView();
						exibir.setVisible(true);
						
						setVisible(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
					}
					
					stmt.close();
					conexao.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(268, 149, 136, 34);
		contentPane.add(btnNewButton);
		
		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		pfSenha.setBounds(25, 219, 197, 34);
		contentPane.add(pfSenha);
	}

}
