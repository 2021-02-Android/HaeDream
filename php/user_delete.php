<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');

	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	$userID = $_POST['userID'];
	// $userPW = $_POST['userPW'];

	// 탈퇴시 유저 정보 삭제
	$q1 = "DELETE FROM user WHERE userID = '$userID'";
	mysqli_query($con, $q1);

	// 무결성 유지 위해 info 테이블에서도 삭제해줘야 함.
	$q2 = "DELETE FROM info WHERE userID = '$userID'";
	mysqli_query($con, $q2);

	mysqli_close($con);
?>