<?php
	require_once 'connection.php';
	header('Content-Type: application/json');

	class Exercises{
		private $db;
		private $connection;

		function __construct(){
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}

		public function getExercises($muscle){
			//$query = "SELECT exercises.name, exercises.muscle, exercises.level, exercises.rating, exercises.equipment, muscleimages.image FROM exercises, muscleimages WHERE TRIM( exercises.muscle ) = TRIM( muscleimages.muscle ) and TRIM( exercises.muscle ) = '$muscle'";
			$query = "SELECT exercises.name, exercises.muscle, exercises.level, exercises.rating, exercises.equipment, exercisedesc.description, images.img1 FROM exercises, exercisedesc, images WHERE TRIM( exercises.name ) = TRIM( exercisedesc.name ) and TRIM( exercises.muscle ) = '$muscle'";

			//$query = "Select * from exercises where trim(muscle) = '$muscle'";
			//$qu = "Select image from muscleimages where muscle = '$muscle' ";
			$r = mysqli_query($this-> connection, $query);
			if(mysqli_num_rows($r) > 0){
				$result = array();
                for($i=0; $i<mysqli_num_rows($r); $i++){
                    $res = mysqli_fetch_array($r);
					array_push($result,array(
                    "name"=>$res['name'],
                    "muscle"=>$res['muscle'],
                    "level"=>$res['level'],
                    "rating"=>$res['rating'],
					"equipment"=>$res['equipment'],
					"description" =>$res['description'],
					"image" =>$res['img1']
                    ));
                }
				echo json_encode(array("exercises"=>$result));
				//$json["exerciseinfo"] = array($result);
                    //$json["foodinfo"] = $result;
                //$json['calories'] = $res['calories'];
			}else{
				$json['error'] = 'Error';
			}
				echo json_encode($json);
				mysqli_close($this->connection);
		}
	}

	$exercises = new Exercises();
		if(isset($_POST['muscle'])){

			$muscle = $_POST['muscle'];
			$exercises -> getExercises($muscle);

		}

?>
