package com.nucleus.data;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class MovieManage {

	private int movieId;
	private String movieName;
	private String movieActor;
	private String movieDesc;
	private MultipartFile movieImage;
	private Blob blobImage;
	private String movieImageName;

	public String getMovieImageName() {
		return movieImageName;
	}

	public void setMovieImageName(String movieImageName) {
		this.movieImageName = movieImageName;
	}

	public Blob getBlobImage() {
		return blobImage;
	}

	public void setBlobImage(Blob blobImage) {
		this.blobImage = blobImage;
	}

	public MovieManage() {
		super();
	}

	public MovieManage(int movieId, String movieName, String movieActor, String movieDesc, MultipartFile movieImage,
			String movieImageName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieActor = movieActor;
		this.movieDesc = movieDesc;
		this.movieImage = movieImage;
		this.movieImageName = movieImageName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public MultipartFile getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(MultipartFile movieImage) {
		this.movieImage = movieImage;
	}
}
