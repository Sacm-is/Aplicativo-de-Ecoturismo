package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ArmazenaID;
import dao.Conexao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setResizable(false);
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Dialog", Font.PLAIN, 16));
		tfUsuario.setBounds(109, 74, 203, 34);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Login");
		lblUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUsuario.setBounds(109, 49, 46, 19);
		contentPane.add(lblUsuario);
		
		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		pfSenha.setBounds(109, 143, 203, 34);
		contentPane.add(pfSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSenha.setBounds(109, 119, 46, 19);
		contentPane.add(lblSenha);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "select *from dados_curso where usuario=? and senha=?;";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String(pfSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						int userID = rs.getInt("id");
						String userType = rs.getString("tipo_usuario");
						
						ArmazenaID.setUserID(userID);
						
						if("ADM".equals(userType)) {
							MenuAdmView admView = new MenuAdmView();
							admView.setVisible(true);
							
							setVisible(false);
						} else if("Cliente".equals(userType)){
							MenuClienteView menu = new MenuClienteView();
							menu.setVisible(true);
							
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Usuário/Senha incorreto.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Usuário/Senha incorreto.");
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(156, 188, 114, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormCadastroView telaDeCadastro = new FormCadastroView();
				telaDeCadastro.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(156, 228, 114, 34);
		contentPane.add(btnNewButton_1);
	}

}
