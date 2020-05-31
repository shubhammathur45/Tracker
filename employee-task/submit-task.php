<?php
require_once('config.php');

$TaskId = isset($_REQUEST['task_id'])?$_REQUEST['task_id']:'';
$EmpId 	= isset($_REQUEST['empid'])?$_REQUEST['empid']:'';

if(!empty($TaskId) && !empty($EmpId)){
    $Query = mysqli_query($con,"select * from employee  where id = '$EmpId' ");
	
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	
	if(count($Result)){
            
            mysqli_query($con, "Update  employee_task set `status` = '1' where  emp_id	='$EmpId' and id ='$TaskId'");
            
            $Response['msg'] = 'success';
            echo json_encode($Response);
            exit;
            
        }else{
            $Response['msg'] = 'Employee is not valid.';
            echo json_encode($Response);
            exit;
        }
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
