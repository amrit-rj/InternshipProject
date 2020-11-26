<html>
<head>
<title>Result</title>
<link rel = "icon" type = "image/png" href = "https://upload.wikimedia.org/wikipedia/en/thumb/b/bb/NIT-Delhi_Logo.svg/1200px-NIT-Delhi_Logo.svg.png">
</head>
<body>
<figure class="aligncenter size-large is-resized"><img src="https://nitdelhi.ac.in/wp-content/uploads/2018/12/NEW-NIT-DELHI-LOGO1.png" alt="NIT Delhi Logo" width="500" height="70.91" /></figure>
<?php
	require './connect.php';
	$name=$_POST["name"];
	$roll=$_POST["roll"];
	$c1=$_POST["c1"];
	$c2=$_POST["c2"];
	echo "<h3>Your Input:</h3>";
	echo '<pre>Name                      : '.$name;
	echo "<br>";
	echo 'Roll No.                  : '.$roll;
	echo "<br>";
	echo 'First Choice for Project  : '.$c1;
	echo "<br>";
	echo 'Second Choice for Project : '.$c2.'</pre>';
	echo "<br><br><h3>Result: </h3>";
	if($c1 === $c2)
		echo "First choice and Second choice can't be same.<br>Please go back and change your choices.<br>Thank You.<br>";
	else{
		if ($result = $mysqli->query("SELECT * FROM faculty_details WHERE C1='".$c1."' AND Cap<30 AND Cap=(SELECT MIN(Cap) FROM faculty_details WHERE C1='".$c1."')")) {
			$row = $result->fetch_assoc();
			$id=$row['ID'];
			if($res = $mysqli->query("INSERT INTO student_details VALUES ('".$name."', ".$roll.", '".$row['Name']."', '".$c1."');")){
				echo "Assigned Supervisor for your given choices is: ".$row['Name']."<br>Assigned Topic for Project: ".$c1;
				if($re = $mysqli->query("UPDATE faculty_details SET Cap=Cap+1 WHERE ID='".$id."'")){
					echo "<br>";
				}
			}
			else
				echo "<br>Duplicate Roll No Found.";
			$result->free();
		}
	
		else if($result = $mysqli->query("SELECT * FROM faculty_details WHERE C2='".$c1."' AND Cap<30 AND Cap=(SELECT MIN(Cap) FROM faculty_details WHERE C2='".$c1."')")){
			$row=$result->fetch_assoc();
			$id=$row['ID'];
			if($res = $mysqli->query("INSERT INTO student_details VALUES ('".$name."', ".$roll.", '".$row['Name']."', '".$c1."')")){
				echo "Assigned Supervisor for your given choices is: ".$row['Name']."<br>Assigned Topic for Project: ".$c1;
				if($re = $mysqli->query("UPDATE faculty_details SET Cap=Cap+1 WHERE ID='".$id."'")){
					echo "<br>";
				}
			}
			else
				echo "<br>Duplicate Roll No Found.";
			$result->free();
		}
		else if($result = $mysqli->query("SELECT * FROM faculty_details WHERE C1='".$c2."' AND Cap<30 AND Cap=(SELECT MIN(Cap) FROM faculty_details WHERE C1='".$c2."')")){
			$row=$result->fetch_assoc();
			$id=$row['ID'];
			if($res = $mysqli->query("INSERT INTO student_details VALUES ('".$name."', ".$roll.", '".$row['Name']."', '".$c1."')")){
				echo "Assigned Supervisor for your given choices is: ".$row['Name']."<br>Assigned Topic for Project: ".$c2;
				if($re = $mysqli->query("UPDATE faculty_details SET Cap=Cap+1 WHERE ID='".$id."'")){
					echo "<br>";
				}
			}
			else
				echo "<br>Duplicate Roll No Found.";
			$result->free();
		}
		else if($result = $mysqli->query("SELECT * FROM faculty_details WHERE C2='".$c2."' AND Cap<30 AND Cap=(SELECT MIN(Cap) FROM faculty_details WHERE C2='".$c2."')")){
			$row=$result->fetch_assoc();
			$id=$row['ID'];
			if($res = $mysqli->query("INSERT INTO student_details VALUES ('".$name."', ".$roll.", '".$row['Name']."', '".$c1."')")){
				echo "Assigned Supervisor for your given choices is: ".$row['Name']."<br>Assigned Topic for Project: ".$c2;
				if($re = $mysqli->query("UPDATE faculty_details SET Cap=Cap+1 WHERE ID='".$id."'")){
					echo "<br>";
				}
			}
			else
				echo "<br>Duplicate Roll No Found.";
			$result->free();
		}
		else
			echo "No Supervisors available for your given choices.<br>Thank You!!";
	}
?>
</body>
</html>