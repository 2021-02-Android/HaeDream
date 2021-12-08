<?php
    $con = mysqli_connect("localhost", "idox23", "minjeong23", "idox23");
    mysqli_query($con,'SET NAMES utf8');

    include "./password.php";

    $userID = $_POST["userID"];
    $userPW = $_POST["userPW"];
    $hashedPassword = password_hash($userPW, PASSWORD_DEFAULT);
   // echo $hashedPassword;

    $result = mysqli_query($con, "SELECT * FROM user WHERE userID = '$userID'");
    $array = mysqli_fetch_array($result);
    $db_pw = $array['userPW'];

 //   echo " / ";
  //  echo $db_pw;

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE userID = ?");
    mysqli_stmt_bind_param($statement, "s", $userID);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $dept, $tel, $birth);

    $response = array();
    $response["success"] = false;


    if (password_verify($userPW, $db_pw)) {
        // Success!
        while(mysqli_stmt_fetch($statement)) {
            $response["success"] = true;
            $response["userID"] = $userID;
            $response["name"] = $name;
            $response["dept"] = $dept;
            $response["tel"] = $tel;
            $response["birth"] = $birth;
        }
    }
    echo json_encode($response);
?>