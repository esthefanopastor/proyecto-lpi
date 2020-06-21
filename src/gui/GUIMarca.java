package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Marca;
import model.MarcaModel;
import util.Validaciones;

public class GUIMarca extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEstado;
	private JTextField txtNombre;
	private JTextField txtEstado;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAgregar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					GUIMarca frame = new GUIMarca();
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
	public GUIMarca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Mantenimiento de Marca");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(12, 32, 658, 25);
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(40, 67, 69, 16);
		contentPane.add(lblNombre);

		lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(40, 137, 69, 16);
		contentPane.add(lblEstado);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setBounds(40, 95, 370, 25);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtEstado = new JTextField();
		txtEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEstado.setColumns(10);
		txtEstado.setBounds(40, 165, 370, 25);
		contentPane.add(txtEstado);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setIcon(new ImageIcon(GUIMarca.class.getResource("/iconos/add.gif")));
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAgregar.setBounds(480, 67, 165, 33);
		contentPane.add(btnAgregar);

		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setIcon(new ImageIcon(GUIMarca.class.getResource("/iconos/edit.gif")));
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(480, 112, 165, 33);
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon(GUIMarca.class.getResource("/iconos/delete.gif")));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(480, 157, 165, 33);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 203, 605, 264);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Estado" }));
		scrollPane.setViewportView(table);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAgregar) {
			btnAgregarActionPerformed(e);
		}
	}

	protected void btnAgregarActionPerformed(ActionEvent e) {
		String nombre = txtNombre.getText();
		String estado = txtEstado.getText();

		if (!nombre.matches(Validaciones.NOMBRE_MARCA)) {
			message("El nombre deben ser letras");
		} else if (!estado.matches(Validaciones.ESTADO_MARCA)) {
			message("El estado debe ser entre 2 a 45 caracteres");
		} else {
			Marca marca = new Marca();
			marca.setNombre(nombre);
			marca.setEstado(estado);

			MarcaModel marcaModel = new MarcaModel();
			int response = marcaModel.insertMarca(marca);

			if (response > 0) {
				message("Marca insertada correctamente");
			}
		}

	}

	void message(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
