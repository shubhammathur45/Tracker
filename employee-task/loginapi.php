<?php
require_once('config.php');

$Email 		= 	isset($_REQUEST['email'])?$_REQUEST['email']:'';
$Password 	= 	isset($_REQUEST['password'])?$_REQUEST['password']:'';

if(!empty($Email)  && !empty($Password)){
	$Pass = md5($Password);
	$Query = mysqli_query($con,"Select id , name , email from admin where email = '$Email' and password = '$Pass' ");
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	if(count($Result)){
		
		$Id = $Result[0]['id'];
		$Response['uid'] = $Id;
		$Response['name'] = $Result[0]['name'];
		$Response['email'] = $Result[0]['email'];;
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