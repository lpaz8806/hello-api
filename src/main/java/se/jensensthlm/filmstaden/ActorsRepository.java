package se.jensensthlm.filmstaden;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ActorsRepository extends RepositoryBase {
    public List<Actor> getAll() throws SQLException {
        var rs = executeQuery("SELECT * FROM actors");

        return Collections.emptyList();
    }

    public Actor getById(long id) throws SQLException {
        var rs = executeQuery("SELECT * FROM actors WHERE id = %s", id);
        if(rs.next())
            return new Actor(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getString("born_on")
            );
        return null;
    }
}
