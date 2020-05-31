<?php
require_once('config.php');
$EmpId = isset($_REQUEST['emp_id'])?$_REQUEST['emp_id']:'';

if(!empty($EmpId)){
    $Query = mysqli_query($con,"delete from employee  where id = '$EmpId' ");
	$Response['msg'] = 'success';
	echo json_encode($Response);
	exit;
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
