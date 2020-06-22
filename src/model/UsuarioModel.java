package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Usuario;
import util.DBConnection;

public class UsuarioModel {
	
	public int insertarUsuario(Usuario usuario) {
		int response = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBConnection.getConnection();

			String query = "insert into usuario values (null, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getDni());
			ps.setString(4, usuario.getLogin());
			ps.setString(5, usuario.getPassword());
			


			response = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return response;
	}
	public List<Usuario> listaUsuario() {
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn =  DBConnection.getConnection();
			String sql ="select * from usuario";
			ps = conn.prepareStatement(sql);
			System.out.println("SQL-->" + ps);
			
			rs = ps.executeQuery();
			
			Usuario u = null;
			while(rs.next()){
				u = new Usuario();
				u.setIdUsuario(rs.getInt("idUsuario"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setDni(rs.getString("dni"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				usuario.add(u);
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	public int actualizarUsuario(Usuario usuario) {
		int response = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE usuario SET nombre=?, apellido=?, dni=?, login=?, password=? where idusuario=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getDni());
			ps.setString(4, usuario.getLogin());
			ps.setString(5, usuario.getPassword());
			ps.setInt(6, usuario.getIdUsuario());
			response = ps.executeUpdate();
			System.out.println("actualizados :  " + response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	public int eliminarUsuario(int idUsuario) {
		int response = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBConnection.getConnection();
			String query = "DELETE FROM usuario WHERE idusuario=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, idUsuario);
			response = ps.executeUpdate();
			System.out.println("eliminados :  " + response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
}
