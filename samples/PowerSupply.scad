$fs=0.25;$fa=6;
union() {
difference(){difference(){translate([57,22,34.5])cube([114,44,69],center=true);
translate([57,23,34.5])cube([110,42,65],center=true);
}union() {
translate([57,1,34.5])cube([45,2,26],center=true);
translate([57,1,10.75])cube([18,2,12],center=true);
translate([96.75,1.5,34.5])rotate([-90,0,0])cylinder(h=3, r=4, center=true);
translate([17.25,1.5,34.5])rotate([-90,0,0])cylinder(h=3, r=3.5, center=true);
translate([10,34,1])cylinder(h=2, r=5, center=true);
translate([104,34,68])cylinder(h=2, r=5, center=true);
}
}cube([52,27,13],center=true);
}
