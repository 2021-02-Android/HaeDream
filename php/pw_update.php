<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');

	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	$userID = $_POST['userID'];
	$hashedPassword = password_hash($_POST['userPW'], PASSWORD_DEFAULT);

	// 안드로이드에서 입력받은 값을 테이블에 추가
	$q = "UPDATE user SET userPW = '$hashedPassword' WHERE userID = '$userID'";
	mysqli_query($con, $q);

	mysqli_close($con);
?>