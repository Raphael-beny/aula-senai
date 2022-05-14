package br.com.senai.manutencaosenaiapi.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.service.PecaService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@Component
public class TelaCadastroDePecas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfDescricao;
	private JButton btnNewButton;
	private JTextArea txtEspecificacoes;
	
	@Autowired
	private PecaService service;
	private JLabel lblQtd;
	private JTextField tfQtd;

	/**
	 * Create the frame.
	 */
	public TelaCadastroDePecas() {
		setTitle("Cadastro de peças");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("Id");
		
		tfId = new JTextField();
		tfId.setEnabled(false);
		tfId.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		
		tfDescricao = new JTextField();
		tfDescricao.setColumns(10);
		
		JLabel lblEspecificacoes = new JLabel("Especificações");
		
		txtEspecificacoes = new JTextArea();
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(tfId.getText() != null && tfId.getText().length() > 0) {
						Peca pecaSalva = new Peca();
						pecaSalva.setDescricao(tfDescricao.getText());
						pecaSalva.setQtdeEmEstoque(Integer.parseInt(tfQtd.getText()));
						pecaSalva.setEspecificacoes(txtEspecificacoes.getText());
						pecaSalva.setId(Integer.parseInt(tfId.getText()));
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(btnSalvar, "Peça alterada com sucesso!");
					}else {
						Peca peca = new Peca();
						peca.setDescricao(tfDescricao.getText());
						peca.setQtdeEmEstoque(Integer.parseInt(tfQtd.getText()));
						peca.setEspecificacoes(txtEspecificacoes.getText());
						Peca pecaSalva = service.inserir(peca);
						JOptionPane.showMessageDialog(btnSalvar, "Peça salva com sucesso!");
						tfId.setText(pecaSalva.getId().toString());
					}
					
					
					
					
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(btnSalvar, ex.getMessage());
				}
				
			}
		});
		
		btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				telaConsultaDePeca.setVisible(true);
//				setVisible(false);
			}
		});
		
		lblQtd = new JLabel("Quantidade");
		
		tfQtd = new JTextField();
		tfQtd.setColumns(10);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEspecificacoes)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(tfId, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
									.addComponent(lblQtd)
									.addGap(46))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(tfDescricao, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
									.addComponent(tfQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSalvar)))
						.addComponent(txtEspecificacoes, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescricao)
								.addComponent(lblQtd))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblId)
							.addGap(6)
							.addComponent(tfId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblEspecificacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEspecificacoes, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnSalvar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void colocarEmEdicao(Peca pecaSalva) {
		tfId.setText(pecaSalva.getId().toString());
		tfDescricao.setText(pecaSalva.getDescricao());
		txtEspecificacoes.setText(pecaSalva.getEspecificacoes());
		tfQtd.setText(pecaSalva.getQtdeEmEstoque().toString());
	}
}
