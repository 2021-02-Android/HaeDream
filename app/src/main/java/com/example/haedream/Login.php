<?php
    $con = mysqli_connect("183.111.174.62", "idox23", "minjeong23", "idox23");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPW = $_POST["userPW"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE userID = ? AND userPW = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $userPW);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPW, $name, $dept, $tel, $birth);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["userID"] = $userID;
        $response["userPW"] = $userPW;
        $response["name"] = $name;
        $response["dept"] = $dept;
        $response["tel"] = $tel;
        $response["birth"] = $birth;
    }

    echo json_encode($response);
?>