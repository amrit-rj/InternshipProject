<?php 
	@$mysqli = new mysqli('localhost','root','amritraj','internship_project');	
	if (mysqli_connect_errno()) {
		printf("Error: %s\n", mysqli_connect_error());
		exit();
	}
?>