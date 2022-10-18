<?php
	include "connect.php";
	$query = "SELECT * FROM loaisanpham";
	$data = mysqli_query($conn, $query);
	$mangloaisanpham = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangloaisanpham, new LoaiSP(
			$row['id'],
			$row['tensanpham'],
			$row['hinhanhloaisanpham']));
	}
	echo json_encode($mangloaisanpham);
	class LoaiSP{	
		function __construct($id,$tenloaisanpham,$hinhanhloaisanpham){
			$this->id = $id;
			$this->tenloaisanpham = $tenloaisanpham;
			$this->hinhanhloaisanpham = $hinhanhloaisanpham;
		}
	}
?>
