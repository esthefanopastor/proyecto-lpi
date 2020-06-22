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


import entity.Usuario;
import model.UsuarioModel;
import util.Validaciones;

public class GUIUsuario extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEstado;
	private JTextField txtApellido;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAgregar;

	private int selectedID = -1;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblNombre_1;
	private JLabel lblNombre_2;
	private JLabel lblNombre_3;
	private JLabel lblNombre_4;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtLogin;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					GUIUsuario frame = new GUIUsuario();
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
	public GUIUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Mantenimiento de Usuario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(10, 25, 658, 31);
		contentPane.add(lblTitulo);

		lblEstado = new JLabel("DNI");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(66, 164, 69, 16);
		contentPane.add(lblEstado);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtApellido.setColumns(10);
		txtApellido.setBounds(209, 120, 211, 25);
		contentPane.add(txtApellido);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setIcon(new ImageIcon(GUIUsuario.class.getResource("/iconos/add.gif")));
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAgregar.setBounds(480, 67, 165, 33);
		contentPane.add(btnAgregar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(GUIUsuario.class.getResource("/iconos/edit.gif")));
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(480, 112, 165, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(GUIUsuario.class.getResource("/iconos/delete.gif")));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(480, 156, 165, 33);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 299, 605, 201);
		contentPane.add(scrollPane);

		table = new JTable();
		this.table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idUsuario", "Nombre", "Apellido", "DNI", "Login", "Password"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre_1.setBounds(66, 84, 69, 16);
		contentPane.add(lblNombre_1);
		
		lblNombre_2 = new JLabel("Apellido");
		lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre_2.setBounds(66, 120, 69, 16);
		contentPane.add(lblNombre_2);
		
		lblNombre_3 = new JLabel("Login");
		lblNombre_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre_3.setBounds(66, 191, 69, 25);
		contentPane.add(lblNombre_3);
		
		lblNombre_4 = new JLabel("Password");
		lblNombre_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre_4.setBounds(66, 236, 84, 16);
		contentPane.add(lblNombre_4);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		txtNombre.setBounds(209, 84, 211, 25);
		contentPane.add(txtNombre);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDni.setColumns(10);
		txtDni.setBounds(209, 159, 211, 25);
		contentPane.add(txtDni);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLogin.setColumns(10);
		txtLogin.setBounds(209, 195, 211, 25);
		contentPane.add(txtLogin);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPassword.setColumns(10);
		txtPassword.setBounds(209, 231, 211, 25);
		contentPane.add(txtPassword);

		listarUsuario();
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
			String apellido = txtApellido.getText();
			String dni = txtDni.getText();
			String login = txtLogin.getText();
			String password = txtPassword.getText();

			if (!nombre.matches(Validaciones.TEXTO)) {
				message("El nombre deben ser letras");
			} else if (!apellido.matches(Validaciones.TEXTO)) {
				message("El apellido ser entre 2 a 20 caracteres");
			}else if (!dni.matches(Validaciones.DNI)) {
				message("El DNI debe tener 8 caracteres");
			}else if (!login.matches(Validaciones.CORREO)) {
				message("El correo debe ser aaaaa@aaa.aaa");
			}else if (!password.matches(Validaciones.PASSWORD)) {
				message("El password debe tener mínimo 8 caracteres, maxímo 45, Al menos una letra mayúscula, Al menos una letra minucula, Al menos un dígito, No espacios en blanco, Al menos 1 caracter especial");
			} else {
				Usuario usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setDni(dni);
				usuario.setLogin(login);
				usuario.setPassword(password);

				UsuarioModel usuarioModel = new UsuarioModel();
				int response = usuarioModel.insertarUsuario(usuario);
				if (response > 0) {
					clearInputs();
					listarUsuario();
					selectedID = -1;
					message("Usuario insertado correctamente");
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
			String apellido = txtApellido.getText();
			String dni = txtDni.getText();
			String login = txtLogin.getText();
			String password = txtPassword.getText();


			if (!nombre.matches(Validaciones.TEXTO)) {
				message("El nombre deben ser letras");
			} else if (!apellido.matches(Validaciones.TEXTO)) {
				message("El apellido ser entre 2 a 20 caracteres");
			}else if (!dni.matches(Validaciones.DNI)) {
				message("El DNI debe tener 8 caracteres");
			}else if (!login.matches(Validaciones.CORREO)) {
				message("El correo debe ser aaaaa@aaa.aaa");
			}else if (!password.matches(Validaciones.PASSWORD)) {
				message("El password debe tener mínimo 8 caracteres, maxímo 45, Al menos una letra mayúscula, Al menos una letra minucula, Al menos un dígito, No espacios en blanco, Al menos 1 caracter especial");
			} else
				
			{
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(selectedID);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setDni(dni);
				usuario.setLogin(login);
				usuario.setPassword(password);

				UsuarioModel UM = new UsuarioModel();
				int response = UM.actualizarUsuario(usuario);

				if (response > 0) {
					clearInputs();
					listarUsuario();
					selectedID = -1;
					message("Se actualizó correctamente el usuario");
				} else {
					message("Error al actualizar el usuario");
				}
			}
		}
	}

	protected void btnEliminarActionPerformed(ActionEvent e) {
		if (selectedID == -1) {
			message("Debe seleccionar una fila");
		} else {
			UsuarioModel usuarioModel = new UsuarioModel();
		
			int response = usuarioModel.eliminarUsuario(selectedID);
			if (response > 0) {
				listarUsuario();
				clearInputs();
				selectedID = -1;
				message("Se eliminó correctamente el usuario");
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
		String apellido = (String) table.getValueAt(row, 2);
		String dni = (String) table.getValueAt(row, 3);
		String login = (String) table.getValueAt(row, 4);
		String password = (String) table.getValueAt(row, 5);

		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDni.setText(dni);
		txtLogin.setText(login);
		txtPassword.setText(password);
		
	}

	void message(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	void clearInputs() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtLogin.setText("");
		txtPassword.setText("");
		txtNombre.requestFocus();
	}

	void listarUsuario() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		UsuarioModel usuarioModel = new UsuarioModel();
		List<Usuario> usuarios =  usuarioModel.listaUsuario();

		for (Usuario usuario : usuarios) {
			Object[] row = { usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getDni(), usuario.getLogin(), usuario.getPassword()};
			tableModel.addRow(row);
		}
	}

}
