package com.nucleus.dao;

import com.nucleus.data.MovieManage;

public interface NucleusDao {

	public String insertData(MovieManage movieManage, boolean choice);

	public MovieManage getData(int id);

	public MovieManage getLastRow();

}
