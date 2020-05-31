<?php
require_once('config.php');

$Userid 		= 	isset($_REQUEST['uid'])?$_REQUEST['uid']:'';

if(!empty($Userid)){
	
	mysqli_query($con,"Update user set login_status ='0' where id = '$Id' ");
	
	$Response['msg'] = 'success';
	echo json_encode($Response);
	exit;

}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}