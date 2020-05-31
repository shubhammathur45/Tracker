<?php
require_once('config.php');


$Email 		= 	isset($_REQUEST['email'])?$_REQUEST['email']:'';

if(!empty($Email)){
	
	$Query = mysqli_query($con,"select id , name from admin  where email = '$Email' ");
	
	$Result = array();
	while($Res = mysqli_fetch_assoc($Query)){
		$Result[] = $Res;
	}
	
	if(count($Result)){
            $Token = rand(11111,99999);
            mysqli_query($con, "Insert into resetpasswordtoken set token = '$Token' , type ='admin' , created_at = now() ,user_id =".$Result[0]['id']);

            $Header = 'Content-type:text/html';
            $To = $Email;
            $Subject = 'Reset Password ';	
            $Message = 'Dear '.$Result[0]['name'];	
            $Message .= 'As per your request to change password your reset password token is '.$Token.'. Please use this token to reset your password.<br/>Thanks';
            //mail($To , $Subject , $Message, $Header);		
            date_default_timezone_set('Asia/Kolkata');

            require './PHPMailer/PHPMailerAutoload.php';

            //Create a new PHPMailer instance
            $mail = new PHPMailer;

            //Tell PHPMailer to use SMTP
            $mail->isSMTP();

            //Enable SMTP debugging
            // 0 = off (for production use)
            // 1 = client messages
            // 2 = client and server messages
            $mail->SMTPDebug = 0;

            //Ask for HTML-friendly debug output
            $mail->Debugoutput = 'html';

            //Set the hostname of the mail server
            $mail->Host = 'smtp.gmail.com';
            // use
            // $mail->Host = gethostbyname('smtp.gmail.com');
            // if your network does not support SMTP over IPv6

            //Set the SMTP port number - 587 for authenticated TLS, a.k.a. RFC4409 SMTP submission
            $mail->Port = 587;

            //Set the encryption system to use - ssl (deprecated) or tls
            $mail->SMTPSecure = 'tls';

            //Whether to use SMTP authentication
            $mail->SMTPAuth = true;

            //Username to use for SMTP authentication - use full email address for gmail
            $mail->Username = "Your gmail id";

            //Password to use for SMTP authentication
            $mail->Password = "Your email password";

            //Set who the message is to be sent from
            $mail->setFrom('', '');


            //Set who the message is to be sent to
            $mail->addAddress($Email , '');

            //Set the subject line
            $mail->Subject = $Subject;

            //Read an HTML message body from an external file, convert referenced images to embedded,
            //convert HTML into a basic plain-text alternative body
            $mail->msgHTML($Message, dirname(__FILE__));

            //Replace the plain text body with one created manually
            $mail->AltBody = 'This is a plain-text message body';

            //Attach an image file
            //$mail->addAttachment('images/phpmailer_mini.png');

            //send the message, check for errors
            if (!$mail->send()) {
                $Response['msg'] = 'There is an error while sending email from server.';
            } else {
                $Response['msg'] = 'success';
            }

            echo json_encode($Response);
        }else{
            $Response['msg'] = 'Email is not registered.';
            echo json_encode($Response);
            exit;
        }
	
	

}else{
	$Response['msg'] = 'Parameter missing';
	echo json_encode($Response);
	exit;
}
