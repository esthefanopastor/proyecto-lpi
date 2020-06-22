package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

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

public class GUIMarca extends JFrame implements ActionListener, MouseListener {

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

	private int selectedID = -1;
	private JButton btnActualizar;
	private JButton btnEliminar;

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

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(GUIMarca.class.getResource("/iconos/edit.gif")));
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(480, 112, 165, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(GUIMarca.class.getResource("/iconos/delete.gif")));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(480, 157, 165, 33);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 203, 605, 264);
		contentPane.add(scrollPane);

		table = new JTable();
		this.table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Estado" }));
		scrollPane.setViewportView(table);

		listarMarcas();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnEliminar) {
			btnEliminarActionPerformed(e);
		}
		if (e.getSource() == this.btnActualizar) {
			btnActualizarActionPerformed(e);
		}
		if (e.getSource() == this.btnAgregar) {
			btnAgregarActionPerformed(e);
		}
	}

	protected void btnAgregarActionPerformed(ActionEvent e) {
		if (txtNombre.getText() == "") {
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
					clearInputs();
					listarMarcas();
					selectedID = -1;
					message("Marca insertada correctamente");
				} else {
					message("Error en el registro");
				}
			}
		} else {
			clearInputs();
		}

	}

	protected void btnActualizarActionPerformed(ActionEvent e) {
		if (selectedID == -1) {
			message("Seleccione una fila");
		} else {
			String nombre = txtNombre.getText();
			String estado = txtEstado.getText();

			if (!nombre.matches(Validaciones.NOMBRE_MARCA)) {
				message("El nombre es de 2 a 45 caracteres");
			} else if (!estado.matches(Validaciones.ESTADO_MARCA)) {
				message("El estado es de 2 a 45 caracteres");
			} else {
				Marca marca = new Marca();
				marca.setId(selectedID);
				marca.setNombre(nombre);
				marca.setEstado(estado);

				MarcaModel marcaModel = new MarcaModel();
				int response = marcaModel.actualizarMarca(marca);

				if (response > 0) {
					clearInputs();
					listarMarcas();
					selectedID = -1;
					message("Se actualizó correctamente la marca");
				} else {
					message("Error al actualizar marca");
				}
			}
		}
	}

	protected void btnEliminarActionPerformed(ActionEvent e) {
		if (selectedID == -1) {
			message("Debe seleccionar una fila");
		} else {
			MarcaModel marcaModel = new MarcaModel();
			int response = marcaModel.eliminarMarca(selectedID);
			if (response > 0) {
				listarMarcas();
				clearInputs();
				selectedID = -1;
				message("Se eliminó correctamente la marca");
			} else {
				message("Error al eliminar");
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.table) {
			tableMouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void tableMouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();

		selectedID = (int) table.getValueAt(row, 0);
		String nombre = (String) table.getValueAt(row, 1);
		String estado = (String) table.getValueAt(row, 2);

		txtNombre.setText(nombre);
		txtEstado.setText(estado);
	}

	void message(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	void clearInputs() {
		txtNombre.setText("");
		txtEstado.setText("");
		txtNombre.requestFocus();
	}

	void listarMarcas() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		MarcaModel marcaModel = new MarcaModel();
		List<Marca> marcas = marcaModel.listarMarcas();

		for (Marca marca : marcas) {
			Object[] row = { marca.getId(), marca.getNombre(), marca.getEstado() };
			tableModel.addRow(row);
		}
	}

}
