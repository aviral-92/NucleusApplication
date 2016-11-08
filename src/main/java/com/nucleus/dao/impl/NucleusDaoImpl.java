package com.nucleus.dao.impl;

import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.nucleus.dao.NucleusDao;
import com.nucleus.dao.extractor.NucleusDaoExtractor;
import com.nucleus.data.MovieManage;

@Component
public class NucleusDaoImpl implements NucleusDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String insertData(MovieManage movieManage, boolean choice) {

		String res;
		int response = 0;
		String query = "INSERT INTO NUCLEUS.MOVIE_MANAGE VALUES (0,?,?,?,?,?)";
		if (!isExist(movieManage)) {
			if (choice) {
				Object args[] = { movieManage.getMovieName(), movieManage.getMovieActor(), movieManage.getMovieDesc(),
						movieManage.getMovieImage(), movieManage.getMovieImageName() };
				response = jdbcTemplate.update(query, args);
			} else {
				try {
					LobHandler lobHandler = new DefaultLobHandler();
					response = jdbcTemplate.update(query,
							new Object[] { movieManage.getMovieName(), movieManage.getMovieActor(),
									movieManage.getMovieDesc(),
									new SqlLobValue(movieManage.getMovieImage().getInputStream(),
											(int) movieManage.getMovieImage().getSize(), lobHandler),
									movieManage.getMovieImageName(), },
							new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR });
				} catch (DataAccessException e) {
					System.out.println("DataAccessException " + e.getMessage());
				} catch (IOException e) {
					System.out.println("DataAccessException " + e.getMessage());
				}
			}
			if (response > 0) {
				res = "Successfully inserterd";
			} else {
				res = "Failure due to large size of image";
			}
		} else {
			res = "Already exist";
		}
		return res;
	}

	@Override
	public MovieManage getData(int id) {

		MovieManage movieManages = jdbcTemplate.query("SELECT * FROM NUCLEUS.MOVIE_MANAGE WHERE MOVIE_ID = " + id,
				new NucleusDaoExtractor());
		return movieManages;
	}

	private boolean isExist(MovieManage movieManage) {

		boolean exist = false;
		boolean isMovieName = false;
		StringBuffer sbr = new StringBuffer("SELECT * FROM NUCLEUS.MOVIE_MANAGE ");
		List<String> args = new ArrayList<String>();
		if (!StringUtils.isEmpty(movieManage)) {
			if (!StringUtils.isEmpty(movieManage.getMovieName())) {
				sbr.append(" WHERE MOVIE_NAME = ? ");
				args.add(movieManage.getMovieName());
				isMovieName = true;
			}
			if (isMovieName) {
				if (!StringUtils.isEmpty(movieManage.getMovieImageName())) {
					sbr.append(" OR MOVIE_IMAGE_NAME = ? ");
					args.add(movieManage.getMovieImageName());
				}
			} else {
				if (!StringUtils.isEmpty(movieManage.getMovieImageName())) {
					sbr.append(" WHERE MOVIE_IMAGE_NAME = ? ");
					args.add(movieManage.getMovieImageName());
				}
			}
		}
		MovieManage movieManages = jdbcTemplate.query(sbr.toString(), new NucleusDaoExtractor(), args.toArray());
		if (!StringUtils.isEmpty(movieManages)) {
			if (movieManage.getMovieName().equals(movieManages.getMovieName())
					|| movieManage.getMovieImageName().equals(movieManages.getMovieImageName())) {
				exist = true;
			}
		}
		return exist;
	}

	@Override
	public MovieManage getLastRow() {

		String query = "SELECT * from `nucleus`.`movie_manage` where MOVIE_ID = (select MAX(MOVIE_ID) from `nucleus`.`movie_manage`)";
		MovieManage movieManages = jdbcTemplate.query(query, new NucleusDaoExtractor());
		return movieManages;
	}
}