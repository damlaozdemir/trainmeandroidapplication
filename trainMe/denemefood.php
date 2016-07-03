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

    public function getFoodDetails($id){
    $query = "Select * from foods where id = '$id' ";
    $r = mysqli_query($this-> connection, $query);
    if(mysqli_num_rows($r) > 0){
        $res = mysqli_fetch_array($r);
        $json['foodname'] = $res['foodname'];

        $result = array();
        array_push($result,array(
        "calories"=>$res['calories'],
        "protein"=>$res['protein'],
        "fat"=>$res['fat'],
        "carbohydrate"=>$res['carbohydrate'],
        ));

        echo json_encode(array("result"=>$result));
        mysqli_close($this->connection);
    }else{
        $json['error'] = 'No food found';
    }
    echo json_encode($json);
    mysqli_close($this->connection);

}
}

$food = new Foods();
if(isset($_POST['id'])){
$id = $_POST['id'];
if(!empty($id)){
    $food -> getFoodDetails($id);
}else{
    echo json_encode("You must fill the food name");
}
}
