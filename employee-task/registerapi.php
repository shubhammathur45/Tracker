<?php
require_once('config.php');

$Name 		= 	isset($_REQUEST['name'])?$_REQUEST['name']:'';
$Email 		= 	isset($_REQUEST['email'])?$_REQUEST['email']:'';
$EmpId 		= 	isset($_REQUEST['emp_id'])?$_REQUEST['emp_id']:'';
$Phone 		= 	isset($_REQUEST['phone'])?$_REQUEST['phone']:'';
$Password 	= 	isset($_REQUEST['password'])?$_REQUEST['password']:'';

if(!empty($Name) && !empty($Email) && !empty($EmpId) && !empty($Phone)  && !empty($Password)){
	if(filter_var($Email ,FILTER_VALIDATE_EMAIL )){
		$Query 	= 	mysqli_query($con,"Select count(id) as total from employee where email = '$Email' or emp_id = '$EmpId' or phone = '$Phone'");
		$Result = 	mysqli_fetch_assoc($Query);
		if($Result['total'] == '0'){
			$Pass =  md5($Password);
			$Date =  date('Y-m-d H:i:s');
			mysqli_query($con,"Insert into employee set name = '$Name' , emp_id = '$EmpId'  , password = '$Pass' , phone = '$Phone' , email = '$Email' ,  created_at='$Date'");
			$Response['msg'] = 'success';
			echo json_encode($Response);
			exit;
		}else{
			$Response['msg'] = 'Email or employee id or phone is already registered.';
			echo json_encode($Response);
			exit;
		}
		
	}else{
		$Response['msg'] = 'Invalid email.';
		echo json_encode($Response);
		exit;
	}
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}