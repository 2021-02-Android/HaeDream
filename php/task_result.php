<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');
	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	// 실행을 위해 SQL문 준비
	// task_test 테이블 레코드 모두 출력, UTF-8 인코딩 안됨
	$statement = mysqli_prepare($con, "SELECT u.name, t.category, t.details, t.info, t.location, t.point, t.period, t.userid, t.accepted FROM task t JOIN user u ON t.userid = u.userID");
	mysqli_stmt_bind_param($statement, "sssssssss", $name, $category, $details, $info, $location, $point, $period, $userid, $accepted);

	// 준비된 명령문을 실행
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	// 결과 저장을 위해 준비된 명령문에 변수를 바인드
	mysqli_stmt_bind_result($statement, $name, $category, $details, $info, $location, $point, $period, $userid, $accepted);

	$result_array = array();
	// 준비된 명령문의 결과를 바인딩 된 변수로 가져오기
	while(mysqli_stmt_fetch($statement)) {
		$row_array = array(
			"name" => $name,
			"category" => $category,
			"details" => $details,
			"info" => $info,
			"location" => $location,
			"point" => $point,
			"period" => $period,
			"userid" => $userid,
			"accepted" => $accepted
		);
	
		array_push($result_array, $row_array);	
	}

	$arr = array(
		"results" => $result_array
	);

	echo json_encode($arr);
	mysqli_close($con);
?>