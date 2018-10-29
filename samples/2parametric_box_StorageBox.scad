$fs=0.25;$fa=6;
difference(){translate([47,26.5,8])cube([94,53,16],center=true);
union() {
translate([16,13.5,8.5])cube([30,25,15],center=true);
translate([16,39.5,8.5])cube([30,25,15],center=true);
translate([47,13.5,8.5])cube([30,25,15],center=true);
translate([47,39.5,8.5])cube([30,25,15],center=true);
translate([78,13.5,8.5])cube([30,25,15],center=true);
translate([78,39.5,8.5])cube([30,25,15],center=true);
}
}