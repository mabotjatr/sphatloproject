<?php
	$con=mysqli_connect("localhost","my_user","my_password","my_db");
	
	$name = $_POST["name"];
	$price = $_POST["price"];
	
	$statement = mysqli_prepare($con,"SELECT * FROM item");
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_result($statement);
	mysqli_stmt_bind_result($statement, $id, $name, $price);
	
	$item = array();
	
	while(mysqli_smt_fecth($statement))
	{
		$item[id] = $id;
		$item[name] = $name;
		$item[price] = $price;
	}
	
	echo json_encode($item);
	
	mysqli_stmt_close($statement);
	
	mysqli_close($con)
	
	

?>