package com.nucleus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nucleus.dao.NucleusDao;
import com.nucleus.data.Login;
import com.nucleus.data.MovieManage;
import com.nucleus.service.ServiceImpl;

@RestController
public class MyController {

	@Autowired
	private Login login;
	@Autowired
	private NucleusDao nucleusDao;
	@Autowired
	private ServiceImpl serviceImpl;

	@RequestMapping(value = "/")
	public ModelAndView hello() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("username") String user, @RequestParam("password") String pswd) {

		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(pswd)) {
			boolean response = login.match(user, pswd);
			if (response) {
				return new ModelAndView("loginSuccess");
			}
		}
		return new ModelAndView("errorPage");
	}

	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(HttpServletRequest request, @RequestParam("movie_name") String movieName,
			@RequestParam("movie_actor") String movieActor, @RequestParam("movie_description") String movieDescription,
			@RequestParam("file") CommonsMultipartFile file) {

		if (file != null) {
			System.out.println("Saving file: " + file.getOriginalFilename());
		}
		String response = null;
		if (!file.isEmpty()) {
			MovieManage movieManage = new MovieManage(0, movieName, movieActor, movieDescription, file,
					file.getOriginalFilename());
			response = nucleusDao.insertData(movieManage, false);
		}
		return new ModelAndView("loginSuccess", "success", response);
	}

	@RequestMapping(value = "/getdata/{id}/{navigate}", method = RequestMethod.GET)
	public ModelAndView getData(@PathVariable int id, @PathVariable String navigate) {

		MovieManage movieManage = null;
		movieManage = serviceImpl.getMovieManage(id, navigate);
		return new ModelAndView("nextSlide", "movieManage", movieManage);
	}
}
