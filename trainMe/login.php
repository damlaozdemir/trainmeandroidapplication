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

		public function login($email, $password){
			$query = "Select * from userinfo where TRIM(email) = '$email' and TRIM(password) = '$password' ";
			$result = mysqli_query($this-> connection, $query);
			if(mysqli_num_rows($result) == 1){
				$json['success'] = 'Welcome ' .$email;
			}else{
				$json['error'] = 'No accounts found';
			}

			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$user = new User();
		if(isset($_POST['email'], $_POST['password'])){
			$email = $_POST['email'];
			$password = $_POST['password'];

			if(!empty($email) && !empty($password)){
				$encrypted_password = md5($password);
				$user -> login($email,$encrypted_password);

			}else{
				echo json_encode("You must fill both fields");

			}
		}

?>
