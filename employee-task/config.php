<?php
date_default_timezone_set('Asia/Kolkata');
$con=mysqli_connect('localhost','gvpst4zz_semptsk','11[WdTF6cr^r','gvpst4zz_sampleemptask');

function getaddress($lat,$lng)
{
   $url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='.trim($lat).','.trim($lng).'&sensor=false';
   $json = @file_get_contents($url);
   $data=json_decode($json);
   $status = $data->status;
   if($status=="OK")
   {
     return $data->results[0]->formatted_address;
   }
   else
   {
     return false;
   }
}


