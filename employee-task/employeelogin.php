<?php
require_once('config.php');

$Email 		= 	isset($_REQUEST['email'])?$_REQUEST['email']:'';
$Password 	= 	isset($_REQUEST['password'])?$_REQUEST['password']:'';
$Lat            = 	isset($_REQUEST['lat'])?$_REQUEST['lat']:'';
$Long           = 	isset($_REQUEST['long'])?$_REQUEST['long']:'';
$Image           = 	isset($_REQUEST['image'])?$_REQUEST['image']:'';

if(!empty($Email)  && !empty($Password) && !empty($Lat) && !empty($Long)){
	$Pass = md5($Password);
	$Query = mysqli_query($con,"Select id from employee where email = '$Email' and password = '$Pass' ");
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	if(count($Result)){
		$Location = getaddress($Lat , $Long);
		$Id = $Result[0]['id'];
                $Image = base64_decode($Image);
                $ImageName = 'login_'.time().'.jpg';
                $image = file_put_contents('./images/'.$ImageName, $Image);
                $Query = mysqli_query($con," Insert into employee_login_location set emp_id = $Id , image = '$ImageName' , location = '$Location' , login_time = now()");
		$Response['uid'] = $Id;
		$Response['msg'] = 'success';
		echo json_encode($Response);
		exit;	
	}else{
		$Response['msg'] = 'Email or password is incorrect.';
		echo json_encode($Response);
		exit;
	}
	
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}