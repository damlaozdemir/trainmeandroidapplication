<?php
	require_once 'config.php';

	class DB_Connection{
		private $connect;
		function __construct(){
			$this->connect = mysqli_connect("localhost","root"," ","trainme") or die ("DB connection error");
		}

		public function get_connection(){
			return $this->connect;
		}
	}
?>
