$fs=0.25;$fa=6;
difference(){union() {
translate([45.1,45.75,0.5])cube([90.2,91.5,1],center=true);
union() {
translate([-0.5,45.75,0.5])cube([1,10,1],center=true);
translate([90.7,45.75,0.5])cube([1,10,1],center=true);
}
}
translate([45.1,71.5,0.5])cube([25,7,1],center=true);
}