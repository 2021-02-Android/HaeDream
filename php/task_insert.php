<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');

	if (!$con) {
		die("연결에 실패 하였습니다.".mysqli_connect_error());
	}

	$category = $_POST['category'];
	$details = $_POST['details'];
	$info = $_POST['info'];
	$location = $_POST['location'];
	$point = $_POST['point'];
	$period = $_POST['period'];
	$userid = $_POST['userid'];
	$accepted = $_POST['accepted'];

	// 안드로이드에서 입력받은 값을 테이블에 추가
	$q = "INSERT INTO task (category, details, info, location, point, period, userid, accepted) values ('$category', '$details', '$info', '$location', '$point', '$period', '$userid', '$accepted')";
	mysqli_query($con, $q);

	// 실행을 위해 SQL문 준비
	$statement = mysqli_prepare($con, "SELECT * FROM task");
	mysqli_stmt_bind_param($statement, "ssssssss", $category, $details, $info, $location, $point, $period, $userid, $accepted);

	// 준비된 명령문을 실행
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	// 결과 저장을 위해 준비된 명령문에 변수를 바인드
	mysqli_stmt_bind_result($statement, $category, $details, $info, $location, $point, $period, $userid, $accepted);

	$response = array();
	$response["success"] = false;

	// 준비된 명령문의 결과를 바인딩 된 변수로 가져오기
	while(mysqli_stmt_fetch($statement)) {
		$response["success"] = true;
		$response["category"] = $category;
		$response["details"] = $details;
		$response["info"] = $info;
		$response["location"] = $location;
		$response["point"] = $point;
		$response["period"] = $period;
		$response["userid"] = $userid;
		$response["accepted"] = $accepted;
	}

	echo json_encode($response);
	mysqli_close($con);
?>