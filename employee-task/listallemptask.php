<?php
require_once('config.php');
$EmpId = isset($_REQUEST['uid'])?$_REQUEST['uid']:'';

if(!empty($EmpId)){
    $Query = mysqli_query($con,"select id , task , task_location , status , last_date from employee_task where emp_id = '$EmpId' order by id desc");
	
    $Result = array();
    while($Res = mysqli_fetch_assoc($Query)){
            $Result[] = $Res;
    }
    
    $Response['task_list'] = $Result;
    $Response['msg'] = 'success';
    echo json_encode($Response);
    exit;


}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
