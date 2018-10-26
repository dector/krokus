$fs=0.25;$fa=6;
difference(){translate([39.5,51.5,15.5])cube([79,103,31],center=true);
union() {
translate([1,1,1])translate([12.5,25,15])cube([25,50,30],center=true);
translate([1,52,1])translate([12.5,25,15])cube([25,50,30],center=true);
translate([27,1,1])translate([12.5,25,15])cube([25,50,30],center=true);
translate([27,52,1])translate([12.5,25,15])cube([25,50,30],center=true);
translate([53,1,1])translate([12.5,25,15])cube([25,50,30],center=true);
translate([53,52,1])translate([12.5,25,15])cube([25,50,30],center=true);
}
}