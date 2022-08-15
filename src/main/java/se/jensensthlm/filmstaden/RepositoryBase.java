package se.jensensthlm.filmstaden;

import java.sql.*;

public abstract class RepositoryBase {
    private Connection db = null;

    protected Connection conn() {
        if(db != null) {
            return db;
        }

        try {
            db = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/filmstaden",
                    "root",
                    ""
            );
            return db;
        }
        catch (Exception e) {
            return null;
        }
    }

    protected ResultSet executeQuery(String query, Object... params) throws SQLException {
        Statement stmt = conn().createStatement();
        return stmt.executeQuery(String.format(query, params));
    }
}
