package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Marca;
import util.DBConnection;

public class MarcaModel {
	public int insertMarca(Marca marca) {
		int response = -1;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DBConnection.getConnection();

			String query = "INSERT INTO marca VALUES (null, ?, ?)";
			statement = connection.prepareStatement(query);

			statement.setString(1, marca.getNombre());
			statement.setNString(2, marca.getEstado());

			response = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return response;
	}

	public int actualizarMarca(Marca marca) {
		int response = -1;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnection.getConnection();
			String query = "UPDATE marca SET nombre=?, estado=? where idmarca=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, marca.getNombre());
			statement.setString(2, marca.getEstado());
			statement.setInt(3, marca.getId());
			response = statement.executeUpdate();
			System.out.println("actualizados :  " + response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public int eliminarMarca(int idMarca) {
		int response = -1;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DBConnection.getConnection();
			String query = "DELETE FROM marca WHERE idmarca=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, idMarca);
			response = statement.executeUpdate();
			System.out.println("eliminados :  " + response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public List<Marca> listarMarcas() {
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DBConnection.getConnection();
			String query = "SELECT *  FROM marca";
			statement = connection.prepareStatement(query);

			resultSet = statement.executeQuery();

			Marca marca = null;
			while (resultSet.next()) {
				marca = new Marca();
				marca.setId(resultSet.getInt("idmarca"));
				marca.setNombre(resultSet.getString("nombre"));
				marca.setEstado(resultSet.getString("estado"));
				marcas.add(marca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return marcas;
	}
}
