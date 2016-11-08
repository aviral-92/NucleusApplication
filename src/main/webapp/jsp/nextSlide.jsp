<%@ page import="com.nucleus.data.MovieManage"%>
<%@ page import="java.util.Map, java.io.*"%>
<div class="left" style="width: 49%;">
	<%
		MovieManage muvi = (MovieManage) request
				.getAttribute("movieManage");
		
	%>
	<img src=<%= "img/Nucleus/"+muvi.getMovieImageName()%> />
</div>
<div class="left" id="desc"
	style="width: 45%; text-align: justify; padding-left: 4%;">
	<b><u>Movie Name</u> :</b>
	<%=muvi.getMovieName()%>
	<br> <b><u>Movie Actor</u> :</b>
	<%=muvi.getMovieActor()%><br><b><u>Movie Description</u> :</b>
	<%=muvi.getMovieDesc()%>
</div>
<script>
index = '<%= muvi.getMovieId()%>';
</script>