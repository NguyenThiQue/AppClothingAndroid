<?php
	include "connect.php";
	$page = $_GET['page'];
	$idsp = $_POST['idsanpham'];
	$space = 5;
	$limit = ($page -1) * $page;
	$mangsanpham = array();
	$query = "SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $limit, $space";
	$data = mysqli_query($conn, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangsanpham, new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['pricesale'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['star'],
			$row['heart'],
			$row['idsanpham']
		));
	}
	echo json_encode($mangsanpham);
	class Sanpham
	{
		
		function __construct($id, $tenSanPham, $giaSP, $pricesale, $hinhAnhSP, $moTaSP, $star, $heart, $idSP)
		{
			$this->id = $id;
			$this->tensanpham = $tenSanPham;
			$this->giasanpham = $giaSP;
			$this->pricesale = $pricesale;
			$this->hinhanhsanpham = $hinhAnhSP;
			$this->moTasanpham = $moTaSP;
			$this->star = $star;
			$this->heart = $heart;
			$this->idsanpham = $idSP;
		}
	}
?>