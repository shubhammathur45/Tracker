<?php
require_once('config.php');

$Token 		= 	isset($_REQUEST['token'])?$_REQUEST['token']:'';
$Pass 		= 	isset($_REQUEST['password'])?md5($_REQUEST['password']):'';

if(!empty($Token) && !empty($Pass)){
	
	$Query = mysqli_query($con,"select user_id ,type from resetpasswordtoken  where token = '$Token' ");
	
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	
	if(count($Result)){
            if($Result[0]['type'] == 'admin'){
                $Table = 'admin';
            }else{
                $Table = 'employee';
            }
            
            mysqli_query($con, "update $Table set password = '$Pass' where id =".$Result[0]['user_id']);
            
            mysqli_query($con , "Delete from resetpasswordtoken where type = '".$Result[0]['type']."' and user_id = '".$Result[0]['user_id']."'");
            $Response['msg'] = 'success';
            echo json_encode($Response);
            exit;
            
        }else{
            $Response['msg'] = 'Token is not valid.';
            echo json_encode($Response);
            exit;
        }
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}