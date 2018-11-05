$fs=0.25;$fa=6;
difference(){difference(){difference(){translate([51.1,51.75,16])cube([102.2,103.5,32],center=true);
translate([51.1,51.75,16.5])cube([100.2,101.5,31],center=true);
}union() {
translate([0.5,51.75,31.5])cube([1,10.6,1],center=true);
translate([101.7,51.75,31.5])cube([1,10.6,1],center=true);
}
}union() {
union() {
translate([0.5,33.7,4.7])cube([1,8,4],center=true);
translate([0.5,47.5,8.2])cube([1,9,11],center=true);
}
union() {
translate([12,16,0.5])cylinder(h=1, r=2.6, center=true);
translate([12,92,0.5])cylinder(h=1, r=2.6, center=true);
translate([90,92,0.5])cylinder(h=1, r=2.6, center=true);
translate([90,16,0.5])cylinder(h=1, r=2.6, center=true);
}
}
}