<?php
require_once 'connection.php';








	class Images{
	    private $db;
	    private $connection;

	    function __construct(){
	        $this->db = new DB_Connection();
	        $this->connection = $this->db->get_connection();
	    }

	    public function uploadImage($image , $name){
			if($_SERVER['REQUEST_METHOD']=='POST'){

			$image = $_POST['image'];
	        $name = $_POST['name'];



			$sql ="SELECT id FROM userpicture ORDER BY id ASC";

			$res = mysqli_query($this->connection,$sql);

			$id = 0;

			while($row = mysqli_fetch_array($res)){
					$id = $row['id'];
			}

			$path = "uploads/$id.png";

			$actualpath = "http://www.trainme.net16.net/trainMe/$path";

			$sql = "INSERT INTO userpicture (path,name) VALUES ('$actualpath','$name')";

			if(mysqli_query($this->connection,$sql)){
				file_put_contents($path,base64_decode($image));
				echo "Successfully Uploaded";
			}

			mysqli_close($this->connection);
		}else{
			echo "Error";
		}
	    }
	}

	    $image = new Images();
	    $image -> uploadImage($name, $image);
