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

public class GerenciadorEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbDados;
	private JTextField tfBusca;
	private JTextField tfNomeLugar;
	private JTextField tfEndereco;
	private JTextField tfDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorEventos frame = new GerenciadorEventos();
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
	public GerenciadorEventos() {
		setResizable(false);
		setTitle("Gerenciador de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 550);
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
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Endere\u00E7o", "Descri\u00E7\u00E3o"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbDados);
		
		JButton btnNewButton = new JButton("Listar Dados");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = new Conexao().getConnection();
					
					String sql = "Select * from dados_Eventos";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("nome_lugar"), rs.getString("endereco"), rs.getString("descricao")});
						
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
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBusca.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o ID");
					
				}else {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "update dados_Eventos set nome_lugar=?, endereco=?, descricao=? where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfNomeLugar.getText());
					stmt.setString(2, tfEndereco.getText());
					stmt.setString(3, tfDescricao.getText());
					stmt.setString(4, tfBusca.getText());
					
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
					
					String sql = "select * from dados_Eventos where id like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%"+tfBusca.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						tfNomeLugar.setText(rs.getString("nome_lugar"));
						tfEndereco.setText(rs.getString("endereco"));
						tfDescricao.setText(rs.getString("descricao"));
						
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
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 412, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNomeLugar = new JTextField();
		tfNomeLugar.setBounds(10, 437, 161, 20);
		contentPane.add(tfNomeLugar);
		tfNomeLugar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Endereço");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(198, 414, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(198, 437, 161, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Descrição");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(386, 384, 72, 14);
		contentPane.add(lblNewLabel_3);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(386, 409, 303, 91);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBusca.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o ID");
					
				}else {
				
				try {
					
					Connection con = new Conexao().getConnection();
					
					String sql = "delete from dados_Eventos where id=?";
					
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
	}
}
