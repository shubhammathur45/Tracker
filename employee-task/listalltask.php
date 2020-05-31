<?php
require_once('config.php');
$AdminId = isset($_REQUEST['uid'])?$_REQUEST['uid']:'';

if(!empty($AdminId)){
    $Query = mysqli_query($con,"select t1.id , t2.name as emp_name , t2.email as emp_email , t2.emp_id, t1.task , t1.task_location , t1.status , t1.last_date from employee_task t1 inner join employee t2 on t1.emp_id = t2.id order by t1.id desc");
	
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
