package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class GerenciadorPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbDados;
	private JTextField tfBusca;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTextField tfSenha;
	private JTextField tfTipoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorPerfil frame = new GerenciadorPerfil();
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
	public GerenciadorPerfil() {
		setResizable(false);
		setTitle("Gerenciador de Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 679, 271);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Usu\u00E1rio", "Email", "Senha", "Tipo de usu\u00E1rio"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JButton btnNewButton = new JButton("Listar Dados");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = new Conexao().getConnection();
					
					String sql = "Select * from dados_curso";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("usuario"), rs.getString("email"), rs.getString("senha"), rs.getString("tipo_usuario")});
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 24, 111, 44);
		contentPane.add(btnNewButton);
		
		JCheckBox cbADM = new JCheckBox("");
		cbADM.setBounds(228, 378, 26, 23);
		contentPane.add(cbADM);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBusca.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o ID");
					
				}else {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "update dados_curso set usuario=?, email=?, senha=?, tipo_usuario=? where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfNome.getText());
					stmt.setString(2, tfEmail.getText());
					stmt.setString(3, tfSenha.getText());
					String tipoUsuario = cbADM.isSelected()? "ADM" : "Cliente";
					stmt.setString(4, tipoUsuario);
					stmt.setString(5, tfBusca.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtualizar.setBounds(198, 24, 111, 44);
		contentPane.add(btnAtualizar);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 360, 99, 20);
		contentPane.add(lblNewLabel);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(10, 381, 86, 20);
		contentPane.add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnAbrir = new JButton("Abrir dados\r\n");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBusca.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o ID");
					
				}else {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "select * from dados_curso where id like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%"+tfBusca.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						String userType = rs.getString("tipo_usuario");
						
						if("ADM".equals(userType)) {
							cbADM.setSelected(true);
						}else if("Cliente".equals(userType)) {
							cbADM.setSelected(false);
						}else {
							JOptionPane.showMessageDialog(null, "Erro, Tente novamente.");
						}
						tfNome.setText(rs.getString("usuario"));
						tfEmail.setText(rs.getString("email"));
						tfSenha.setText(rs.getString("senha"));
						
					}
					
					rs.close();
					con.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAbrir.setBounds(106, 380, 99, 23);
		contentPane.add(btnAbrir);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 412, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 437, 161, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 460, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(11, 483, 161, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBusca.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o ID");
					
				}else {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "delete from dados_curso where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfBusca.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBounds(386, 24, 111, 44);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmView admView = new MenuAdmView();
				admView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setBounds(578, 24, 111, 44);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(233, 412, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(232, 437, 161, 20);
		contentPane.add(tfSenha);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ADM");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(233, 363, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		tfTipoUsuario = new JTextField();
		tfTipoUsuario.setColumns(10);
		tfTipoUsuario.setBounds(232, 483, 0, 20);
		contentPane.add(tfTipoUsuario);
	
	}
}
