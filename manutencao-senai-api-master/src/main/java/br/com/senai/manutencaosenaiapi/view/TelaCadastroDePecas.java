package br.com.senai.manutencaosenaiapi.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TiposDePecaService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

@Component
public class TelaCadastroDePecas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfDescricao;
	private JButton btnNewButton;
	private JTextArea txtEspecificacoes;
	
	@Autowired
	private TelaDeConsultaDeTipoDePeca telaDeConsultaDeTipoDePeca;
	
	@Autowired
	private TiposDePecaService tiposDePecaService;
	
	@Autowired
	private PecaService service;
	private JLabel lblQtd;
	private JTextField tfQtd;
	private JComboBox<TipoDePeca> jComboBox;

	public TelaCadastroDePecas() {
		setTitle("Cadastro de peças");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 460);
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
						pecaSalva.setTipo((TipoDePeca) jComboBox.getSelectedItem());
						pecaSalva.setId(Integer.parseInt(tfId.getText()));
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(btnSalvar, "Peça alterada com sucesso!");
					}else {
						Peca peca = new Peca();
						peca.setDescricao(tfDescricao.getText());
						peca.setQtdeEmEstoque(Integer.parseInt(tfQtd.getText()));
						peca.setEspecificacoes(txtEspecificacoes.getText());
						peca.setTipo((TipoDePeca) jComboBox.getSelectedItem());
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
				setVisible(false);
			}
		});
		
		lblQtd = new JLabel("Quantidade");
		
		tfQtd = new JTextField();
		tfQtd.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		
		jComboBox = new JComboBox<>();
		
		JButton btnSelecTipo = new JButton("Gerenciar Tipos");
		btnSelecTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeConsultaDeTipoDePeca.setVisible(true);
				telaDeConsultaDeTipoDePeca.setLocationRelativeTo(null);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblId)
										.addComponent(tfId, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
									.addGap(34)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfDescricao, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
								.addComponent(jComboBox, 0, 377, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQtd)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnSelecTipo)
									.addComponent(tfQtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(41))
						.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspecificacoes)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSalvar)
							.addComponent(txtEspecificacoes, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
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
					.addGap(32)
					.addComponent(lblTipo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelecTipo)
						.addComponent(jComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(lblEspecificacoes)
					.addGap(12)
					.addComponent(txtEspecificacoes, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(btnSalvar)
					.addGap(22)
					.addComponent(btnNewButton)
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
	
	public void listarTipos() {
		List<TipoDePeca> tiposEncontrados = tiposDePecaService.listarTodos();
		tiposEncontrados.forEach(tipo -> {
			jComboBox.addItem(tipo);
		});
	}
	
}
