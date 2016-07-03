<?php
require_once 'connection.php';
header('Content-Type: application/json');
class Foods{
    private $db;
    private $connection;

    function __construct(){
        $this->db = new DB_Connection();
        $this->connection = $this->db->get_connection();
    }

    public function getFoodInfo(){
        $query = "Select * from foods";
        $r = mysqli_query($this-> connection, $query);
        if(mysqli_num_rows($r) > 0){
                $result = array();
                for($i=0; $i<mysqli_num_rows($r); $i++){
                    $res = mysqli_fetch_array($r);

                    array_push($result,array(
                    "foodname"=>$res['foodname'],
                    "calories"=>$res['calories'],
                    "protein"=>$res['protein'],
                    "fat"=>$res['fat'],
                    "carbohydrate"=>$res['carbohydrate'],
                    ));
                }
                //$json["foodsinfos"] = array($result);
                echo json_encode(array("foods"=>$result));
                    //$json["foodinfo"] = $result;
                //$json['calories'] = $res['calories'];
        }else{
            $json['error'] = 'No food found';
        }
        echo json_encode($json);
        mysqli_close($this->connection);
    }
}

    $food = new Foods();
    $food -> getFoodInfo();
