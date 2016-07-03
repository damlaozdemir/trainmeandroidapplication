<?php
	require_once 'connection.php';
	header('Content-Type: application/json');

	class User{
		private $db;
		private $connection;

		function __construct(){
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

		public function register($name, $surname, $email, $age, $password , $aim){
			$query = "Select * from userinfo where email = '$email'";
			$result = mysqli_query($this-> connection, $query);
			if(mysqli_num_rows($result) == 0){
				$query=" Insert into userinfo(name, surname, email,age,password,aim ) value ('$name', '$surname', '$email','$age', '$password', '$aim')";
				$is_inserted = mysqli_query($this->connection,$query);
				if($is_inserted ==1){
					$json['success'] = 'Account created, welcome ' .$name;

				}else{
					$json['error'] = 'Please try again';
				}
			}
				echo json_encode($json);
				mysqli_close($this->connection);
		}
	}

	$user = new User();
		if(isset($_POST['name'], $_POST['surname'],$_POST['email'],$_POST['age'],$_POST['password'],$_POST['aim'])){
			$name = $_POST['name'];
			$surname = $_POST['surname'];
			$email = $_POST['email'];
			$age = $_POST['age'];
			$password = $_POST['password'];
			$aim = $_POST['aim'];

			if(!empty($name) && !empty($surname) && !empty($email) && !empty($age) && !empty($password) && !empty($aim)){
				$encrypted_password = md5($password);
				$user -> register($name, $surname,$email, $age,$encrypted_password, $aim);

			}else{
				echo json_encode("You must fill all fields");
			}
		}

?>
