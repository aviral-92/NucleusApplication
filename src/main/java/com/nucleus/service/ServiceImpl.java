package com.nucleus.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nucleus.dao.NucleusDao;
import com.nucleus.data.MovieManage;

@Service
public class ServiceImpl {

	@Autowired
	private NucleusDao nucleusDao;

	public MovieManage getMovieManage(int id, String navigate) {

		MovieManage movieManage = null;
		if (navigate.equals("next"))
			movieManage = nucleusDao.getData(id + 1);
		else {
			movieManage = nucleusDao.getData(id - 1);
		}
		if (!StringUtils.isEmpty(movieManage)) {
			imageRender(movieManage);
		} else {
			if (navigate.equals("next"))
				movieManage = nucleusDao.getData(1);
			else
				movieManage = nucleusDao.getLastRow();
			if (!StringUtils.isEmpty(movieManage)) {
				imageRender(movieManage);
			} else
				movieManage = new MovieManage();
		}
		return movieManage;
	}

	private void imageRender(MovieManage movieManage) {

		try {
			File file = new File("src\\main\\webapp\\img\\Nucleus");
			file.mkdir();
			File image = new File("src\\main\\webapp\\img\\Nucleus\\" + movieManage.getMovieImageName());
			if (!image.exists()) {
				FileOutputStream fos = new FileOutputStream(image);
				byte[] buffer = new byte[1];
				InputStream is = movieManage.getBlobImage().getBinaryStream();
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
