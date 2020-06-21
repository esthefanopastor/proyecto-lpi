package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
