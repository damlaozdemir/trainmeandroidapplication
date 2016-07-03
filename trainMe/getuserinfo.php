<?php
	require_once 'connection.php';
	header('Content-Type: application/json');

	class UserInfo{
		private $db;
		private $connection;

		function __construct(){
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

		public function finduser($email){
			$query = "Select name, surname, email, age, aim from userinfo where `email` = '$email'";
			$r = mysqli_query($this-> connection, $query);
			if(mysqli_num_rows($r) == 1){
				$result = array();
                for($i=0; $i<mysqli_num_rows($r); $i++){
                    $res = mysqli_fetch_array($r);

                    array_push($result,array(
                    "name"=>$res['name'],
                    "surname"=>$res['surname'],
                    "email"=>$res['email'],
                    "age"=>$res['age'],
					"aim"=>$res['aim']
                    ));
                }

               	echo json_encode(array("userinfo"=>$result));
			}else{
				$json['error'] = 'Error';

			}
				echo json_encode($json);
				mysqli_close($this->connection);
		}
	}

	$userinfo = new UserInfo();
		if(isset($_POST['email'])){

			$email = $_POST['email'];
			$userinfo -> finduser($email);

		}

?>
