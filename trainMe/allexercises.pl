
@muscles =("abdominals","abductors","adductors","biceps","calves","chest","forearms","glutes","hamstrings","lats","lower-back","middle-back","neck","quadriceps","shoulders","traps","triceps");
$outputfilename = 'exerciseinfos2.txt';
open($fh2, '>', $outputfilename);
foreach $muscle(@muscles){

	$url="http://www.bodybuilding.com/exercises/list/muscle/selected/$muscle";

	`curl $url >INPUT`;
	$inputfilename = 'INPUT';
	open($fh, '<', $inputfilename);


	while ($row = <$fh>){
		chomp $row;
		if($row =~ /'> (.*) <\/a><\/h3>/ ){

    	$exercises = join(" ", split("-", $1));
			$exercises2 = join("", split("/", $exercises));
			$exercises3 = join("", split("&", $exercises2));
			$exercises4 = join(" ", split("   ", $exercises3));
			$exercises5 = join("-", split(" ", $exercises4));

			$exerciseurl = "http://www.bodybuilding.com/exercises/detail/view/name/$exercises5";

			# print $fh2 "$exercises4, $muscle, ";


			`curl $exerciseurl >INPUT2`;
			$inputfilename2 = 'INPUT2';
			open($fh3, '<', $inputfilename2);
			$equipment = "-";
			while ($row2 = <$fh3>){
				#chomp $row2;

				#if($row2 =~ /filter\/level\/id\/\d+\/level\/(.*)">/){
				#	$level = $1;
				#	print $fh2 "$level,";
				#}
				#if($row2 =~ /equipment\/id\/\d+\/equipment\/(.*)\'>/){
				#		$equipment = $1;
				#}
				#if($row2 =~ /Information<\/span><span class='rating'>(.*)<\/span><span class='best' style='display:none/){
				#	$rating = $1;
				#	print $fh2 "$rating, ";
				#}
				if($row2 =~ m/^<li>(\s[x-y])<\/li>/g){
					$desc = $1;
					print $fh2 "$desc, ";
				} #elsif($row2 =~ /Information<\/span>(.*)<\/div><\/div> /){

					#$rating = 0;
					#print $fh2 "$rating, ";
				#}
			}
			#print $fh2 "$equipment \n"
		}
	}
}
