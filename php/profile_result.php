<?php
	$con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
	mysqli_query($con,'SET NAMES utf8');
	if (!$con) {
		die("���ῡ ���� �Ͽ����ϴ�.".mysqli_connect_error());
	}


	// ������ ���� SQL�� �غ�
	$statement = mysqli_prepare($con, "SELECT userID, profile FROM info");
	mysqli_stmt_bind_param($statement, "ss", $userID, $profile);

	// �غ�� ���ɹ��� ����
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);

	// ��� ������ ���� �غ�� ���ɹ��� ������ ���ε�
	mysqli_stmt_bind_result($statement, $userID, $profile);

	$result_array = array();
	// �غ�� ���ɹ��� ����� ���ε� �� ������ ��������
	while(mysqli_stmt_fetch($statement)) {
		$row_array = array(
			"userID" => $userID,
			"profile" => $profile
		);
		array_push($result_array, $row_array);	
	}

	$arr = array(
		"results" => $result_array
	);

	echo json_encode($arr);
	mysqli_close($con);
?>