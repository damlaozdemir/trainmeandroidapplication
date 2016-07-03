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

		public function addexercise($userid, $exerciseid,$programid){
			$query = "Select * from userinfo where trim(id) = '$userid'";
			$result = mysqli_query($this-> connection, $query);
			if(mysqli_num_rows($result) > 0){
				$query="Insert into addedexercises(userid,exerciseid,  programid) value ('$userid', '$exerciseid', '$programid')";
				$is_inserted = mysqli_query($this->connection,$query);
				if($is_inserted ==1){
					$json['success']= 'exercise added';

				}else{
					$json['error'] = 'Please try again';
				}
			}
				echo json_encode($json);
				mysqli_close($this->connection);
		}
	}

	$user = new User();
		if(isset($_POST['userid'], $_POST['exerciseid'],$_POST['programid'])){
			$userid = $_POST['userid'];
			$exerciseid = $_POST['exerciseid'];
			$programid = $_POST['programid'];


			if(!empty($userid) && !empty($exerciseid) && !empty($programid)){
				$user -> addexercise($userid, $exerciseid,$programid);

			}else{
				echo json_encode("No exercise selected");
			}
		}

?>
