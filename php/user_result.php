<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');
	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	// 실행을 위해 SQL문 준비
	$statement = mysqli_prepare($con, "SELECT i.profile, u.userID, u.name, u.dept, u.tel, u.birth, i.intro FROM user u JOIN info i ON u.userID = i.userID");
	mysqli_stmt_bind_param($statement, "ssssss", $profile, $userID, $name, $dept, $tel, $birth, $intro);

	// 준비된 명령문을 실행
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	// 결과 저장을 위해 준비된 명령문에 변수를 바인드
	mysqli_stmt_bind_result($statement, $profile, $userID, $name, $dept, $tel, $birth, $intro);

	$result_array = array();
	// 준비된 명령문의 결과를 바인딩 된 변수로 가져오기
	while(mysqli_stmt_fetch($statement)) {
		$row_array = array(
			"profile" => $profile,
			"userid" => $userID,
			"name" => $name,
			"dept" => $dept,
			"tel" => $tel,
			"birth" => $birth,
			"intro" => $intro
		);
	
		array_push($result_array, $row_array);	
	}

	$arr = array(
		"results" => $result_array
	);

	echo json_encode($arr);
	mysqli_close($con);
?>