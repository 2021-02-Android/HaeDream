<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');

	include "./password.php";

	$hashedPassword = password_hash($_POST['userPW'], PASSWORD_DEFAULT);
	//echo $hashedPassword;

	if (!$con) {
		die("회원가입 DB 연결 실패".mysqli_connect_error());
	}

	$userID = $_POST['userID'];
	$name = $_POST['name'];
	$dept = $_POST['dept'];
	$tel = $_POST['tel'];
	$birth = $_POST['birth'];
	$profile = $_POST['profile'];

	$join_sql = "SELECT userID FROM user WHERE userID = '$userID'";
	$join_result = mysqli_query($con, $join_sql);
	while($join_row = mysqli_fetch_array($join_result)){
		$userID_e = $join_row['userID'];
	}
	if($userID == $userID_e) { // ID 중복 검사
		$response["success"] = false;
	}
	else { // 안드로이드에서 입력받은 값을 테이블에 추가
		$sql = "INSERT INTO user (userID, userPW, name, dept, tel, birth) values ('$userID', '$hashedPassword', '$name', '$dept', '$tel', '$birth')";
		mysqli_query($con, $sql);
		$sql2 = "INSERT INTO info (userID, profile) values ('$userID', '$profile')";
		mysqli_query($con, $sql2);

		$response["success"] = true;
	}

	// $response = array();

	echo json_encode($response);
	mysqli_close($con);
?>