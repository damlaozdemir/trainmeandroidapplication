<?php
require_once 'connection.php';
header('Content-Type: application/json');


class ExerciseInfo{
    private $db;
    private $connection;

    function __construct(){
        $this->db = new DB_Connection();
        $this->connection = $this->db->get_connection();

    }

    function addExDesc(){
        $filename = 'empdata.json';
        $json = file_get_contents($filename);

        //convert json object to php associative array
        $data = json_decode($json, true);
        echo var_dump($data);
        // loop through the array
        foreach ($data as $row) {
            // get the employee details
            $name = $row['name'];
            $desc = $row['desc'];


            $query = "Insert into exercisedesc(name, description) value ('$row['name']', '$row['desc']')";
            $is_inserted = mysqli_query($this->connection,$query);
            if($is_inserted ==1){
                echo "Description added.";

            }else{
                echo "Error.";
            }

        }
        mysqli_close($this->connection);



    }
}

    $addExDesc = new ExerciseInfo();
    $addExDesc -> addExDesc();
?>
