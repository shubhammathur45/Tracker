<?php
require_once('config.php');
$TaskId = isset($_REQUEST['task_id'])?$_REQUEST['task_id']:'';

if(!empty($TaskId)){
    $Query = mysqli_query($con,"delete from employee_task  where id = '$TaskId' ");
	$Response['msg'] = 'success';
	echo json_encode($Response);
	exit;
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
