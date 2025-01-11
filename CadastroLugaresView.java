package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CadastroLugaresView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					CadastroLugaresView frame = new CadastroLugaresView();
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
	public CadastroLugaresView() {
		setResizable(false);
		setTitle("Cadastro de lugares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 46, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfNomeLugar = new JTextField();
		tfNomeLugar.setBounds(10, 64, 464, 29);
		contentPane.add(tfNomeLugar);
		tfNomeLugar.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(10, 107, 92, 14);
		contentPane.add(lblNewLabel_1);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(10, 126, 464, 29);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição");
		lblNewLabel_2.setBounds(10, 166, 92, 14);
		contentPane.add(lblNewLabel_2);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(10, 182, 464, 115);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conexao = new Conexao().getConnection();
					
					String nomeLugar = tfNomeLugar.getText();
				    String endereco = tfEndereco.getText();
				    String descricao = tfDescricao.getText();

				    if (nomeLugar.isEmpty() || endereco.isEmpty() || descricao.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Nome/Endereço/Descrição não podem estar em branco.");
				        return;
				    }
					
					String sql = "INSERT INTO dados_lugares (nome_lugar, endereco, descricao) VALUES (?,?,?);";
					
					PreparedStatement stmt = conexao.prepareStatement(sql);
					
					stmt.setString(1, tfNomeLugar.getText());
					stmt.setString(2, tfEndereco.getText());
					stmt.setString(3, tfDescricao.getText());
					
					int rs = stmt.executeUpdate();
					
					if(rs > 0) {
						
						MenuAdmView exibir = new MenuAdmView();
						exibir.setVisible(true);
						
						setVisible(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar local.");
					}
					
					stmt.close();
					conexao.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
			}
		);
		btnNewButton.setBounds(373, 308, 101, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Cadastro de Lugares");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3.setBounds(174, 11, 143, 24);
		contentPane.add(lblNewLabel_3);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmView admView = new MenuAdmView();
				admView.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 308, 101, 36);
		contentPane.add(btnVoltar);
	}
}
