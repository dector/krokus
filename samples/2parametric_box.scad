$fs=0.25;$fa=6;
difference(){translate([47,26.5,8])cube([94,53,16],center=true);
union() {
translate([1,1,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
translate([1,27,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
translate([32,1,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
translate([32,27,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
translate([63,1,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
translate([63,27,1])translate([15,12.5,7.5])cube([30,25,15],center=true);
}
}