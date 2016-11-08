package com.nucleus.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.nucleus.data.MovieManage;

public class NucleusDaoExtractor implements ResultSetExtractor<MovieManage> {

	@Override
	public MovieManage extractData(ResultSet rs) throws SQLException, DataAccessException {

		MovieManage movieManage = null;
		if (rs.next()) {
			movieManage = new MovieManage();
			movieManage.setMovieId(rs.getInt("movie_id"));
			movieManage.setMovieName(rs.getString("movie_name"));
			movieManage.setMovieActor(rs.getString("movie_actor"));
			movieManage.setMovieDesc(rs.getString("movie_description"));
			movieManage.setBlobImage(rs.getBlob("movie_image"));
			movieManage.setMovieImageName(rs.getString("movie_image_name"));
		}
		return movieManage;
	}
}
