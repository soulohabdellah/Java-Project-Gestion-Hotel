package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.IDAO;
import entities.Categorie;




public class CategorieService implements IDAO<Categorie>{
	@Override
	public String toString() {
		return "CategorieService [categories=" + categories + "]";
	}

	List<Categorie> categories;
	public CategorieService() {
		categories = new ArrayList<Categorie>();
	}

	@Override
	public Boolean create(Categorie object) {
		String query = "insert into categorie values (null,?,?);";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getCode());
			preparedStatement.setString(2, object.getLibelle());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(Categorie object) {
		String query = "UPDATE categorie SET code=?,libelle=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getCode());
			preparedStatement.setString(2, object.getLibelle());
			preparedStatement.setInt(3, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Categorie object) {
		String query = "delete from categorie where id=?";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Categorie findById(int id) {
		String query = "select * from categorie where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Categorie(resultSet.getInt("id"), resultSet.getString("code"),resultSet.getString("libelle"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Categorie> findAll() {
		String query = "select * from categorie;";
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 categories.add(new Categorie(resultSet.getInt("id"), resultSet.getString("code"),resultSet.getString("libelle")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}


}
