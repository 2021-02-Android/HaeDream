<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');

	if (!$con) {
		die("회원가입 DB 연결 실패".mysqli_connect_error());
	}

	$userID = $_POST['userID'];

	$join_sql = "SELECT userID FROM user WHERE userID = '$userID'";
	$join_result = mysqli_query($con, $join_sql);
	while($join_row = mysqli_fetch_array($join_result)){
		$userID_e = $join_row['userID'];
	}
	if($userID == $userID_e) { // ID 중복 검사
		$response["success"] = false;
	}
	else { // 안드로이드에서 입력받은 값을 테이블에 추가
		$response["success"] = true;
	}

	// $response = array();

	echo json_encode($response);
	mysqli_close($con);
?>