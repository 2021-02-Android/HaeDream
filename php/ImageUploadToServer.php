<?
	$data = $_POST["data1"];
	$file_path = "uploads/";
	echo $file_path;


	$file_path = $file_path . basename( $_FILES['uploaded_file']['name']);

	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
		echo $file_path;
		echo "file upload success";
	} else{
		echo "file upload fail";
	}


	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');
	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	$q = "UPDATE info SET profile = '$file_path' where userID = '$data'";

	mysqli_query($con, $q);
	mysqli_close($con);

?>