package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ArmazenaID;
import dao.Conexao;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class PerfilView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfEmail;
	private JTextField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilView frame = new PerfilView();
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
	public PerfilView() {
		setTitle("Perfil");
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
		
		JButton btnPerfil = new JButton("Eventos");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventosView eventosView = new EventosView();
				eventosView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnPerfil.setBounds(467, 11, 173, 36);
		contentPane.add(btnPerfil);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(234, 113, 194, 31);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(234, 88, 77, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmail.setBounds(234, 155, 77, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(234, 224, 77, 14);
		contentPane.add(lblNewLabel_1_1);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(234, 180, 194, 31);
		contentPane.add(tfEmail);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(234, 249, 194, 31);
		contentPane.add(tfSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "update dados_curso set usuario=?, email=?, senha=? where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfEmail.getText());
					stmt.setString(3, tfSenha.getText());
					stmt.setInt(4, ArmazenaID.getUserID());
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSalvar.setBounds(277, 301, 106, 36);
		contentPane.add(btnSalvar);
		
		carregarDados();
	}
	
	private void carregarDados() {
		try {
			Connection con = new Conexao().getConnection();
			
			String sql = "SELECT usuario, email, senha FROM dados_curso WHERE id = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ArmazenaID.getUserID());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
                tfUsuario.setText(rs.getString("usuario"));
                tfEmail.setText(rs.getString("email"));
                tfSenha.setText(rs.getString("senha"));
            }
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
