<?php
require_once('config.php');
$AdminId = isset($_REQUEST['uid'])?$_REQUEST['uid']:'';

if(!empty($AdminId)){
    $Query = mysqli_query($con,"select id , name from admin  where id = '$AdminId' ");
	
    $Result = array();
    while($Res = mysqli_fetch_assoc($Query)){
            $Result[] = $Res;
    }
    if(count($Result)){
        $Query = mysqli_query($con,"select id , name , emp_id ,email ,phone , created_at from employee order by id desc ");
	
        $Result = array();
        while($Res = mysqli_fetch_assoc($Query)){
                $Result[] = $Res;
        }
        $Response['employee_list'] = $Result;
        $Response['msg'] = 'success';
        echo json_encode($Response);
        exit;
    }else{
        $Response['msg'] = 'User is not authorize.';
        echo json_encode($Response);
        exit;
    }

}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
