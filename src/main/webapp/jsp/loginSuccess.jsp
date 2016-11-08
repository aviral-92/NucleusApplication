<html>
<head>
<title>Upload Multiple File Request Page</title>
<script type="text/javascript">
	function validate() {
		if (document.getElementById("movieName").value == "") {
			alert("Movie name can not be blank");
		} else if (document.getElementById("movieActor").value == "") {
			alert("Movie Actor can not be blank.");
		} else if (document.getElementById("MovieDescription").value == "") {
			alert("Movie Description can not be blank.");
		} else if (document.getElementById("imageUpload").value == "") {
			alert("Image Upload can not be blank.");
		}
	}
</script>
</head>
<body>
	<form method="POST" action="uploadMultipleFile"
		enctype="multipart/form-data">
		<label>Movie Name</label><input type="text" name="movie_name"
			id="movieName" placeholder="Movie Name" size="25" /><br> <label>Movie
			Actor</label><input type="text" name="movie_actor" id="movieActor"
			placeholder="Movie Actor" size="25" /><br> <label>Movie
			Description</label>
		<textarea rows="2" cols="10" name="movie_description"
			id="MovieDescription" placeholder="Movie Description"></textarea>
		<br> File1 to upload: <input type="file" name="file"
			id="imageUpload"> <br> <input type="submit"
			value="Upload" onclick="validate();"> Press here to upload
		the file! otherwise press back <input type="button" name="button"
			value="Back" onclick="location.href = 'http://localhost:6064/';">
	</form>
</body>
<%
	String resp = (String) request.getAttribute("success");
	if (resp != null) {
		out.print(resp);
	}
%>
</html>