<?php

	if($_SERVER['REQUEST_METHOD']=='GET'){
		$id = $_GET['id'];
		$sql = "select * from images where id = '$id'";
		require_once('connection.php');

		$r = mysqli_query($con,$sql);

		$result = mysqli_fetch_array($r);

		header('content-type: image/jpeg');

		echo base64_decode($result['image']);

		mysqli_close($con);

	}else{
		echo "Error";
	}
