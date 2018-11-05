$fs=0.25;$fa=6;
difference(){union() {
translate([50.1,50.75,0.5])cube([100.2,101.5,1],center=true);
union() {
translate([-0.5,50.75,0.5])cube([1,10,1],center=true);
translate([100.7,50.75,0.5])cube([1,10,1],center=true);
}
}
translate([50.1,81.5,0.5])cube([25,7,1],center=true);
}