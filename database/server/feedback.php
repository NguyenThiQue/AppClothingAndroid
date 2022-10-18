<?php
	include "connect.php";
	$idspf = $_POST['idsp'];
	$query = "SELECT * FROM feedback WHERE idsanpham = $idspf";
	$data = mysqli_query($conn, $query);
	$mangFeedback = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangFeedback, new Feedback(
			$row['id'],
			$row['anhuser'],
			$row['tenuser'],
			$row['noidung'],
			$row['hinhanh'],
			$row['idsp']
		));
	}
	echo json_encode($mangFeedback);
	class Feedback{	
		function __construct($id,$anhuser,$tenuser, $noidung, $hinhanh, $idsp){
			$this->id = $id;
			$this->anhuser = $anhuser;
			$this->tenuser = $tenuser;
			$this->noidung = $noidung;
			$this->hinhanh = $hinhanh;
			$this->idsp = $idsp;
		}
	}
?>