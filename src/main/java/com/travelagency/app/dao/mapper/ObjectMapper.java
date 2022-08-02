package com.travelagency.app.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {
    /**
     * Extract from result set t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws SQLException the sql exception
     */
    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Make unique t.
     *
     * @param cache  the cache
     * @param object the object
     * @return the t
     */
    T makeUnique(Map<Integer, T> cache, T object);
}
