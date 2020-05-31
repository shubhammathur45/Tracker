<?php
require_once('config.php');

$Uid 		= 	isset($_REQUEST['uid'])?$_REQUEST['uid']:'';
$Name 		= 	isset($_REQUEST['name'])?$_REQUEST['name']:'';
$Email 		= 	isset($_REQUEST['email'])?$_REQUEST['email']:'';
$Phone 		= 	isset($_REQUEST['phone'])?$_REQUEST['phone']:'';


if(!empty($Uid) && !empty($Name) && !empty($Email)  && !empty($Phone)){
	
	$Query = mysqli_query($con,"select * from employee  where id = '$Uid' ");
	
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	
	if(count($Result)){
            
            mysqli_query($con, "update employee set `name` = '$Name' , `email` = '$Email', phone='$Phone'  where id =".$Result[0]['id']);
            
            $Response['msg'] = 'success';
            echo json_encode($Response);
            exit;
            
        }else{
            $Response['msg'] = 'User is not valid.';
            echo json_encode($Response);
            exit;
        }
}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}