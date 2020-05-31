<?php
require_once('config.php');
$Lat            = 	isset($_REQUEST['lat'])?$_REQUEST['lat']:'';
$Long           = 	isset($_REQUEST['long'])?$_REQUEST['long']:'';
$EmpId           = 	isset($_REQUEST['emp_id'])?$_REQUEST['emp_id']:'';

if(!empty($EmpId) && !empty($Lat) && !empty($Long)){
	$Query = mysqli_query($con,"Select id from employee where id=$EmpId ");
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	if(count($Result)){
		$Location = getaddress($Lat , $Long);
		$Id = $Result[0]['id'];
                $Query = mysqli_query($con,"Insert into track_employee_location set emp_id = $Id , location = '$Location' , latitude = '$Lat' , longitude = '$Long' , tracking_time = now()");
		$Response['msg'] = 'success';
		echo json_encode($Response);
		exit;	
	}else{
		$Response['msg'] = 'Employee not exist.';
		echo json_encode($Response);
		exit;
	}
	
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}