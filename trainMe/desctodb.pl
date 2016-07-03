use DBI;
use DBD::mysql;

use warnings;


=pod
$dbh = DBI->connect('dbi:mysql:trainme','root','')or die "Connection Error: $DBI::errstr\n";

$inputfilename = 'itemson.json';
open($fh, '<', $inputfilename);


while ($row = <$fh>){
		chomp $row;

		push my @myArray, split("--", $row);
		$query = "insert into foods (foodname, calories, protein, fat, carbohydrate)
			values (?, ?, ?, ?, ?) ";
		$statement = $dbh->prepare($query);
		$statement->execute($myArray[0], $myArray[1], $myArray[2], $myArray[3], $myArray[4]);
		$statement->finish();

}
=cut

use JSON::Parse 'itemson.json';
    my $perl = parse_json ('["name", "desc"]');
    print ref $perl, "\n";
