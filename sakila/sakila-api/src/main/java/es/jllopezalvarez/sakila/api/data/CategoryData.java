package es.jllopezalvarez.sakila.api.data;


import es.jllopezalvarez.sakila.api.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryData {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/sakila";
    private static final String USER = "sakilauser";
    private static final String PASSWORD = "sakilauserpassword";
    private static final String SQL_GET_ALL = "select category_id, name, last_update from category";
    private static final String SQL_GET_BY_ID = "select category_id, name, last_update from category where category_id = ?";
    private static final String SQL_CREATE = "insert into category (name) values (?)";
    private static final String SQL_UPDATE = "update category set name = ? where category_id = ?";
    private static final String SQL_DELETE = "delete from category where category_id = ?";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAST_UPDATE = "last_update";

    public static List<Category> getAll() {
        // Creamos la colección para almacenar los resultados.
        List<Category> results = new ArrayList<>();

        // Abrimos conexión, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {
            // Recorremos los registros
            while (resultSet.next()) {
                // Creamos un objeto por cada registro y lo añadimos a la lista
                results.add(new Category(resultSet.getInt(COLUMN_CATEGORY_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getTimestamp(COLUMN_LAST_UPDATE).toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public static Optional<Category> getById(int id) {
        // Abrimos conexión y creamos preparedStatement
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)) {
            // Parametrizamos la sentencia
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si next() devuelve true, es que hay registro.
                // Alternativa, usar ResultSet.isBeforeFirst para identificar si hay resultados, y hacer el next()
                if (resultSet.next()) {
                    // Hay resultados, devolvemos el objeto.
                    return Optional.of(new Category(resultSet.getInt(COLUMN_CATEGORY_ID),
                            resultSet.getString(COLUMN_NAME),
                            resultSet.getTimestamp(COLUMN_LAST_UPDATE).toLocalDateTime()));
                } else {
                    // No hay resultados
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Category create(Category category) {
        // Abrimos conexión y creamos preparedStatement. Usamos Statement.RETURN_GENERATED_KEYS para poder recuperar el ID de la categoría creada, y usarlo para devolver el objeto
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            // Parametrizamos la sentencia
            statement.setString(1, category.getName());
            // Ejecutamos la sentencia y nos quedamos con el número de filas afectadas
            int affectedRows = statement.executeUpdate();
            // Si no se han devuelto filas lanzamos una excepción
            if (affectedRows == 0) {
                throw new RuntimeException("Error al crear la categoría. La sentencia SQL ha devuelto 0 filas afectadas.");
            }
            // Tenemos que obtener el ResultSet de claves generadas
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                // Si no hay claves generadas, lanzamos una excepción.
                if (!generatedKeys.next()) {
                    throw new RuntimeException("Error al crear la categoría. La sentencia SQL no ha devuelto claves generadas.");
                }
                int generatedKey = generatedKeys.getInt(1);
                // Con la clave generada, obtenemos el objeto
                Optional<Category> newCategory = getById(generatedKey);
                // Si no hemos podido recuperarla, lanzamos excepción
                if (newCategory.isEmpty()) {
                    throw new RuntimeException("Error al crear la categoría. No se ha podido recuperar la categoría creada.");
                }
                // Devolvemos el objeto creado
                return newCategory.get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Category update(Category category) {
        // Abrimos conexión y creamos preparedStatement.
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            // Parametrizamos la sentencia.
            statement.setString(1, category.getName());
            statement.setInt(1, category.getCategoryId());
            // Ejecutamos la sentencia y nos quedamos con el número de filas afectadas
            int affectedRows = statement.executeUpdate();
            // Si no se han devuelto filas lanzamos una excepción
            if (affectedRows == 0) {
                throw new RuntimeException("Error al actualizar la categoría. La sentencia SQL ha devuelto 0 filas afectadas.");
            }
            // Recuperamos de nuevo el objeto, para tenerlo actualizado
            Optional<Category> updatedCategory = getById(category.getCategoryId());
            // Si no hemos podido recuperarla, lanzamos excepción
            if (updatedCategory.isEmpty()) {
                throw new RuntimeException("Error al actualizar la categoría. No se ha podido recuperar la categoría actualizada.");
            }
            // Devolvemos el objeto creado
            return updatedCategory.get();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        // Abrimos conexión y creamos preparedStatement.
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            // Parametrizamos la sentencia.
            statement.setInt(1, id);
            // Ejecutamos la sentencia y nos quedamos con el número de filas afectadas
            int affectedRows = statement.executeUpdate();
            // Si no se han devuelto filas lanzamos una excepción
            if (affectedRows == 0) {
                throw new RuntimeException("Error al eliminar la categoría. La sentencia SQL ha devuelto 0 filas afectadas.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
