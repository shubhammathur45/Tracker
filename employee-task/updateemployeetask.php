<?php
require_once('config.php');

$TaskId = isset($_REQUEST['task_id'])?$_REQUEST['task_id']:'';
$EmpId 		= 	isset($_REQUEST['empid'])?$_REQUEST['empid']:'';
$Task 		= 	isset($_REQUEST['task'])?$_REQUEST['task']:'';
$Location 	= 	isset($_REQUEST['location'])?$_REQUEST['location']:'';
$Lastdate	= 	isset($_REQUEST['last_date'])?$_REQUEST['last_date']:'';

if(!empty($TaskId) && !empty($EmpId) && !empty($Task) && !empty($Location) && !empty($Lastdate)){
	
	$Query = mysqli_query($con,"select * from employee  where id = '$EmpId' ");
	
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	
	if(count($Result)){
            
            mysqli_query($con, "Update  employee_task set `task` = '$Task' , `task_location` = '$Location', emp_id	='$EmpId', last_date = '$Lastdate' where id =$TaskId");
            
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