$inputfilename = 'foodhtml';
open($fh, '<', $inputfilename);

$outputfilename = 'foods.txt';
open($fh2, '>', $outputfilename);


while ($row = <$fh>){
  chomp $row;
  if($row =~ /<td width="69%">(.*)<\/td>/ ){
    print $fh2 "$1--";
  }
  if($row =~ /<td width="0%">(.*)<\/td>/ ){
    print $fh2 "$1--";
  }
  if($row =~ /<td width="8%">(.*)<\/td>/ ){
    print $fh2 "$1--";
  }
  if($row =~ /<td width="4%">(.*)<\/td>/ ){
    print $fh2 "$1--";
  }
  if($row =~ /<td width="19%">(.*)<\/td>/ ){
    print $fh2 "$1 \n ";
  }

}
