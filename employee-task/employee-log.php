<?php
require_once('config.php');

$EmpId           = 	isset($_REQUEST['emp_id'])?$_REQUEST['emp_id']:'';

if(!empty($EmpId)){
	$Query = mysqli_query($con,"Select id from employee where id=$EmpId ");
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	if(count($Result)){
		$Id = $Result[0]['id'];
                $Query = mysqli_query($con,"Select * from employee_login_location where emp_id = $Id  order by id desc");
		$Response['logins'] = [];
                while($Res = mysqli_fetch_assoc($Query)){
                        $Response['logins'][] = $Res;
                }
                $Response['image_path'] = 'http://gvpstohana.in/app/employee-task/images/';
                
                $Query = mysqli_query($con,"Select * from track_employee_location where emp_id = $Id  order by id desc");
		$Response['trackings'] = [];
                while($Res = mysqli_fetch_assoc($Query)){
                        $Response['trackings'][] = $Res;
                }
                
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